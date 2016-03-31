package com.dianping.cat.consumer.cross.model.entity;

import static com.dianping.cat.consumer.cross.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.cross.model.Constants.ENTITY_LOCAL;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.cross.model.BaseEntity;
import com.dianping.cat.consumer.cross.model.IVisitor;

public class Local extends BaseEntity<Local> {
   private String m_id;

   private Map<String, Remote> m_remotes = new LinkedHashMap<String, Remote>();

   public Local() {
   }

   public Local(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitLocal(this);
   }

   public Local addRemote(Remote remote) {
      m_remotes.put(remote.getId(), remote);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Local) {
         Local _o = (Local) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Remote findRemote(String id) {
      return m_remotes.get(id);
   }

   public Remote findOrCreateRemote(String id) {
      Remote remote = m_remotes.get(id);

      if (remote == null) {
         synchronized (m_remotes) {
            remote = m_remotes.get(id);

            if (remote == null) {
               remote = new Remote(id);
               m_remotes.put(id, remote);
            }
         }
      }

      return remote;
   }

   public String getId() {
      return m_id;
   }

   public Map<String, Remote> getRemotes() {
      return m_remotes;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Local other) {
      assertAttributeEquals(other, ENTITY_LOCAL, ATTR_ID, m_id, other.getId());

   }

   public boolean removeRemote(String id) {
      if (m_remotes.containsKey(id)) {
         m_remotes.remove(id);
         return true;
      }

      return false;
   }

   public Local setId(String id) {
      m_id = id;
      return this;
   }

}
