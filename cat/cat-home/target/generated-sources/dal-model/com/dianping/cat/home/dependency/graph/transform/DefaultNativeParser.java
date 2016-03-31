package com.dianping.cat.home.dependency.graph.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dianping.cat.home.dependency.graph.IVisitor;
import com.dianping.cat.home.dependency.graph.entity.TopologyEdge;
import com.dianping.cat.home.dependency.graph.entity.TopologyGraph;
import com.dianping.cat.home.dependency.graph.entity.TopologyNode;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static TopologyGraph parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static TopologyGraph parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      TopologyGraph topologyGraph = new TopologyGraph();

      try {
         topologyGraph.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return topologyGraph;
   }

   @Override
   public void visitTopologyEdge(TopologyEdge topologyEdge) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitTopologyEdgeChildren(topologyEdge, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitTopologyEdgeChildren(TopologyEdge topologyEdge, int _field, int _type) {
      switch (_field) {
         case 1:
            topologyEdge.setKey(readString());
            break;
         case 2:
            topologyEdge.setType(readString());
            break;
         case 3:
            topologyEdge.setTarget(readString());
            break;
         case 4:
            topologyEdge.setOpposite(readBoolean());
            break;
         case 5:
            topologyEdge.setWeight(readInt());
            break;
         case 6:
            topologyEdge.setStatus(readInt());
            break;
         case 7:
            topologyEdge.setDes(readString());
            break;
         case 8:
            topologyEdge.setLink(readString());
            break;
         case 9:
            topologyEdge.setSelf(readString());
            break;
         case 10:
            topologyEdge.setDashed(readBoolean());
            break;
      }
   }

   @Override
   public void visitTopologyGraph(TopologyGraph topologyGraph) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitTopologyGraphChildren(topologyGraph, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitTopologyGraphChildren(TopologyGraph topologyGraph, int _field, int _type) {
      switch (_field) {
         case 1:
            topologyGraph.setId(readString());
            break;
         case 2:
            topologyGraph.setType(readString());
            break;
         case 3:
            topologyGraph.setStatus(readInt());
            break;
         case 4:
            topologyGraph.setDes(readString());
            break;
         case 33:
            if (_type == 1) {
              TopologyNode nodes = new TopologyNode();

              visitTopologyNode(nodes);
              m_linker.onTopologyNode(topologyGraph, nodes);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 TopologyNode nodes = new TopologyNode();

                 visitTopologyNode(nodes);
                 m_linker.onTopologyNode(topologyGraph, nodes);
               }
            }
            break;
         case 34:
            if (_type == 1) {
              TopologyEdge edges = new TopologyEdge();

              visitTopologyEdge(edges);
              m_linker.onTopologyEdge(topologyGraph, edges);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 TopologyEdge edges = new TopologyEdge();

                 visitTopologyEdge(edges);
                 m_linker.onTopologyEdge(topologyGraph, edges);
               }
            }
            break;
      }
   }

   @Override
   public void visitTopologyNode(TopologyNode topologyNode) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitTopologyNodeChildren(topologyNode, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitTopologyNodeChildren(TopologyNode topologyNode, int _field, int _type) {
      switch (_field) {
         case 1:
            topologyNode.setId(readString());
            break;
         case 2:
            topologyNode.setType(readString());
            break;
         case 3:
            topologyNode.setStatus(readInt());
            break;
         case 4:
            topologyNode.setDes(readString());
            break;
         case 5:
            topologyNode.setLink(readString());
            break;
         case 6:
            topologyNode.setWeight(readInt());
            break;
      }
   }

   private boolean readBoolean() {
      try {
         return m_in.readByte() == 1 ? Boolean.TRUE : Boolean.FALSE;
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
