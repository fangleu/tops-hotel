package com.dianping.cat.consumer.heartbeat.model.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Map;

import com.dianping.cat.consumer.heartbeat.model.IVisitor;
import com.dianping.cat.consumer.heartbeat.model.entity.Detail;
import com.dianping.cat.consumer.heartbeat.model.entity.Disk;
import com.dianping.cat.consumer.heartbeat.model.entity.Extension;
import com.dianping.cat.consumer.heartbeat.model.entity.HeartbeatReport;
import com.dianping.cat.consumer.heartbeat.model.entity.Machine;
import com.dianping.cat.consumer.heartbeat.model.entity.Period;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(HeartbeatReport heartbeatReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(heartbeatReport, out);
      return out.toByteArray();
   }

   public static void build(HeartbeatReport heartbeatReport, OutputStream out) {
      heartbeatReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDetail(Detail detail) {
      if (detail.getId() != null) {
         writeTag(1, 1);
         writeString(detail.getId());
      }

      writeTag(2, 0);
      writeDouble(detail.getValue());

      writeTag(63, 3);
   }

   @Override
   public void visitDisk(Disk disk) {
      if (disk.getPath() != null) {
         writeTag(1, 1);
         writeString(disk.getPath());
      }

      writeTag(2, 0);
      writeLong(disk.getTotal());

      writeTag(3, 0);
      writeLong(disk.getFree());

      writeTag(4, 0);
      writeLong(disk.getUsable());

      writeTag(63, 3);
   }

   @Override
   public void visitExtension(Extension extension) {
      if (extension.getId() != null) {
         writeTag(1, 1);
         writeString(extension.getId());
      }

      if (!extension.getDetails().isEmpty()) {
         writeTag(33, 2);
         writeInt(extension.getDetails().size());

         for (Detail detail : extension.getDetails().values()) {
            detail.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitHeartbeatReport(HeartbeatReport heartbeatReport) {
      writeTag(63, 0);

      if (heartbeatReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(heartbeatReport.getDomain());
      }

      if (heartbeatReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(heartbeatReport.getStartTime());
      }

      if (heartbeatReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(heartbeatReport.getEndTime());
      }

      if (!heartbeatReport.getDomainNames().isEmpty()) {
         writeTag(4, 2);
         writeInt(heartbeatReport.getDomainNames().size());

         for (String domain : heartbeatReport.getDomainNames()) {
            writeString(domain);
         }
      }

      if (!heartbeatReport.getIps().isEmpty()) {
         writeTag(5, 2);
         writeInt(heartbeatReport.getIps().size());

         for (String ip : heartbeatReport.getIps()) {
            writeString(ip);
         }
      }

      if (!heartbeatReport.getMachines().isEmpty()) {
         writeTag(33, 2);
         writeInt(heartbeatReport.getMachines().size());

         for (Machine machine : heartbeatReport.getMachines().values()) {
            machine.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitMachine(Machine machine) {
      if (machine.getIp() != null) {
         writeTag(1, 1);
         writeString(machine.getIp());
      }

      if (machine.getClasspath() != null) {
         writeTag(2, 1);
         writeString(machine.getClasspath());
      }

      if (!machine.getPeriods().isEmpty()) {
         writeTag(33, 2);
         writeInt(machine.getPeriods().size());

         for (Period period : machine.getPeriods()) {
            period.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitPeriod(Period period) {
      writeTag(1, 0);
      writeInt(period.getMinute());

      writeTag(2, 0);
      writeInt(period.getThreadCount());

      writeTag(3, 0);
      writeInt(period.getDaemonCount());

      writeTag(4, 0);
      writeInt(period.getTotalStartedCount());

      writeTag(5, 0);
      writeInt(period.getCatThreadCount());

      writeTag(6, 0);
      writeInt(period.getPigeonThreadCount());

      writeTag(7, 0);
      writeInt(period.getHttpThreadCount());

      writeTag(8, 0);
      writeLong(period.getNewGcCount());

      writeTag(9, 0);
      writeLong(period.getOldGcCount());

      writeTag(10, 0);
      writeLong(period.getMemoryFree());

      writeTag(11, 0);
      writeLong(period.getHeapUsage());

      writeTag(12, 0);
      writeLong(period.getNoneHeapUsage());

      writeTag(13, 0);
      writeDouble(period.getSystemLoadAverage());

      writeTag(14, 0);
      writeLong(period.getCatMessageProduced());

      writeTag(15, 0);
      writeLong(period.getCatMessageOverflow());

      writeTag(16, 0);
      writeDouble(period.getCatMessageSize());

      if (!period.getDisks().isEmpty()) {
         writeTag(33, 2);
         writeInt(period.getDisks().size());

         for (Disk disk : period.getDisks()) {
            disk.accept(m_visitor);
         }
      }

      if (!period.getExtensions().isEmpty()) {
         writeTag(34, 2);
         writeInt(period.getExtensions().size());

         for (Extension extension : period.getExtensions().values()) {
            extension.accept(m_visitor);
         }
      }

      if (!period.getDynamicAttributes().isEmpty()) {
         writeTag(63, 2);
         writeInt(period.getDynamicAttributes().size());

         for (Map.Entry<String, String> dynamicAttribute : period.getDynamicAttributes().entrySet()) {
            writeString(dynamicAttribute.getKey());
            writeString(dynamicAttribute.getValue());
         }
      }

      writeTag(63, 3);
   }

   private void writeDate(java.util.Date value) {
      try {
         writeVarint(value.getTime());
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeDouble(double value) {
      try {
         m_out.writeDouble(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeInt(int value) {
      try {
         writeVarint(value & 0xFFFFFFFFL);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeLong(long value) {
      try {
         writeVarint(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeString(String value) {
      try {
         m_out.writeUTF(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeTag(int field, int type) {
      try {
         m_out.writeByte((field << 2) + type);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected void writeVarint(long value) throws IOException {
      while (true) {
         if ((value & ~0x7FL) == 0) {
            m_out.writeByte((byte) value);
            return;
         } else {
            m_out.writeByte(((byte) value & 0x7F) | 0x80);
            value >>>= 7;
         }
      }
   }
}
