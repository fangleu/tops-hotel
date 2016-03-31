package com.dianping.cat.home.heavy.transform;

import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public interface ILinker {

   public boolean onHeavyCache(HeavyReport parent, HeavyCache heavyCache);

   public boolean onHeavyCall(HeavyReport parent, HeavyCall heavyCall);

   public boolean onHeavySql(HeavyReport parent, HeavySql heavySql);

   public boolean onService(HeavySql parent, Service service);

   public boolean onService(HeavyCall parent, Service service);

   public boolean onService(HeavyCache parent, Service service);

   public boolean onUrl(HeavySql parent, Url url);

   public boolean onUrl(HeavyCall parent, Url url);

   public boolean onUrl(HeavyCache parent, Url url);
}
