package com.dianping.cat.consumer.dependency.model.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.consumer.dependency.model.IVisitor;
import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(DependencyReport dependencyReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(dependencyReport, out);
      return out.toByteArray();
   }

   public static void build(DependencyReport dependencyReport, OutputStream out) {
      dependencyReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDependency(Dependency dependency) {
      if (dependency.getType() != null) {
         writeTag(1, 1);
         writeString(dependency.getType());
      }

      if (dependency.getTarget() != null) {
         writeTag(2, 1);
         writeString(dependency.getTarget());
      }

      writeTag(3, 0);
      writeLong(dependency.getTotalCount());

      writeTag(4, 0);
      writeDouble(dependency.getAvg());

      writeTag(5, 0);
      writeLong(dependency.getErrorCount());

      if (dependency.getKey() != null) {
         writeTag(6, 1);
         writeString(dependency.getKey());
      }

      writeTag(7, 0);
      writeDouble(dependency.getSum());

      writeTag(63, 3);
   }

   @Override
   public void visitDependencyReport(DependencyReport dependencyReport) {
      writeTag(63, 0);

      if (dependencyReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(dependencyReport.getDomain());
      }

      if (dependencyReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(dependencyReport.getStartTime());
      }

      if (dependencyReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(dependencyReport.getEndTime());
      }

      if (!dependencyReport.getDomainNames().isEmpty()) {
         writeTag(4, 2);
         writeInt(dependencyReport.getDomainNames().size());

         for (String domainName : dependencyReport.getDomainNames()) {
            writeString(domainName);
         }
      }

      if (!dependencyReport.getSegments().isEmpty()) {
         writeTag(33, 2);
         writeInt(dependencyReport.getSegments().size());

         for (Segment segment : dependencyReport.getSegments().values()) {
            segment.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitIndex(Index index) {
      if (index.getName() != null) {
         writeTag(1, 1);
         writeString(index.getName());
      }

      writeTag(2, 0);
      writeLong(index.getTotalCount());

      writeTag(3, 0);
      writeLong(index.getErrorCount());

      writeTag(4, 0);
      writeDouble(index.getSum());

      writeTag(5, 0);
      writeDouble(index.getAvg());

      writeTag(63, 3);
   }

   @Override
   public void visitSegment(Segment segment) {
      if (segment.getId() != null) {
         writeTag(1, 1);
         writeInt(segment.getId());
      }

      if (segment.getExceptionCount() != null) {
         writeTag(2, 1);
         writeInt(segment.getExceptionCount());
      }

      if (!segment.getIndexs().isEmpty()) {
         writeTag(33, 2);
         writeInt(segment.getIndexs().size());

         for (Index index : segment.getIndexs().values()) {
            index.accept(m_visitor);
         }
      }

      if (!segment.getDependencies().isEmpty()) {
         writeTag(34, 2);
         writeInt(segment.getDependencies().size());

         for (Dependency dependency : segment.getDependencies().values()) {
            dependency.accept(m_visitor);
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
