package com.dianping.cat.configuration.app.speed.entity;

import static com.dianping.cat.configuration.app.speed.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.speed.Constants.ENTITY_SPEED;

import com.dianping.cat.configuration.app.speed.BaseEntity;
import com.dianping.cat.configuration.app.speed.IVisitor;

public class Speed extends BaseEntity<Speed> {
   private int m_id;

   private String m_page;

   private int m_step;

   private String m_title;

   private int m_threshold;

   public Speed() {
   }

   public Speed(int id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitSpeed(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Speed) {
         Speed _o = (Speed) obj;
         int id = _o.getId();

         return m_id == id;
      }

      return false;
   }

   public int getId() {
      return m_id;
   }

   public String getPage() {
      return m_page;
   }

   public int getStep() {
      return m_step;
   }

   public int getThreshold() {
      return m_threshold;
   }

   public String getTitle() {
      return m_title;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + m_id;

      return hash;
   }

   @Override
   public void mergeAttributes(Speed other) {
      assertAttributeEquals(other, ENTITY_SPEED, ATTR_ID, m_id, other.getId());

      if (other.getPage() != null) {
         m_page = other.getPage();
      }

      m_step = other.getStep();

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }

      m_threshold = other.getThreshold();
   }

   public Speed setId(int id) {
      m_id = id;
      return this;
   }

   public Speed setPage(String page) {
      m_page = page;
      return this;
   }

   public Speed setStep(int step) {
      m_step = step;
      return this;
   }

   public Speed setThreshold(int threshold) {
      m_threshold = threshold;
      return this;
   }

   public Speed setTitle(String title) {
      m_title = title;
      return this;
   }

}
