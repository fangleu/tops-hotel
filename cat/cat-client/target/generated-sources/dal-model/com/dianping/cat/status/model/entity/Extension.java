package com.dianping.cat.status.model.entity;

import static com.dianping.cat.status.model.Constants.ATTR_ID;
import static com.dianping.cat.status.model.Constants.ENTITY_EXTENSION;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.status.model.BaseEntity;
import com.dianping.cat.status.model.IVisitor;

public class Extension extends BaseEntity<Extension> {
   private String m_id;

   private String m_description;

   private Map<String, ExtensionDetail> m_details = new LinkedHashMap<String, ExtensionDetail>();

   private Map<String, String> m_dynamicAttributes = new LinkedHashMap<String, String>();

   public Extension() {
   }

   public Extension(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitExtension(this);
   }

   public Extension addExtensionDetail(ExtensionDetail extensionDetail) {
      m_details.put(extensionDetail.getId(), extensionDetail);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Extension) {
         Extension _o = (Extension) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public ExtensionDetail findExtensionDetail(String id) {
      return m_details.get(id);
   }

   public ExtensionDetail findOrCreateExtensionDetail(String id) {
      ExtensionDetail extensionDetail = m_details.get(id);

      if (extensionDetail == null) {
         synchronized (m_details) {
            extensionDetail = m_details.get(id);

            if (extensionDetail == null) {
               extensionDetail = new ExtensionDetail(id);
               m_details.put(id, extensionDetail);
            }
         }
      }

      return extensionDetail;
   }

   public String getDynamicAttribute(String name) {
      return m_dynamicAttributes.get(name);
   }

   public Map<String, String> getDynamicAttributes() {
      return m_dynamicAttributes;
   }

   public String getDescription() {
      return m_description;
   }

   public Map<String, ExtensionDetail> getDetails() {
      return m_details;
   }

   public String getId() {
      return m_id;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Extension other) {
      assertAttributeEquals(other, ENTITY_EXTENSION, ATTR_ID, m_id, other.getId());

      for (Map.Entry<String, String> e : other.getDynamicAttributes().entrySet()) {
         m_dynamicAttributes.put(e.getKey(), e.getValue());
      }

   }

   public boolean removeExtensionDetail(String id) {
      if (m_details.containsKey(id)) {
         m_details.remove(id);
         return true;
      }

      return false;
   }

   public void setDynamicAttribute(String name, String value) {
      m_dynamicAttributes.put(name, value);
   }

   public Extension setDescription(String description) {
      m_description = description;
      return this;
   }

   public Extension setId(String id) {
      m_id = id;
      return this;
   }

}
