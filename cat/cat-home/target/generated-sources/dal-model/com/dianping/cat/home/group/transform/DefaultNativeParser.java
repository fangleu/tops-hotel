package com.dianping.cat.home.group.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.group.IVisitor;
import com.dianping.cat.home.group.entity.Domain;
import com.dianping.cat.home.group.entity.DomainGroup;
import com.dianping.cat.home.group.entity.Group;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static DomainGroup parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static DomainGroup parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      DomainGroup domainGroup = new DomainGroup();

      try {
         domainGroup.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return domainGroup;
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
              Group groups = new Group();

              visitGroup(groups);
              m_linker.onGroup(domain, groups);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Group groups = new Group();

                 visitGroup(groups);
                 m_linker.onGroup(domain, groups);
               }
            }
            break;
      }
   }

   @Override
   public void visitDomainGroup(DomainGroup domainGroup) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitDomainGroupChildren(domainGroup, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitDomainGroupChildren(DomainGroup domainGroup, int _field, int _type) {
      switch (_field) {
         case 33:
            if (_type == 1) {
              Domain domains = new Domain();

              visitDomain(domains);
              m_linker.onDomain(domainGroup, domains);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Domain domains = new Domain();

                 visitDomain(domains);
                 m_linker.onDomain(domainGroup, domains);
               }
            }
            break;
      }
   }

   @Override
   public void visitGroup(Group group) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitGroupChildren(group, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitGroupChildren(Group group, int _field, int _type) {
      switch (_field) {
         case 1:
            group.setId(readString());
            break;
         case 2:
            if (_type == 1) {
                  group.addIp(readString());
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                  group.addIp(readString());
               }
            }
            break;
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
