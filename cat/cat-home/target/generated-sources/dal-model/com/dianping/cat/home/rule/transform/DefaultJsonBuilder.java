package com.dianping.cat.home.rule.transform;

import static com.dianping.cat.home.rule.Constants.ATTR_ALERTTYPE;
import static com.dianping.cat.home.rule.Constants.ATTR_ENDTIME;
import static com.dianping.cat.home.rule.Constants.ATTR_ID;
import static com.dianping.cat.home.rule.Constants.ATTR_METRICITEMTEXT;
import static com.dianping.cat.home.rule.Constants.ATTR_MINUTE;
import static com.dianping.cat.home.rule.Constants.ATTR_MONITORAVG;
import static com.dianping.cat.home.rule.Constants.ATTR_MONITORCOUNT;
import static com.dianping.cat.home.rule.Constants.ATTR_MONITORSUM;
import static com.dianping.cat.home.rule.Constants.ATTR_PRODUCTTEXT;
import static com.dianping.cat.home.rule.Constants.ATTR_STARTTIME;
import static com.dianping.cat.home.rule.Constants.ATTR_TEXT;
import static com.dianping.cat.home.rule.Constants.ATTR_TITLE;
import static com.dianping.cat.home.rule.Constants.ATTR_TYPE;
import static com.dianping.cat.home.rule.Constants.ENTITY_CONDITIONS;
import static com.dianping.cat.home.rule.Constants.ENTITY_CONFIGS;
import static com.dianping.cat.home.rule.Constants.ENTITY_METRIC_ITEMS;
import static com.dianping.cat.home.rule.Constants.ENTITY_RULES;
import static com.dianping.cat.home.rule.Constants.ENTITY_SUB_CONDITIONS;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dianping.cat.home.rule.IEntity;
import com.dianping.cat.home.rule.IVisitor;
import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.Config;
import com.dianping.cat.home.rule.entity.MetricItem;
import com.dianping.cat.home.rule.entity.MonitorRules;
import com.dianping.cat.home.rule.entity.Rule;
import com.dianping.cat.home.rule.entity.SubCondition;

public class DefaultJsonBuilder implements IVisitor {

   private IVisitor m_visitor;

   private int m_level;

   private StringBuilder m_sb;

   private boolean m_compact;

   public DefaultJsonBuilder() {
      this(false);
   }

   public DefaultJsonBuilder(boolean compact) {
      this(compact, new StringBuilder(4096));
   }

   public DefaultJsonBuilder(boolean compact, StringBuilder sb) {
      m_compact = compact;
      m_sb = sb;
      m_visitor = this;
   }

   protected void arrayBegin(String name) {
      indent();
      m_sb.append('"').append(name).append(m_compact ? "\":[" : "\": [\r\n");
      m_level++;
   }

   protected void arrayEnd(String name) {
      m_level--;

      trimComma();
      indent();
      m_sb.append("],").append(m_compact ? "" : "\r\n");
   }

   protected void attributes(Map<String, String> dynamicAttributes, Object... nameValues) {
      int len = nameValues.length;

      for (int i = 0; i + 1 < len; i += 2) {
         Object attrName = nameValues[i];
         Object attrValue = nameValues[i + 1];

         if (attrValue != null) {
            if (attrValue instanceof List) {
               @SuppressWarnings("unchecked")
               List<Object> list = (List<Object>) attrValue;

               if (!list.isEmpty()) {
                  indent();
                  m_sb.append('"').append(attrName).append(m_compact ? "\":[" : "\": [");

                  for (Object item : list) {
                     m_sb.append(' ');
                     toString(m_sb, item);
                     m_sb.append(',');
                  }

                  m_sb.setLength(m_sb.length() - 1);
                  m_sb.append(m_compact ? "]," : " ],\r\n");
               }
            } else {
               if (m_compact) {
                  m_sb.append('"').append(attrName).append("\":");
                  toString(m_sb, attrValue);
                  m_sb.append(",");
               } else {
                  indent();
                  m_sb.append('"').append(attrName).append("\": ");
                  toString(m_sb, attrValue);
                  m_sb.append(",\r\n");
               }
            }
         }
      }

      if (dynamicAttributes != null) {
         for (Map.Entry<String, String> e : dynamicAttributes.entrySet()) {
            if (m_compact) {
               m_sb.append('"').append(e.getKey()).append("\":");
               toString(m_sb, e.getValue());
               m_sb.append(",");
            } else {
               indent();
               m_sb.append('"').append(e.getKey()).append("\": ");
               toString(m_sb, e.getValue());
               m_sb.append(",\r\n");
            }
         }
      }
   }

   public String build(IEntity<?> entity) {
      objectBegin(null);
      entity.accept(this);
      objectEnd(null);
      trimComma();

      return m_sb.toString();
   }

   public String buildArray(Collection<? extends IEntity<?>> entities) {
      m_sb.append('[');

      if (entities != null) {
         for (IEntity<?> entity : entities) {
            objectBegin(null);
            entity.accept(this);
            objectEnd(null);
         }

         trimComma();
      }

      m_sb.append(']');

      return m_sb.toString();
   }

   protected void indent() {
      if (!m_compact) {
         for (int i = m_level - 1; i >= 0; i--) {
            m_sb.append("   ");
         }
      }
   }

   protected void objectBegin(String name) {
      indent();

      if (name == null) {
         m_sb.append("{").append(m_compact ? "" : "\r\n");
      } else {
         m_sb.append('"').append(name).append(m_compact ? "\":{" : "\": {\r\n");
      }

      m_level++;
   }

   protected void objectEnd(String name) {
      m_level--;

      trimComma();
      indent();
      m_sb.append(m_compact ? "}," : "},\r\n");
   }

   protected void toString(StringBuilder sb, Object value) {
      if (value == null) {
         sb.append("null");
      } else if (value instanceof Boolean || value instanceof Number) {
         sb.append(value);
      } else {
         String val = value.toString();
         int len = val.length();

         sb.append('"');

         for (int i = 0; i < len; i++) {
            char ch = val.charAt(i);

            switch (ch) {
            case '\\':
            case '/':
            case '"':
               sb.append('\\').append(ch);
               break;
            case '\t':
               sb.append("\\t");
               break;
            case '\r':
               sb.append("\\r");
               break;
            case '\n':
               sb.append("\\n");
               break;
            default:
               sb.append(ch);
               break;
            }
         }

         sb.append('"');
      }
   }

   protected void trimComma() {
      int len = m_sb.length();

      if (m_compact) {
         if (len > 1 && m_sb.charAt(len - 1) == ',') {
            m_sb.replace(len - 1, len, "");
         }
      } else {
         if (len > 3 && m_sb.charAt(len - 3) == ',') {
            m_sb.replace(len - 3, len - 2, "");
         }
      }
   }

   @Override
   public void visitCondition(Condition condition) {
      attributes(null, ATTR_MINUTE, condition.getMinute(), ATTR_TITLE, condition.getTitle(), ATTR_ALERTTYPE, condition.getAlertType());

      if (!condition.getSubConditions().isEmpty()) {
         arrayBegin(ENTITY_SUB_CONDITIONS);

         for (SubCondition subCondition : condition.getSubConditions()) {
            objectBegin(null);
            subCondition.accept(m_visitor);
            objectEnd(null);
         }

         arrayEnd(ENTITY_SUB_CONDITIONS);
      }
   }

   @Override
   public void visitConfig(Config config) {
      attributes(null, ATTR_STARTTIME, config.getStarttime(), ATTR_ENDTIME, config.getEndtime());

      if (!config.getConditions().isEmpty()) {
         arrayBegin(ENTITY_CONDITIONS);

         for (Condition condition : config.getConditions()) {
            objectBegin(null);
            condition.accept(m_visitor);
            objectEnd(null);
         }

         arrayEnd(ENTITY_CONDITIONS);
      }
   }

   @Override
   public void visitMetricItem(MetricItem metricItem) {
      attributes(null, ATTR_MONITORCOUNT, metricItem.getMonitorCount(), ATTR_MONITORSUM, metricItem.getMonitorSum(), ATTR_MONITORAVG, metricItem.getMonitorAvg(), ATTR_PRODUCTTEXT, metricItem.getProductText(), ATTR_METRICITEMTEXT, metricItem.getMetricItemText());
   }

   @Override
   public void visitMonitorRules(MonitorRules monitorRules) {

      if (!monitorRules.getRules().isEmpty()) {
         objectBegin(ENTITY_RULES);

         for (Map.Entry<String, Rule> e : monitorRules.getRules().entrySet()) {
            String key = String.valueOf(e.getKey());

            objectBegin(key);
            e.getValue().accept(m_visitor);
            objectEnd(key);
         }

         objectEnd(ENTITY_RULES);
      }
   }

   @Override
   public void visitRule(Rule rule) {
      attributes(null, ATTR_ID, rule.getId());

      if (!rule.getMetricItems().isEmpty()) {
         arrayBegin(ENTITY_METRIC_ITEMS);

         for (MetricItem metricItem : rule.getMetricItems()) {
            objectBegin(null);
            metricItem.accept(m_visitor);
            objectEnd(null);
         }

         arrayEnd(ENTITY_METRIC_ITEMS);
      }

      if (!rule.getConfigs().isEmpty()) {
         arrayBegin(ENTITY_CONFIGS);

         for (Config config : rule.getConfigs()) {
            objectBegin(null);
            config.accept(m_visitor);
            objectEnd(null);
         }

         arrayEnd(ENTITY_CONFIGS);
      }
   }

   @Override
   public void visitSubCondition(SubCondition subCondition) {
      attributes(null, ATTR_TYPE, subCondition.getType(), ATTR_TEXT, subCondition.getText());
   }
}
