package com.dianping.cat.consumer.event.model.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.consumer.event.model.IVisitor;
import com.dianping.cat.consumer.event.model.entity.EventName;
import com.dianping.cat.consumer.event.model.entity.EventReport;
import com.dianping.cat.consumer.event.model.entity.EventType;
import com.dianping.cat.consumer.event.model.entity.Machine;
import com.dianping.cat.consumer.event.model.entity.Range;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(EventReport eventReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(eventReport, out);
      return out.toByteArray();
   }

   public static void build(EventReport eventReport, OutputStream out) {
      eventReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitEventReport(EventReport eventReport) {
      writeTag(63, 0);

      if (eventReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(eventReport.getDomain());
      }

      if (eventReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(eventReport.getStartTime());
      }

      if (eventReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(eventReport.getEndTime());
      }

      if (!eventReport.getDomainNames().isEmpty()) {
         writeTag(4, 2);
         writeInt(eventReport.getDomainNames().size());

         for (String domain : eventReport.getDomainNames()) {
            writeString(domain);
         }
      }

      if (!eventReport.getIps().isEmpty()) {
         writeTag(5, 2);
         writeInt(eventReport.getIps().size());

         for (String ip : eventReport.getIps()) {
            writeString(ip);
         }
      }

      if (!eventReport.getMachines().isEmpty()) {
         writeTag(33, 2);
         writeInt(eventReport.getMachines().size());

         for (Machine machine : eventReport.getMachines().values()) {
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

      if (!machine.getTypes().isEmpty()) {
         writeTag(33, 2);
         writeInt(machine.getTypes().size());

         for (EventType type : machine.getTypes().values()) {
            type.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitName(EventName name) {
      if (name.getId() != null) {
         writeTag(1, 1);
         writeString(name.getId());
      }

      writeTag(2, 0);
      writeLong(name.getTotalCount());

      writeTag(3, 0);
      writeLong(name.getFailCount());

      writeTag(4, 0);
      writeDouble(name.getFailPercent());

      if (name.getSuccessMessageUrl() != null) {
         writeTag(5, 1);
         writeString(name.getSuccessMessageUrl());
      }

      if (name.getFailMessageUrl() != null) {
         writeTag(6, 1);
         writeString(name.getFailMessageUrl());
      }

      writeTag(7, 0);
      writeDouble(name.getTps());

      if (!name.getRanges().isEmpty()) {
         writeTag(33, 2);
         writeInt(name.getRanges().size());

         for (Range range : name.getRanges().values()) {
            range.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitRange(Range range) {
      if (range.getValue() != null) {
         writeTag(1, 1);
         writeInt(range.getValue());
      }

      writeTag(2, 0);
      writeInt(range.getCount());

      writeTag(3, 0);
      writeInt(range.getFails());

      writeTag(63, 3);
   }

   @Override
   public void visitType(EventType type) {
      if (type.getId() != null) {
         writeTag(1, 1);
         writeString(type.getId());
      }

      writeTag(2, 0);
      writeLong(type.getTotalCount());

      writeTag(3, 0);
      writeLong(type.getFailCount());

      writeTag(4, 0);
      writeDouble(type.getFailPercent());

      if (type.getSuccessMessageUrl() != null) {
         writeTag(5, 1);
         writeString(type.getSuccessMessageUrl());
      }

      if (type.getFailMessageUrl() != null) {
         writeTag(6, 1);
         writeString(type.getFailMessageUrl());
      }

      writeTag(7, 0);
      writeDouble(type.getTps());

      if (!type.getNames().isEmpty()) {
         writeTag(33, 2);
         writeInt(type.getNames().size());

         for (EventName name : type.getNames().values()) {
            name.accept(m_visitor);
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
