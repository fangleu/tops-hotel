package com.dianping.cat.home.storage.transform;

import java.util.Stack;

import com.dianping.cat.home.storage.IEntity;
import com.dianping.cat.home.storage.IVisitor;
import com.dianping.cat.home.storage.entity.Link;
import com.dianping.cat.home.storage.entity.Machine;
import com.dianping.cat.home.storage.entity.Storage;
import com.dianping.cat.home.storage.entity.StorageGroup;
import com.dianping.cat.home.storage.entity.StorageGroupConfig;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private StorageGroupConfig m_storageGroupConfig;

   public DefaultMerger() {
   }

   public DefaultMerger(StorageGroupConfig storageGroupConfig) {
      m_storageGroupConfig = storageGroupConfig;
      m_objs.push(storageGroupConfig);
   }

   public StorageGroupConfig getStorageGroupConfig() {
      return m_storageGroupConfig;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeLink(Link to, Link from) {
      to.mergeAttributes(from);
      to.getPars().addAll(from.getPars());
   }

   protected void mergeMachine(Machine to, Machine from) {
      to.mergeAttributes(from);
   }

   protected void mergeStorage(Storage to, Storage from) {
      to.mergeAttributes(from);
   }

   protected void mergeStorageGroup(StorageGroup to, StorageGroup from) {
      to.mergeAttributes(from);
   }

   protected void mergeStorageGroupConfig(StorageGroupConfig to, StorageGroupConfig from) {
      to.mergeAttributes(from);
   }

   @Override
   public void visitLink(Link from) {
      Link to = (Link) m_objs.peek();

      mergeLink(to, from);
      visitLinkChildren(to, from);
   }

   protected void visitLinkChildren(Link to, Link from) {
   }

   @Override
   public void visitMachine(Machine from) {
      Machine to = (Machine) m_objs.peek();

      mergeMachine(to, from);
      visitMachineChildren(to, from);
   }

   protected void visitMachineChildren(Machine to, Machine from) {
   }

   @Override
   public void visitStorage(Storage from) {
      Storage to = (Storage) m_objs.peek();

      mergeStorage(to, from);
      visitStorageChildren(to, from);
   }

   protected void visitStorageChildren(Storage to, Storage from) {
      for (Machine source : from.getMachines().values()) {
         Machine target = to.findMachine(source.getId());

         if (target == null) {
            target = new Machine(source.getId());
            to.addMachine(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitStorageGroup(StorageGroup from) {
      StorageGroup to = (StorageGroup) m_objs.peek();

      mergeStorageGroup(to, from);
      visitStorageGroupChildren(to, from);
   }

   protected void visitStorageGroupChildren(StorageGroup to, StorageGroup from) {
      if (from.getLink() != null) {
         Link target = to.getLink();

         if (target == null) {
            target = new Link();
            to.setLink(target);
         }

         m_objs.push(target);
         from.getLink().accept(this);
         m_objs.pop();
      }

      for (Storage source : from.getStorages().values()) {
         Storage target = to.findStorage(source.getId());

         if (target == null) {
            target = new Storage(source.getId());
            to.addStorage(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitStorageGroupConfig(StorageGroupConfig from) {
      StorageGroupConfig to = (StorageGroupConfig) m_objs.peek();

      mergeStorageGroupConfig(to, from);
      visitStorageGroupConfigChildren(to, from);
   }

   protected void visitStorageGroupConfigChildren(StorageGroupConfig to, StorageGroupConfig from) {
      for (StorageGroup source : from.getStorageGroups().values()) {
         StorageGroup target = to.findStorageGroup(source.getId());

         if (target == null) {
            target = new StorageGroup(source.getId());
            to.addStorageGroup(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }
}
