package com.dianping.cat.consumer.dependency.model.entity;

import static com.dianping.cat.consumer.dependency.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.dependency.model.Constants.ENTITY_SEGMENT;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.dependency.model.BaseEntity;
import com.dianping.cat.consumer.dependency.model.IVisitor;

public class Segment extends BaseEntity<Segment> {
   private Integer m_id;

   private Integer m_exceptionCount;

   private Map<String, Index> m_indexs = new LinkedHashMap<String, Index>();

   private Map<String, Dependency> m_dependencies = new LinkedHashMap<String, Dependency>();

   public Segment() {
   }

   public Segment(Integer id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitSegment(this);
   }

   public Segment addDependency(Dependency dependency) {
      m_dependencies.put(dependency.getKey(), dependency);
      return this;
   }

   public Segment addIndex(Index index) {
      m_indexs.put(index.getName(), index);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Segment) {
         Segment _o = (Segment) obj;
         Integer id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Dependency findDependency(String key) {
      return m_dependencies.get(key);
   }

   public Index findIndex(String name) {
      return m_indexs.get(name);
   }

   public Dependency findOrCreateDependency(String key) {
      Dependency dependency = m_dependencies.get(key);

      if (dependency == null) {
         synchronized (m_dependencies) {
            dependency = m_dependencies.get(key);

            if (dependency == null) {
               dependency = new Dependency(key);
               m_dependencies.put(key, dependency);
            }
         }
      }

      return dependency;
   }

   public Index findOrCreateIndex(String name) {
      Index index = m_indexs.get(name);

      if (index == null) {
         synchronized (m_indexs) {
            index = m_indexs.get(name);

            if (index == null) {
               index = new Index(name);
               m_indexs.put(name, index);
            }
         }
      }

      return index;
   }

   public Map<String, Dependency> getDependencies() {
      return m_dependencies;
   }

   public Integer getExceptionCount() {
      return m_exceptionCount;
   }

   public Integer getId() {
      return m_id;
   }

   public Map<String, Index> getIndexs() {
      return m_indexs;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Segment other) {
      assertAttributeEquals(other, ENTITY_SEGMENT, ATTR_ID, m_id, other.getId());

      if (other.getExceptionCount() != null) {
         m_exceptionCount = other.getExceptionCount();
      }
   }

   public boolean removeDependency(String key) {
      if (m_dependencies.containsKey(key)) {
         m_dependencies.remove(key);
         return true;
      }

      return false;
   }

   public boolean removeIndex(String name) {
      if (m_indexs.containsKey(name)) {
         m_indexs.remove(name);
         return true;
      }

      return false;
   }

   public Segment setExceptionCount(Integer exceptionCount) {
      m_exceptionCount = exceptionCount;
      return this;
   }

   public Segment setId(Integer id) {
      m_id = id;
      return this;
   }

}
