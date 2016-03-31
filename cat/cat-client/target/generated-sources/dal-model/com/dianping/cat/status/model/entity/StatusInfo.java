package com.dianping.cat.status.model.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.status.model.BaseEntity;
import com.dianping.cat.status.model.IVisitor;

public class StatusInfo extends BaseEntity<StatusInfo> {
   private java.util.Date m_timestamp;

   private RuntimeInfo m_runtime;

   private OsInfo m_os;

   private DiskInfo m_disk;

   private MemoryInfo m_memory;

   private ThreadsInfo m_thread;

   private MessageInfo m_message;

   private Map<String, Extension> m_extensions = new LinkedHashMap<String, Extension>();

   public StatusInfo() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitStatus(this);
   }

   public StatusInfo addExtension(Extension extension) {
      m_extensions.put(extension.getId(), extension);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof StatusInfo) {
         StatusInfo _o = (StatusInfo) obj;
         java.util.Date timestamp = _o.getTimestamp();
         RuntimeInfo runtime = _o.getRuntime();
         OsInfo os = _o.getOs();
         DiskInfo disk = _o.getDisk();
         MemoryInfo memory = _o.getMemory();
         ThreadsInfo thread = _o.getThread();
         MessageInfo message = _o.getMessage();
         Map<String, Extension> extensions = _o.getExtensions();
         boolean result = true;

         result &= (m_timestamp == timestamp || m_timestamp != null && m_timestamp.equals(timestamp));
         result &= (m_runtime == runtime || m_runtime != null && m_runtime.equals(runtime));
         result &= (m_os == os || m_os != null && m_os.equals(os));
         result &= (m_disk == disk || m_disk != null && m_disk.equals(disk));
         result &= (m_memory == memory || m_memory != null && m_memory.equals(memory));
         result &= (m_thread == thread || m_thread != null && m_thread.equals(thread));
         result &= (m_message == message || m_message != null && m_message.equals(message));
         result &= (m_extensions == extensions || m_extensions != null && m_extensions.equals(extensions));

         return result;
      }

      return false;
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

   public DiskInfo getDisk() {
      return m_disk;
   }

   public Map<String, Extension> getExtensions() {
      return m_extensions;
   }

   public MemoryInfo getMemory() {
      return m_memory;
   }

   public MessageInfo getMessage() {
      return m_message;
   }

   public OsInfo getOs() {
      return m_os;
   }

   public RuntimeInfo getRuntime() {
      return m_runtime;
   }

   public ThreadsInfo getThread() {
      return m_thread;
   }

   public java.util.Date getTimestamp() {
      return m_timestamp;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_timestamp == null ? 0 : m_timestamp.hashCode());
      hash = hash * 31 + (m_runtime == null ? 0 : m_runtime.hashCode());
      hash = hash * 31 + (m_os == null ? 0 : m_os.hashCode());
      hash = hash * 31 + (m_disk == null ? 0 : m_disk.hashCode());
      hash = hash * 31 + (m_memory == null ? 0 : m_memory.hashCode());
      hash = hash * 31 + (m_thread == null ? 0 : m_thread.hashCode());
      hash = hash * 31 + (m_message == null ? 0 : m_message.hashCode());
      hash = hash * 31 + (m_extensions == null ? 0 : m_extensions.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(StatusInfo other) {
      if (other.getTimestamp() != null) {
         m_timestamp = other.getTimestamp();
      }
   }

   public boolean removeExtension(String id) {
      if (m_extensions.containsKey(id)) {
         m_extensions.remove(id);
         return true;
      }

      return false;
   }

   public StatusInfo setDisk(DiskInfo disk) {
      m_disk = disk;
      return this;
   }

   public StatusInfo setMemory(MemoryInfo memory) {
      m_memory = memory;
      return this;
   }

   public StatusInfo setMessage(MessageInfo message) {
      m_message = message;
      return this;
   }

   public StatusInfo setOs(OsInfo os) {
      m_os = os;
      return this;
   }

   public StatusInfo setRuntime(RuntimeInfo runtime) {
      m_runtime = runtime;
      return this;
   }

   public StatusInfo setThread(ThreadsInfo thread) {
      m_thread = thread;
      return this;
   }

   public StatusInfo setTimestamp(java.util.Date timestamp) {
      m_timestamp = timestamp;
      return this;
   }

}
