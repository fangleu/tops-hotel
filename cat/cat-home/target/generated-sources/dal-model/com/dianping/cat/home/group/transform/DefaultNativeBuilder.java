package com.dianping.cat.home.group.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.group.IVisitor;
import com.dianping.cat.home.group.entity.Domain;
import com.dianping.cat.home.group.entity.DomainGroup;
import com.dianping.cat.home.group.entity.Group;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(DomainGroup domainGroup) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(domainGroup, out);
      return out.toByteArray();
   }

   public static void build(DomainGroup domainGroup, OutputStream out) {
      domainGroup.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitDomain(Domain domain) {
      if (domain.getId() != null) {
         writeTag(1, 1);
         writeString(domain.getId());
      }

      if (!domain.getGroups().isEmpty()) {
         writeTag(33, 2);
         writeInt(domain.getGroups().size());

         for (Group group : domain.getGroups().values()) {
            group.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitDomainGroup(DomainGroup domainGroup) {
      writeTag(63, 0);

      if (!domainGroup.getDomains().isEmpty()) {
         writeTag(33, 2);
         writeInt(domainGroup.getDomains().size());

         for (Domain domain : domainGroup.getDomains().values()) {
            domain.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitGroup(Group group) {
      if (group.getId() != null) {
         writeTag(1, 1);
         writeString(group.getId());
      }

      if (!group.getIps().isEmpty()) {
         writeTag(2, 2);
         writeInt(group.getIps().size());

         for (String ip : group.getIps()) {
            writeString(ip);
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
