package com.dianping.cat.home.network.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.network.IVisitor;
import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static NetGraphSet parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static NetGraphSet parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      NetGraphSet netGraphSet = new NetGraphSet();

      try {
         netGraphSet.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return netGraphSet;
   }

   @Override
   public void visitAnchor(Anchor anchor) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitAnchorChildren(anchor, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitAnchorChildren(Anchor anchor, int _field, int _type) {
      switch (_field) {
         case 1:
            anchor.setName(readString());
            break;
         case 2:
            anchor.setX(readInt());
            break;
         case 3:
            anchor.setY(readInt());
            break;
      }
   }

   @Override
   public void visitConnection(Connection connection) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitConnectionChildren(connection, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitConnectionChildren(Connection connection, int _field, int _type) {
      switch (_field) {
         case 1:
            connection.setFrom(readString());
            break;
         case 2:
            connection.setTo(readString());
            break;
         case 3:
            connection.setInstate(readInt());
            break;
         case 4:
            connection.setOutstate(readInt());
            break;
         case 5:
            connection.setInsum(readDouble());
            break;
         case 6:
            connection.setOutsum(readDouble());
            break;
         case 7:
            connection.setInDiscardsState(readInt());
            break;
         case 8:
            connection.setOutDiscardsState(readInt());
            break;
         case 9:
            connection.setInErrorsState(readInt());
            break;
         case 10:
            connection.setOutErrorsState(readInt());
            break;
         case 11:
            connection.setInDiscards(readDouble());
            break;
         case 12:
            connection.setOutDiscards(readDouble());
            break;
         case 13:
            connection.setInErrors(readDouble());
            break;
         case 14:
            connection.setOutErrors(readDouble());
            break;
         case 33:
            if (_type == 1) {
              Interface interfaces = new Interface();

              visitInterface(interfaces);
              m_linker.onInterface(connection, interfaces);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Interface interfaces = new Interface();

                 visitInterface(interfaces);
                 m_linker.onInterface(connection, interfaces);
               }
            }
            break;
      }
   }

   @Override
   public void visitInterface(Interface _interface) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitInterfaceChildren(_interface, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitInterfaceChildren(Interface _interface, int _field, int _type) {
      switch (_field) {
         case 1:
            _interface.setGroup(readString());
            break;
         case 2:
            _interface.setDomain(readString());
            break;
         case 3:
            _interface.setKey(readString());
            break;
         case 4:
            _interface.setInstate(readInt());
            break;
         case 5:
            _interface.setOutstate(readInt());
            break;
         case 6:
            _interface.setIn(readDouble());
            break;
         case 7:
            _interface.setOut(readDouble());
            break;
         case 8:
            _interface.setInDiscardsState(readInt());
            break;
         case 9:
            _interface.setOutDiscardsState(readInt());
            break;
         case 10:
            _interface.setInErrorsState(readInt());
            break;
         case 11:
            _interface.setOutErrorsState(readInt());
            break;
         case 12:
            _interface.setInDiscards(readDouble());
            break;
         case 13:
            _interface.setOutDiscards(readDouble());
            break;
         case 14:
            _interface.setInErrors(readDouble());
            break;
         case 15:
            _interface.setOutErrors(readDouble());
            break;
      }
   }

   @Override
   public void visitNetGraph(NetGraph netGraph) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitNetGraphChildren(netGraph, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitNetGraphChildren(NetGraph netGraph, int _field, int _type) {
      switch (_field) {
         case 1:
            netGraph.setMinute(readInt());
            break;
         case 33:
            if (_type == 1) {
              NetTopology netTopologies = new NetTopology();

              visitNetTopology(netTopologies);
              m_linker.onNetTopology(netGraph, netTopologies);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 NetTopology netTopologies = new NetTopology();

                 visitNetTopology(netTopologies);
                 m_linker.onNetTopology(netGraph, netTopologies);
               }
            }
            break;
      }
   }

   @Override
   public void visitNetGraphSet(NetGraphSet netGraphSet) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitNetGraphSetChildren(netGraphSet, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitNetGraphSetChildren(NetGraphSet netGraphSet, int _field, int _type) {
      switch (_field) {
         case 33:
            if (_type == 1) {
              NetGraph netGraphs = new NetGraph();

              visitNetGraph(netGraphs);
              m_linker.onNetGraph(netGraphSet, netGraphs);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 NetGraph netGraphs = new NetGraph();

                 visitNetGraph(netGraphs);
                 m_linker.onNetGraph(netGraphSet, netGraphs);
               }
            }
            break;
      }
   }

   @Override
   public void visitNetTopology(NetTopology netTopology) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitNetTopologyChildren(netTopology, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitNetTopologyChildren(NetTopology netTopology, int _field, int _type) {
      switch (_field) {
         case 1:
            netTopology.setName(readString());
            break;
         case 33:
            if (_type == 1) {
              Anchor anchors = new Anchor();

              visitAnchor(anchors);
              m_linker.onAnchor(netTopology, anchors);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Anchor anchors = new Anchor();

                 visitAnchor(anchors);
                 m_linker.onAnchor(netTopology, anchors);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              Switch switchs = new Switch();

              visitSwitch(switchs);
              m_linker.onSwitch(netTopology, switchs);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Switch switchs = new Switch();

                 visitSwitch(switchs);
                 m_linker.onSwitch(netTopology, switchs);
               }
            }
            break;
         case 35:
            if (_type == 1) {
              Connection connections = new Connection();

              visitConnection(connections);
              m_linker.onConnection(netTopology, connections);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Connection connections = new Connection();

                 visitConnection(connections);
                 m_linker.onConnection(netTopology, connections);
               }
            }
            break;
      }
   }

   @Override
   public void visitSwitch(Switch _switch) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitSwitchChildren(_switch, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitSwitchChildren(Switch _switch, int _field, int _type) {
      switch (_field) {
         case 1:
            _switch.setName(readString());
            break;
         case 2:
            _switch.setX(readInt());
            break;
         case 3:
            _switch.setY(readInt());
            break;
         case 4:
            _switch.setState(readInt());
            break;
      }
   }

   private double readDouble() {
      try {
         return m_in.readDouble();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private int readInt() {
      try {
         return (int) readVarint(32);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private String readString() {
      try {
         return m_in.readUTF();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private byte readTag() {
      try {
         return m_in.readByte();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected long readVarint(final int length) throws IOException {
      int shift = 0;
      long result = 0;

      while (shift < length) {
         final byte b = m_in.readByte();
         result |= (long) (b & 0x7F) << shift;
         if ((b & 0x80) == 0) {
            return result;
         }
         shift += 7;
      }

      throw new RuntimeException("Malformed variable int " + length + "!");
   }
}
