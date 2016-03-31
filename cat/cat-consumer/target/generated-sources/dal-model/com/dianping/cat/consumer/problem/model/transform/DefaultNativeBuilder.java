package com.dianping.cat.consumer.problem.model.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.consumer.problem.model.IVisitor;
import com.dianping.cat.consumer.problem.model.entity.Duration;
import com.dianping.cat.consumer.problem.model.entity.Entity;
import com.dianping.cat.consumer.problem.model.entity.Entry;
import com.dianping.cat.consumer.problem.model.entity.JavaThread;
import com.dianping.cat.consumer.problem.model.entity.Machine;
import com.dianping.cat.consumer.problem.model.entity.ProblemReport;
import com.dianping.cat.consumer.problem.model.entity.Segment;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(ProblemReport problemReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(problemReport, out);
      return out.toByteArray();
   }

   public static void build(ProblemReport problemReport, OutputStream out) {
      problemReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDuration(Duration duration) {
      writeTag(1, 0);
      writeInt(duration.getValue());

      writeTag(2, 0);
      writeInt(duration.getCount());

      if (!duration.getMessages().isEmpty()) {
         writeTag(3, 2);
         writeInt(duration.getMessages().size());

         for (String message : duration.getMessages()) {
            writeString(message);
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

      if (entity.getType() != null) {
         writeTag(2, 1);
         writeString(entity.getType());
      }

      if (entity.getStatus() != null) {
         writeTag(3, 1);
         writeString(entity.getStatus());
      }

      if (!entity.getThreads().isEmpty()) {
         writeTag(33, 2);
         writeInt(entity.getThreads().size());

         for (JavaThread thread : entity.getThreads().values()) {
            thread.accept(m_visitor);
         }
      }

      if (!entity.getDurations().isEmpty()) {
         writeTag(34, 2);
         writeInt(entity.getDurations().size());

         for (Duration duration : entity.getDurations().values()) {
            duration.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitEntry(Entry entry) {
      if (entry.getType() != null) {
         writeTag(1, 1);
         writeString(entry.getType());
      }

      if (entry.getStatus() != null) {
         writeTag(2, 1);
         writeString(entry.getStatus());
      }

      if (!entry.getDurations().isEmpty()) {
         writeTag(33, 2);
         writeInt(entry.getDurations().size());

         for (Duration duration : entry.getDurations().values()) {
            duration.accept(m_visitor);
         }
      }

      if (!entry.getThreads().isEmpty()) {
         writeTag(34, 2);
         writeInt(entry.getThreads().size());

         for (JavaThread thread : entry.getThreads().values()) {
            thread.accept(m_visitor);
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

      if (!machine.getEntries().isEmpty()) {
         writeTag(33, 2);
         writeInt(machine.getEntries().size());

         for (Entry entry : machine.getEntries()) {
            entry.accept(m_visitor);
         }
      }

      if (!machine.getEntities().isEmpty()) {
         writeTag(34, 2);
         writeInt(machine.getEntities().size());

         for (Entity entity : machine.getEntities().values()) {
            entity.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitProblemReport(ProblemReport problemReport) {
      writeTag(63, 0);

      if (problemReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(problemReport.getDomain());
      }

      if (problemReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(problemReport.getStartTime());
      }

      if (problemReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(problemReport.getEndTime());
      }

      if (!problemReport.getDomainNames().isEmpty()) {
         writeTag(4, 2);
         writeInt(problemReport.getDomainNames().size());

         for (String domain : problemReport.getDomainNames()) {
            writeString(domain);
         }
      }

      if (!problemReport.getIps().isEmpty()) {
         writeTag(5, 2);
         writeInt(problemReport.getIps().size());

         for (String ip : problemReport.getIps()) {
            writeString(ip);
         }
      }

      if (!problemReport.getMachines().isEmpty()) {
         writeTag(33, 2);
         writeInt(problemReport.getMachines().size());

         for (Machine machine : problemReport.getMachines().values()) {
            machine.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitSegment(Segment segment) {
      if (segment.getId() != null) {
         writeTag(1, 1);
         writeInt(segment.getId());
      }

      writeTag(2, 0);
      writeInt(segment.getCount());

      if (!segment.getMessages().isEmpty()) {
         writeTag(3, 2);
         writeInt(segment.getMessages().size());

         for (String message : segment.getMessages()) {
            writeString(message);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitThread(JavaThread thread) {
      if (thread.getGroupName() != null) {
         writeTag(1, 1);
         writeString(thread.getGroupName());
      }

      if (thread.getName() != null) {
         writeTag(2, 1);
         writeString(thread.getName());
      }

      if (thread.getId() != null) {
         writeTag(3, 1);
         writeString(thread.getId());
      }

      if (!thread.getSegments().isEmpty()) {
         writeTag(33, 2);
         writeInt(thread.getSegments().size());

         for (Segment segment : thread.getSegments().values()) {
            segment.accept(m_visitor);
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
