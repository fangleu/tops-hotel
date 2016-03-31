package com.dianping.cat.configuration.web.url.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.configuration.web.url.IVisitor;
import com.dianping.cat.configuration.web.url.entity.Code;
import com.dianping.cat.configuration.web.url.entity.PatternItem;
import com.dianping.cat.configuration.web.url.entity.UrlPattern;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(UrlPattern urlPattern) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(urlPattern, out);
      return out.toByteArray();
   }

   public static void build(UrlPattern urlPattern, OutputStream out) {
      urlPattern.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitCode(Code code) {
      if (code.getId() != null) {
         writeTag(1, 1);
         writeInt(code.getId());
      }

      if (code.getName() != null) {
         writeTag(2, 1);
         writeString(code.getName());
      }

      if (code.getStatus() != null) {
         writeTag(3, 1);
         writeInt(code.getStatus());
      }

      writeTag(63, 3);
   }

   @Override
   public void visitPatternItem(PatternItem patternItem) {
      if (patternItem.getGroup() != null) {
         writeTag(1, 1);
         writeString(patternItem.getGroup());
      }

      if (patternItem.getName() != null) {
         writeTag(2, 1);
         writeString(patternItem.getName());
      }

      if (patternItem.getPattern() != null) {
         writeTag(3, 1);
         writeString(patternItem.getPattern());
      }

      if (patternItem.getDomain() != null) {
         writeTag(4, 1);
         writeString(patternItem.getDomain());
      }

      writeTag(5, 0);
      writeInt(patternItem.getId());

      writeTag(63, 3);
   }

   @Override
   public void visitUrlPattern(UrlPattern urlPattern) {
      writeTag(63, 0);

      if (!urlPattern.getPatternItems().isEmpty()) {
         writeTag(33, 2);
         writeInt(urlPattern.getPatternItems().size());

         for (PatternItem patternItem : urlPattern.getPatternItems().values()) {
            patternItem.accept(m_visitor);
         }
      }

      if (!urlPattern.getCodes().isEmpty()) {
         writeTag(34, 2);
         writeInt(urlPattern.getCodes().size());

         for (Code code : urlPattern.getCodes().values()) {
            code.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   private void writeInt(int value) {
      try {
         writeVarint(value & 0xFFFFFFFFL);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeString(String value) {
      try {
         m_out.writeUTF(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeTag(int field, int type) {
      try {
         m_out.writeByte((field << 2) + type);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected void writeVarint(long value) throws IOException {
      while (true) {
         if ((value & ~0x7FL) == 0) {
            m_out.writeByte((byte) value);
            return;
         } else {
            m_out.writeByte(((byte) value & 0x7F) | 0x80);
            value >>>= 7;
         }
      }
   }
}
