package com.dianping.cat.home.network.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.home.network.IVisitor;
import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      m_out = new DataOutputStream(out);
      m_visitor = this;
   }

   public static byte[] build(NetGraphSet netGraphSet) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(netGraphSet, out);
      return out.toByteArray();
   }

   public static void build(NetGraphSet netGraphSet, OutputStream out) {
      netGraphSet.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitAnchor(Anchor anchor) {
      if (anchor.getName() != null) {
         writeTag(1, 1);
         writeString(anchor.getName());
      }

      if (anchor.getX() != null) {
         writeTag(2, 1);
         writeInt(anchor.getX());
      }

      if (anchor.getY() != null) {
         writeTag(3, 1);
         writeInt(anchor.getY());
      }

      writeTag(63, 3);
   }

   @Override
   public void visitConnection(Connection connection) {
      if (connection.getFrom() != null) {
         writeTag(1, 1);
         writeString(connection.getFrom());
      }

      if (connection.getTo() != null) {
         writeTag(2, 1);
         writeString(connection.getTo());
      }

      if (connection.getInstate() != null) {
         writeTag(3, 1);
         writeInt(connection.getInstate());
      }

      if (connection.getOutstate() != null) {
         writeTag(4, 1);
         writeInt(connection.getOutstate());
      }

      if (connection.getInsum() != null) {
         writeTag(5, 1);
         writeDouble(connection.getInsum());
      }

      if (connection.getOutsum() != null) {
         writeTag(6, 1);
         writeDouble(connection.getOutsum());
      }

      if (connection.getInDiscardsState() != null) {
         writeTag(7, 1);
         writeInt(connection.getInDiscardsState());
      }

      if (connection.getOutDiscardsState() != null) {
         writeTag(8, 1);
         writeInt(connection.getOutDiscardsState());
      }

      if (connection.getInErrorsState() != null) {
         writeTag(9, 1);
         writeInt(connection.getInErrorsState());
      }

      if (connection.getOutErrorsState() != null) {
         writeTag(10, 1);
         writeInt(connection.getOutErrorsState());
      }

      if (connection.getInDiscards() != null) {
         writeTag(11, 1);
         writeDouble(connection.getInDiscards());
      }

      if (connection.getOutDiscards() != null) {
         writeTag(12, 1);
         writeDouble(connection.getOutDiscards());
      }

      if (connection.getInErrors() != null) {
         writeTag(13, 1);
         writeDouble(connection.getInErrors());
      }

      if (connection.getOutErrors() != null) {
         writeTag(14, 1);
         writeDouble(connection.getOutErrors());
      }

      if (!connection.getInterfaces().isEmpty()) {
         writeTag(33, 2);
         writeInt(connection.getInterfaces().size());

         for (Interface interface_ : connection.getInterfaces()) {
            interface_.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitInterface(Interface _interface) {
      if (_interface.getGroup() != null) {
         writeTag(1, 1);
         writeString(_interface.getGroup());
      }

      if (_interface.getDomain() != null) {
         writeTag(2, 1);
         writeString(_interface.getDomain());
      }

      if (_interface.getKey() != null) {
         writeTag(3, 1);
         writeString(_interface.getKey());
      }

      if (_interface.getInstate() != null) {
         writeTag(4, 1);
         writeInt(_interface.getInstate());
      }

      if (_interface.getOutstate() != null) {
         writeTag(5, 1);
         writeInt(_interface.getOutstate());
      }

      if (_interface.getIn() != null) {
         writeTag(6, 1);
         writeDouble(_interface.getIn());
      }

      if (_interface.getOut() != null) {
         writeTag(7, 1);
         writeDouble(_interface.getOut());
      }

      if (_interface.getInDiscardsState() != null) {
         writeTag(8, 1);
         writeInt(_interface.getInDiscardsState());
      }

      if (_interface.getOutDiscardsState() != null) {
         writeTag(9, 1);
         writeInt(_interface.getOutDiscardsState());
      }

      if (_interface.getInErrorsState() != null) {
         writeTag(10, 1);
         writeInt(_interface.getInErrorsState());
      }

      if (_interface.getOutErrorsState() != null) {
         writeTag(11, 1);
         writeInt(_interface.getOutErrorsState());
      }

      if (_interface.getInDiscards() != null) {
         writeTag(12, 1);
         writeDouble(_interface.getInDiscards());
      }

      if (_interface.getOutDiscards() != null) {
         writeTag(13, 1);
         writeDouble(_interface.getOutDiscards());
      }

      if (_interface.getInErrors() != null) {
         writeTag(14, 1);
         writeDouble(_interface.getInErrors());
      }

      if (_interface.getOutErrors() != null) {
         writeTag(15, 1);
         writeDouble(_interface.getOutErrors());
      }

      writeTag(63, 3);
   }

   @Override
   public void visitNetGraph(NetGraph netGraph) {
      if (netGraph.getMinute() != null) {
         writeTag(1, 1);
         writeInt(netGraph.getMinute());
      }

      if (!netGraph.getNetTopologies().isEmpty()) {
         writeTag(33, 2);
         writeInt(netGraph.getNetTopologies().size());

         for (NetTopology netTopology : netGraph.getNetTopologies()) {
            netTopology.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitNetGraphSet(NetGraphSet netGraphSet) {
      writeTag(63, 0);

      if (!netGraphSet.getNetGraphs().isEmpty()) {
         writeTag(33, 2);
         writeInt(netGraphSet.getNetGraphs().size());

         for (NetGraph netGraph : netGraphSet.getNetGraphs().values()) {
            netGraph.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitNetTopology(NetTopology netTopology) {
      if (netTopology.getName() != null) {
         writeTag(1, 1);
         writeString(netTopology.getName());
      }

      if (!netTopology.getAnchors().isEmpty()) {
         writeTag(33, 2);
         writeInt(netTopology.getAnchors().size());

         for (Anchor anchor : netTopology.getAnchors()) {
            anchor.accept(m_visitor);
         }
      }

      if (!netTopology.getSwitchs().isEmpty()) {
         writeTag(34, 2);
         writeInt(netTopology.getSwitchs().size());

         for (Switch switch_ : netTopology.getSwitchs()) {
            switch_.accept(m_visitor);
         }
      }

      if (!netTopology.getConnections().isEmpty()) {
         writeTag(35, 2);
         writeInt(netTopology.getConnections().size());

         for (Connection connection : netTopology.getConnections()) {
            connection.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitSwitch(Switch _switch) {
      if (_switch.getName() != null) {
         writeTag(1, 1);
         writeString(_switch.getName());
      }

      if (_switch.getX() != null) {
         writeTag(2, 1);
         writeInt(_switch.getX());
      }

      if (_switch.getY() != null) {
         writeTag(3, 1);
         writeInt(_switch.getY());
      }

      if (_switch.getState() != null) {
         writeTag(4, 1);
         writeInt(_switch.getState());
      }

      writeTag(63, 3);
   }

   private void writeDouble(double value) {
      try {
         m_out.writeDouble(value);
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
