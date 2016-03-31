package com.dianping.cat.consumer.metric.model.entity;

import static com.dianping.cat.consumer.metric.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_METRIC_ITEM;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.dianping.cat.consumer.metric.model.BaseEntity;
import com.dianping.cat.consumer.metric.model.IVisitor;

public class MetricItem extends BaseEntity<MetricItem> {
   private String m_id;

   private String m_type;

   private Set<String> m_domains = new LinkedHashSet<String>();

   private Map<String, Abtest> m_abtests = new LinkedHashMap<String, Abtest>();

   private Map<Integer, Segment> m_segments = new LinkedHashMap<Integer, Segment>();

   public MetricItem() {
   }

   public MetricItem(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMetricItem(this);
   }

   public MetricItem addAbtest(Abtest abtest) {
      m_abtests.put(abtest.getRunId(), abtest);
      return this;
   }

   public MetricItem addDomain(String domain) {
      m_domains.add(domain);
      return this;
   }

   public MetricItem addSegment(Segment segment) {
      m_segments.put(segment.getId(), segment);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof MetricItem) {
         MetricItem _o = (MetricItem) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Abtest findAbtest(String runId) {
      return m_abtests.get(runId);
   }

   public Segment findSegment(Integer id) {
      return m_segments.get(id);
   }

   public Abtest findOrCreateAbtest(String runId) {
      Abtest abtest = m_abtests.get(runId);

      if (abtest == null) {
         synchronized (m_abtests) {
            abtest = m_abtests.get(runId);

            if (abtest == null) {
               abtest = new Abtest(runId);
               m_abtests.put(runId, abtest);
            }
         }
      }

      return abtest;
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

   public Map<String, Abtest> getAbtests() {
      return m_abtests;
   }

   public Set<String> getDomains() {
      return m_domains;
   }

   public String getId() {
      return m_id;
   }

   public Map<Integer, Segment> getSegments() {
      return m_segments;
   }

   public String getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(MetricItem other) {
      assertAttributeEquals(other, ENTITY_METRIC_ITEM, ATTR_ID, m_id, other.getId());

      if (other.getType() != null) {
         m_type = other.getType();
      }
   }

   public boolean removeAbtest(String runId) {
      if (m_abtests.containsKey(runId)) {
         m_abtests.remove(runId);
         return true;
      }

      return false;
   }

   public boolean removeSegment(Integer id) {
      if (m_segments.containsKey(id)) {
         m_segments.remove(id);
         return true;
      }

      return false;
   }

   public MetricItem setId(String id) {
      m_id = id;
      return this;
   }

   public MetricItem setType(String type) {
      m_type = type;
      return this;
   }

}
