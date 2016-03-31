package com.dianping.cat.consumer.metric.model.entity;

import static com.dianping.cat.consumer.metric.model.Constants.ATTR_NAME;
import static com.dianping.cat.consumer.metric.model.Constants.ENTITY_GROUP;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.metric.model.BaseEntity;
import com.dianping.cat.consumer.metric.model.IVisitor;

public class Group extends BaseEntity<Group> {
   private String m_name;

   private Map<Integer, Point> m_points = new LinkedHashMap<Integer, Point>();

   public Group() {
   }

   public Group(String name) {
      m_name = name;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitGroup(this);
   }

   public Group addPoint(Point point) {
      m_points.put(point.getId(), point);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Group) {
         Group _o = (Group) obj;
         String name = _o.getName();

         return m_name == name || m_name != null && m_name.equals(name);
      }

      return false;
   }

   public Point findPoint(Integer id) {
      return m_points.get(id);
   }

   public Point findOrCreatePoint(Integer id) {
      Point point = m_points.get(id);

      if (point == null) {
         synchronized (m_points) {
            point = m_points.get(id);

            if (point == null) {
               point = new Point(id);
               m_points.put(id, point);
            }
         }
      }

      return point;
   }

   public String getName() {
      return m_name;
   }

   public Map<Integer, Point> getPoints() {
      return m_points;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Group other) {
      assertAttributeEquals(other, ENTITY_GROUP, ATTR_NAME, m_name, other.getName());

   }

   public boolean removePoint(Integer id) {
      if (m_points.containsKey(id)) {
         m_points.remove(id);
         return true;
      }

      return false;
   }

   public Group setName(String name) {
      m_name = name;
      return this;
   }

}
