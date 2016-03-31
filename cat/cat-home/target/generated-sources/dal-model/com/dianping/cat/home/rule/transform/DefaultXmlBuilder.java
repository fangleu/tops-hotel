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
import static com.dianping.cat.home.rule.Constants.ENTITY_CONDITION;
import static com.dianping.cat.home.rule.Constants.ENTITY_CONFIG;
import static com.dianping.cat.home.rule.Constants.ENTITY_METRIC_ITEM;
import static com.dianping.cat.home.rule.Constants.ENTITY_MONITOR_RULES;
import static com.dianping.cat.home.rule.Constants.ENTITY_RULE;
import static com.dianping.cat.home.rule.Constants.ENTITY_SUB_CONDITION;

import com.dianping.cat.home.rule.IEntity;
import com.dianping.cat.home.rule.IVisitor;
import com.dianping.cat.home.rule.entity.Condition;
import com.dianping.cat.home.rule.entity.Config;
import com.dianping.cat.home.rule.entity.MetricItem;
import com.dianping.cat.home.rule.entity.MonitorRules;
import com.dianping.cat.home.rule.entity.Rule;
import com.dianping.cat.home.rule.entity.SubCondition;

public class DefaultXmlBuilder implements IVisitor {

   private IVisitor m_visitor = this;

   private int m_level;

   private StringBuilder m_sb;

   private boolean m_compact;

   public DefaultXmlBuilder() {
      this(false);
   }

   public DefaultXmlBuilder(boolean compact) {
      this(compact, new StringBuilder(4096));
   }

   public DefaultXmlBuilder(boolean compact, StringBuilder sb) {
      m_compact = compact;
      m_sb = sb;
      m_sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
   }

   public String buildXml(IEntity<?> entity) {
      entity.accept(m_visitor);
      return m_sb.toString();
   }

   protected void endTag(String name) {
      m_level--;

      indent();
      m_sb.append("</").append(name).append(">\r\n");
   }

   protected String escape(Object value) {
      return escape(value, false);
   }
   
   protected String escape(Object value, boolean text) {
      if (value == null) {
         return null;
      }

      String str = value.toString();
      int len = str.length();
      StringBuilder sb = new StringBuilder(len + 16);

      for (int i = 0; i < len; i++) {
         final char ch = str.charAt(i);

         switch (ch) {
         case '<':
            sb.append("&lt;");
            break;
         case '>':
            sb.append("&gt;");
            break;
         case '&':
            sb.append("&amp;");
            break;
         case '"':
            if (!text) {
               sb.append("&quot;");
               break;
            }
         default:
            sb.append(ch);
            break;
         }
      }

      return sb.toString();
   }
   
   protected void indent() {
      if (!m_compact) {
         for (int i = m_level - 1; i >= 0; i--) {
            m_sb.append("   ");
         }
      }
   }

   protected void startTag(String name) {
      startTag(name, false, null);
   }
   
   protected void startTag(String name, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
      startTag(name, null, closed, dynamicAttributes, nameValues);
   }

   protected void startTag(String name, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
      startTag(name, null, false, dynamicAttributes, nameValues);
   }

   protected void startTag(String name, Object text, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
      indent();

      m_sb.append('<').append(name);

      int len = nameValues.length;

      for (int i = 0; i + 1 < len; i += 2) {
         Object attrName = nameValues[i];
         Object attrValue = nameValues[i + 1];

         if (attrValue != null) {
            m_sb.append(' ').append(attrName).append("=\"").append(escape(attrValue)).append('"');
         }
      }

      if (dynamicAttributes != null) {
         for (java.util.Map.Entry<String, String> e : dynamicAttributes.entrySet()) {
            m_sb.append(' ').append(e.getKey()).append("=\"").append(escape(e.getValue())).append('"');
         }
      }

      if (text != null && closed) {
         m_sb.append('>');
         m_sb.append(escape(text, true));
         m_sb.append("</").append(name).append(">\r\n");
      } else {
         if (closed) {
            m_sb.append('/');
         } else {
            m_level++;
         }
   
         m_sb.append(">\r\n");
      }
   }

   @Override
   public void visitCondition(Condition condition) {
      startTag(ENTITY_CONDITION, null, ATTR_MINUTE, condition.getMinute(), ATTR_TITLE, condition.getTitle(), ATTR_ALERTTYPE, condition.getAlertType());

      if (!condition.getSubConditions().isEmpty()) {
         for (SubCondition subCondition : condition.getSubConditions().toArray(new SubCondition[0])) {
            subCondition.accept(m_visitor);
         }
      }

      endTag(ENTITY_CONDITION);
   }

   @Override
   public void visitConfig(Config config) {
      startTag(ENTITY_CONFIG, null, ATTR_STARTTIME, config.getStarttime(), ATTR_ENDTIME, config.getEndtime());

      if (!config.getConditions().isEmpty()) {
         for (Condition condition : config.getConditions().toArray(new Condition[0])) {
            condition.accept(m_visitor);
         }
      }

      endTag(ENTITY_CONFIG);
   }

   @Override
   public void visitMetricItem(MetricItem metricItem) {
      startTag(ENTITY_METRIC_ITEM, true, null, ATTR_MONITORCOUNT, metricItem.getMonitorCount(), ATTR_MONITORSUM, metricItem.getMonitorSum(), ATTR_MONITORAVG, metricItem.getMonitorAvg(), ATTR_PRODUCTTEXT, metricItem.getProductText(), ATTR_METRICITEMTEXT, metricItem.getMetricItemText());
   }

   @Override
   public void visitMonitorRules(MonitorRules monitorRules) {
      startTag(ENTITY_MONITOR_RULES, null);

      if (!monitorRules.getRules().isEmpty()) {
         for (Rule rule : monitorRules.getRules().values().toArray(new Rule[0])) {
            rule.accept(m_visitor);
         }
      }

      endTag(ENTITY_MONITOR_RULES);
   }

   @Override
   public void visitRule(Rule rule) {
      startTag(ENTITY_RULE, null, ATTR_ID, rule.getId());

      if (!rule.getMetricItems().isEmpty()) {
         for (MetricItem metricItem : rule.getMetricItems().toArray(new MetricItem[0])) {
            metricItem.accept(m_visitor);
         }
      }

      if (!rule.getConfigs().isEmpty()) {
         for (Config config : rule.getConfigs().toArray(new Config[0])) {
            config.accept(m_visitor);
         }
      }

      endTag(ENTITY_RULE);
   }

   @Override
   public void visitSubCondition(SubCondition subCondition) {
      startTag(ENTITY_SUB_CONDITION, true, null, ATTR_TYPE, subCondition.getType(), ATTR_TEXT, subCondition.getText());
   }
}
