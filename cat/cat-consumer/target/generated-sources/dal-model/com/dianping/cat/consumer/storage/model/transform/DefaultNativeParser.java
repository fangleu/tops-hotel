package com.dianping.cat.consumer.storage.model.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.consumer.storage.model.IVisitor;
import com.dianping.cat.consumer.storage.model.entity.Domain;
import com.dianping.cat.consumer.storage.model.entity.Machine;
import com.dianping.cat.consumer.storage.model.entity.Operation;
import com.dianping.cat.consumer.storage.model.entity.Segment;
import com.dianping.cat.consumer.storage.model.entity.Sql;
import com.dianping.cat.consumer.storage.model.entity.StorageReport;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static StorageReport parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static StorageReport parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      StorageReport storageReport = new StorageReport();

      try {
         storageReport.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return storageReport;
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
              Operation operations = new Operation();

              visitOperation(operations);
              m_linker.onOperation(domain, operations);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Operation operations = new Operation();

                 visitOperation(operations);
                 m_linker.onOperation(domain, operations);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              Sql sqls = new Sql();

              visitSql(sqls);
              m_linker.onSql(domain, sqls);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Sql sqls = new Sql();

                 visitSql(sqls);
                 m_linker.onSql(domain, sqls);
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
              Domain domains = new Domain();

              visitDomain(domains);
              m_linker.onDomain(machine, domains);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Domain domains = new Domain();

                 visitDomain(domains);
                 m_linker.onDomain(machine, domains);
               }
            }
            break;
      }
   }

   @Override
   public void visitOperation(Operation operation) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitOperationChildren(operation, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitOperationChildren(Operation operation, int _field, int _type) {
      switch (_field) {
         case 1:
            operation.setId(readString());
            break;
         case 2:
            operation.setCount(readLong());
            break;
         case 3:
            operation.setAvg(readDouble());
            break;
         case 4:
            operation.setSum(readDouble());
            break;
         case 5:
            operation.setError(readLong());
            break;
         case 6:
            operation.setLongCount(readLong());
            break;
         case 33:
            if (_type == 1) {
              Segment segments = new Segment();

              visitSegment(segments);
              m_linker.onSegment(operation, segments);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Segment segments = new Segment();

                 visitSegment(segments);
                 m_linker.onSegment(operation, segments);
               }
            }
            break;
      }
   }

   @Override
   public void visitSegment(Segment segment) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitSegmentChildren(segment, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitSegmentChildren(Segment segment, int _field, int _type) {
      switch (_field) {
         case 1:
            segment.setId(readInt());
            break;
         case 2:
            segment.setCount(readLong());
            break;
         case 3:
            segment.setAvg(readDouble());
            break;
         case 4:
            segment.setSum(readDouble());
            break;
         case 5:
            segment.setError(readLong());
            break;
         case 6:
            segment.setLongCount(readLong());
            break;
      }
   }

   @Override
   public void visitSql(Sql sql) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitSqlChildren(sql, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitSqlChildren(Sql sql, int _field, int _type) {
      switch (_field) {
         case 1:
            sql.setId(readString());
            break;
         case 2:
            sql.setStatement(readString());
            break;
         case 3:
            sql.setCount(readInt());
            break;
      }
   }

   @Override
   public void visitStorageReport(StorageReport storageReport) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitStorageReportChildren(storageReport, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitStorageReportChildren(StorageReport storageReport, int _field, int _type) {
      switch (_field) {
         case 1:
            storageReport.setId(readString());
            break;
         case 2:
            storageReport.setName(readString());
            break;
         case 3:
            storageReport.setType(readString());
            break;
         case 4:
            storageReport.setStartTime(readDate());
            break;
         case 5:
            storageReport.setEndTime(readDate());
            break;
         case 6:
            if (_type == 1) {
                  storageReport.addId(readString());
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                  storageReport.addId(readString());
               }
            }
            break;
         case 7:
            if (_type == 1) {
                  storageReport.addIp(readString());
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                  storageReport.addIp(readString());
               }
            }
            break;
         case 8:
            if (_type == 1) {
                  storageReport.addOp(readString());
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                  storageReport.addOp(readString());
               }
            }
            break;
         case 33:
            if (_type == 1) {
              Machine machines = new Machine();

              visitMachine(machines);
              m_linker.onMachine(storageReport, machines);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Machine machines = new Machine();

                 visitMachine(machines);
                 m_linker.onMachine(storageReport, machines);
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
