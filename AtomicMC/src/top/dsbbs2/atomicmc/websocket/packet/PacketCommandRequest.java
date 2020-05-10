
package top.dsbbs2.atomicmc.websocket.packet;

import java.util.UUID;

import top.dsbbs2.atomicmc.websocket.packet.base.Header;

public final class PacketCommandRequest implements IPacket<PacketCommandRequest>
{
  private static final long serialVersionUID = -3021341116599110693L;
  public final CommandBody body;
  public PacketCommandRequest(String commandLine)
  {
    this.body=new CommandBody(commandLine);
  }
  public static class CommandBody{
    public final Origin origin=new Origin();
    public static class Origin{
      public final String type="player";
    }
    public String commandLine;
    public CommandBody(String commandLine)
    {
      this.commandLine=commandLine;
    }
    public final long version=1;
  }
  public final Header header=new Header(UUID.randomUUID(),"commandRequest",1,"commandRequest");
}
