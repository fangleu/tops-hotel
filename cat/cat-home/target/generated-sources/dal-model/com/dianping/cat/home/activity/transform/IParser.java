package com.dianping.cat.home.activity.transform;

import com.dianping.cat.home.activity.entity.Activity;
import com.dianping.cat.home.activity.entity.ActivityConfig;

public interface IParser<T> {
   public ActivityConfig parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForActivity(IMaker<T> maker, ILinker linker, Activity parent, T node);
}
