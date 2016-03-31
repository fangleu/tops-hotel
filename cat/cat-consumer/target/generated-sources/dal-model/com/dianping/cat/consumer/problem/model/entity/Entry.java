package com.dianping.cat.consumer.problem.model.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.problem.model.BaseEntity;
import com.dianping.cat.consumer.problem.model.IVisitor;

public class Entry extends BaseEntity<Entry> {
   private String m_type;

   private String m_status;

   private Map<Integer, Duration> m_durations = new LinkedHashMap<Integer, Duration>();

   private Map<String, JavaThread> m_threads = new LinkedHashMap<String, JavaThread>();

   public Entry() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitEntry(this);
   }

   public Entry addDuration(Duration duration) {
      m_durations.put(duration.getValue(), duration);
      return this;
   }

   public Entry addThread(JavaThread thread) {
      m_threads.put(thread.getId(), thread);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Entry) {
         Entry _o = (Entry) obj;
         String type = _o.getType();
         String status = _o.getStatus();
         Map<Integer, Duration> durations = _o.getDurations();
         Map<String, JavaThread> threads = _o.getThreads();
         boolean result = true;

         result &= (m_type == type || m_type != null && m_type.equals(type));
         result &= (m_status == status || m_status != null && m_status.equals(status));
         result &= (m_durations == durations || m_durations != null && m_durations.equals(durations));
         result &= (m_threads == threads || m_threads != null && m_threads.equals(threads));

         return result;
      }

      return false;
   }

   public Duration findDuration(int value) {
      return m_durations.get(value);
   }

   public JavaThread findThread(String id) {
      return m_threads.get(id);
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

   public JavaThread findOrCreateThread(String id) {
      JavaThread thread = m_threads.get(id);

      if (thread == null) {
         synchronized (m_threads) {
            thread = m_threads.get(id);

            if (thread == null) {
               thread = new JavaThread(id);
               m_threads.put(id, thread);
            }
         }
      }

      return thread;
   }

   public Map<Integer, Duration> getDurations() {
      return m_durations;
   }

   public String getStatus() {
      return m_status;
   }

   public Map<String, JavaThread> getThreads() {
      return m_threads;
   }

   public String getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());
      hash = hash * 31 + (m_status == null ? 0 : m_status.hashCode());
      hash = hash * 31 + (m_durations == null ? 0 : m_durations.hashCode());
      hash = hash * 31 + (m_threads == null ? 0 : m_threads.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Entry other) {
      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getStatus() != null) {
         m_status = other.getStatus();
      }
   }

   public boolean removeDuration(int value) {
      if (m_durations.containsKey(value)) {
         m_durations.remove(value);
         return true;
      }

      return false;
   }

   public boolean removeThread(String id) {
      if (m_threads.containsKey(id)) {
         m_threads.remove(id);
         return true;
      }

      return false;
   }

   public Entry setStatus(String status) {
      m_status = status;
      return this;
   }

   public Entry setType(String type) {
      m_type = type;
      return this;
   }

}
