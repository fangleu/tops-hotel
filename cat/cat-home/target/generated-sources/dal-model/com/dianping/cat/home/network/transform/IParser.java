package com.dianping.cat.home.network.transform;

import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public interface IParser<T> {
   public NetGraphSet parse(IMaker<T> maker, ILinker linker, T node);

   public void parseForAnchor(IMaker<T> maker, ILinker linker, Anchor parent, T node);

   public void parseForConnection(IMaker<T> maker, ILinker linker, Connection parent, T node);

   public void parseForInterface(IMaker<T> maker, ILinker linker, Interface parent, T node);

   public void parseForNetGraph(IMaker<T> maker, ILinker linker, NetGraph parent, T node);

   public void parseForNetTopology(IMaker<T> maker, ILinker linker, NetTopology parent, T node);

   public void parseForSwitch(IMaker<T> maker, ILinker linker, Switch parent, T node);
}
