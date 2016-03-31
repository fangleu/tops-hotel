package com.dianping.cat.core.config;

public class _INDEX {
   public static Class<?>[] getEntityClasses() {
      return new Class<?>[] { ConfigEntity.class };
   }

   public static Class<?>[] getDaoClasses() {
      return new Class<?>[] { ConfigDao.class };
   }

   public static Class<?>[] getDoClasses() {
      return new Class<?>[] { Config.class };
   }

}
