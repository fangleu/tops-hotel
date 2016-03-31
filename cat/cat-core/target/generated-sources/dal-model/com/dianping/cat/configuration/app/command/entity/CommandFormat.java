package com.dianping.cat.configuration.app.command.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.configuration.app.command.BaseEntity;
import com.dianping.cat.configuration.app.command.IVisitor;

public class CommandFormat extends BaseEntity<CommandFormat> {
   private List<Rule> m_rules = new ArrayList<Rule>();

   private int m_picSmall = 1000;

   private int m_picMiddel = 2000;

   private int m_picLarge = 4000;

   public CommandFormat() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCommandFormat(this);
   }

   public CommandFormat addRule(Rule rule) {
      m_rules.add(rule);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof CommandFormat) {
         CommandFormat _o = (CommandFormat) obj;
         List<Rule> rules = _o.getRules();
         int picSmall = _o.getPicSmall();
         int picMiddel = _o.getPicMiddel();
         int picLarge = _o.getPicLarge();
         boolean result = true;

         result &= (m_rules == rules || m_rules != null && m_rules.equals(rules));
         result &= (m_picSmall == picSmall);
         result &= (m_picMiddel == picMiddel);
         result &= (m_picLarge == picLarge);

         return result;
      }

      return false;
   }

   public int getPicLarge() {
      return m_picLarge;
   }

   public int getPicMiddel() {
      return m_picMiddel;
   }

   public int getPicSmall() {
      return m_picSmall;
   }

   public List<Rule> getRules() {
      return m_rules;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_rules == null ? 0 : m_rules.hashCode());
      hash = hash * 31 + m_picSmall;
      hash = hash * 31 + m_picMiddel;
      hash = hash * 31 + m_picLarge;

      return hash;
   }

   @Override
   public void mergeAttributes(CommandFormat other) {
      m_picSmall = other.getPicSmall();

      m_picMiddel = other.getPicMiddel();

      m_picLarge = other.getPicLarge();
   }

   public CommandFormat setPicLarge(int picLarge) {
      m_picLarge = picLarge;
      return this;
   }

   public CommandFormat setPicMiddel(int picMiddel) {
      m_picMiddel = picMiddel;
      return this;
   }

   public CommandFormat setPicSmall(int picSmall) {
      m_picSmall = picSmall;
      return this;
   }

}
