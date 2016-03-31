package com.dianping.cat.configuration.server.entity;

import com.dianping.cat.configuration.server.BaseEntity;
import com.dianping.cat.configuration.server.IVisitor;

public class Ldap extends BaseEntity<Ldap> {
   private String m_ldapUrl = "ldap://idcldap.dianpingoa.com/DC=dianpingoa,DC=com";

   public Ldap() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitLdap(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Ldap) {
         Ldap _o = (Ldap) obj;
         String ldapUrl = _o.getLdapUrl();
         boolean result = true;

         result &= (m_ldapUrl == ldapUrl || m_ldapUrl != null && m_ldapUrl.equals(ldapUrl));

         return result;
      }

      return false;
   }

   public String getLdapUrl() {
      return m_ldapUrl;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_ldapUrl == null ? 0 : m_ldapUrl.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Ldap other) {
      if (other.getLdapUrl() != null) {
         m_ldapUrl = other.getLdapUrl();
      }
   }

   public Ldap setLdapUrl(String ldapUrl) {
      m_ldapUrl = ldapUrl;
      return this;
   }

}
