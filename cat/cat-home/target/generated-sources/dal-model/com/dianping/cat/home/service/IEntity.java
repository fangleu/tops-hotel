package com.dianping.cat.home.service;

public interface IEntity<T> {
   public void accept(IVisitor visitor);

   public void mergeAttributes(T other);

}
