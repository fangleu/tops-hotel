package com.dianping.cat.consumer.storage.model.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.consumer.storage.model.IVisitor;
import com.dianping.cat.consumer.storage.model.entity.Domain;
import com.dianping.cat.consumer.storage.model.entity.Machine;
import com.dianping.cat.consumer.storage.model.entity.Operation;
import com.dianping.cat.consumer.storage.model.entity.Segment;
import com.dianping.cat.consumer.storage.model.entity.Sql;
import com.dianping.cat.consumer.storage.model.entity.StorageReport;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(StorageReport storageReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(storageReport, out);
      return out.toByteArray();
   }

   public static void build(StorageReport storageReport, OutputStream out) {
      storageReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDomain(Domain domain) {
      if (domain.getId() != null) {
         writeTag(1, 1);
         writeString(domain.getId());
      }

      if (!domain.getOperations().isEmpty()) {
         writeTag(33, 2);
         writeInt(domain.getOperations().size());

         for (Operation operation : domain.getOperations().values()) {
            operation.accept(m_visitor);
         }
      }

      if (!domain.getSqls().isEmpty()) {
         writeTag(34, 2);
         writeInt(domain.getSqls().size());

         for (Sql sql : domain.getSqls().values()) {
            sql.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitMachine(Machine machine) {
      if (machine.getId() != null) {
         writeTag(1, 1);
         writeString(machine.getId());
      }

      if (!machine.getDomains().isEmpty()) {
         writeTag(33, 2);
         writeInt(machine.getDomains().size());

         for (Domain domain : machine.getDomains().values()) {
            domain.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitOperation(Operation operation) {
      if (operation.getId() != null) {
         writeTag(1, 1);
         writeString(operation.getId());
      }

      writeTag(2, 0);
      writeLong(operation.getCount());

      writeTag(3, 0);
      writeDouble(operation.getAvg());

      writeTag(4, 0);
      writeDouble(operation.getSum());

      writeTag(5, 0);
      writeLong(operation.getError());

      writeTag(6, 0);
      writeLong(operation.getLongCount());

      if (!operation.getSegments().isEmpty()) {
         writeTag(33, 2);
         writeInt(operation.getSegments().size());

         for (Segment segment : operation.getSegments().values()) {
            segment.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitSegment(Segment segment) {
      writeTag(1, 0);
      writeInt(segment.getId());

      writeTag(2, 0);
      writeLong(segment.getCount());

      writeTag(3, 0);
      writeDouble(segment.getAvg());

      writeTag(4, 0);
      writeDouble(segment.getSum());

      writeTag(5, 0);
      writeLong(segment.getError());

      writeTag(6, 0);
      writeLong(segment.getLongCount());

      writeTag(63, 3);
   }

   @Override
   public void visitSql(Sql sql) {
      if (sql.getId() != null) {
         writeTag(1, 1);
         writeString(sql.getId());
      }

      if (sql.getStatement() != null) {
         writeTag(2, 1);
         writeString(sql.getStatement());
      }

      writeTag(3, 0);
      writeInt(sql.getCount());

      writeTag(63, 3);
   }

   @Override
   public void visitStorageReport(StorageReport storageReport) {
      writeTag(63, 0);

      if (storageReport.getId() != null) {
         writeTag(1, 1);
         writeString(storageReport.getId());
      }

      if (storageReport.getName() != null) {
         writeTag(2, 1);
         writeString(storageReport.getName());
      }

      if (storageReport.getType() != null) {
         writeTag(3, 1);
         writeString(storageReport.getType());
      }

      if (storageReport.getStartTime() != null) {
         writeTag(4, 1);
         writeDate(storageReport.getStartTime());
      }

      if (storageReport.getEndTime() != null) {
         writeTag(5, 1);
         writeDate(storageReport.getEndTime());
      }

      if (!storageReport.getIds().isEmpty()) {
         writeTag(6, 2);
         writeInt(storageReport.getIds().size());

         for (String id : storageReport.getIds()) {
            writeString(id);
         }
      }

      if (!storageReport.getIps().isEmpty()) {
         writeTag(7, 2);
         writeInt(storageReport.getIps().size());

         for (String ip : storageReport.getIps()) {
            writeString(ip);
         }
      }

      if (!storageReport.getOps().isEmpty()) {
         writeTag(8, 2);
         writeInt(storageReport.getOps().size());

         for (String op : storageReport.getOps()) {
            writeString(op);
         }
      }

      if (!storageReport.getMachines().isEmpty()) {
         writeTag(33, 2);
         writeInt(storageReport.getMachines().size());

         for (Machine machine : storageReport.getMachines().values()) {
            machine.accept(m_visitor);
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
