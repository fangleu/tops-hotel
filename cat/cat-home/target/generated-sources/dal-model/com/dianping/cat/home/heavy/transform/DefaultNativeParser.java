package com.dianping.cat.home.heavy.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.heavy.IVisitor;
import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static HeavyReport parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static HeavyReport parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      HeavyReport heavyReport = new HeavyReport();

      try {
         heavyReport.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return heavyReport;
   }

   @Override
   public void visitHeavyCache(HeavyCache heavyCache) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitHeavyCacheChildren(heavyCache, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitHeavyCacheChildren(HeavyCache heavyCache, int _field, int _type) {
      switch (_field) {
         case 33:
            if (_type == 1) {
              Url urls = new Url();

              visitUrl(urls);
              m_linker.onUrl(heavyCache, urls);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Url urls = new Url();

                 visitUrl(urls);
                 m_linker.onUrl(heavyCache, urls);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              Service services = new Service();

              visitService(services);
              m_linker.onService(heavyCache, services);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Service services = new Service();

                 visitService(services);
                 m_linker.onService(heavyCache, services);
               }
            }
            break;
      }
   }

   @Override
   public void visitHeavyCall(HeavyCall heavyCall) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitHeavyCallChildren(heavyCall, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitHeavyCallChildren(HeavyCall heavyCall, int _field, int _type) {
      switch (_field) {
         case 33:
            if (_type == 1) {
              Url urls = new Url();

              visitUrl(urls);
              m_linker.onUrl(heavyCall, urls);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Url urls = new Url();

                 visitUrl(urls);
                 m_linker.onUrl(heavyCall, urls);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              Service services = new Service();

              visitService(services);
              m_linker.onService(heavyCall, services);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Service services = new Service();

                 visitService(services);
                 m_linker.onService(heavyCall, services);
               }
            }
            break;
      }
   }

   @Override
   public void visitHeavyReport(HeavyReport heavyReport) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitHeavyReportChildren(heavyReport, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitHeavyReportChildren(HeavyReport heavyReport, int _field, int _type) {
      switch (_field) {
         case 1:
            heavyReport.setDomain(readString());
            break;
         case 2:
            heavyReport.setStartTime(readDate());
            break;
         case 3:
            heavyReport.setEndTime(readDate());
            break;
         case 33:
            HeavySql heavySql = new HeavySql();

            visitHeavySql(heavySql);
            m_linker.onHeavySql(heavyReport, heavySql);
            break;
         case 34:
            HeavyCall heavyCall = new HeavyCall();

            visitHeavyCall(heavyCall);
            m_linker.onHeavyCall(heavyReport, heavyCall);
            break;
         case 35:
            HeavyCache heavyCache = new HeavyCache();

            visitHeavyCache(heavyCache);
            m_linker.onHeavyCache(heavyReport, heavyCache);
            break;
      }
   }

   @Override
   public void visitHeavySql(HeavySql heavySql) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitHeavySqlChildren(heavySql, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitHeavySqlChildren(HeavySql heavySql, int _field, int _type) {
      switch (_field) {
         case 33:
            if (_type == 1) {
              Url urls = new Url();

              visitUrl(urls);
              m_linker.onUrl(heavySql, urls);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Url urls = new Url();

                 visitUrl(urls);
                 m_linker.onUrl(heavySql, urls);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              Service services = new Service();

              visitService(services);
              m_linker.onService(heavySql, services);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Service services = new Service();

                 visitService(services);
                 m_linker.onService(heavySql, services);
               }
            }
            break;
      }
   }

   @Override
   public void visitService(Service service) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitServiceChildren(service, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitServiceChildren(Service service, int _field, int _type) {
      switch (_field) {
         case 1:
            service.setDomain(readString());
            break;
         case 2:
            service.setName(readString());
            break;
         case 3:
            service.setLogview(readString());
            break;
         case 4:
            service.setCount(readLong());
            break;
         case 5:
            service.setKey(readString());
            break;
      }
   }

   @Override
   public void visitUrl(Url url) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitUrlChildren(url, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitUrlChildren(Url url, int _field, int _type) {
      switch (_field) {
         case 1:
            url.setDomain(readString());
            break;
         case 2:
            url.setName(readString());
            break;
         case 3:
            url.setLogview(readString());
            break;
         case 4:
            url.setCount(readLong());
            break;
         case 5:
            url.setKey(readString());
            break;
      }
   }

   private java.util.Date readDate() {
      try {
         return new java.util.Date(readVarint(64));
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

   private long readLong() {
      try {
         return readVarint(64);
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
