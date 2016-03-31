package com.dianping.cat.home.utilization.entity;

import static com.dianping.cat.home.utilization.Constants.ATTR_ID;
import static com.dianping.cat.home.utilization.Constants.ENTITY_MACHINESTATE;

import com.dianping.cat.home.utilization.BaseEntity;
import com.dianping.cat.home.utilization.IVisitor;

public class MachineState extends BaseEntity<MachineState> {
   private String m_id;

   private long m_count;

   private double m_sum;

   private double m_avg;

   private double m_avgMax;

   public MachineState() {
   }

   public MachineState(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMachineState(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof MachineState) {
         MachineState _o = (MachineState) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public double getAvg() {
      return m_avg;
   }

   public double getAvgMax() {
      return m_avgMax;
   }

   public long getCount() {
      return m_count;
   }

   public String getId() {
      return m_id;
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

   @Override
   public void mergeAttributes(MachineState other) {
      assertAttributeEquals(other, ENTITY_MACHINESTATE, ATTR_ID, m_id, other.getId());

      m_count = other.getCount();

      m_sum = other.getSum();

      m_avg = other.getAvg();

      m_avgMax = other.getAvgMax();
   }

   public MachineState setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public MachineState setAvgMax(double avgMax) {
      m_avgMax = avgMax;
      return this;
   }

   public MachineState setCount(long count) {
      m_count = count;
      return this;
   }

   public MachineState setId(String id) {
      m_id = id;
      return this;
   }

   public MachineState setSum(double sum) {
      m_sum = sum;
      return this;
   }

}
