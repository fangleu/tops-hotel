package com.dianping.cat.configuration.web.url.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.configuration.web.url.IVisitor;
import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static UrlPattern parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static UrlPattern parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      UrlPattern urlPattern = new UrlPattern();

      try {
         urlPattern.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return urlPattern;
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
            code.setId(readInt());
            break;
         case 2:
            code.setName(readString());
            break;
         case 3:
            code.setStatus(readInt());
            break;
      }
   }

   @Override
   public void visitPatternItem(PatternItem patternItem) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitPatternItemChildren(patternItem, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitPatternItemChildren(PatternItem patternItem, int _field, int _type) {
      switch (_field) {
         case 1:
            patternItem.setGroup(readString());
            break;
         case 2:
            patternItem.setName(readString());
            break;
         case 3:
            patternItem.setPattern(readString());
            break;
         case 4:
            patternItem.setDomain(readString());
            break;
         case 5:
            patternItem.setId(readInt());
            break;
      }
   }

   @Override
   public void visitUrlPattern(UrlPattern urlPattern) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitUrlPatternChildren(urlPattern, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitUrlPatternChildren(UrlPattern urlPattern, int _field, int _type) {
      switch (_field) {
         case 33:
            if (_type == 1) {
              PatternItem patternItems = new PatternItem();

              visitPatternItem(patternItems);
              m_linker.onPatternItem(urlPattern, patternItems);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 PatternItem patternItems = new PatternItem();

                 visitPatternItem(patternItems);
                 m_linker.onPatternItem(urlPattern, patternItems);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              Code codes = new Code();

              visitCode(codes);
              m_linker.onCode(urlPattern, codes);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Code codes = new Code();

                 visitCode(codes);
                 m_linker.onCode(urlPattern, codes);
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
