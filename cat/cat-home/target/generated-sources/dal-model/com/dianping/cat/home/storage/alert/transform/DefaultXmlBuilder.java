package com.dianping.cat.home.storage.alert.transform;

import static com.dianping.cat.home.storage.alert.Constants.ATTR_CONTENT;
import static com.dianping.cat.home.storage.alert.Constants.ATTR_COUNT;
import static com.dianping.cat.home.storage.alert.Constants.ATTR_END_TIME;
import static com.dianping.cat.home.storage.alert.Constants.ATTR_ID;
import static com.dianping.cat.home.storage.alert.Constants.ATTR_LEVEL;
import static com.dianping.cat.home.storage.alert.Constants.ATTR_START_TIME;
import static com.dianping.cat.home.storage.alert.Constants.ATTR_TITLE;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_DETAIL;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_MACHINE;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_OPERATION;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_STORAGE;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_STORAGE_ALERT_INFO;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_TARGET;

import com.dianping.cat.home.storage.alert.IEntity;
import com.dianping.cat.home.storage.alert.IVisitor;
import com.dianping.cat.home.storage.alert.entity.Detail;
import com.dianping.cat.home.storage.alert.entity.Machine;
import com.dianping.cat.home.storage.alert.entity.Operation;
import com.dianping.cat.home.storage.alert.entity.Storage;
import com.dianping.cat.home.storage.alert.entity.StorageAlertInfo;
import com.dianping.cat.home.storage.alert.entity.Target;

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

   protected String toString(java.util.Date date, String format) {
      if (date != null) {
         return new java.text.SimpleDateFormat(format).format(date);
      } else {
         return null;
      }
   }

   @Override
   public void visitDetail(Detail detail) {
      startTag(ENTITY_DETAIL, true, null, ATTR_CONTENT, detail.getContent(), ATTR_LEVEL, detail.getLevel());
   }

   @Override
   public void visitMachine(Machine machine) {
      startTag(ENTITY_MACHINE, null, ATTR_ID, machine.getId(), ATTR_LEVEL, machine.getLevel(), ATTR_COUNT, machine.getCount());

      if (!machine.getOperations().isEmpty()) {
         for (Operation operation : machine.getOperations().values().toArray(new Operation[0])) {
            operation.accept(m_visitor);
         }
      }

      endTag(ENTITY_MACHINE);
   }

   @Override
   public void visitOperation(Operation operation) {
      startTag(ENTITY_OPERATION, null, ATTR_ID, operation.getId(), ATTR_LEVEL, operation.getLevel(), ATTR_COUNT, operation.getCount());

      if (!operation.getTargets().isEmpty()) {
         for (Target target : operation.getTargets().values().toArray(new Target[0])) {
            target.accept(m_visitor);
         }
      }

      endTag(ENTITY_OPERATION);
   }

   @Override
   public void visitStorage(Storage storage) {
      startTag(ENTITY_STORAGE, null, ATTR_ID, storage.getId(), ATTR_LEVEL, storage.getLevel(), ATTR_COUNT, storage.getCount());

      if (!storage.getMachines().isEmpty()) {
         for (Machine machine : storage.getMachines().values().toArray(new Machine[0])) {
            machine.accept(m_visitor);
         }
      }

      endTag(ENTITY_STORAGE);
   }

   @Override
   public void visitStorageAlertInfo(StorageAlertInfo storageAlertInfo) {
      startTag(ENTITY_STORAGE_ALERT_INFO, null, ATTR_ID, storageAlertInfo.getId(), ATTR_START_TIME, toString(storageAlertInfo.getStartTime(), "yyyy-MM-dd HH:mm:ss"), ATTR_END_TIME, toString(storageAlertInfo.getEndTime(), "yyyy-MM-dd HH:mm:ss"));

      if (!storageAlertInfo.getStorages().isEmpty()) {
         for (Storage storage : storageAlertInfo.getStorages().values().toArray(new Storage[0])) {
            storage.accept(m_visitor);
         }
      }

      endTag(ENTITY_STORAGE_ALERT_INFO);
   }

   @Override
   public void visitTarget(Target target) {
      startTag(ENTITY_TARGET, null, ATTR_ID, target.getId(), ATTR_TITLE, target.getTitle(), ATTR_LEVEL, target.getLevel(), ATTR_COUNT, target.getCount());

      if (!target.getDetails().isEmpty()) {
         for (Detail detail : target.getDetails().toArray(new Detail[0])) {
            detail.accept(m_visitor);
         }
      }

      endTag(ENTITY_TARGET);
   }
}
