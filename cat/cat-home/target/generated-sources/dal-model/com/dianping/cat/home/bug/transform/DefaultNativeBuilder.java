package com.dianping.cat.home.bug.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.bug.IVisitor;
import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(BugReport bugReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(bugReport, out);
      return out.toByteArray();
   }

   public static void build(BugReport bugReport, OutputStream out) {
      bugReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitBugReport(BugReport bugReport) {
      writeTag(63, 0);

      if (bugReport.getStartTime() != null) {
         writeTag(1, 1);
         writeDate(bugReport.getStartTime());
      }

      if (bugReport.getDomain() != null) {
         writeTag(2, 1);
         writeString(bugReport.getDomain());
      }

      if (bugReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(bugReport.getEndTime());
      }

      if (!bugReport.getDomains().isEmpty()) {
         writeTag(33, 2);
         writeInt(bugReport.getDomains().size());

         for (Domain domain : bugReport.getDomains().values()) {
            domain.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitDomain(Domain domain) {
      if (domain.getId() != null) {
         writeTag(1, 1);
         writeString(domain.getId());
      }

      if (domain.getProblemUrl() != null) {
         writeTag(2, 1);
         writeString(domain.getProblemUrl());
      }

      if (domain.getDepartment() != null) {
         writeTag(3, 1);
         writeString(domain.getDepartment());
      }

      if (domain.getProductLine() != null) {
         writeTag(4, 1);
         writeString(domain.getProductLine());
      }

      if (domain.getExcpetion() != null) {
         writeTag(5, 1);
         writeString(domain.getExcpetion());
      }

      if (!domain.getExceptionItems().isEmpty()) {
         writeTag(33, 2);
         writeInt(domain.getExceptionItems().size());

         for (ExceptionItem exceptionItem : domain.getExceptionItems().values()) {
            exceptionItem.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitExceptionItem(ExceptionItem exceptionItem) {
      if (exceptionItem.getId() != null) {
         writeTag(1, 1);
         writeString(exceptionItem.getId());
      }

      writeTag(2, 0);
      writeInt(exceptionItem.getCount());

      if (!exceptionItem.getMessages().isEmpty()) {
         writeTag(3, 2);
         writeInt(exceptionItem.getMessages().size());

         for (String message : exceptionItem.getMessages()) {
            writeString(message);
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

   private void writeInt(int value) {
      try {
         writeVarint(value & 0xFFFFFFFFL);
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
