package com.dianping.cat.configuration.app.command.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.configuration.app.command.BaseEntity;
import com.dianping.cat.configuration.app.command.IVisitor;

public class Rule extends BaseEntity<Rule> {
   private String m_pattern;

   private int m_type;

   private List<Command> m_commands = new ArrayList<Command>();

   public Rule() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitRule(this);
   }

   public Rule addCommand(Command command) {
      m_commands.add(command);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Rule) {
         Rule _o = (Rule) obj;
         String pattern = _o.getPattern();
         int type = _o.getType();
         List<Command> commands = _o.getCommands();
         boolean result = true;

         result &= (m_pattern == pattern || m_pattern != null && m_pattern.equals(pattern));
         result &= (m_type == type);
         result &= (m_commands == commands || m_commands != null && m_commands.equals(commands));

         return result;
      }

      return false;
   }

   public List<Command> getCommands() {
      return m_commands;
   }

   public String getPattern() {
      return m_pattern;
   }

   public int getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_pattern == null ? 0 : m_pattern.hashCode());
      hash = hash * 31 + m_type;
      hash = hash * 31 + (m_commands == null ? 0 : m_commands.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Rule other) {
      if (other.getPattern() != null) {
         m_pattern = other.getPattern();
      }

      m_type = other.getType();
   }

   public Rule setPattern(String pattern) {
      m_pattern = pattern;
      return this;
   }

   public Rule setType(int type) {
      m_type = type;
      return this;
   }

}
