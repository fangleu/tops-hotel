package com.dianping.cat.home.app.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.app.IVisitor;
import com.dianping.cat.home.app.entity.AppReport;
import com.dianping.cat.home.app.entity.Code;
import com.dianping.cat.home.app.entity.Command;
import com.dianping.cat.home.app.entity.Transaction;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static AppReport parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static AppReport parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      AppReport appReport = new AppReport();

      try {
         appReport.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return appReport;
   }

   @Override
   public void visitAppReport(AppReport appReport) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitAppReportChildren(appReport, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitAppReportChildren(AppReport appReport, int _field, int _type) {
      switch (_field) {
         case 1:
            appReport.setId(readString());
            break;
         case 2:
            appReport.setStartTime(readDate());
            break;
         case 3:
            appReport.setEndTime(readDate());
            break;
         case 33:
            if (_type == 1) {
              Command commands = new Command();

              visitCommand(commands);
              m_linker.onCommand(appReport, commands);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Command commands = new Command();

                 visitCommand(commands);
                 m_linker.onCommand(appReport, commands);
               }
            }
            break;
      }
   }

   @Override
   public void visitCode(Code code) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitCodeChildren(code, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitCodeChildren(Code code, int _field, int _type) {
      switch (_field) {
         case 1:
            code.setId(readString());
            break;
         case 2:
            code.setCount(readLong());
            break;
         case 3:
            code.setSum(readDouble());
            break;
         case 4:
            code.setAvg(readDouble());
            break;
         case 5:
            code.setErrors(readLong());
            break;
         case 6:
            code.setSuccessRatio(readDouble());
            break;
      }
   }

   @Override
   public void visitCommand(Command command) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitCommandChildren(command, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitCommandChildren(Command command, int _field, int _type) {
      switch (_field) {
         case 1:
            command.setId(readInt());
            break;
         case 2:
            command.setName(readString());
            break;
         case 3:
            command.setCount(readLong());
            break;
         case 4:
            command.setSum(readDouble());
            break;
         case 5:
            command.setAvg(readDouble());
            break;
         case 6:
            command.setErrors(readLong());
            break;
         case 7:
            command.setSuccessRatio(readDouble());
            break;
         case 8:
            command.setRequestSum(readLong());
            break;
         case 9:
            command.setRequestAvg(readDouble());
            break;
         case 10:
            command.setResponseSum(readLong());
            break;
         case 11:
            command.setResponseAvg(readDouble());
            break;
         case 33:
            Transaction transaction = new Transaction();

            visitTransaction(transaction);
            m_linker.onTransaction(command, transaction);
            break;
         case 34:
            if (_type == 1) {
              Code codes = new Code();

              visitCode(codes);
              m_linker.onCode(command, codes);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Code codes = new Code();

                 visitCode(codes);
                 m_linker.onCode(command, codes);
               }
            }
            break;
      }
   }

   @Override
   public void visitTransaction(Transaction transaction) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitTransactionChildren(transaction, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitTransactionChildren(Transaction transaction, int _field, int _type) {
      switch (_field) {
         case 1:
            transaction.setUrl(readString());
            break;
         case 2:
            transaction.setCount(readLong());
            break;
         case 3:
            transaction.setAvg(readDouble());
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
