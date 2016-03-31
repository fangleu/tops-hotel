package com.dianping.cat.consumer.problem.model.entity;

import static com.dianping.cat.consumer.problem.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.problem.model.Constants.ENTITY_ENTITY;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.problem.model.BaseEntity;
import com.dianping.cat.consumer.problem.model.IVisitor;

public class Entity extends BaseEntity<Entity> {
   private String m_id;

   private String m_type;

   private String m_status;

   private Map<String, JavaThread> m_threads = new LinkedHashMap<String, JavaThread>();

   private Map<Integer, Duration> m_durations = new LinkedHashMap<Integer, Duration>();

   public Entity() {
   }

   public Entity(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitEntity(this);
   }

   public Entity addDuration(Duration duration) {
      m_durations.put(duration.getValue(), duration);
      return this;
   }

   public Entity addThread(JavaThread thread) {
      m_threads.put(thread.getId(), thread);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Entity) {
         Entity _o = (Entity) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
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

   public String getId() {
      return m_id;
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

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Entity other) {
      assertAttributeEquals(other, ENTITY_ENTITY, ATTR_ID, m_id, other.getId());

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

   public Entity setId(String id) {
      m_id = id;
      return this;
   }

   public Entity setStatus(String status) {
      m_status = status;
      return this;
   }

   public Entity setType(String type) {
      m_type = type;
      return this;
   }

}
