package com.dianping.cat.consumer.state.model.entity;

import static com.dianping.cat.consumer.state.model.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.state.model.Constants.ENTITY_PROCESSDOMAIN;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.dianping.cat.consumer.state.model.BaseEntity;
import com.dianping.cat.consumer.state.model.IVisitor;

public class ProcessDomain extends BaseEntity<ProcessDomain> {
   private String m_name;

   private Set<String> m_ips = new LinkedHashSet<String>();

   private long m_total;

   private long m_totalLoss;

   private double m_size;

   private double m_avg;

   private Map<Long, Detail> m_details = new LinkedHashMap<Long, Detail>();

   public ProcessDomain() {
   }

   public ProcessDomain(String name) {
      m_name = name;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitProcessDomain(this);
   }

   public ProcessDomain addDetail(Detail detail) {
      m_details.put(detail.getId(), detail);
      return this;
   }

   public ProcessDomain addIp(String ip) {
      m_ips.add(ip);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ProcessDomain) {
         ProcessDomain _o = (ProcessDomain) obj;
         String name = _o.getName();

         return m_name == name || m_name != null && m_name.equals(name);
      }

      return false;
   }

   public Detail findDetail(Long id) {
      return m_details.get(id);
   }

   public Detail findOrCreateDetail(Long id) {
      Detail detail = m_details.get(id);

      if (detail == null) {
         synchronized (m_details) {
            detail = m_details.get(id);

            if (detail == null) {
               detail = new Detail(id);
               m_details.put(id, detail);
            }
         }
      }

      return detail;
   }

   public double getAvg() {
      return m_avg;
   }

   public Map<Long, Detail> getDetails() {
      return m_details;
   }

   public Set<String> getIps() {
      return m_ips;
   }

   public String getName() {
      return m_name;
   }

   public double getSize() {
      return m_size;
   }

   public long getTotal() {
      return m_total;
   }

   public long getTotalLoss() {
      return m_totalLoss;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ProcessDomain other) {
      assertAttributeEquals(other, ENTITY_PROCESSDOMAIN, ATTR_NAME, m_name, other.getName());

      m_total = other.getTotal();

      m_totalLoss = other.getTotalLoss();

      m_size = other.getSize();

      m_avg = other.getAvg();
   }

   public boolean removeDetail(Long id) {
      if (m_details.containsKey(id)) {
         m_details.remove(id);
         return true;
      }

      return false;
   }

   public ProcessDomain setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public ProcessDomain setName(String name) {
      m_name = name;
      return this;
   }

   public ProcessDomain setSize(double size) {
      m_size = size;
      return this;
   }

   public ProcessDomain setTotal(long total) {
      m_total = total;
      return this;
   }

   public ProcessDomain setTotalLoss(long totalLoss) {
      m_totalLoss = totalLoss;
      return this;
   }

}
