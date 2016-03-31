package com.dianping.cat.home.app.entity;

import static com.dianping.cat.home.app.Constants.ATTR_ID;
import static com.dianping.cat.home.app.Constants.ENTITY_CODE;

import com.dianping.cat.home.app.BaseEntity;
import com.dianping.cat.home.app.IVisitor;

public class Code extends BaseEntity<Code> {
   private String m_id;

   private long m_count;

   private double m_sum;

   private double m_avg;

   private long m_errors;

   private double m_successRatio;

   public Code() {
   }

   public Code(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCode(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Code) {
         Code _o = (Code) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public long getCount() {
      return m_count;
   }

   public long getErrors() {
      return m_errors;
   }

   public String getId() {
      return m_id;
   }

   public double getSuccessRatio() {
      return m_successRatio;
   }

   public double getSum() {
      return m_sum;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public Code incCount() {
      m_count++;
      return this;
   }

   public Code incCount(long count) {
      m_count += count;
      return this;
   }

   public Code incErrors() {
      m_errors++;
      return this;
   }

   public Code incErrors(long errors) {
      m_errors += errors;
      return this;
   }

   public Code incSum() {
      m_sum++;
      return this;
   }

   public Code incSum(double sum) {
      m_sum += sum;
      return this;
   }

   @Override
   public void mergeAttributes(Code other) {
      assertAttributeEquals(other, ENTITY_CODE, ATTR_ID, m_id, other.getId());

      m_count = other.getCount();

      m_sum = other.getSum();

      m_avg = other.getAvg();

      m_errors = other.getErrors();

      m_successRatio = other.getSuccessRatio();
   }

   public Code setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public Code setCount(long count) {
      m_count = count;
      return this;
   }

   public Code setErrors(long errors) {
      m_errors = errors;
      return this;
   }

   public Code setId(String id) {
      m_id = id;
      return this;
   }

   public Code setSuccessRatio(double successRatio) {
      m_successRatio = successRatio;
      return this;
   }

   public Code setSum(double sum) {
      m_sum = sum;
      return this;
   }

}
