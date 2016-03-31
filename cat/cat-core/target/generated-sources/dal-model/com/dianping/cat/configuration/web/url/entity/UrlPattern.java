package com.dianping.cat.configuration.web.url.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.configuration.web.url.BaseEntity;
import com.dianping.cat.configuration.web.url.IVisitor;

public class UrlPattern extends BaseEntity<UrlPattern> {
   private Map<String, PatternItem> m_patternItems = new LinkedHashMap<String, PatternItem>();

   private Map<Integer, Code> m_codes = new LinkedHashMap<Integer, Code>();

   public UrlPattern() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitUrlPattern(this);
   }

   public UrlPattern addCode(Code code) {
      m_codes.put(code.getId(), code);
      return this;
   }

   public UrlPattern addPatternItem(PatternItem patternItem) {
      m_patternItems.put(patternItem.getName(), patternItem);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof UrlPattern) {
         UrlPattern _o = (UrlPattern) obj;
         Map<String, PatternItem> patternItems = _o.getPatternItems();
         Map<Integer, Code> codes = _o.getCodes();
         boolean result = true;

         result &= (m_patternItems == patternItems || m_patternItems != null && m_patternItems.equals(patternItems));
         result &= (m_codes == codes || m_codes != null && m_codes.equals(codes));

         return result;
      }

      return false;
   }

   public Code findCode(Integer id) {
      return m_codes.get(id);
   }

   public PatternItem findPatternItem(String name) {
      return m_patternItems.get(name);
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

   public PatternItem findOrCreatePatternItem(String name) {
      PatternItem patternItem = m_patternItems.get(name);

      if (patternItem == null) {
         synchronized (m_patternItems) {
            patternItem = m_patternItems.get(name);

            if (patternItem == null) {
               patternItem = new PatternItem(name);
               m_patternItems.put(name, patternItem);
            }
         }
      }

      return patternItem;
   }

   public Map<Integer, Code> getCodes() {
      return m_codes;
   }

   public Map<String, PatternItem> getPatternItems() {
      return m_patternItems;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_patternItems == null ? 0 : m_patternItems.hashCode());
      hash = hash * 31 + (m_codes == null ? 0 : m_codes.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(UrlPattern other) {
   }

   public boolean removeCode(Integer id) {
      if (m_codes.containsKey(id)) {
         m_codes.remove(id);
         return true;
      }

      return false;
   }

   public boolean removePatternItem(String name) {
      if (m_patternItems.containsKey(name)) {
         m_patternItems.remove(name);
         return true;
      }

      return false;
   }

}
