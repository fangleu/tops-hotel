package com.dianping.cat.consumer.heartbeat.model.entity;

import static com.dianping.cat.consumer.heartbeat.model.Constants.ATTR_PATH;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_DISK;

import com.dianping.cat.consumer.heartbeat.model.BaseEntity;
import com.dianping.cat.consumer.heartbeat.model.IVisitor;

public class Disk extends BaseEntity<Disk> {
   private String m_path;

   private long m_total;

   private long m_free;

   private long m_usable;

   public Disk() {
   }

   public Disk(String path) {
      m_path = path;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDisk(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Disk) {
         Disk _o = (Disk) obj;
         String path = _o.getPath();

         return m_path == path || m_path != null && m_path.equals(path);
      }

      return false;
   }

   public long getFree() {
      return m_free;
   }

   public String getPath() {
      return m_path;
   }

   public long getTotal() {
      return m_total;
   }

   public long getUsable() {
      return m_usable;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_path == null ? 0 : m_path.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Disk other) {
      assertAttributeEquals(other, ENTITY_DISK, ATTR_PATH, m_path, other.getPath());

      m_total = other.getTotal();

      m_free = other.getFree();

      m_usable = other.getUsable();
   }

   public Disk setFree(long free) {
      m_free = free;
      return this;
   }

   public Disk setPath(String path) {
      m_path = path;
      return this;
   }

   public Disk setTotal(long total) {
      m_total = total;
      return this;
   }

   public Disk setUsable(long usable) {
      m_usable = usable;
      return this;
   }

}
