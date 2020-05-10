
package top.dsbbs2.atomicmc.websocket.packet.base;

import java.util.UUID;

public class Header
{
  public UUID requestId;
  public String messagePurpose;
  public long version;
  public String messageType;
  public Header(UUID requestId,String messagePurpose,long version,String messageType)
  {
    this.requestId=requestId;
    this.messagePurpose=messagePurpose;
    this.version=version;
    this.messageType=messageType;
  }
}
