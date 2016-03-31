package com.dianping.cat.configuration.app.entity;

import static com.dianping.cat.configuration.app.Constants.ATTR_ID;
import static com.dianping.cat.configuration.app.Constants.ENTITY_COMMAND;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.configuration.app.BaseEntity;
import com.dianping.cat.configuration.app.IVisitor;

public class Command extends BaseEntity<Command> {
   private Integer m_id;

   private String m_name;

   private String m_domain;

   private String m_title;

   private boolean m_all = true;

   private int m_threshold = 30;

   private Map<Integer, Code> m_codes = new LinkedHashMap<Integer, Code>();

   public Command() {
   }

   public Command(Integer id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCommand(this);
   }

   public Command addCode(Code code) {
      m_codes.put(code.getId(), code);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Command) {
         Command _o = (Command) obj;
         Integer id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public Code findCode(Integer id) {
      return m_codes.get(id);
   }

   public Code findOrCreateCode(Integer id) {
      Code code = m_codes.get(id);

      if (code == null) {
         synchronized (m_codes) {
            code = m_codes.get(id);

            if (code == null) {
               code = new Code(id);
               m_codes.put(id, code);
            }
         }
      }

      return code;
   }

   public boolean getAll() {
      return m_all;
   }

   public Map<Integer, Code> getCodes() {
      return m_codes;
   }

   public String getDomain() {
      return m_domain;
   }

   public Integer getId() {
      return m_id;
   }

   public String getName() {
      return m_name;
   }

   public int getThreshold() {
      return m_threshold;
   }

   public String getTitle() {
      return m_title;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   public boolean isAll() {
      return m_all;
   }

   @Override
   public void mergeAttributes(Command other) {
      assertAttributeEquals(other, ENTITY_COMMAND, ATTR_ID, m_id, other.getId());

      if (other.getName() != null) {
         m_name = other.getName();
      }

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }

      m_all = other.getAll();

      m_threshold = other.getThreshold();
   }

   public boolean removeCode(Integer id) {
      if (m_codes.containsKey(id)) {
         m_codes.remove(id);
         return true;
      }

      return false;
   }

   public Command setAll(boolean all) {
      m_all = all;
      return this;
   }

   public Command setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public Command setId(Integer id) {
      m_id = id;
      return this;
   }

   public Command setName(String name) {
      m_name = name;
      return this;
   }

   public Command setThreshold(int threshold) {
      m_threshold = threshold;
      return this;
   }

   public Command setTitle(String title) {
      m_title = title;
      return this;
   }

}
