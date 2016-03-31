package com.dianping.cat.configuration.server.entity;

import static com.dianping.cat.configuration.server.Constants.ATTR_ID;
import static com.dianping.cat.configuration.server.Constants.ENTITY_HDFS;

import com.dianping.cat.configuration.server.BaseEntity;
import com.dianping.cat.configuration.server.IVisitor;

public class HdfsConfig extends BaseEntity<HdfsConfig> {
   private String m_id;

   private String m_maxSize = "128M";

   private String m_serverUri;

   private String m_baseDir;

   public HdfsConfig() {
   }

   public HdfsConfig(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitHdfs(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof HdfsConfig) {
         HdfsConfig _o = (HdfsConfig) obj;
         String id = _o.getId();

         return m_id == id || m_id != null && m_id.equals(id);
      }

      return false;
   }

   public String getBaseDir() {
      return m_baseDir;
   }

   public String getId() {
      return m_id;
   }

   public String getMaxSize() {
      return m_maxSize;
   }

   public String getServerUri() {
      return m_serverUri;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(HdfsConfig other) {
      assertAttributeEquals(other, ENTITY_HDFS, ATTR_ID, m_id, other.getId());

      if (other.getMaxSize() != null) {
         m_maxSize = other.getMaxSize();
      }

      if (other.getServerUri() != null) {
         m_serverUri = other.getServerUri();
      }

      if (other.getBaseDir() != null) {
         m_baseDir = other.getBaseDir();
      }
   }

   public HdfsConfig setBaseDir(String baseDir) {
      m_baseDir = baseDir;
      return this;
   }

   public HdfsConfig setId(String id) {
      m_id = id;
      return this;
   }

   public HdfsConfig setMaxSize(String maxSize) {
      m_maxSize = maxSize;
      return this;
   }

   public HdfsConfig setServerUri(String serverUri) {
      m_serverUri = serverUri;
      return this;
   }

}
