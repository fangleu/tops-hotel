package com.dianping.cat.home.app.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.app.IVisitor;
import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(AppReport appReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(appReport, out);
      return out.toByteArray();
   }

   public static void build(AppReport appReport, OutputStream out) {
      appReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitAppReport(AppReport appReport) {
      writeTag(63, 0);

      if (appReport.getId() != null) {
         writeTag(1, 1);
         writeString(appReport.getId());
      }

      if (appReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(appReport.getStartTime());
      }

      if (appReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(appReport.getEndTime());
      }

      if (!appReport.getCommands().isEmpty()) {
         writeTag(33, 2);
         writeInt(appReport.getCommands().size());

         for (Command command : appReport.getCommands().values()) {
            command.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitCode(Code code) {
      if (code.getId() != null) {
         writeTag(1, 1);
         writeString(code.getId());
      }

      writeTag(2, 0);
      writeLong(code.getCount());

      writeTag(3, 0);
      writeDouble(code.getSum());

      writeTag(4, 0);
      writeDouble(code.getAvg());

      writeTag(5, 0);
      writeLong(code.getErrors());

      writeTag(6, 0);
      writeDouble(code.getSuccessRatio());

      writeTag(63, 3);
   }

   @Override
   public void visitCommand(Command command) {
      writeTag(1, 0);
      writeInt(command.getId());

      if (command.getName() != null) {
         writeTag(2, 1);
         writeString(command.getName());
      }

      writeTag(3, 0);
      writeLong(command.getCount());

      writeTag(4, 0);
      writeDouble(command.getSum());

      writeTag(5, 0);
      writeDouble(command.getAvg());

      writeTag(6, 0);
      writeLong(command.getErrors());

      writeTag(7, 0);
      writeDouble(command.getSuccessRatio());

      writeTag(8, 0);
      writeLong(command.getRequestSum());

      writeTag(9, 0);
      writeDouble(command.getRequestAvg());

      writeTag(10, 0);
      writeLong(command.getResponseSum());

      writeTag(11, 0);
      writeDouble(command.getResponseAvg());

      if (command.getTransaction() != null) {
         writeTag(33, 1);
         command.getTransaction().accept(m_visitor);
      }

      if (!command.getCodes().isEmpty()) {
         writeTag(34, 2);
         writeInt(command.getCodes().size());

         for (Code code : command.getCodes().values()) {
            code.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitTransaction(Transaction transaction) {
      if (transaction.getUrl() != null) {
         writeTag(1, 1);
         writeString(transaction.getUrl());
      }

      writeTag(2, 0);
      writeLong(transaction.getCount());

      writeTag(3, 0);
      writeDouble(transaction.getAvg());

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
