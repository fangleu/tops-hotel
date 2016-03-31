package com.dianping.cat.home.utilization.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.utilization.IVisitor;
import com.dianping.cat.home.utilization.entity.ApplicationState;
import com.dianping.cat.home.utilization.entity.Domain;
import com.dianping.cat.home.utilization.entity.MachineState;
import com.dianping.cat.home.utilization.entity.UtilizationReport;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(UtilizationReport utilizationReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(utilizationReport, out);
      return out.toByteArray();
   }

   public static void build(UtilizationReport utilizationReport, OutputStream out) {
      utilizationReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitApplicationState(ApplicationState applicationState) {
      if (applicationState.getId() != null) {
         writeTag(1, 1);
         writeString(applicationState.getId());
      }

      writeTag(2, 0);
      writeLong(applicationState.getFailureCount());

      writeTag(3, 0);
      writeDouble(applicationState.getFailurePercent());

      writeTag(4, 0);
      writeLong(applicationState.getCount());

      writeTag(5, 0);
      writeDouble(applicationState.getMaxQps());

      writeTag(6, 0);
      writeDouble(applicationState.getAvg());

      writeTag(7, 0);
      writeDouble(applicationState.getSum());

      writeTag(8, 0);
      writeDouble(applicationState.getAvg95());

      writeTag(63, 3);
   }

   @Override
   public void visitDomain(Domain domain) {
      if (domain.getId() != null) {
         writeTag(1, 1);
         writeString(domain.getId());
      }

      writeTag(2, 0);
      writeInt(domain.getMachineNumber());

      if (domain.getCmdbId() != null) {
         writeTag(3, 1);
         writeString(domain.getCmdbId());
      }

      if (!domain.getMachineStates().isEmpty()) {
         writeTag(33, 2);
         writeInt(domain.getMachineStates().size());

         for (MachineState machineState : domain.getMachineStates().values()) {
            machineState.accept(m_visitor);
         }
      }

      if (!domain.getApplicationStates().isEmpty()) {
         writeTag(34, 2);
         writeInt(domain.getApplicationStates().size());

         for (ApplicationState applicationState : domain.getApplicationStates().values()) {
            applicationState.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitMachineState(MachineState machineState) {
      if (machineState.getId() != null) {
         writeTag(1, 1);
         writeString(machineState.getId());
      }

      writeTag(2, 0);
      writeLong(machineState.getCount());

      writeTag(3, 0);
      writeDouble(machineState.getSum());

      writeTag(4, 0);
      writeDouble(machineState.getAvg());

      writeTag(5, 0);
      writeDouble(machineState.getAvgMax());

      writeTag(63, 3);
   }

   @Override
   public void visitUtilizationReport(UtilizationReport utilizationReport) {
      writeTag(63, 0);

      if (utilizationReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(utilizationReport.getDomain());
      }

      if (utilizationReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(utilizationReport.getStartTime());
      }

      if (utilizationReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(utilizationReport.getEndTime());
      }

      if (!utilizationReport.getDomains().isEmpty()) {
         writeTag(33, 2);
         writeInt(utilizationReport.getDomains().size());

         for (Domain domain : utilizationReport.getDomains().values()) {
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
