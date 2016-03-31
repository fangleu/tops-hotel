package com.dianping.cat.status.model.transform;

import java.util.Stack;

import com.dianping.cat.status.model.IEntity;
import com.dianping.cat.status.model.IVisitor;
import com.dianping.cat.status.model.entity.DiskInfo;
import com.dianping.cat.status.model.entity.DiskVolumeInfo;
import com.dianping.cat.status.model.entity.Extension;
import com.dianping.cat.status.model.entity.ExtensionDetail;
import com.dianping.cat.status.model.entity.GcInfo;
import com.dianping.cat.status.model.entity.MemoryInfo;
import com.dianping.cat.status.model.entity.MessageInfo;
import com.dianping.cat.status.model.entity.OsInfo;
import com.dianping.cat.status.model.entity.RuntimeInfo;
import com.dianping.cat.status.model.entity.StatusInfo;
import com.dianping.cat.status.model.entity.ThreadsInfo;

public class DefaultMerger implements IVisitor {

   private Stack<Object> m_objs = new Stack<Object>();

   private StatusInfo m_status;

   public DefaultMerger() {
   }

   public DefaultMerger(StatusInfo status) {
      m_status = status;
      m_objs.push(status);
   }

   public StatusInfo getStatus() {
      return m_status;
   }

   protected Stack<Object> getObjects() {
      return m_objs;
   }

   public <T> void merge(IEntity<T> to, IEntity<T> from) {
      m_objs.push(to);
      from.accept(this);
      m_objs.pop();
   }

   protected void mergeDisk(DiskInfo to, DiskInfo from) {
      to.mergeAttributes(from);
   }

   protected void mergeDiskVolume(DiskVolumeInfo to, DiskVolumeInfo from) {
      to.mergeAttributes(from);
   }

   protected void mergeExtension(Extension to, Extension from) {
      to.mergeAttributes(from);
      to.setDescription(from.getDescription());
   }

   protected void mergeExtensionDetail(ExtensionDetail to, ExtensionDetail from) {
      to.mergeAttributes(from);
   }

   protected void mergeGc(GcInfo to, GcInfo from) {
      to.mergeAttributes(from);
   }

   protected void mergeMemory(MemoryInfo to, MemoryInfo from) {
      to.mergeAttributes(from);
   }

   protected void mergeMessage(MessageInfo to, MessageInfo from) {
      to.mergeAttributes(from);
   }

   protected void mergeOs(OsInfo to, OsInfo from) {
      to.mergeAttributes(from);
   }

   protected void mergeRuntime(RuntimeInfo to, RuntimeInfo from) {
      to.mergeAttributes(from);
      to.setUserDir(from.getUserDir());
      to.setJavaClasspath(from.getJavaClasspath());
   }

   protected void mergeStatus(StatusInfo to, StatusInfo from) {
      to.mergeAttributes(from);
   }

   protected void mergeThread(ThreadsInfo to, ThreadsInfo from) {
      to.mergeAttributes(from);
      to.setDump(from.getDump());
   }

   @Override
   public void visitDisk(DiskInfo from) {
      DiskInfo to = (DiskInfo) m_objs.peek();

      mergeDisk(to, from);
      visitDiskChildren(to, from);
   }

   protected void visitDiskChildren(DiskInfo to, DiskInfo from) {
      for (DiskVolumeInfo source : from.getDiskVolumes()) {
         DiskVolumeInfo target = to.findDiskVolume(source.getId());

         if (target == null) {
            target = new DiskVolumeInfo(source.getId());
            to.addDiskVolume(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitDiskVolume(DiskVolumeInfo from) {
      DiskVolumeInfo to = (DiskVolumeInfo) m_objs.peek();

      mergeDiskVolume(to, from);
      visitDiskVolumeChildren(to, from);
   }

   protected void visitDiskVolumeChildren(DiskVolumeInfo to, DiskVolumeInfo from) {
   }

   @Override
   public void visitExtension(Extension from) {
      Extension to = (Extension) m_objs.peek();

      mergeExtension(to, from);
      visitExtensionChildren(to, from);
   }

   protected void visitExtensionChildren(Extension to, Extension from) {
      for (ExtensionDetail source : from.getDetails().values()) {
         ExtensionDetail target = to.findExtensionDetail(source.getId());

         if (target == null) {
            target = new ExtensionDetail(source.getId());
            to.addExtensionDetail(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitExtensionDetail(ExtensionDetail from) {
      ExtensionDetail to = (ExtensionDetail) m_objs.peek();

      mergeExtensionDetail(to, from);
      visitExtensionDetailChildren(to, from);
   }

   protected void visitExtensionDetailChildren(ExtensionDetail to, ExtensionDetail from) {
   }

   @Override
   public void visitGc(GcInfo from) {
      GcInfo to = (GcInfo) m_objs.peek();

      mergeGc(to, from);
      visitGcChildren(to, from);
   }

   protected void visitGcChildren(GcInfo to, GcInfo from) {
   }

   @Override
   public void visitMemory(MemoryInfo from) {
      MemoryInfo to = (MemoryInfo) m_objs.peek();

      mergeMemory(to, from);
      visitMemoryChildren(to, from);
   }

   protected void visitMemoryChildren(MemoryInfo to, MemoryInfo from) {
      for (GcInfo source : from.getGcs()) {
         GcInfo target = null;

         if (target == null) {
            target = new GcInfo();
            to.addGc(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitMessage(MessageInfo from) {
      MessageInfo to = (MessageInfo) m_objs.peek();

      mergeMessage(to, from);
      visitMessageChildren(to, from);
   }

   protected void visitMessageChildren(MessageInfo to, MessageInfo from) {
   }

   @Override
   public void visitOs(OsInfo from) {
      OsInfo to = (OsInfo) m_objs.peek();

      mergeOs(to, from);
      visitOsChildren(to, from);
   }

   protected void visitOsChildren(OsInfo to, OsInfo from) {
   }

   @Override
   public void visitRuntime(RuntimeInfo from) {
      RuntimeInfo to = (RuntimeInfo) m_objs.peek();

      mergeRuntime(to, from);
      visitRuntimeChildren(to, from);
   }

   protected void visitRuntimeChildren(RuntimeInfo to, RuntimeInfo from) {
   }

   @Override
   public void visitStatus(StatusInfo from) {
      StatusInfo to = (StatusInfo) m_objs.peek();

      mergeStatus(to, from);
      visitStatusChildren(to, from);
   }

   protected void visitStatusChildren(StatusInfo to, StatusInfo from) {
      if (from.getRuntime() != null) {
         RuntimeInfo target = to.getRuntime();

         if (target == null) {
            target = new RuntimeInfo();
            to.setRuntime(target);
         }

         m_objs.push(target);
         from.getRuntime().accept(this);
         m_objs.pop();
      }

      if (from.getOs() != null) {
         OsInfo target = to.getOs();

         if (target == null) {
            target = new OsInfo();
            to.setOs(target);
         }

         m_objs.push(target);
         from.getOs().accept(this);
         m_objs.pop();
      }

      if (from.getDisk() != null) {
         DiskInfo target = to.getDisk();

         if (target == null) {
            target = new DiskInfo();
            to.setDisk(target);
         }

         m_objs.push(target);
         from.getDisk().accept(this);
         m_objs.pop();
      }

      if (from.getMemory() != null) {
         MemoryInfo target = to.getMemory();

         if (target == null) {
            target = new MemoryInfo();
            to.setMemory(target);
         }

         m_objs.push(target);
         from.getMemory().accept(this);
         m_objs.pop();
      }

      if (from.getThread() != null) {
         ThreadsInfo target = to.getThread();

         if (target == null) {
            target = new ThreadsInfo();
            to.setThread(target);
         }

         m_objs.push(target);
         from.getThread().accept(this);
         m_objs.pop();
      }

      if (from.getMessage() != null) {
         MessageInfo target = to.getMessage();

         if (target == null) {
            target = new MessageInfo();
            to.setMessage(target);
         }

         m_objs.push(target);
         from.getMessage().accept(this);
         m_objs.pop();
      }

      for (Extension source : from.getExtensions().values()) {
         Extension target = to.findExtension(source.getId());

         if (target == null) {
            target = new Extension(source.getId());
            to.addExtension(target);
         }

         m_objs.push(target);
         source.accept(this);
         m_objs.pop();
      }
   }

   @Override
   public void visitThread(ThreadsInfo from) {
      ThreadsInfo to = (ThreadsInfo) m_objs.peek();

      mergeThread(to, from);
      visitThreadChildren(to, from);
   }

   protected void visitThreadChildren(ThreadsInfo to, ThreadsInfo from) {
   }
}
