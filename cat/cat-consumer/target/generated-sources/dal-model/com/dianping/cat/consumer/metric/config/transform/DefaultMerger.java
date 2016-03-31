package com.dianping.cat.consumer.metric.config.transform;

import java.util.Stack;

import com.dianping.cat.consumer.metric.config.IEntity;
import com.dianping.cat.consumer.metric.config.IVisitor;
import com.dianping.cat.consumer.metric.config.entity.MetricConfig;
import com.dianping.cat.consumer.metric.config.entity.MetricItemConfig;
import com.dianping.cat.consumer.metric.config.entity.Tag;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private MetricConfig m_metricConfig;

   public DefaultMerger() {
   }

   public DefaultMerger(MetricConfig metricConfig) {
      m_metricConfig = metricConfig;
      m_objs.push(metricConfig);
   }

   public MetricConfig getMetricConfig() {
      return m_metricConfig;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeMetricConfig(MetricConfig to, MetricConfig from) {
      to.mergeAttributes(from);
   }

   protected void mergeMetricItemConfig(MetricItemConfig to, MetricItemConfig from) {
      to.mergeAttributes(from);
   }

   protected void mergeTag(Tag to, Tag from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitMetricConfig(MetricConfig from) {
      MetricConfig to = (MetricConfig) m_objs.peek();

      mergeMetricConfig(to, from);
      visitMetricConfigChildren(to, from);
   }

   protected void visitMetricConfigChildren(MetricConfig to, MetricConfig from) {
      for (MetricItemConfig source : from.getMetricItemConfigs().values()) {
         MetricItemConfig target = to.findMetricItemConfig(source.getId());

         if (target == null) {
            target = new MetricItemConfig(source.getId());
            to.addMetricItemConfig(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitMetricItemConfig(MetricItemConfig from) {
      MetricItemConfig to = (MetricItemConfig) m_objs.peek();

      mergeMetricItemConfig(to, from);
      visitMetricItemConfigChildren(to, from);
   }

   protected void visitMetricItemConfigChildren(MetricItemConfig to, MetricItemConfig from) {
      for (Tag source : from.getTags()) {
         Tag target = null;

         if (target == null) {
            target = new Tag();
            to.addTag(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitTag(Tag from) {
      Tag to = (Tag) m_objs.peek();

      mergeTag(to, from);
      visitTagChildren(to, from);
   }

   protected void visitTagChildren(Tag to, Tag from) {
   }
}
