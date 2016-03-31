package com.dianping.cat.configuration.server.filter.transform;

import static com.dianping.cat.configuration.server.filter.Constants.ATTR_ID;
import static com.dianping.cat.configuration.server.filter.Constants.ENTITY_CRASH_LOG_DOMAIN;
import static com.dianping.cat.configuration.server.filter.Constants.ENTITY_SERVER_FILTER_CONFIG;

import java.util.Stack;

import com.dianping.cat.configuration.server.filter.IVisitor;
import com.dianping.cat.configuration.server.filter.entity.CrashLogDomain;
import com.dianping.cat.configuration.server.filter.entity.ServerFilterConfig;

public class DefaultValidator implements IVisitor {

   private Path m_path = new Path();
   
   protected void assertRequired(String name, Object value) {
      if (value == null) {
         throw new RuntimeException(String.format("%s at path(%s) is required!", name, m_path));
      }
   }

   @Override
   public void visitCrashLogDomain(CrashLogDomain crashLogDomain) {
      m_path.down(ENTITY_CRASH_LOG_DOMAIN);

      assertRequired(ATTR_ID, crashLogDomain.getId());

      m_path.up(ENTITY_CRASH_LOG_DOMAIN);
   }

   @Override
   public void visitServerFilterConfig(ServerFilterConfig serverFilterConfig) {
      m_path.down(ENTITY_SERVER_FILTER_CONFIG);

      visitServerFilterConfigChildren(serverFilterConfig);

      m_path.up(ENTITY_SERVER_FILTER_CONFIG);
   }

   protected void visitServerFilterConfigChildren(ServerFilterConfig serverFilterConfig) {
      for (CrashLogDomain crashLogDomain : serverFilterConfig.getCrashLogDomains().values()) {
         visitCrashLogDomain(crashLogDomain);
      }
   }

   static class Path {
      private Stack<String> m_sections = new Stack<String>();

      public Path down(String nextSection) {
         m_sections.push(nextSection);

         return this;
      }

      @Override
      public String toString() {
         StringBuilder sb = new StringBuilder();

         for (String section : m_sections) {
            sb.append('/').append(section);
         }

         return sb.toString();
      }

      public Path up(String currentSection) {
         if (m_sections.isEmpty() || !m_sections.peek().equals(currentSection)) {
            throw new RuntimeException("INTERNAL ERROR: stack mismatched!");
         }

         m_sections.pop();
         return this;
      }
   }
}
