package com.dianping.cat.home.storage.alert.entity;

import static com.dianping.cat.home.storage.alert.Constants.ATTR_ID;
import static com.dianping.cat.home.storage.alert.Constants.ENTITY_TARGET;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.storage.alert.BaseEntity;
import com.dianping.cat.home.storage.alert.IVisitor;

public class Target extends BaseEntity<Target> {
   private String m_id;

   private String m_title;

   private int m_level;

   private int m_count;

   private List<Detail> m_details = new ArrayList<Detail>();

   public Target() {
   }

   public Target(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitTarget(this);
   }

   public Target addDetail(Detail detail) {
      m_details.add(detail);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Target) {
         Target _o = (Target) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Detail findDetail(String content) {
      for (Detail detail : m_details) {
         if (!detail.getContent().equals(content)) {
            continue;
         }

         return detail;
      }

      return null;
   }

   public int getCount() {
      return m_count;
   }

   public List<Detail> getDetails() {
      return m_details;
   }

   public String getId() {
      return m_id;
   }

   public int getLevel() {
      return m_level;
   }

   public String getTitle() {
      return m_title;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public Target incCount() {
      m_count++;
      return this;
   }

   public Target incCount(int count) {
      m_count += count;
      return this;
   }

   @Override
   public void mergeAttributes(Target other) {
      assertAttributeEquals(other, ENTITY_TARGET, ATTR_ID, m_id, other.getId());

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }

      m_level = other.getLevel();

      m_count = other.getCount();
   }

   public boolean removeDetail(String content) {
      int len = m_details.size();

      for (int i = 0; i < len; i++) {
         Detail detail = m_details.get(i);

         if (!detail.getContent().equals(content)) {
            continue;
         }

         m_details.remove(i);
         return true;
      }

      return false;
   }

   public Target setCount(int count) {
      m_count = count;
      return this;
   }

   public Target setId(String id) {
      m_id = id;
      return this;
   }

   public Target setLevel(int level) {
      m_level = level;
      return this;
   }

   public Target setTitle(String title) {
      m_title = title;
      return this;
   }

}
