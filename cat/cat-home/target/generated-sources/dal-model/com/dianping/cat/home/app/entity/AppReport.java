package com.dianping.cat.home.app.entity;

import static com.dianping.cat.home.app.Constants.ATTR_ID;
import static com.dianping.cat.home.app.Constants.ENTITY_APP_REPORT;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.app.BaseEntity;
import com.dianping.cat.home.app.IVisitor;

public class AppReport extends BaseEntity<AppReport> {
   private String m_id;

   private java.util.Date m_startTime;

   private java.util.Date m_endTime;

   private Map<Integer, Command> m_commands = new LinkedHashMap<Integer, Command>();

   public AppReport() {
   }

   public AppReport(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAppReport(this);
   }

   public AppReport addCommand(Command command) {
      m_commands.put(command.getId(), command);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof AppReport) {
         AppReport _o = (AppReport) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Command findCommand(int id) {
      return m_commands.get(id);
   }

   public Command findOrCreateCommand(int id) {
      Command command = m_commands.get(id);

      if (command == null) {
         synchronized (m_commands) {
            command = m_commands.get(id);

            if (command == null) {
               command = new Command(id);
               m_commands.put(id, command);
            }
         }
      }

      return command;
   }

   public Map<Integer, Command> getCommands() {
      return m_commands;
   }

   public java.util.Date getEndTime() {
      return m_endTime;
   }

   public String getId() {
      return m_id;
   }

   public java.util.Date getStartTime() {
      return m_startTime;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(AppReport other) {
      assertAttributeEquals(other, ENTITY_APP_REPORT, ATTR_ID, m_id, other.getId());

      if (other.getStartTime() != null) {
         m_startTime = other.getStartTime();
      }

      if (other.getEndTime() != null) {
         m_endTime = other.getEndTime();
      }
   }

   public boolean removeCommand(int id) {
      if (m_commands.containsKey(id)) {
         m_commands.remove(id);
         return true;
      }

      return false;
   }

   public AppReport setEndTime(java.util.Date endTime) {
      m_endTime = endTime;
      return this;
   }

   public AppReport setId(String id) {
      m_id = id;
      return this;
   }

   public AppReport setStartTime(java.util.Date startTime) {
      m_startTime = startTime;
      return this;
   }

}
