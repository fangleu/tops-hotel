package com.dianping.cat.consumer.transaction.model.entity;

import static com.dianping.cat.consumer.transaction.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.transaction.model.Constants.ENTITY_NAME;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.transaction.model.BaseEntity;
import com.dianping.cat.consumer.transaction.model.IVisitor;

public class TransactionName extends BaseEntity<TransactionName> {
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

   private Map<Integer, Range> m_ranges = new LinkedHashMap<Integer, Range>();

   private Map<Integer, Duration> m_durations = new LinkedHashMap<Integer, Duration>();

   private double m_totalPercent;

   private double m_tps;

   private double m_line95Value;

   private double m_line99Value;

   private Map<Integer, AllDuration> m_allDurations = new LinkedHashMap<Integer, AllDuration>();

   public TransactionName() {
   }

   public TransactionName(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitName(this);
   }

   public TransactionName addAllDuration(AllDuration allDuration) {
      m_allDurations.put(allDuration.getValue(), allDuration);
      return this;
   }

   public TransactionName addDuration(Duration duration) {
      m_durations.put(duration.getValue(), duration);
      return this;
   }

   public TransactionName addRange(Range range) {
      m_ranges.put(range.getValue(), range);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof TransactionName) {
         TransactionName _o = (TransactionName) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public AllDuration findAllDuration(int value) {
      return m_allDurations.get(value);
   }

   public Duration findDuration(int value) {
      return m_durations.get(value);
   }

   public Range findRange(int value) {
      return m_ranges.get(value);
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

   public Duration findOrCreateDuration(int value) {
      Duration duration = m_durations.get(value);

      if (duration == null) {
         synchronized (m_durations) {
            duration = m_durations.get(value);

            if (duration == null) {
               duration = new Duration(value);
               m_durations.put(value, duration);
            }
         }
      }

      return duration;
   }

   public Range findOrCreateRange(int value) {
      Range range = m_ranges.get(value);

      if (range == null) {
         synchronized (m_ranges) {
            range = m_ranges.get(value);

            if (range == null) {
               range = new Range(value);
               m_ranges.put(value, range);
            }
         }
      }

      return range;
   }

   public Map<Integer, AllDuration> getAllDurations() {
      return m_allDurations;
   }

   public double getAvg() {
      return m_avg;
   }

   public Map<Integer, Duration> getDurations() {
      return m_durations;
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

   public Map<Integer, Range> getRanges() {
      return m_ranges;
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

   public double getTotalPercent() {
      return m_totalPercent;
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

   public TransactionName incFailCount() {
      m_failCount++;
      return this;
   }

   public TransactionName incFailCount(long failCount) {
      m_failCount += failCount;
      return this;
   }

   public TransactionName incTotalCount() {
      m_totalCount++;
      return this;
   }

   public TransactionName incTotalCount(long totalCount) {
      m_totalCount += totalCount;
      return this;
   }

   @Override
   public void mergeAttributes(TransactionName other) {
      assertAttributeEquals(other, ENTITY_NAME, ATTR_ID, m_id, other.getId());

      m_totalCount = other.getTotalCount();

      m_failCount = other.getFailCount();

      m_failPercent = other.getFailPercent();

      m_min = other.getMin();

      m_max = other.getMax();

      m_avg = other.getAvg();

      m_sum = other.getSum();

      m_sum2 = other.getSum2();

      m_std = other.getStd();

      m_totalPercent = other.getTotalPercent();

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

   public boolean removeDuration(int value) {
      if (m_durations.containsKey(value)) {
         m_durations.remove(value);
         return true;
      }

      return false;
   }

   public boolean removeRange(int value) {
      if (m_ranges.containsKey(value)) {
         m_ranges.remove(value);
         return true;
      }

      return false;
   }

   public TransactionName setAvg(double avg) {
      m_avg = avg;
      return this;
   }

   public TransactionName setFailCount(long failCount) {
      m_failCount = failCount;
      return this;
   }

   public TransactionName setFailMessageUrl(String failMessageUrl) {
      m_failMessageUrl = failMessageUrl;
      return this;
   }

   public TransactionName setFailPercent(double failPercent) {
      m_failPercent = failPercent;
      return this;
   }

   public TransactionName setId(String id) {
      m_id = id;
      return this;
   }

   public TransactionName setLine95Value(double line95Value) {
      m_line95Value = line95Value;
      return this;
   }

   public TransactionName setLine99Value(double line99Value) {
      m_line99Value = line99Value;
      return this;
   }

   public TransactionName setMax(double max) {
      m_max = max;
      return this;
   }

   public TransactionName setMin(double min) {
      m_min = min;
      return this;
   }

   public TransactionName setStd(double std) {
      m_std = std;
      return this;
   }

   public TransactionName setSuccessMessageUrl(String successMessageUrl) {
      m_successMessageUrl = successMessageUrl;
      return this;
   }

   public TransactionName setSum(double sum) {
      m_sum = sum;
      return this;
   }

   public TransactionName setSum2(double sum2) {
      m_sum2 = sum2;
      return this;
   }

   public TransactionName setTotalCount(long totalCount) {
      m_totalCount = totalCount;
      return this;
   }

   public TransactionName setTotalPercent(double totalPercent) {
      m_totalPercent = totalPercent;
      return this;
   }

   public TransactionName setTps(double tps) {
      m_tps = tps;
      return this;
   }

}
