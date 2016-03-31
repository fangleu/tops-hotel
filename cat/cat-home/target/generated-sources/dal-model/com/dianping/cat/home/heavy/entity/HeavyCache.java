package com.dianping.cat.home.heavy.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.heavy.BaseEntity;
import com.dianping.cat.home.heavy.IVisitor;

public class HeavyCache extends BaseEntity<HeavyCache> {
   private Map<String, Url> m_urls = new LinkedHashMap<String, Url>();

   private Map<String, Service> m_services = new LinkedHashMap<String, Service>();

   public HeavyCache() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitHeavyCache(this);
   }

   public HeavyCache addService(Service service) {
      m_services.put(service.getKey(), service);
      return this;
   }

   public HeavyCache addUrl(Url url) {
      m_urls.put(url.getKey(), url);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof HeavyCache) {
         HeavyCache _o = (HeavyCache) obj;
         Map<String, Url> urls = _o.getUrls();
         Map<String, Service> services = _o.getServices();
         boolean result = true;

         result &= (m_urls == urls || m_urls != null && m_urls.equals(urls));
         result &= (m_services == services || m_services != null && m_services.equals(services));

         return result;
      }

      return false;
   }

   public Service findService(String key) {
      return m_services.get(key);
   }

   public Url findUrl(String key) {
      return m_urls.get(key);
   }

   public Service findOrCreateService(String key) {
      Service service = m_services.get(key);

      if (service == null) {
         synchronized (m_services) {
            service = m_services.get(key);

            if (service == null) {
               service = new Service(key);
               m_services.put(key, service);
            }
         }
      }

      return service;
   }

   public Url findOrCreateUrl(String key) {
      Url url = m_urls.get(key);

      if (url == null) {
         synchronized (m_urls) {
            url = m_urls.get(key);

            if (url == null) {
               url = new Url(key);
               m_urls.put(key, url);
            }
         }
      }

      return url;
   }

   public Map<String, Service> getServices() {
      return m_services;
   }

   public Map<String, Url> getUrls() {
      return m_urls;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_urls == null ? 0 : m_urls.hashCode());
      hash = hash * 31 + (m_services == null ? 0 : m_services.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(HeavyCache other) {
   }

   public boolean removeService(String key) {
      if (m_services.containsKey(key)) {
         m_services.remove(key);
         return true;
      }

      return false;
   }

   public boolean removeUrl(String key) {
      if (m_urls.containsKey(key)) {
         m_urls.remove(key);
         return true;
      }

      return false;
   }

}
