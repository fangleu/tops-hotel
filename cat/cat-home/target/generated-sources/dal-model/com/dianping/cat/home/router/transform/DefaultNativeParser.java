package com.dianping.cat.home.router.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.router.IVisitor;
import com.dianping.cat.home.router.entity.DefaultServer;
import com.dianping.cat.home.router.entity.Domain;
import com.dianping.cat.home.router.entity.RouterConfig;
import com.dianping.cat.home.router.entity.Server;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static RouterConfig parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static RouterConfig parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      RouterConfig routerConfig = new RouterConfig();

      try {
         routerConfig.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return routerConfig;
   }

   @Override
   public void visitDefaultServer(DefaultServer defaultServer) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitDefaultServerChildren(defaultServer, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitDefaultServerChildren(DefaultServer defaultServer, int _field, int _type) {
      switch (_field) {
         case 1:
            defaultServer.setId(readString());
            break;
         case 2:
            defaultServer.setPort(readInt());
            break;
         case 3:
            defaultServer.setEnable(readBoolean());
            break;
         case 4:
            defaultServer.setWeight(readDouble());
            break;
      }
   }

   @Override
   public void visitDomain(Domain domain) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitDomainChildren(domain, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitDomainChildren(Domain domain, int _field, int _type) {
      switch (_field) {
         case 1:
            domain.setId(readString());
            break;
         case 33:
            if (_type == 1) {
              Server servers = new Server();

              visitServer(servers);
              m_linker.onServer(domain, servers);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Server servers = new Server();

                 visitServer(servers);
                 m_linker.onServer(domain, servers);
               }
            }
            break;
      }
   }

   @Override
   public void visitRouterConfig(RouterConfig routerConfig) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitRouterConfigChildren(routerConfig, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitRouterConfigChildren(RouterConfig routerConfig, int _field, int _type) {
      switch (_field) {
         case 1:
            routerConfig.setStartTime(readDate());
            break;
         case 2:
            routerConfig.setDomain(readString());
            break;
         case 3:
            routerConfig.setBackupServer(readString());
            break;
         case 4:
            routerConfig.setBackupServerPort(readInt());
            break;
         case 5:
            routerConfig.setEndTime(readDate());
            break;
         case 33:
            if (_type == 1) {
              DefaultServer defaultServers = new DefaultServer();

              visitDefaultServer(defaultServers);
              m_linker.onDefaultServer(routerConfig, defaultServers);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 DefaultServer defaultServers = new DefaultServer();

                 visitDefaultServer(defaultServers);
                 m_linker.onDefaultServer(routerConfig, defaultServers);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              Domain domains = new Domain();

              visitDomain(domains);
              m_linker.onDomain(routerConfig, domains);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Domain domains = new Domain();

                 visitDomain(domains);
                 m_linker.onDomain(routerConfig, domains);
               }
            }
            break;
      }
   }

   @Override
   public void visitServer(Server server) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitServerChildren(server, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitServerChildren(Server server, int _field, int _type) {
      switch (_field) {
         case 1:
            server.setId(readString());
            break;
         case 2:
            server.setPort(readInt());
            break;
         case 3:
            server.setWeight(readDouble());
            break;
      }
   }

   private boolean readBoolean() {
      try {
         return m_in.readByte() == 1 ? Boolean.TRUE : Boolean.FALSE;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private java.util.Date readDate() {
      try {
         return new java.util.Date(readVarint(64));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private double readDouble() {
      try {
         return m_in.readDouble();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private int readInt() {
      try {
         return (int) readVarint(32);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private String readString() {
      try {
         return m_in.readUTF();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private byte readTag() {
      try {
         return m_in.readByte();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected long readVarint(final int length) throws IOException {
      int shift = 0;
      long result = 0;

      while (shift < length) {
         final byte b = m_in.readByte();
         result |= (long) (b & 0x7F) << shift;
         if ((b & 0x80) == 0) {
            return result;
         }
         shift += 7;
      }

      throw new RuntimeException("Malformed variable int " + length + "!");
   }
}
