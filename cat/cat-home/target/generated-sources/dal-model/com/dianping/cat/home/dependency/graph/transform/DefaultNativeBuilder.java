package com.dianping.cat.home.dependency.graph.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.dependency.graph.IVisitor;
import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(TopologyGraph topologyGraph) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(topologyGraph, out);
      return out.toByteArray();
   }

   public static void build(TopologyGraph topologyGraph, OutputStream out) {
      topologyGraph.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitTopologyEdge(TopologyEdge topologyEdge) {
      if (topologyEdge.getKey() != null) {
         writeTag(1, 1);
         writeString(topologyEdge.getKey());
      }

      if (topologyEdge.getType() != null) {
         writeTag(2, 1);
         writeString(topologyEdge.getType());
      }

      if (topologyEdge.getTarget() != null) {
         writeTag(3, 1);
         writeString(topologyEdge.getTarget());
      }

      writeTag(4, 0);
      writeBoolean(topologyEdge.getOpposite());

      writeTag(5, 0);
      writeInt(topologyEdge.getWeight());

      writeTag(6, 0);
      writeInt(topologyEdge.getStatus());

      if (topologyEdge.getDes() != null) {
         writeTag(7, 1);
         writeString(topologyEdge.getDes());
      }

      if (topologyEdge.getLink() != null) {
         writeTag(8, 1);
         writeString(topologyEdge.getLink());
      }

      if (topologyEdge.getSelf() != null) {
         writeTag(9, 1);
         writeString(topologyEdge.getSelf());
      }

      writeTag(10, 0);
      writeBoolean(topologyEdge.getDashed());

      writeTag(63, 3);
   }

   @Override
   public void visitTopologyGraph(TopologyGraph topologyGraph) {
      writeTag(63, 0);

      if (topologyGraph.getId() != null) {
         writeTag(1, 1);
         writeString(topologyGraph.getId());
      }

      if (topologyGraph.getType() != null) {
         writeTag(2, 1);
         writeString(topologyGraph.getType());
      }

      writeTag(3, 0);
      writeInt(topologyGraph.getStatus());

      if (topologyGraph.getDes() != null) {
         writeTag(4, 1);
         writeString(topologyGraph.getDes());
      }

      if (!topologyGraph.getNodes().isEmpty()) {
         writeTag(33, 2);
         writeInt(topologyGraph.getNodes().size());

         for (TopologyNode topologyNode : topologyGraph.getNodes().values()) {
            topologyNode.accept(m_visitor);
         }
      }

      if (!topologyGraph.getEdges().isEmpty()) {
         writeTag(34, 2);
         writeInt(topologyGraph.getEdges().size());

         for (TopologyEdge topologyEdge : topologyGraph.getEdges().values()) {
            topologyEdge.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitTopologyNode(TopologyNode topologyNode) {
      if (topologyNode.getId() != null) {
         writeTag(1, 1);
         writeString(topologyNode.getId());
      }

      if (topologyNode.getType() != null) {
         writeTag(2, 1);
         writeString(topologyNode.getType());
      }

      writeTag(3, 0);
      writeInt(topologyNode.getStatus());

      if (topologyNode.getDes() != null) {
         writeTag(4, 1);
         writeString(topologyNode.getDes());
      }

      if (topologyNode.getLink() != null) {
         writeTag(5, 1);
         writeString(topologyNode.getLink());
      }

      writeTag(6, 0);
      writeInt(topologyNode.getWeight());

      writeTag(63, 3);
   }

   private void writeBoolean(boolean value) {
      try {
         m_out.writeByte(value ? 1 : 0);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeInt(int value) {
      try {
         writeVarint(value & 0xFFFFFFFFL);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeString(String value) {
      try {
         m_out.writeUTF(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeTag(int field, int type) {
      try {
         m_out.writeByte((field << 2) + type);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected void writeVarint(long value) throws IOException {
      while (true) {
         if ((value & ~0x7FL) == 0) {
            m_out.writeByte((byte) value);
            return;
         } else {
            m_out.writeByte(((byte) value & 0x7F) | 0x80);
            value >>>= 7;
         }
      }
   }
}
