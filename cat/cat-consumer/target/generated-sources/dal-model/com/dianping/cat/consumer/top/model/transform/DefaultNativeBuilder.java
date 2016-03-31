package com.dianping.cat.consumer.top.model.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.consumer.top.model.IVisitor;
import com.dianping.cat.consumer.top.model.entity.Domain;
import com.dianping.cat.consumer.top.model.entity.Error;
import com.dianping.cat.consumer.top.model.entity.Machine;
import com.dianping.cat.consumer.top.model.entity.Segment;
import com.dianping.cat.consumer.top.model.entity.TopReport;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(TopReport topReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(topReport, out);
      return out.toByteArray();
   }

   public static void build(TopReport topReport, OutputStream out) {
      topReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDomain(Domain domain) {
      if (domain.getName() != null) {
         writeTag(1, 1);
         writeString(domain.getName());
      }

      if (!domain.getSegments().isEmpty()) {
         writeTag(33, 2);
         writeInt(domain.getSegments().size());

         for (Segment segment : domain.getSegments().values()) {
            segment.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitError(Error error) {
      if (error.getId() != null) {
         writeTag(1, 1);
         writeString(error.getId());
      }

      writeTag(2, 0);
      writeInt(error.getCount());

      writeTag(63, 3);
   }

   @Override
   public void visitMachine(Machine machine) {
      if (machine.getId() != null) {
         writeTag(1, 1);
         writeString(machine.getId());
      }

      writeTag(2, 0);
      writeInt(machine.getCount());

      writeTag(63, 3);
   }

   @Override
   public void visitSegment(Segment segment) {
      if (segment.getId() != null) {
         writeTag(1, 1);
         writeInt(segment.getId());
      }

      writeTag(2, 0);
      writeLong(segment.getError());

      writeTag(3, 0);
      writeLong(segment.getUrl());

      writeTag(4, 0);
      writeDouble(segment.getUrlDuration());

      writeTag(5, 0);
      writeLong(segment.getService());

      writeTag(6, 0);
      writeDouble(segment.getServiceDuration());

      writeTag(7, 0);
      writeLong(segment.getSql());

      writeTag(8, 0);
      writeDouble(segment.getSqlDuration());

      writeTag(9, 0);
      writeLong(segment.getCall());

      writeTag(10, 0);
      writeDouble(segment.getCallDuration());

      writeTag(11, 0);
      writeLong(segment.getCache());

      writeTag(12, 0);
      writeDouble(segment.getCacheDuration());

      writeTag(13, 0);
      writeLong(segment.getUrlError());

      writeTag(14, 0);
      writeDouble(segment.getUrlSum());

      writeTag(15, 0);
      writeLong(segment.getServiceError());

      writeTag(16, 0);
      writeDouble(segment.getServiceSum());

      writeTag(17, 0);
      writeLong(segment.getSqlError());

      writeTag(18, 0);
      writeDouble(segment.getSqlSum());

      writeTag(19, 0);
      writeLong(segment.getCallError());

      writeTag(20, 0);
      writeDouble(segment.getCallSum());

      writeTag(21, 0);
      writeLong(segment.getCacheError());

      writeTag(22, 0);
      writeDouble(segment.getCacheSum());

      if (!segment.getErrors().isEmpty()) {
         writeTag(33, 2);
         writeInt(segment.getErrors().size());

         for (Error error : segment.getErrors().values()) {
            error.accept(m_visitor);
         }
      }

      if (!segment.getMachines().isEmpty()) {
         writeTag(34, 2);
         writeInt(segment.getMachines().size());

         for (Machine machine : segment.getMachines().values()) {
            machine.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitTopReport(TopReport topReport) {
      writeTag(63, 0);

      if (topReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(topReport.getDomain());
      }

      if (topReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(topReport.getStartTime());
      }

      if (topReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(topReport.getEndTime());
      }

      if (!topReport.getDomains().isEmpty()) {
         writeTag(33, 2);
         writeInt(topReport.getDomains().size());

         for (Domain domain : topReport.getDomains().values()) {
            domain.accept(m_visitor);
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
