package com.dianping.cat.consumer.heartbeat.model.entity;

import static com.dianping.cat.consumer.heartbeat.model.Constants.ATTR_MINUTE;
import static com.dianping.cat.consumer.heartbeat.model.Constants.ENTITY_PERIOD;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dianping.cat.consumer.heartbeat.model.BaseEntity;
import com.dianping.cat.consumer.heartbeat.model.IVisitor;

public class Period extends BaseEntity<Period> {
   private int m_minute;

   private int m_threadCount;

   private int m_daemonCount;

   private int m_totalStartedCount;

   private int m_catThreadCount;

   private int m_pigeonThreadCount;

   private int m_httpThreadCount;

   private long m_newGcCount;

   private long m_oldGcCount;

   private long m_memoryFree;

   private long m_heapUsage;

   private long m_noneHeapUsage;

   private double m_systemLoadAverage;

   private long m_catMessageProduced;

   private long m_catMessageOverflow;

   private double m_catMessageSize;

   private List<Disk> m_disks = new ArrayList<Disk>();

   private Map<String, Extension> m_extensions = new LinkedHashMap<String, Extension>();

   private Map<String, String> m_dynamicAttributes = new LinkedHashMap<String, String>();

   public Period() {
   }

   public Period(int minute) {
      m_minute = minute;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitPeriod(this);
   }

   public Period addDisk(Disk disk) {
      m_disks.add(disk);
      return this;
   }

   public Period addExtension(Extension extension) {
      m_extensions.put(extension.getId(), extension);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Period) {
         Period _o = (Period) obj;
         int minute = _o.getMinute();

         return m_minute == minute;
      }

      return false;
   }

   public Disk findDisk(String path) {
      for (Disk disk : m_disks) {
         if (!disk.getPath().equals(path)) {
            continue;
         }

         return disk;
      }

      return null;
   }

   public Extension findExtension(String id) {
      return m_extensions.get(id);
   }

   public Extension findOrCreateExtension(String id) {
      Extension extension = m_extensions.get(id);

      if (extension == null) {
         synchronized (m_extensions) {
            extension = m_extensions.get(id);

            if (extension == null) {
               extension = new Extension(id);
               m_extensions.put(id, extension);
            }
         }
      }

      return extension;
   }

   public String getDynamicAttribute(String name) {
      return m_dynamicAttributes.get(name);
   }

   public Map<String, String> getDynamicAttributes() {
      return m_dynamicAttributes;
   }

   public long getCatMessageOverflow() {
      return m_catMessageOverflow;
   }

   public long getCatMessageProduced() {
      return m_catMessageProduced;
   }

   public double getCatMessageSize() {
      return m_catMessageSize;
   }

   public int getCatThreadCount() {
      return m_catThreadCount;
   }

   public int getDaemonCount() {
      return m_daemonCount;
   }

   public List<Disk> getDisks() {
      return m_disks;
   }

   public Map<String, Extension> getExtensions() {
      return m_extensions;
   }

   public long getHeapUsage() {
      return m_heapUsage;
   }

   public int getHttpThreadCount() {
      return m_httpThreadCount;
   }

   public long getMemoryFree() {
      return m_memoryFree;
   }

   public int getMinute() {
      return m_minute;
   }

   public long getNewGcCount() {
      return m_newGcCount;
   }

   public long getNoneHeapUsage() {
      return m_noneHeapUsage;
   }

   public long getOldGcCount() {
      return m_oldGcCount;
   }

   public int getPigeonThreadCount() {
      return m_pigeonThreadCount;
   }

   public double getSystemLoadAverage() {
      return m_systemLoadAverage;
   }

   public int getThreadCount() {
      return m_threadCount;
   }

   public int getTotalStartedCount() {
      return m_totalStartedCount;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + m_minute;

      return hash;
   }

   @Override
   public void mergeAttributes(Period other) {
      assertAttributeEquals(other, ENTITY_PERIOD, ATTR_MINUTE, m_minute, other.getMinute());

      for (Map.Entry<String, String> e : other.getDynamicAttributes().entrySet()) {
         m_dynamicAttributes.put(e.getKey(), e.getValue());
      }

      m_threadCount = other.getThreadCount();

      m_daemonCount = other.getDaemonCount();

      m_totalStartedCount = other.getTotalStartedCount();

      m_catThreadCount = other.getCatThreadCount();

      m_pigeonThreadCount = other.getPigeonThreadCount();

      m_httpThreadCount = other.getHttpThreadCount();

      m_newGcCount = other.getNewGcCount();

      m_oldGcCount = other.getOldGcCount();

      m_memoryFree = other.getMemoryFree();

      m_heapUsage = other.getHeapUsage();

      m_noneHeapUsage = other.getNoneHeapUsage();

      m_systemLoadAverage = other.getSystemLoadAverage();

      m_catMessageProduced = other.getCatMessageProduced();

      m_catMessageOverflow = other.getCatMessageOverflow();

      m_catMessageSize = other.getCatMessageSize();
   }

   public boolean removeDisk(String path) {
      int len = m_disks.size();

      for (int i = 0; i < len; i++) {
         Disk disk = m_disks.get(i);

         if (!disk.getPath().equals(path)) {
            continue;
         }

         m_disks.remove(i);
         return true;
      }

      return false;
   }

   public boolean removeExtension(String id) {
      if (m_extensions.containsKey(id)) {
         m_extensions.remove(id);
         return true;
      }

      return false;
   }

   public void setDynamicAttribute(String name, String value) {
      m_dynamicAttributes.put(name, value);
   }

   public Period setCatMessageOverflow(long catMessageOverflow) {
      m_catMessageOverflow = catMessageOverflow;
      return this;
   }

   public Period setCatMessageProduced(long catMessageProduced) {
      m_catMessageProduced = catMessageProduced;
      return this;
   }

   public Period setCatMessageSize(double catMessageSize) {
      m_catMessageSize = catMessageSize;
      return this;
   }

   public Period setCatThreadCount(int catThreadCount) {
      m_catThreadCount = catThreadCount;
      return this;
   }

   public Period setDaemonCount(int daemonCount) {
      m_daemonCount = daemonCount;
      return this;
   }

   public Period setHeapUsage(long heapUsage) {
      m_heapUsage = heapUsage;
      return this;
   }

   public Period setHttpThreadCount(int httpThreadCount) {
      m_httpThreadCount = httpThreadCount;
      return this;
   }

   public Period setMemoryFree(long memoryFree) {
      m_memoryFree = memoryFree;
      return this;
   }

   public Period setMinute(int minute) {
      m_minute = minute;
      return this;
   }

   public Period setNewGcCount(long newGcCount) {
      m_newGcCount = newGcCount;
      return this;
   }

   public Period setNoneHeapUsage(long noneHeapUsage) {
      m_noneHeapUsage = noneHeapUsage;
      return this;
   }

   public Period setOldGcCount(long oldGcCount) {
      m_oldGcCount = oldGcCount;
      return this;
   }

   public Period setPigeonThreadCount(int pigeonThreadCount) {
      m_pigeonThreadCount = pigeonThreadCount;
      return this;
   }

   public Period setSystemLoadAverage(double systemLoadAverage) {
      m_systemLoadAverage = systemLoadAverage;
      return this;
   }

   public Period setThreadCount(int threadCount) {
      m_threadCount = threadCount;
      return this;
   }

   public Period setTotalStartedCount(int totalStartedCount) {
      m_totalStartedCount = totalStartedCount;
      return this;
   }

}
