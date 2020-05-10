
package top.dsbbs2.atomicmc.websocket.packet;

import java.io.Serializable;
import top.dsbbs2.atomicmc.main.Main;

public interface IPacket<T extends IPacket<T>> extends Serializable,Cloneable
{
  default String serialize()
  {
    return Main.GSON.toJson(this);
  }
  default String serializeWithoutFormat()
  {
    return Main.GSON_NOFORMAT.toJson(this);
  }
  @SuppressWarnings("unchecked")
  default Class<T> getPacketClass()
  {
    return (Class<T>)this.getClass();
  }
}
