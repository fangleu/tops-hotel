package com.dianping.cat.configuration.app.command.entity;

import com.dianping.cat.configuration.app.command.BaseEntity;
import com.dianping.cat.configuration.app.command.IVisitor;

public class Command extends BaseEntity<Command> {
   private String m_id;

   public Command() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCommand(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Command) {
         Command _o = (Command) obj;
         String id = _o.getId();
         boolean result = true;

         result &= (m_id == id || m_id != null && m_id.equals(id));

         return result;
      }

      return false;
   }

   public String getId() {
      return m_id;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Command other) {
      if (other.getId() != null) {
         m_id = other.getId();
      }
   }

   public Command setId(String id) {
      m_id = id;
      return this;
   }

}
