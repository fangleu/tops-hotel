package com.dianping.cat.status.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.status.model.BaseEntity;
import com.dianping.cat.status.model.IVisitor;

public class MemoryInfo extends BaseEntity<MemoryInfo> {
   private long m_max;

   private long m_total;

   private long m_free;

   private long m_heapUsage;

   private long m_nonHeapUsage;

   private List<GcInfo> m_gcs = new ArrayList<GcInfo>();

   public MemoryInfo() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMemory(this);
   }

   public MemoryInfo addGc(GcInfo gc) {
      m_gcs.add(gc);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof MemoryInfo) {
         MemoryInfo _o = (MemoryInfo) obj;
         long max = _o.getMax();
         long total = _o.getTotal();
         long free = _o.getFree();
         long heapUsage = _o.getHeapUsage();
         long nonHeapUsage = _o.getNonHeapUsage();
         List<GcInfo> gcs = _o.getGcs();
         boolean result = true;

         result &= (m_max == max);
         result &= (m_total == total);
         result &= (m_free == free);
         result &= (m_heapUsage == heapUsage);
         result &= (m_nonHeapUsage == nonHeapUsage);
         result &= (m_gcs == gcs || m_gcs != null && m_gcs.equals(gcs));

         return result;
      }

      return false;
   }

   public long getFree() {
      return m_free;
   }

   public List<GcInfo> getGcs() {
      return m_gcs;
   }

   public long getHeapUsage() {
      return m_heapUsage;
   }

   public long getMax() {
      return m_max;
   }

   public long getNonHeapUsage() {
      return m_nonHeapUsage;
   }

   public long getTotal() {
      return m_total;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (int) (m_max ^ (m_max >>> 32));
      hash = hash * 31 + (int) (m_total ^ (m_total >>> 32));
      hash = hash * 31 + (int) (m_free ^ (m_free >>> 32));
      hash = hash * 31 + (int) (m_heapUsage ^ (m_heapUsage >>> 32));
      hash = hash * 31 + (int) (m_nonHeapUsage ^ (m_nonHeapUsage >>> 32));
      hash = hash * 31 + (m_gcs == null ? 0 : m_gcs.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(MemoryInfo other) {
      m_max = other.getMax();

      m_total = other.getTotal();

      m_free = other.getFree();

      m_heapUsage = other.getHeapUsage();

      m_nonHeapUsage = other.getNonHeapUsage();
   }

   public MemoryInfo setFree(long free) {
      m_free = free;
      return this;
   }

   public MemoryInfo setHeapUsage(long heapUsage) {
      m_heapUsage = heapUsage;
      return this;
   }

   public MemoryInfo setMax(long max) {
      m_max = max;
      return this;
   }

   public MemoryInfo setNonHeapUsage(long nonHeapUsage) {
      m_nonHeapUsage = nonHeapUsage;
      return this;
   }

   public MemoryInfo setTotal(long total) {
      m_total = total;
      return this;
   }

}
