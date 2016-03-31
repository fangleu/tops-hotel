package com.dianping.cat.home.jar.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.jar.IVisitor;
import com.dianping.cat.home.jar.entity.Domain;
import com.dianping.cat.home.jar.entity.Jar;
import com.dianping.cat.home.jar.entity.JarReport;
import com.dianping.cat.home.jar.entity.Machine;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(JarReport jarReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(jarReport, out);
      return out.toByteArray();
   }

   public static void build(JarReport jarReport, OutputStream out) {
      jarReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDomain(Domain domain) {
      if (domain.getId() != null) {
         writeTag(1, 1);
         writeString(domain.getId());
      }

      if (!domain.getMachines().isEmpty()) {
         writeTag(33, 2);
         writeInt(domain.getMachines().size());

         for (Machine machine : domain.getMachines().values()) {
            machine.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitJar(Jar jar) {
      if (jar.getId() != null) {
         writeTag(1, 1);
         writeString(jar.getId());
      }

      if (jar.getVersion() != null) {
         writeTag(2, 1);
         writeString(jar.getVersion());
      }

      writeTag(63, 3);
   }

   @Override
   public void visitJarReport(JarReport jarReport) {
      writeTag(63, 0);

      if (jarReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(jarReport.getDomain());
      }

      if (jarReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(jarReport.getStartTime());
      }

      if (jarReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(jarReport.getEndTime());
      }

      if (!jarReport.getDomains().isEmpty()) {
         writeTag(33, 2);
         writeInt(jarReport.getDomains().size());

         for (Domain domain : jarReport.getDomains().values()) {
            domain.accept(m_visitor);
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

      if (!machine.getJars().isEmpty()) {
         writeTag(33, 2);
         writeInt(machine.getJars().size());

         for (Jar jar : machine.getJars()) {
            jar.accept(m_visitor);
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
