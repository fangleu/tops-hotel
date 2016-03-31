package com.dianping.cat.configuration.app.entity;

import static com.dianping.cat.configuration.app.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.Constants.ENTITY_CONFIG_ITEM;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.configuration.app.BaseEntity;
import com.dianping.cat.configuration.app.IVisitor;

public class ConfigItem extends BaseEntity<ConfigItem> {
   private String m_id;

   private Map<Integer, Item> m_items = new LinkedHashMap<Integer, Item>();

   public ConfigItem() {
   }

   public ConfigItem(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitConfigItem(this);
   }

   public ConfigItem addItem(Item item) {
      m_items.put(item.getId(), item);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ConfigItem) {
         ConfigItem _o = (ConfigItem) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Item findItem(Integer id) {
      return m_items.get(id);
   }

   public Item findOrCreateItem(Integer id) {
      Item item = m_items.get(id);

      if (item == null) {
         synchronized (m_items) {
            item = m_items.get(id);

            if (item == null) {
               item = new Item(id);
               m_items.put(id, item);
            }
         }
      }

      return item;
   }

   public String getId() {
      return m_id;
   }

   public Map<Integer, Item> getItems() {
      return m_items;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ConfigItem other) {
      assertAttributeEquals(other, ENTITY_CONFIG_ITEM, ATTR_ID, m_id, other.getId());

   }

   public boolean removeItem(Integer id) {
      if (m_items.containsKey(id)) {
         m_items.remove(id);
         return true;
      }

      return false;
   }

   public ConfigItem setId(String id) {
      m_id = id;
      return this;
   }

}
