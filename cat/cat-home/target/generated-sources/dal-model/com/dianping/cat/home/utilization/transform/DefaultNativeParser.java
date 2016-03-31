package com.dianping.cat.home.utilization.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.utilization.IVisitor;
import com.dianping.cat.home.utilization.entity.ApplicationState;
import com.dianping.cat.home.utilization.entity.Domain;
import com.dianping.cat.home.utilization.entity.MachineState;
import com.dianping.cat.home.utilization.entity.UtilizationReport;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static UtilizationReport parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static UtilizationReport parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      UtilizationReport utilizationReport = new UtilizationReport();

      try {
         utilizationReport.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return utilizationReport;
   }

   @Override
   public void visitApplicationState(ApplicationState applicationState) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitApplicationStateChildren(applicationState, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitApplicationStateChildren(ApplicationState applicationState, int _field, int _type) {
      switch (_field) {
         case 1:
            applicationState.setId(readString());
            break;
         case 2:
            applicationState.setFailureCount(readLong());
            break;
         case 3:
            applicationState.setFailurePercent(readDouble());
            break;
         case 4:
            applicationState.setCount(readLong());
            break;
         case 5:
            applicationState.setMaxQps(readDouble());
            break;
         case 6:
            applicationState.setAvg(readDouble());
            break;
         case 7:
            applicationState.setSum(readDouble());
            break;
         case 8:
            applicationState.setAvg95(readDouble());
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
         case 2:
            domain.setMachineNumber(readInt());
            break;
         case 3:
            domain.setCmdbId(readString());
            break;
         case 33:
            if (_type == 1) {
              MachineState machineStates = new MachineState();

              visitMachineState(machineStates);
              m_linker.onMachineState(domain, machineStates);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 MachineState machineStates = new MachineState();

                 visitMachineState(machineStates);
                 m_linker.onMachineState(domain, machineStates);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              ApplicationState applicationStates = new ApplicationState();

              visitApplicationState(applicationStates);
              m_linker.onApplicationState(domain, applicationStates);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 ApplicationState applicationStates = new ApplicationState();

                 visitApplicationState(applicationStates);
                 m_linker.onApplicationState(domain, applicationStates);
               }
            }
            break;
      }
   }

   @Override
   public void visitMachineState(MachineState machineState) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitMachineStateChildren(machineState, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitMachineStateChildren(MachineState machineState, int _field, int _type) {
      switch (_field) {
         case 1:
            machineState.setId(readString());
            break;
         case 2:
            machineState.setCount(readLong());
            break;
         case 3:
            machineState.setSum(readDouble());
            break;
         case 4:
            machineState.setAvg(readDouble());
            break;
         case 5:
            machineState.setAvgMax(readDouble());
            break;
      }
   }

   @Override
   public void visitUtilizationReport(UtilizationReport utilizationReport) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitUtilizationReportChildren(utilizationReport, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitUtilizationReportChildren(UtilizationReport utilizationReport, int _field, int _type) {
      switch (_field) {
         case 1:
            utilizationReport.setDomain(readString());
            break;
         case 2:
            utilizationReport.setStartTime(readDate());
            break;
         case 3:
            utilizationReport.setEndTime(readDate());
            break;
         case 33:
            if (_type == 1) {
              Domain domains = new Domain();

              visitDomain(domains);
              m_linker.onDomain(utilizationReport, domains);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Domain domains = new Domain();

                 visitDomain(domains);
                 m_linker.onDomain(utilizationReport, domains);
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
