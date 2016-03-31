package com.dianping.cat.consumer.matrix.model.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.consumer.matrix.model.IVisitor;
import com.dianping.cat.consumer.matrix.model.entity.Matrix;
import com.dianping.cat.consumer.matrix.model.entity.MatrixReport;
import com.dianping.cat.consumer.matrix.model.entity.Ratio;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(MatrixReport matrixReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(matrixReport, out);
      return out.toByteArray();
   }

   public static void build(MatrixReport matrixReport, OutputStream out) {
      matrixReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitMatrix(Matrix matrix) {
      if (matrix.getType() != null) {
         writeTag(1, 1);
         writeString(matrix.getType());
      }

      if (matrix.getName() != null) {
         writeTag(2, 1);
         writeString(matrix.getName());
      }

      writeTag(3, 0);
      writeInt(matrix.getCount());

      writeTag(4, 0);
      writeLong(matrix.getTotalTime());

      if (matrix.getUrl() != null) {
         writeTag(5, 1);
         writeString(matrix.getUrl());
      }

      if (!matrix.getRatios().isEmpty()) {
         writeTag(33, 2);
         writeInt(matrix.getRatios().size());

         for (Ratio ratio : matrix.getRatios().values()) {
            ratio.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitMatrixReport(MatrixReport matrixReport) {
      writeTag(63, 0);

      if (matrixReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(matrixReport.getDomain());
      }

      if (matrixReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(matrixReport.getStartTime());
      }

      if (matrixReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(matrixReport.getEndTime());
      }

      if (!matrixReport.getDomainNames().isEmpty()) {
         writeTag(4, 2);
         writeInt(matrixReport.getDomainNames().size());

         for (String domain : matrixReport.getDomainNames()) {
            writeString(domain);
         }
      }

      if (!matrixReport.getMatrixs().isEmpty()) {
         writeTag(33, 2);
         writeInt(matrixReport.getMatrixs().size());

         for (Matrix matrix : matrixReport.getMatrixs().values()) {
            matrix.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitRatio(Ratio ratio) {
      if (ratio.getType() != null) {
         writeTag(1, 1);
         writeString(ratio.getType());
      }

      writeTag(2, 0);
      writeInt(ratio.getMin());

      writeTag(3, 0);
      writeInt(ratio.getMax());

      writeTag(4, 0);
      writeInt(ratio.getTotalCount());

      writeTag(5, 0);
      writeLong(ratio.getTotalTime());

      if (ratio.getUrl() != null) {
         writeTag(6, 1);
         writeString(ratio.getUrl());
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
