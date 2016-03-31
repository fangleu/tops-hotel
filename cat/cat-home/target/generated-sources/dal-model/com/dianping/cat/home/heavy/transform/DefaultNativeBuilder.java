package com.dianping.cat.home.heavy.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.heavy.IVisitor;
import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(HeavyReport heavyReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(heavyReport, out);
      return out.toByteArray();
   }

   public static void build(HeavyReport heavyReport, OutputStream out) {
      heavyReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitHeavyCache(HeavyCache heavyCache) {
      if (!heavyCache.getUrls().isEmpty()) {
         writeTag(33, 2);
         writeInt(heavyCache.getUrls().size());

         for (Url url : heavyCache.getUrls().values()) {
            url.accept(m_visitor);
         }
      }

      if (!heavyCache.getServices().isEmpty()) {
         writeTag(34, 2);
         writeInt(heavyCache.getServices().size());

         for (Service service : heavyCache.getServices().values()) {
            service.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitHeavyCall(HeavyCall heavyCall) {
      if (!heavyCall.getUrls().isEmpty()) {
         writeTag(33, 2);
         writeInt(heavyCall.getUrls().size());

         for (Url url : heavyCall.getUrls().values()) {
            url.accept(m_visitor);
         }
      }

      if (!heavyCall.getServices().isEmpty()) {
         writeTag(34, 2);
         writeInt(heavyCall.getServices().size());

         for (Service service : heavyCall.getServices().values()) {
            service.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitHeavyReport(HeavyReport heavyReport) {
      writeTag(63, 0);

      if (heavyReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(heavyReport.getDomain());
      }

      if (heavyReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(heavyReport.getStartTime());
      }

      if (heavyReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(heavyReport.getEndTime());
      }

      if (heavyReport.getHeavySql() != null) {
         writeTag(33, 1);
         heavyReport.getHeavySql().accept(m_visitor);
      }

      if (heavyReport.getHeavyCall() != null) {
         writeTag(34, 1);
         heavyReport.getHeavyCall().accept(m_visitor);
      }

      if (heavyReport.getHeavyCache() != null) {
         writeTag(35, 1);
         heavyReport.getHeavyCache().accept(m_visitor);
      }

      writeTag(63, 3);
   }

   @Override
   public void visitHeavySql(HeavySql heavySql) {
      if (!heavySql.getUrls().isEmpty()) {
         writeTag(33, 2);
         writeInt(heavySql.getUrls().size());

         for (Url url : heavySql.getUrls().values()) {
            url.accept(m_visitor);
         }
      }

      if (!heavySql.getServices().isEmpty()) {
         writeTag(34, 2);
         writeInt(heavySql.getServices().size());

         for (Service service : heavySql.getServices().values()) {
            service.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitService(Service service) {
      if (service.getDomain() != null) {
         writeTag(1, 1);
         writeString(service.getDomain());
      }

      if (service.getName() != null) {
         writeTag(2, 1);
         writeString(service.getName());
      }

      if (service.getLogview() != null) {
         writeTag(3, 1);
         writeString(service.getLogview());
      }

      writeTag(4, 0);
      writeLong(service.getCount());

      if (service.getKey() != null) {
         writeTag(5, 1);
         writeString(service.getKey());
      }

      writeTag(63, 3);
   }

   @Override
   public void visitUrl(Url url) {
      if (url.getDomain() != null) {
         writeTag(1, 1);
         writeString(url.getDomain());
      }

      if (url.getName() != null) {
         writeTag(2, 1);
         writeString(url.getName());
      }

      if (url.getLogview() != null) {
         writeTag(3, 1);
         writeString(url.getLogview());
      }

      writeTag(4, 0);
      writeLong(url.getCount());

      if (url.getKey() != null) {
         writeTag(5, 1);
         writeString(url.getKey());
      }

      writeTag(63, 3);
   }

   private void writeDate(java.util.Date value) {
      try {
         writeVarint(value.getTime());
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeInt(int value) {
      try {
         writeVarint(value & 0xFFFFFFFFL);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeLong(long value) {
      try {
         writeVarint(value);
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
