package com.dianping.cat.home.heavy.transform;

import com.dianping.cat.home.heavy.entity.HeavyCache;
import com.dianping.cat.home.heavy.entity.HeavyCall;
import com.dianping.cat.home.heavy.entity.HeavyReport;
import com.dianping.cat.home.heavy.entity.HeavySql;
import com.dianping.cat.home.heavy.entity.Service;
import com.dianping.cat.home.heavy.entity.Url;

public interface IParser<T> {
   public HeavyReport parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForHeavyCache(IMaker<T> maker, ILinker linker, HeavyCache parent, T node);

   public void parseForHeavyCall(IMaker<T> maker, ILinker linker, HeavyCall parent, T node);

   public void parseForHeavySql(IMaker<T> maker, ILinker linker, HeavySql parent, T node);

   public void parseForService(IMaker<T> maker, ILinker linker, Service parent, T node);

   public void parseForUrl(IMaker<T> maker, ILinker linker, Url parent, T node);
}
