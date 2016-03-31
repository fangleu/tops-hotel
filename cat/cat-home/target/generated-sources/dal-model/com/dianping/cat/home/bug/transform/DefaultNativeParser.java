package com.dianping.cat.home.bug.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.bug.IVisitor;
import com.dianping.cat.home.bug.entity.BugReport;
import com.dianping.cat.home.bug.entity.Domain;
import com.dianping.cat.home.bug.entity.ExceptionItem;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static BugReport parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static BugReport parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      BugReport bugReport = new BugReport();

      try {
         bugReport.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return bugReport;
   }

   @Override
   public void visitBugReport(BugReport bugReport) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitBugReportChildren(bugReport, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitBugReportChildren(BugReport bugReport, int _field, int _type) {
      switch (_field) {
         case 1:
            bugReport.setStartTime(readDate());
            break;
         case 2:
            bugReport.setDomain(readString());
            break;
         case 3:
            bugReport.setEndTime(readDate());
            break;
         case 33:
            if (_type == 1) {
              Domain domains = new Domain();

              visitDomain(domains);
              m_linker.onDomain(bugReport, domains);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Domain domains = new Domain();

                 visitDomain(domains);
                 m_linker.onDomain(bugReport, domains);
               }
            }
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
            domain.setProblemUrl(readString());
            break;
         case 3:
            domain.setDepartment(readString());
            break;
         case 4:
            domain.setProductLine(readString());
            break;
         case 5:
            domain.setExcpetion(readString());
            break;
         case 33:
            if (_type == 1) {
              ExceptionItem exceptionItems = new ExceptionItem();

              visitExceptionItem(exceptionItems);
              m_linker.onExceptionItem(domain, exceptionItems);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 ExceptionItem exceptionItems = new ExceptionItem();

                 visitExceptionItem(exceptionItems);
                 m_linker.onExceptionItem(domain, exceptionItems);
               }
            }
            break;
      }
   }

   @Override
   public void visitExceptionItem(ExceptionItem exceptionItem) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitExceptionItemChildren(exceptionItem, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitExceptionItemChildren(ExceptionItem exceptionItem, int _field, int _type) {
      switch (_field) {
         case 1:
            exceptionItem.setId(readString());
            break;
         case 2:
            exceptionItem.setCount(readInt());
            break;
         case 3:
            if (_type == 1) {
                  exceptionItem.addMessage(readString());
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                  exceptionItem.addMessage(readString());
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
