package com.dianping.cat.home.storage.transform;

import static com.dianping.cat.home.storage.Constants.ATTR_ALERT;
import static com.dianping.cat.home.storage.Constants.ATTR_DEPARTMENT;
import static com.dianping.cat.home.storage.Constants.ATTR_ID;
import static com.dianping.cat.home.storage.Constants.ATTR_PRODUCTLINE;
import static com.dianping.cat.home.storage.Constants.ATTR_TITLE;
import static com.dianping.cat.home.storage.Constants.ATTR_URL;
import static com.dianping.cat.home.storage.Constants.ELEMENT_PAR;
import static com.dianping.cat.home.storage.Constants.ENTITY_LINK;
import static com.dianping.cat.home.storage.Constants.ENTITY_MACHINE;
import static com.dianping.cat.home.storage.Constants.ENTITY_STORAGE;
import static com.dianping.cat.home.storage.Constants.ENTITY_STORAGE_GROUP;
import static com.dianping.cat.home.storage.Constants.ENTITY_STORAGE_GROUP_CONFIG;

import com.dianping.cat.home.storage.IEntity;
import com.dianping.cat.home.storage.IVisitor;
import com.dianping.cat.home.storage.entity.Link;
import com.dianping.cat.home.storage.entity.Machine;
import com.dianping.cat.home.storage.entity.Storage;
import com.dianping.cat.home.storage.entity.StorageGroup;
import com.dianping.cat.home.storage.entity.StorageGroupConfig;

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

   protected void tagWithText(String name, String text, Object... nameValues) {
      if (text == null) {
         return;
      }
      
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

      m_sb.append(">");
      m_sb.append(escape(text, true));
      m_sb.append("</").append(name).append(">\r\n");
   }

   protected void element(String name, String text, boolean escape) {
      if (text == null) {
         return;
      }
      
      indent();
      
      m_sb.append('<').append(name).append(">");
      
      if (escape) {
         m_sb.append(escape(text, true));
      } else {
         m_sb.append("<![CDATA[").append(text).append("]]>");
      }
      
      m_sb.append("</").append(name).append(">\r\n");
   }

   @Override
   public void visitLink(Link link) {
      startTag(ENTITY_LINK, null, ATTR_URL, link.getUrl());

      if (!link.getPars().isEmpty()) {
         for (String par : link.getPars().toArray(new String[0])) {
            tagWithText(ELEMENT_PAR, par);
         }
      }

      endTag(ENTITY_LINK);
   }

   @Override
   public void visitMachine(Machine machine) {
      startTag(ENTITY_MACHINE, true, null, ATTR_ID, machine.getId(), ATTR_TITLE, machine.getTitle(), ATTR_ALERT, machine.isAlert());
   }

   @Override
   public void visitStorage(Storage storage) {
      startTag(ENTITY_STORAGE, null, ATTR_ID, storage.getId(), ATTR_DEPARTMENT, storage.getDepartment(), ATTR_PRODUCTLINE, storage.getProductline(), ATTR_TITLE, storage.getTitle());

      if (!storage.getMachines().isEmpty()) {
         for (Machine machine : storage.getMachines().values().toArray(new Machine[0])) {
            machine.accept(m_visitor);
         }
      }

      endTag(ENTITY_STORAGE);
   }

   @Override
   public void visitStorageGroup(StorageGroup storageGroup) {
      startTag(ENTITY_STORAGE_GROUP, null, ATTR_ID, storageGroup.getId());

      if (storageGroup.getLink() != null) {
         storageGroup.getLink().accept(m_visitor);
      }

      if (!storageGroup.getStorages().isEmpty()) {
         for (Storage storage : storageGroup.getStorages().values().toArray(new Storage[0])) {
            storage.accept(m_visitor);
         }
      }

      endTag(ENTITY_STORAGE_GROUP);
   }

   @Override
   public void visitStorageGroupConfig(StorageGroupConfig storageGroupConfig) {
      startTag(ENTITY_STORAGE_GROUP_CONFIG, null);

      if (!storageGroupConfig.getStorageGroups().isEmpty()) {
         for (StorageGroup storageGroup : storageGroupConfig.getStorageGroups().values().toArray(new StorageGroup[0])) {
            storageGroup.accept(m_visitor);
         }
      }

      endTag(ENTITY_STORAGE_GROUP_CONFIG);
   }
}
