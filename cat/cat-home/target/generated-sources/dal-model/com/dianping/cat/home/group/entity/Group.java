package com.dianping.cat.home.group.entity;

import static com.dianping.cat.home.group.Constants.ATTR_ID;
import static com.dianping.cat.home.group.Constants.ENTITY_GROUP;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.group.BaseEntity;
import com.dianping.cat.home.group.IVisitor;

public class Group extends BaseEntity<Group> {
   private String m_id;

   private List<String> m_ips = new ArrayList<String>();

   public Group() {
   }

   public Group(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitGroup(this);
   }

   public Group addIp(String ip) {
      m_ips.add(ip);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Group) {
         Group _o = (Group) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getId() {
      return m_id;
   }

   public List<String> getIps() {
      return m_ips;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Group other) {
      assertAttributeEquals(other, ENTITY_GROUP, ATTR_ID, m_id, other.getId());

   }

   public Group setId(String id) {
      m_id = id;
      return this;
   }

}
