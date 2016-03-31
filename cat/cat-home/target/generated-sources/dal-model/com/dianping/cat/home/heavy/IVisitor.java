package com.dianping.cat.home.heavy;

import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public interface IVisitor {

   public void visitHeavyCache(HeavyCache heavyCache);

   public void visitHeavyCall(HeavyCall heavyCall);

   public void visitHeavyReport(HeavyReport heavyReport);

   public void visitHeavySql(HeavySql heavySql);

   public void visitService(Service service);

   public void visitUrl(Url url);
}
