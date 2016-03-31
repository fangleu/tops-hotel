package com.dianping.cat.home.system.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.system.IVisitor;
import com.dianping.cat.home.system.entity.Day;
import com.dianping.cat.home.system.entity.Domain;
import com.dianping.cat.home.system.entity.Entity;
import com.dianping.cat.home.system.entity.Machine;
import com.dianping.cat.home.system.entity.Rush;
import com.dianping.cat.home.system.entity.SystemReport;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static SystemReport parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static SystemReport parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      SystemReport systemReport = new SystemReport();

      try {
         systemReport.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return systemReport;
   }

   @Override
   public void visitDay(Day day) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitDayChildren(day, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitDayChildren(Day day, int _field, int _type) {
      switch (_field) {
         case 1:
            day.setCount(readLong());
            break;
         case 2:
            day.setSum(readDouble());
            break;
         case 3:
            day.setAvg(readDouble());
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
              Entity entities = new Entity();

              visitEntity(entities);
              m_linker.onEntity(domain, entities);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Entity entities = new Entity();

                 visitEntity(entities);
                 m_linker.onEntity(domain, entities);
               }
            }
            break;
      }
   }

   @Override
   public void visitEntity(Entity entity) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitEntityChildren(entity, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitEntityChildren(Entity entity, int _field, int _type) {
      switch (_field) {
         case 1:
            entity.setId(readString());
            break;
         case 33:
            Rush rush = new Rush();

            visitRush(rush);
            m_linker.onRush(entity, rush);
            break;
         case 34:
            Day day = new Day();

            visitDay(day);
            m_linker.onDay(entity, day);
            break;
         case 35:
            if (_type == 1) {
              Machine machines = new Machine();

              visitMachine(machines);
              m_linker.onMachine(entity, machines);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Machine machines = new Machine();

                 visitMachine(machines);
                 m_linker.onMachine(entity, machines);
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
            machine.setIp(readString());
            break;
      }
   }

   @Override
   public void visitRush(Rush rush) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitRushChildren(rush, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitRushChildren(Rush rush, int _field, int _type) {
      switch (_field) {
         case 1:
            rush.setCount(readLong());
            break;
         case 2:
            rush.setSum(readDouble());
            break;
         case 3:
            rush.setAvg(readDouble());
            break;
      }
   }

   @Override
   public void visitSystemReport(SystemReport systemReport) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitSystemReportChildren(systemReport, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitSystemReportChildren(SystemReport systemReport, int _field, int _type) {
      switch (_field) {
         case 1:
            systemReport.setStartTime(readDate());
            break;
         case 2:
            systemReport.setEndTime(readDate());
            break;
         case 33:
            if (_type == 1) {
              Domain domains = new Domain();

              visitDomain(domains);
              m_linker.onDomain(systemReport, domains);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Domain domains = new Domain();

                 visitDomain(domains);
                 m_linker.onDomain(systemReport, domains);
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
