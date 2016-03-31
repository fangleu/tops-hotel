package com.dianping.cat.home.jar.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.jar.IVisitor;
import com.dianping.cat.home.jar.entity.Domain;
import com.dianping.cat.home.jar.entity.Jar;
import com.dianping.cat.home.jar.entity.JarReport;
import com.dianping.cat.home.jar.entity.Machine;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static JarReport parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static JarReport parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      JarReport jarReport = new JarReport();

      try {
         jarReport.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return jarReport;
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
              Machine machines = new Machine();

              visitMachine(machines);
              m_linker.onMachine(domain, machines);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Machine machines = new Machine();

                 visitMachine(machines);
                 m_linker.onMachine(domain, machines);
               }
            }
            break;
      }
   }

   @Override
   public void visitJar(Jar jar) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitJarChildren(jar, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitJarChildren(Jar jar, int _field, int _type) {
      switch (_field) {
         case 1:
            jar.setId(readString());
            break;
         case 2:
            jar.setVersion(readString());
            break;
      }
   }

   @Override
   public void visitJarReport(JarReport jarReport) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitJarReportChildren(jarReport, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitJarReportChildren(JarReport jarReport, int _field, int _type) {
      switch (_field) {
         case 1:
            jarReport.setDomain(readString());
            break;
         case 2:
            jarReport.setStartTime(readDate());
            break;
         case 3:
            jarReport.setEndTime(readDate());
            break;
         case 33:
            if (_type == 1) {
              Domain domains = new Domain();

              visitDomain(domains);
              m_linker.onDomain(jarReport, domains);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Domain domains = new Domain();

                 visitDomain(domains);
                 m_linker.onDomain(jarReport, domains);
               }
            }
            break;
      }
   }

   @Override
   public void visitMachine(Machine machine) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitMachineChildren(machine, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitMachineChildren(Machine machine, int _field, int _type) {
      switch (_field) {
         case 1:
            machine.setId(readString());
            break;
         case 33:
            if (_type == 1) {
              Jar jars = new Jar();

              visitJar(jars);
              m_linker.onJar(machine, jars);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Jar jars = new Jar();

                 visitJar(jars);
                 m_linker.onJar(machine, jars);
               }
            }
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
