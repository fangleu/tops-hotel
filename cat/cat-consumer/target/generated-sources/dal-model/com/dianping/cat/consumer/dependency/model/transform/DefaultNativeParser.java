package com.dianping.cat.consumer.dependency.model.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.consumer.dependency.model.IVisitor;
import com.dianping.cat.consumer.dependency.model.entity.Dependency;
import com.dianping.cat.consumer.dependency.model.entity.DependencyReport;
import com.dianping.cat.consumer.dependency.model.entity.Index;
import com.dianping.cat.consumer.dependency.model.entity.Segment;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static DependencyReport parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static DependencyReport parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      DependencyReport dependencyReport = new DependencyReport();

      try {
         dependencyReport.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return dependencyReport;
   }

   @Override
   public void visitDependency(Dependency dependency) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitDependencyChildren(dependency, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitDependencyChildren(Dependency dependency, int _field, int _type) {
      switch (_field) {
         case 1:
            dependency.setType(readString());
            break;
         case 2:
            dependency.setTarget(readString());
            break;
         case 3:
            dependency.setTotalCount(readLong());
            break;
         case 4:
            dependency.setAvg(readDouble());
            break;
         case 5:
            dependency.setErrorCount(readLong());
            break;
         case 6:
            dependency.setKey(readString());
            break;
         case 7:
            dependency.setSum(readDouble());
            break;
      }
   }

   @Override
   public void visitDependencyReport(DependencyReport dependencyReport) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitDependencyReportChildren(dependencyReport, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitDependencyReportChildren(DependencyReport dependencyReport, int _field, int _type) {
      switch (_field) {
         case 1:
            dependencyReport.setDomain(readString());
            break;
         case 2:
            dependencyReport.setStartTime(readDate());
            break;
         case 3:
            dependencyReport.setEndTime(readDate());
            break;
         case 4:
            if (_type == 1) {
                  dependencyReport.addDomainName(readString());
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                  dependencyReport.addDomainName(readString());
               }
            }
            break;
         case 33:
            if (_type == 1) {
              Segment segments = new Segment();

              visitSegment(segments);
              m_linker.onSegment(dependencyReport, segments);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Segment segments = new Segment();

                 visitSegment(segments);
                 m_linker.onSegment(dependencyReport, segments);
               }
            }
            break;
      }
   }

   @Override
   public void visitIndex(Index index) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitIndexChildren(index, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitIndexChildren(Index index, int _field, int _type) {
      switch (_field) {
         case 1:
            index.setName(readString());
            break;
         case 2:
            index.setTotalCount(readLong());
            break;
         case 3:
            index.setErrorCount(readLong());
            break;
         case 4:
            index.setSum(readDouble());
            break;
         case 5:
            index.setAvg(readDouble());
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
            segment.setExceptionCount(readInt());
            break;
         case 33:
            if (_type == 1) {
              Index indexs = new Index();

              visitIndex(indexs);
              m_linker.onIndex(segment, indexs);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Index indexs = new Index();

                 visitIndex(indexs);
                 m_linker.onIndex(segment, indexs);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              Dependency dependencies = new Dependency();

              visitDependency(dependencies);
              m_linker.onDependency(segment, dependencies);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Dependency dependencies = new Dependency();

                 visitDependency(dependencies);
                 m_linker.onDependency(segment, dependencies);
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
