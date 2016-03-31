package com.dianping.cat.configuration.server.black.entity;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.dianping.cat.configuration.server.black.BaseEntity;
import com.dianping.cat.configuration.server.black.IVisitor;

public class BlackList extends BaseEntity<BlackList> {
   private Set<String> m_domainNames = new LinkedHashSet<String>();

   private Set<String> m_ips = new LinkedHashSet<String>();

   private Map<String, Property> m_properties = new LinkedHashMap<String, Property>();

   public BlackList() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitBlackList(this);
   }

   public BlackList addDomain(String domain) {
      m_domainNames.add(domain);
      return this;
   }

   public BlackList addIp(String ip) {
      m_ips.add(ip);
      return this;
   }

   public BlackList addProperty(Property property) {
      m_properties.put(property.getName(), property);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof BlackList) {
         BlackList _o = (BlackList) obj;
         Set<String> domainNames = _o.getDomainNames();
         Set<String> ips = _o.getIps();
         Map<String, Property> properties = _o.getProperties();
         boolean result = true;

         result &= (m_domainNames == domainNames || m_domainNames != null && m_domainNames.equals(domainNames));
         result &= (m_ips == ips || m_ips != null && m_ips.equals(ips));
         result &= (m_properties == properties || m_properties != null && m_properties.equals(properties));

         return result;
      }

      return false;
   }

   public Property findProperty(String name) {
      return m_properties.get(name);
   }

   public Set<String> getDomainNames() {
      return m_domainNames;
   }

   public Set<String> getIps() {
      return m_ips;
   }

   public Map<String, Property> getProperties() {
      return m_properties;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_domainNames == null ? 0 : m_domainNames.hashCode());
      hash = hash * 31 + (m_ips == null ? 0 : m_ips.hashCode());
      hash = hash * 31 + (m_properties == null ? 0 : m_properties.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(BlackList other) {
   }

   public boolean removeProperty(String name) {
      if (m_properties.containsKey(name)) {
         m_properties.remove(name);
         return true;
      }

      return false;
   }

}
