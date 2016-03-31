package com.dianping.cat.consumer.all.config.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.all.config.BaseEntity;
import com.dianping.cat.consumer.all.config.IVisitor;

public class AllConfig extends BaseEntity<AllConfig> {
   private Map<String, Report> m_reports = new LinkedHashMap<String, Report>();

   public AllConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAllConfig(this);
   }

   public AllConfig addReport(Report report) {
      m_reports.put(report.getId(), report);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof AllConfig) {
         AllConfig _o = (AllConfig) obj;
         Map<String, Report> reports = _o.getReports();
         boolean result = true;

         result &= (m_reports == reports || m_reports != null && m_reports.equals(reports));

         return result;
      }

      return false;
   }

   public Report findReport(String id) {
      return m_reports.get(id);
   }

   public Report findOrCreateReport(String id) {
      Report report = m_reports.get(id);

      if (report == null) {
         synchronized (m_reports) {
            report = m_reports.get(id);

            if (report == null) {
               report = new Report(id);
               m_reports.put(id, report);
            }
         }
      }

      return report;
   }

   public Map<String, Report> getReports() {
      return m_reports;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_reports == null ? 0 : m_reports.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(AllConfig other) {
   }

   public boolean removeReport(String id) {
      if (m_reports.containsKey(id)) {
         m_reports.remove(id);
         return true;
      }

      return false;
   }

}
