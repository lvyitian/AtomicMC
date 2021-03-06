
package top.dsbbs2.atomicmc.websocket.packet;

import java.util.UUID;

import top.dsbbs2.atomicmc.websocket.packet.base.EventBody;
import top.dsbbs2.atomicmc.websocket.packet.base.EventType;
import top.dsbbs2.atomicmc.websocket.packet.base.Header;

public final class PacketSubscribeEvent implements IPacket<PacketSubscribeEvent>
{
  private static final long serialVersionUID = -3021341116599110693L;
  public final EventBody body;
  public PacketSubscribeEvent(String eventName)
  {
    this.body=new EventBody(eventName);
  }
  public PacketSubscribeEvent(EventType eventType)
  {
    this(eventType.toString());
  }
  public final Header header=new Header(UUID.randomUUID(),"subscribe",1,"commandRequest");
}
