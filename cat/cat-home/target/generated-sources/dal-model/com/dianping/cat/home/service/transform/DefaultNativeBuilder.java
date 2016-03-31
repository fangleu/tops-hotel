package com.dianping.cat.home.service.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.service.IVisitor;
import com.dianping.cat.home.service.entity.Domain;
import com.dianping.cat.home.service.entity.ServiceReport;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(ServiceReport serviceReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(serviceReport, out);
      return out.toByteArray();
   }

   public static void build(ServiceReport serviceReport, OutputStream out) {
      serviceReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDomain(Domain domain) {
      if (domain.getId() != null) {
         writeTag(1, 1);
         writeString(domain.getId());
      }

      writeTag(2, 0);
      writeLong(domain.getTotalCount());

      writeTag(3, 0);
      writeLong(domain.getFailureCount());

      writeTag(4, 0);
      writeDouble(domain.getFailurePercent());

      writeTag(5, 0);
      writeDouble(domain.getSum());

      writeTag(6, 0);
      writeDouble(domain.getAvg());

      writeTag(7, 0);
      writeDouble(domain.getQps());

      writeTag(63, 3);
   }

   @Override
   public void visitServiceReport(ServiceReport serviceReport) {
      writeTag(63, 0);

      if (serviceReport.getStartTime() != null) {
         writeTag(1, 1);
         writeDate(serviceReport.getStartTime());
      }

      if (serviceReport.getDomain() != null) {
         writeTag(2, 1);
         writeString(serviceReport.getDomain());
      }

      if (serviceReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(serviceReport.getEndTime());
      }

      if (!serviceReport.getDomains().isEmpty()) {
         writeTag(33, 2);
         writeInt(serviceReport.getDomains().size());

         for (Domain domain : serviceReport.getDomains().values()) {
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
