package com.dianping.cat.home.alert.thirdparty.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.alert.thirdparty.BaseEntity;
import com.dianping.cat.home.alert.thirdparty.IVisitor;

public class ThirdPartyConfig extends BaseEntity<ThirdPartyConfig> {
   private List<Http> m_https = new ArrayList<Http>();

   private List<Socket> m_sockets = new ArrayList<Socket>();

   public ThirdPartyConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitThirdPartyConfig(this);
   }

   public ThirdPartyConfig addHttp(Http http) {
      m_https.add(http);
      return this;
   }

   public ThirdPartyConfig addSocket(Socket socket) {
      m_sockets.add(socket);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ThirdPartyConfig) {
         ThirdPartyConfig _o = (ThirdPartyConfig) obj;
         List<Http> https = _o.getHttps();
         List<Socket> sockets = _o.getSockets();
         boolean result = true;

         result &= (m_https == https || m_https != null && m_https.equals(https));
         result &= (m_sockets == sockets || m_sockets != null && m_sockets.equals(sockets));

         return result;
      }

      return false;
   }

   public List<Http> getHttps() {
      return m_https;
   }

   public List<Socket> getSockets() {
      return m_sockets;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_https == null ? 0 : m_https.hashCode());
      hash = hash * 31 + (m_sockets == null ? 0 : m_sockets.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(ThirdPartyConfig other) {
   }

}
