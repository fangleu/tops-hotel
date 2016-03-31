package com.dianping.cat.home.storage.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.storage.BaseEntity;
import com.dianping.cat.home.storage.IVisitor;

public class Link extends BaseEntity<Link> {
   private String m_url;

   private List<String> m_pars = new ArrayList<String>();

   public Link() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitLink(this);
   }

   public Link addPar(String par) {
      m_pars.add(par);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Link) {
         Link _o = (Link) obj;
         String url = _o.getUrl();
         List<String> pars = _o.getPars();
         boolean result = true;

         result &= (m_url == url || m_url != null && m_url.equals(url));
         result &= (m_pars == pars || m_pars != null && m_pars.equals(pars));

         return result;
      }

      return false;
   }

   public List<String> getPars() {
      return m_pars;
   }

   public String getUrl() {
      return m_url;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_url == null ? 0 : m_url.hashCode());
      hash = hash * 31 + (m_pars == null ? 0 : m_pars.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Link other) {
      if (other.getUrl() != null) {
         m_url = other.getUrl();
      }
   }

   public Link setUrl(String url) {
      m_url = url;
      return this;
   }

}
