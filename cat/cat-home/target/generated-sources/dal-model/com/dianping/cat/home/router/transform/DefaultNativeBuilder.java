package com.dianping.cat.home.router.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.router.IVisitor;
import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(RouterConfig routerConfig) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(routerConfig, out);
      return out.toByteArray();
   }

   public static void build(RouterConfig routerConfig, OutputStream out) {
      routerConfig.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDefaultServer(DefaultServer defaultServer) {
      if (defaultServer.getId() != null) {
         writeTag(1, 1);
         writeString(defaultServer.getId());
      }

      writeTag(2, 0);
      writeInt(defaultServer.getPort());

      writeTag(3, 0);
      writeBoolean(defaultServer.getEnable());

      writeTag(4, 0);
      writeDouble(defaultServer.getWeight());

      writeTag(63, 3);
   }

   @Override
   public void visitDomain(Domain domain) {
      if (domain.getId() != null) {
         writeTag(1, 1);
         writeString(domain.getId());
      }

      if (!domain.getServers().isEmpty()) {
         writeTag(33, 2);
         writeInt(domain.getServers().size());

         for (Server server : domain.getServers()) {
            server.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitRouterConfig(RouterConfig routerConfig) {
      writeTag(63, 0);

      if (routerConfig.getStartTime() != null) {
         writeTag(1, 1);
         writeDate(routerConfig.getStartTime());
      }

      if (routerConfig.getDomain() != null) {
         writeTag(2, 1);
         writeString(routerConfig.getDomain());
      }

      if (routerConfig.getBackupServer() != null) {
         writeTag(3, 1);
         writeString(routerConfig.getBackupServer());
      }

      writeTag(4, 0);
      writeInt(routerConfig.getBackupServerPort());

      if (routerConfig.getEndTime() != null) {
         writeTag(5, 1);
         writeDate(routerConfig.getEndTime());
      }

      if (!routerConfig.getDefaultServers().isEmpty()) {
         writeTag(33, 2);
         writeInt(routerConfig.getDefaultServers().size());

         for (DefaultServer defaultServer : routerConfig.getDefaultServers()) {
            defaultServer.accept(m_visitor);
         }
      }

      if (!routerConfig.getDomains().isEmpty()) {
         writeTag(34, 2);
         writeInt(routerConfig.getDomains().size());

         for (Domain domain : routerConfig.getDomains().values()) {
            domain.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitServer(Server server) {
      if (server.getId() != null) {
         writeTag(1, 1);
         writeString(server.getId());
      }

      writeTag(2, 0);
      writeInt(server.getPort());

      writeTag(3, 0);
      writeDouble(server.getWeight());

      writeTag(63, 3);
   }

   private void writeBoolean(boolean value) {
      try {
         m_out.writeByte(value ? 1 : 0);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeDate(java.util.Date value) {
      try {
         writeVarint(value.getTime());
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeDouble(double value) {
      try {
         m_out.writeDouble(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeInt(int value) {
      try {
         writeVarint(value & 0xFFFFFFFFL);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeString(String value) {
      try {
         m_out.writeUTF(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeTag(int field, int type) {
      try {
         m_out.writeByte((field << 2) + type);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected void writeVarint(long value) throws IOException {
      while (true) {
         if ((value & ~0x7FL) == 0) {
            m_out.writeByte((byte) value);
            return;
         } else {
            m_out.writeByte(((byte) value & 0x7F) | 0x80);
            value >>>= 7;
         }
      }
   }
}
