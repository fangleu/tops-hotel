package com.dianping.cat.home.alert.thirdparty.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.alert.thirdparty.BaseEntity;
import com.dianping.cat.home.alert.thirdparty.IVisitor;

public class Http extends BaseEntity<Http> {
   private String m_url;

   private String m_type;

   private String m_domain;

   private List<Par> m_pars = new ArrayList<Par>();

   public Http() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitHttp(this);
   }

   public Http addPar(Par par) {
      m_pars.add(par);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Http) {
         Http _o = (Http) obj;
         String url = _o.getUrl();
         String type = _o.getType();
         String domain = _o.getDomain();
         List<Par> pars = _o.getPars();
         boolean result = true;

         result &= (m_url == url || m_url != null && m_url.equals(url));
         result &= (m_type == type || m_type != null && m_type.equals(type));
         result &= (m_domain == domain || m_domain != null && m_domain.equals(domain));
         result &= (m_pars == pars || m_pars != null && m_pars.equals(pars));

         return result;
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
   }

   public List<Par> getPars() {
      return m_pars;
   }

   public String getType() {
      return m_type;
   }

   public String getUrl() {
      return m_url;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_url == null ? 0 : m_url.hashCode());
      hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());
      hash = hash * 31 + (m_domain == null ? 0 : m_domain.hashCode());
      hash = hash * 31 + (m_pars == null ? 0 : m_pars.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Http other) {
      if (other.getUrl() != null) {
         m_url = other.getUrl();
      }

      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }
   }

   public Http setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public Http setType(String type) {
      m_type = type;
      return this;
   }

   public Http setUrl(String url) {
      m_url = url;
      return this;
   }

}
