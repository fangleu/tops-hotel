package com.dianping.cat.consumer.dependency.model.entity;

import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_DOMAIN;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_DEPENDENCY_REPORT;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.dianping.cat.consumer.dependency.model.BaseEntity;
import com.dianping.cat.consumer.dependency.model.IVisitor;

public class DependencyReport extends BaseEntity<DependencyReport> {
   private String m_domain;

   private java.util.Date m_startTime;

   private java.util.Date m_endTime;

   private Map<Integer, Segment> m_segments = new LinkedHashMap<Integer, Segment>();

   private Set<String> m_domainNames = new LinkedHashSet<String>();

   public DependencyReport() {
   }

   public DependencyReport(String domain) {
      m_domain = domain;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDependencyReport(this);
   }

   public DependencyReport addDomainName(String domainName) {
      m_domainNames.add(domainName);
      return this;
   }

   public DependencyReport addSegment(Segment segment) {
      m_segments.put(segment.getId(), segment);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof DependencyReport) {
         DependencyReport _o = (DependencyReport) obj;
         String domain = _o.getDomain();

         return m_domain == domain || m_domain != null && m_domain.equals(domain);
      }

      return false;
   }

   public Segment findSegment(Integer id) {
      return m_segments.get(id);
   }

   public Segment findOrCreateSegment(Integer id) {
      Segment segment = m_segments.get(id);

      if (segment == null) {
         synchronized (m_segments) {
            segment = m_segments.get(id);

            if (segment == null) {
               segment = new Segment(id);
               m_segments.put(id, segment);
            }
         }
      }

      return segment;
   }

   public String getDomain() {
      return m_domain;
   }

   public Set<String> getDomainNames() {
      return m_domainNames;
   }

   public java.util.Date getEndTime() {
      return m_endTime;
   }

   public Map<Integer, Segment> getSegments() {
      return m_segments;
   }

   public java.util.Date getStartTime() {
      return m_startTime;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_domain == null ? 0 : m_domain.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(DependencyReport other) {
      assertAttributeEquals(other, ENTITY_DEPENDENCY_REPORT, ATTR_DOMAIN, m_domain, other.getDomain());

      if (other.getStartTime() != null) {
         m_startTime = other.getStartTime();
      }

      if (other.getEndTime() != null) {
         m_endTime = other.getEndTime();
      }
   }

   public boolean removeSegment(Integer id) {
      if (m_segments.containsKey(id)) {
         m_segments.remove(id);
         return true;
      }

      return false;
   }

   public DependencyReport setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public DependencyReport setEndTime(java.util.Date endTime) {
      m_endTime = endTime;
      return this;
   }

   public DependencyReport setStartTime(java.util.Date startTime) {
      m_startTime = startTime;
      return this;
   }

}
