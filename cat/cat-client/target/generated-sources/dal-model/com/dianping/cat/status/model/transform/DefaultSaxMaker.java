package com.dianping.cat.status.model.transform;

import static com.dianping.cat.status.model.Constants.ATTR_ARCH;
import static com.dianping.cat.status.model.Constants.ATTR_AVAILABLE_PROCESSORS;
import static com.dianping.cat.status.model.Constants.ATTR_BYTES;
import static com.dianping.cat.status.model.Constants.ATTR_CAT_THREAD_COUNT;
import static com.dianping.cat.status.model.Constants.ATTR_COMMITTED_VIRTUAL_MEMORY;
import static com.dianping.cat.status.model.Constants.ATTR_COUNT;
import static com.dianping.cat.status.model.Constants.ATTR_DAEMON_COUNT;
import static com.dianping.cat.status.model.Constants.ATTR_FREE;
import static com.dianping.cat.status.model.Constants.ATTR_FREE_PHYSICAL_MEMORY;
import static com.dianping.cat.status.model.Constants.ATTR_FREE_SWAP_SPACE;
import static com.dianping.cat.status.model.Constants.ATTR_HEAP_USAGE;
import static com.dianping.cat.status.model.Constants.ATTR_HTTP_THREAD_COUNT;
import static com.dianping.cat.status.model.Constants.ATTR_ID;
import static com.dianping.cat.status.model.Constants.ATTR_JAVA_VERSION;
import static com.dianping.cat.status.model.Constants.ATTR_MAX;
import static com.dianping.cat.status.model.Constants.ATTR_NAME;
import static com.dianping.cat.status.model.Constants.ATTR_NON_HEAP_USAGE;
import static com.dianping.cat.status.model.Constants.ATTR_OVERFLOWED;
import static com.dianping.cat.status.model.Constants.ATTR_PEEK_COUNT;
import static com.dianping.cat.status.model.Constants.ATTR_PIGEON_THREAD_COUNT;
import static com.dianping.cat.status.model.Constants.ATTR_PROCESS_TIME;
import static com.dianping.cat.status.model.Constants.ATTR_PRODUCED;
import static com.dianping.cat.status.model.Constants.ATTR_START_TIME;
import static com.dianping.cat.status.model.Constants.ATTR_SYSTEM_LOAD_AVERAGE;
import static com.dianping.cat.status.model.Constants.ATTR_TIME;
import static com.dianping.cat.status.model.Constants.ATTR_TIMESTAMP;
import static com.dianping.cat.status.model.Constants.ATTR_TOTAL;
import static com.dianping.cat.status.model.Constants.ATTR_TOTAL_PHYSICAL_MEMORY;
import static com.dianping.cat.status.model.Constants.ATTR_TOTAL_STARTED_COUNT;
import static com.dianping.cat.status.model.Constants.ATTR_TOTAL_SWAP_SPACE;
import static com.dianping.cat.status.model.Constants.ATTR_UP_TIME;
import static com.dianping.cat.status.model.Constants.ATTR_USABLE;
import static com.dianping.cat.status.model.Constants.ATTR_USER_NAME;
import static com.dianping.cat.status.model.Constants.ATTR_VALUE;
import static com.dianping.cat.status.model.Constants.ATTR_VERSION;

import java.util.Map;
import org.xml.sax.Attributes;

import com.dianping.cat.status.model.entity.DiskInfo;
import com.dianping.cat.status.model.entity.DiskVolumeInfo;
import com.dianping.cat.status.model.entity.Extension;
import com.dianping.cat.status.model.entity.ExtensionDetail;
import com.dianping.cat.status.model.entity.GcInfo;
import com.dianping.cat.status.model.entity.MemoryInfo;
import com.dianping.cat.status.model.entity.MessageInfo;
import com.dianping.cat.status.model.entity.OsInfo;
import com.dianping.cat.status.model.entity.RuntimeInfo;
import com.dianping.cat.status.model.entity.StatusInfo;
import com.dianping.cat.status.model.entity.ThreadsInfo;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public DiskInfo buildDisk(Attributes attributes) {
      DiskInfo disk = new DiskInfo();

      return disk;
   }

   @Override
   public DiskVolumeInfo buildDiskVolume(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String total = attributes.getValue(ATTR_TOTAL);
      String free = attributes.getValue(ATTR_FREE);
      String usable = attributes.getValue(ATTR_USABLE);
      DiskVolumeInfo diskVolume = new DiskVolumeInfo(id);

      if (total != null) {
         diskVolume.setTotal(convert(Long.class, total, 0L));
      }

      if (free != null) {
         diskVolume.setFree(convert(Long.class, free, 0L));
      }

      if (usable != null) {
         diskVolume.setUsable(convert(Long.class, usable, 0L));
      }

      return diskVolume;
   }

   @Override
   public Extension buildExtension(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      Extension extension = new Extension(id);

      Map<String, String> dynamicAttributes = extension.getDynamicAttributes();
      int _length = attributes == null ? 0 : attributes.getLength();

      for (int i = 0; i < _length; i++) {
         String _name = attributes.getQName(i);
         String _value = attributes.getValue(i);

         dynamicAttributes.put(_name, _value);
      }

      dynamicAttributes.remove(ATTR_ID);

      return extension;
   }

   @Override
   public ExtensionDetail buildExtensionDetail(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String value = attributes.getValue(ATTR_VALUE);
      ExtensionDetail extensionDetail = new ExtensionDetail(id);

      if (value != null) {
         extensionDetail.setValue(convert(Double.class, value, 0.0));
      }

      Map<String, String> dynamicAttributes = extensionDetail.getDynamicAttributes();
      int _length = attributes == null ? 0 : attributes.getLength();

      for (int i = 0; i < _length; i++) {
         String _name = attributes.getQName(i);
         String _value = attributes.getValue(i);

         dynamicAttributes.put(_name, _value);
      }

      dynamicAttributes.remove(ATTR_ID);
      dynamicAttributes.remove(ATTR_VALUE);

      return extensionDetail;
   }

   @Override
   public GcInfo buildGc(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      String count = attributes.getValue(ATTR_COUNT);
      String time = attributes.getValue(ATTR_TIME);
      GcInfo gc = new GcInfo();

      if (name != null) {
         gc.setName(name);
      }

      if (count != null) {
         gc.setCount(convert(Long.class, count, 0L));
      }

      if (time != null) {
         gc.setTime(convert(Long.class, time, 0L));
      }

      return gc;
   }

   @Override
   public MemoryInfo buildMemory(Attributes attributes) {
      String max = attributes.getValue(ATTR_MAX);
      String total = attributes.getValue(ATTR_TOTAL);
      String free = attributes.getValue(ATTR_FREE);
      String heapUsage = attributes.getValue(ATTR_HEAP_USAGE);
      String nonHeapUsage = attributes.getValue(ATTR_NON_HEAP_USAGE);
      MemoryInfo memory = new MemoryInfo();

      if (max != null) {
         memory.setMax(convert(Long.class, max, 0L));
      }

      if (total != null) {
         memory.setTotal(convert(Long.class, total, 0L));
      }

      if (free != null) {
         memory.setFree(convert(Long.class, free, 0L));
      }

      if (heapUsage != null) {
         memory.setHeapUsage(convert(Long.class, heapUsage, 0L));
      }

      if (nonHeapUsage != null) {
         memory.setNonHeapUsage(convert(Long.class, nonHeapUsage, 0L));
      }

      return memory;
   }

   @Override
   public MessageInfo buildMessage(Attributes attributes) {
      String produced = attributes.getValue(ATTR_PRODUCED);
      String overflowed = attributes.getValue(ATTR_OVERFLOWED);
      String bytes = attributes.getValue(ATTR_BYTES);
      MessageInfo message = new MessageInfo();

      if (produced != null) {
         message.setProduced(convert(Long.class, produced, 0L));
      }

      if (overflowed != null) {
         message.setOverflowed(convert(Long.class, overflowed, 0L));
      }

      if (bytes != null) {
         message.setBytes(convert(Long.class, bytes, 0L));
      }

      return message;
   }

   @Override
   public OsInfo buildOs(Attributes attributes) {
      String name = attributes.getValue(ATTR_NAME);
      String arch = attributes.getValue(ATTR_ARCH);
      String version = attributes.getValue(ATTR_VERSION);
      String availableProcessors = attributes.getValue(ATTR_AVAILABLE_PROCESSORS);
      String systemLoadAverage = attributes.getValue(ATTR_SYSTEM_LOAD_AVERAGE);
      String processTime = attributes.getValue(ATTR_PROCESS_TIME);
      String totalPhysicalMemory = attributes.getValue(ATTR_TOTAL_PHYSICAL_MEMORY);
      String freePhysicalMemory = attributes.getValue(ATTR_FREE_PHYSICAL_MEMORY);
      String committedVirtualMemory = attributes.getValue(ATTR_COMMITTED_VIRTUAL_MEMORY);
      String totalSwapSpace = attributes.getValue(ATTR_TOTAL_SWAP_SPACE);
      String freeSwapSpace = attributes.getValue(ATTR_FREE_SWAP_SPACE);
      OsInfo os = new OsInfo();

      if (name != null) {
         os.setName(name);
      }

      if (arch != null) {
         os.setArch(arch);
      }

      if (version != null) {
         os.setVersion(version);
      }

      if (availableProcessors != null) {
         os.setAvailableProcessors(convert(Integer.class, availableProcessors, 0));
      }

      if (systemLoadAverage != null) {
         os.setSystemLoadAverage(convert(Double.class, systemLoadAverage, 0.0));
      }

      if (processTime != null) {
         os.setProcessTime(convert(Long.class, processTime, 0L));
      }

      if (totalPhysicalMemory != null) {
         os.setTotalPhysicalMemory(convert(Long.class, totalPhysicalMemory, 0L));
      }

      if (freePhysicalMemory != null) {
         os.setFreePhysicalMemory(convert(Long.class, freePhysicalMemory, 0L));
      }

      if (committedVirtualMemory != null) {
         os.setCommittedVirtualMemory(convert(Long.class, committedVirtualMemory, 0L));
      }

      if (totalSwapSpace != null) {
         os.setTotalSwapSpace(convert(Long.class, totalSwapSpace, 0L));
      }

      if (freeSwapSpace != null) {
         os.setFreeSwapSpace(convert(Long.class, freeSwapSpace, 0L));
      }

      return os;
   }

   @Override
   public RuntimeInfo buildRuntime(Attributes attributes) {
      String startTime = attributes.getValue(ATTR_START_TIME);
      String upTime = attributes.getValue(ATTR_UP_TIME);
      String javaVersion = attributes.getValue(ATTR_JAVA_VERSION);
      String userName = attributes.getValue(ATTR_USER_NAME);
      RuntimeInfo runtime = new RuntimeInfo();

      if (startTime != null) {
         runtime.setStartTime(convert(Long.class, startTime, 0L));
      }

      if (upTime != null) {
         runtime.setUpTime(convert(Long.class, upTime, 0L));
      }

      if (javaVersion != null) {
         runtime.setJavaVersion(javaVersion);
      }

      if (userName != null) {
         runtime.setUserName(userName);
      }

      return runtime;
   }

   @Override
   public StatusInfo buildStatus(Attributes attributes) {
      String timestamp = attributes.getValue(ATTR_TIMESTAMP);
      StatusInfo status = new StatusInfo();

      if (timestamp != null) {
         status.setTimestamp(toDate(timestamp, "yyyy-MM-dd HH:mm:ss.SSS", null));
      }

      return status;
   }

   @Override
   public ThreadsInfo buildThread(Attributes attributes) {
      String count = attributes.getValue(ATTR_COUNT);
      String daemonCount = attributes.getValue(ATTR_DAEMON_COUNT);
      String peekCount = attributes.getValue(ATTR_PEEK_COUNT);
      String totalStartedCount = attributes.getValue(ATTR_TOTAL_STARTED_COUNT);
      String catThreadCount = attributes.getValue(ATTR_CAT_THREAD_COUNT);
      String pigeonThreadCount = attributes.getValue(ATTR_PIGEON_THREAD_COUNT);
      String httpThreadCount = attributes.getValue(ATTR_HTTP_THREAD_COUNT);
      ThreadsInfo thread = new ThreadsInfo();

      if (count != null) {
         thread.setCount(convert(Integer.class, count, 0));
      }

      if (daemonCount != null) {
         thread.setDaemonCount(convert(Integer.class, daemonCount, 0));
      }

      if (peekCount != null) {
         thread.setPeekCount(convert(Integer.class, peekCount, 0));
      }

      if (totalStartedCount != null) {
         thread.setTotalStartedCount(convert(Integer.class, totalStartedCount, 0));
      }

      if (catThreadCount != null) {
         thread.setCatThreadCount(convert(Integer.class, catThreadCount, 0));
      }

      if (pigeonThreadCount != null) {
         thread.setPigeonThreadCount(convert(Integer.class, pigeonThreadCount, 0));
      }

      if (httpThreadCount != null) {
         thread.setHttpThreadCount(convert(Integer.class, httpThreadCount, 0));
      }

      return thread;
   }

   @SuppressWarnings("unchecked")
   protected <T> T convert(Class<T> type, String value, T defaultValue) {
      if (value == null) {
         return defaultValue;
      }

      if (type == Boolean.class) {
         return (T) Boolean.valueOf(value);
      } else if (type == Integer.class) {
         return (T) Integer.valueOf(value);
      } else if (type == Long.class) {
         return (T) Long.valueOf(value);
      } else if (type == Short.class) {
         return (T) Short.valueOf(value);
      } else if (type == Float.class) {
         return (T) Float.valueOf(value);
      } else if (type == Double.class) {
         return (T) Double.valueOf(value);
      } else if (type == Byte.class) {
         return (T) Byte.valueOf(value);
      } else if (type == Character.class) {
         return (T) (Character) value.charAt(0);
      } else {
         return (T) value;
      }
   }

   protected java.util.Date toDate(String str, String format, java.util.Date defaultValue) {
      if (str == null || str.length() == 0) {
         return defaultValue;
      }

      try {
         return new java.text.SimpleDateFormat(format).parse(str);
      } catch (java.text.ParseException e) {
         throw new RuntimeException(String.format("Unable to parse date(%s) in format(%s)!", str, format), e);
      }
   }
}
