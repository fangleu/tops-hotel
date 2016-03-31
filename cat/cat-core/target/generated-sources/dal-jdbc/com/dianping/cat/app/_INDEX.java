package com.dianping.cat.app;

public class _INDEX {
   public static Class<?>[] getEntityClasses() {
      return new Class<?>[] { AppCommandDataEntity.class, AppConnectionDataEntity.class, AppSpeedDataEntity.class, WebApiDataEntity.class };
   }

   public static Class<?>[] getDaoClasses() {
      return new Class<?>[] { AppCommandDataDao.class, AppConnectionDataDao.class, AppSpeedDataDao.class, WebApiDataDao.class };
   }

   public static Class<?>[] getDoClasses() {
      return new Class<?>[] { AppCommandData.class, AppConnectionData.class, AppSpeedData.class, WebApiData.class };
   }

}
