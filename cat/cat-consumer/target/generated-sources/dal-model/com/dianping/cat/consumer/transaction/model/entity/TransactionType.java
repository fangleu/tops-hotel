package com.dianping.cat.consumer.transaction.model.entity;

import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_TYPE;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.transaction.model.BaseEntity;
import com.dianping.cat.consumer.transaction.model.IVisitor;

public class TransactionType extends BaseEntity<TransactionType> {
   private String m_id;

   private long m_totalCount;

   private long m_failCount;

   private double m_failPercent;

   private double m_min = 86400000d;

   private double m_max = -1d;

   private double m_avg;

   private double m_sum;

   private double m_sum2;

   private double m_std;

   private String m_successMessageUrl;

   private String m_failMessageUrl;

   private Map<String, TransactionName> m_names = new LinkedHashMap<String, TransactionName>();

   private double m_tps;

   private double m_line95Value;

   private double m_line99Value;

   private Map<Integer, Range2> m_range2s = new LinkedHashMap<Integer, Range2>();

   private Map<Integer, AllDuration> m_allDurations = new LinkedHashMap<Integer, AllDuration>();

   private Map<String, String> m_dynamicAttributes = new LinkedHashMap<String, String>();

   public TransactionType() {
   }

   public TransactionType(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitType(this);
   }

   public TransactionType addAllDuration(AllDuration allDuration) {
      m_allDurations.put(allDuration.getValue(), allDuration);
      return this;
   }

   public TransactionType addName(TransactionName name) {
      m_names.put(name.getId(), name);
      return this;
   }

   public TransactionType addRange2(Range2 range2) {
      m_range2s.put(range2.getValue(), range2);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof TransactionType) {
         TransactionType _o = (TransactionType) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public AllDuration findAllDuration(int value) {
      return m_allDurations.get(value);
   }

   public TransactionName findName(String id) {
      return m_names.get(id);
   }

   public Range2 findRange2(int value) {
      return m_range2s.get(value);
   }

   public AllDuration findOrCreateAllDuration(int value) {
      AllDuration allDuration = m_allDurations.get(value);

      if (allDuration == null) {
         synchronized (m_allDurations) {
            allDuration = m_allDurations.get(value);

            if (allDuration == null) {
               allDuration = new AllDuration(value);
               m_allDurations.put(value, allDuration);
            }
         }
      }

      return allDuration;
   }

   public TransactionName findOrCreateName(String id) {
      TransactionName name = m_names.get(id);

      if (name == null) {
         synchronized (m_names) {
            name = m_names.get(id);

            if (name == null) {
               name = new TransactionName(id);
               m_names.put(id, name);
            }
         }
      }

      return name;
   }

   public Range2 findOrCreateRange2(int value) {
      Range2 range2 = m_range2s.get(value);

      if (range2 == null) {
         synchronized (m_range2s) {
            range2 = m_range2s.get(value);

            if (range2 == null) {
               range2 = new Range2(value);
               m_range2s.put(value, range2);
            }
         }
      }

      return range2;
   }

   public String getDynamicAttribute(String name) {
      return m_dynamicAttributes.get(name);
   }

   public Map<String, String> getDynamicAttributes() {
      return m_dynamicAttributes;
   }

   public Map<Integer, AllDuration> getAllDurations() {
      return m_allDurations;
   }

   public double getAvg() {
      return m_avg;
   }

   public long getFailCount() {
      return m_failCount;
   }

   public String getFailMessageUrl() {
      return m_failMessageUrl;
   }

   public double getFailPercent() {
      return m_failPercent;
   }

   public String getId() {
      return m_id;
   }

   public double getLine95Value() {
      return m_line95Value;
   }

   public double getLine99Value() {
      return m_line99Value;
   }

   public double getMax() {
      return m_max;
   }

   public double getMin() {
      return m_min;
   }

   public Map<String, TransactionName> getNames() {
      return m_names;
   }

   public Map<Integer, Range2> getRange2s() {
      return m_range2s;
   }

   public double getStd() {
      return m_std;
   }

   public String getSuccessMessageUrl() {
      return m_successMessageUrl;
   }

   public double getSum() {
      return m_sum;
   }

   public double getSum2() {
      return m_sum2;
   }

   public long getTotalCount() {
      return m_totalCount;
   }

   public double getTps() {
      return m_tps;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public TransactionType incFailCount() {
      m_failCount++;
      return this;
   }

   public TransactionType incFailCount(long failCount) {
      m_failCount += failCount;
      return this;
   }

   public TransactionType incTotalCount() {
      m_totalCount++;
      return this;
   }

   public TransactionType incTotalCount(long totalCount) {
      m_totalCount += totalCount;
      return this;
   }

   @Override
   public void mergeAttributes(TransactionType other) {
      assertAttributeEquals(other, ENTITY_TYPE, ATTR_ID, m_id, other.getId());

      for (Map.Entry<String, String> e : other.getDynamicAttributes().entrySet()) {
         m_dynamicAttributes.put(e.getKey(), e.getValue());
      }

      m_totalCount = other.getTotalCount();

      m_failCount = other.getFailCount();

      m_failPercent = other.getFailPercent();

      m_min = other.getMin();

      m_max = other.getMax();

      m_avg = other.getAvg();

      m_sum = other.getSum();

      m_sum2 = other.getSum2();

      m_std = other.getStd();

      m_tps = other.getTps();

      m_line95Value = other.getLine95Value();

      m_line99Value = other.getLine99Value();
   }

   public boolean removeAllDuration(int value) {
      if (m_allDurations.containsKey(value)) {
         m_allDurations.remove(value);
         return true;
      }

      return false;
   }

   public boolean removeName(String id) {
      if (m_names.containsKey(id)) {
         m_names.remove(id);
         return true;
      }

      return false;
   }

   public boolean removeRange2(int value) {
      if (m_range2s.containsKey(value)) {
         m_range2s.remove(value);
         return true;
      }

      return false;
   }

   public void setDynamicAttribute(String name, String value) {
      m_dynamicAttributes.put(name, value);
   }

   public TransactionType setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public TransactionType setFailCount(long failCount) {
      m_failCount = failCount;
      return this;
   }

   public TransactionType setFailMessageUrl(String failMessageUrl) {
      m_failMessageUrl = failMessageUrl;
      return this;
   }

   public TransactionType setFailPercent(double failPercent) {
      m_failPercent = failPercent;
      return this;
   }

   public TransactionType setId(String id) {
      m_id = id;
      return this;
   }

   public TransactionType setLine95Value(double line95Value) {
      m_line95Value = line95Value;
      return this;
   }

   public TransactionType setLine99Value(double line99Value) {
      m_line99Value = line99Value;
      return this;
   }

   public TransactionType setMax(double max) {
      m_max = max;
      return this;
   }

   public TransactionType setMin(double min) {
      m_min = min;
      return this;
   }

   public TransactionType setStd(double std) {
      m_std = std;
      return this;
   }

   public TransactionType setSuccessMessageUrl(String successMessageUrl) {
      m_successMessageUrl = successMessageUrl;
      return this;
   }

   public TransactionType setSum(double sum) {
      m_sum = sum;
      return this;
   }

   public TransactionType setSum2(double sum2) {
      m_sum2 = sum2;
      return this;
   }

   public TransactionType setTotalCount(long totalCount) {
      m_totalCount = totalCount;
      return this;
   }

   public TransactionType setTps(double tps) {
      m_tps = tps;
      return this;
   }

}
