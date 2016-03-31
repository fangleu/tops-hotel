package com.dianping.cat.home.heavy.transform;

import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public interface IMaker<T> {

   public HeavyCache buildHeavyCache(T node);

   public HeavyCall buildHeavyCall(T node);

   public HeavyReport buildHeavyReport(T node);

   public HeavySql buildHeavySql(T node);

   public Service buildService(T node);

   public Url buildUrl(T node);
}
