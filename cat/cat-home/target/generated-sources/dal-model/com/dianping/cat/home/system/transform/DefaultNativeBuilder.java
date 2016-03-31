package com.dianping.cat.home.system.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.system.IVisitor;
import com.dianping.cat.home.system.entity.Day;
import com.dianping.cat.home.system.entity.Domain;
import com.dianping.cat.home.system.entity.Entity;
import com.dianping.cat.home.system.entity.Machine;
import com.dianping.cat.home.system.entity.Rush;
import com.dianping.cat.home.system.entity.SystemReport;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(SystemReport systemReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(systemReport, out);
      return out.toByteArray();
   }

   public static void build(SystemReport systemReport, OutputStream out) {
      systemReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDay(Day day) {
      writeTag(1, 0);
      writeLong(day.getCount());

      writeTag(2, 0);
      writeDouble(day.getSum());

      writeTag(3, 0);
      writeDouble(day.getAvg());

      writeTag(63, 3);
   }

   @Override
   public void visitDomain(Domain domain) {
      if (domain.getId() != null) {
         writeTag(1, 1);
         writeString(domain.getId());
      }

      if (!domain.getEntities().isEmpty()) {
         writeTag(33, 2);
         writeInt(domain.getEntities().size());

         for (Entity entity : domain.getEntities().values()) {
            entity.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitEntity(Entity entity) {
      if (entity.getId() != null) {
         writeTag(1, 1);
         writeString(entity.getId());
      }

      if (entity.getRush() != null) {
         writeTag(33, 1);
         entity.getRush().accept(m_visitor);
      }

      if (entity.getDay() != null) {
         writeTag(34, 1);
         entity.getDay().accept(m_visitor);
      }

      if (!entity.getMachines().isEmpty()) {
         writeTag(35, 2);
         writeInt(entity.getMachines().size());

         for (Machine machine : entity.getMachines().values()) {
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

      writeTag(63, 3);
   }

   @Override
   public void visitRush(Rush rush) {
      writeTag(1, 0);
      writeLong(rush.getCount());

      writeTag(2, 0);
      writeDouble(rush.getSum());

      writeTag(3, 0);
      writeDouble(rush.getAvg());

      writeTag(63, 3);
   }

   @Override
   public void visitSystemReport(SystemReport systemReport) {
      writeTag(63, 0);

      if (systemReport.getStartTime() != null) {
         writeTag(1, 1);
         writeDate(systemReport.getStartTime());
      }

      if (systemReport.getEndTime() != null) {
         writeTag(2, 1);
         writeDate(systemReport.getEndTime());
      }

      if (!systemReport.getDomains().isEmpty()) {
         writeTag(33, 2);
         writeInt(systemReport.getDomains().size());

         for (Domain domain : systemReport.getDomains().values()) {
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
