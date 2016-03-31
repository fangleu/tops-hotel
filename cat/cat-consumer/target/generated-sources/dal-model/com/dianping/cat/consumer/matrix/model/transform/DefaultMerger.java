package com.dianping.cat.consumer.matrix.model.transform;

import java.util.Stack;

import com.dianping.cat.consumer.matrix.model.IEntity;
import com.dianping.cat.consumer.matrix.model.IVisitor;
import com.dianping.cat.consumer.matrix.model.entity.Matrix;
import com.dianping.cat.consumer.matrix.model.entity.MatrixReport;
import com.dianping.cat.consumer.matrix.model.entity.Ratio;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private MatrixReport m_matrixReport;

   public DefaultMerger() {
   }

   public DefaultMerger(MatrixReport matrixReport) {
      m_matrixReport = matrixReport;
      m_objs.push(matrixReport);
   }

   public MatrixReport getMatrixReport() {
      return m_matrixReport;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeMatrix(Matrix to, Matrix from) {
      to.mergeAttributes(from);
   }

   protected void mergeMatrixReport(MatrixReport to, MatrixReport from) {
      to.mergeAttributes(from);
      to.getDomainNames().addAll(from.getDomainNames());
   }

   protected void mergeRatio(Ratio to, Ratio from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitMatrix(Matrix from) {
      Matrix to = (Matrix) m_objs.peek();

      mergeMatrix(to, from);
      visitMatrixChildren(to, from);
   }

   protected void visitMatrixChildren(Matrix to, Matrix from) {
      for (Ratio source : from.getRatios().values()) {
         Ratio target = to.findRatio(source.getType());

         if (target == null) {
            target = new Ratio(source.getType());
            to.addRatio(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitMatrixReport(MatrixReport from) {
      MatrixReport to = (MatrixReport) m_objs.peek();

      mergeMatrixReport(to, from);
      visitMatrixReportChildren(to, from);
   }

   protected void visitMatrixReportChildren(MatrixReport to, MatrixReport from) {
      for (Matrix source : from.getMatrixs().values()) {
         Matrix target = to.findMatrix(source.getName());

         if (target == null) {
            target = new Matrix(source.getName());
            to.addMatrix(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitRatio(Ratio from) {
      Ratio to = (Ratio) m_objs.peek();

      mergeRatio(to, from);
      visitRatioChildren(to, from);
   }

   protected void visitRatioChildren(Ratio to, Ratio from) {
   }
}
