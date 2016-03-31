package com.dianping.cat.consumer.event.model.entity;

import static com.dianping.cat.consumer.event.model.Constants.ATTR_VALUE;
import static com.dianping.cat.consumer.event.model.Constants.ENTITY_RANGE;

import com.dianping.cat.consumer.event.model.BaseEntity;
import com.dianping.cat.consumer.event.model.IVisitor;

public class Range extends BaseEntity<Range> {
   private Integer m_value;

   private int m_count;

   private int m_fails;

   public Range() {
   }

   public Range(Integer value) {
      m_value = value;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitRange(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Range) {
         Range _o = (Range) obj;
         Integer value = _o.getValue();

         return m_value == value || m_value != null && m_value.equals(value);
      }

      return false;
   }

   public int getCount() {
      return m_count;
   }

   public int getFails() {
      return m_fails;
   }

   public Integer getValue() {
      return m_value;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_value == null ? 0 : m_value.hashCode());

      return hash;
   }

   public Range incCount() {
      m_count++;
      return this;
   }

   public Range incCount(int count) {
      m_count += count;
      return this;
   }

   public Range incFails() {
      m_fails++;
      return this;
   }

   public Range incFails(int fails) {
      m_fails += fails;
      return this;
   }

   @Override
   public void mergeAttributes(Range other) {
      assertAttributeEquals(other, ENTITY_RANGE, ATTR_VALUE, m_value, other.getValue());

      m_count = other.getCount();

      m_fails = other.getFails();
   }

   public Range setCount(int count) {
      m_count = count;
      return this;
   }

   public Range setFails(int fails) {
      m_fails = fails;
      return this;
   }

   public Range setValue(Integer value) {
      m_value = value;
      return this;
   }

}
