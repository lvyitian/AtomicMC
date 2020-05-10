
package top.dsbbs2.atomicmc;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

import org.java_websocket.WebSocket;

import top.dsbbs2.atomicmc.main.Main;
import top.dsbbs2.atomicmc.plugin.PluginLoader;
import top.dsbbs2.atomicmc.scheduler.AtomicMCScheduler;
import top.dsbbs2.atomicmc.websocket.packet.IPacket;
import top.dsbbs2.atomicmc.websocket.packet.PacketCommandRequest;
import top.dsbbs2.atomicmc.websocket.packet.PacketSubscribeEvent;
import top.dsbbs2.atomicmc.websocket.packet.PacketUnsubscribeEvent;
import top.dsbbs2.atomicmc.websocket.packet.base.EventType;

public final class AtomicMC
{
  private AtomicMC()
  {
    throw new RuntimeException("This Class cannot have an instance!");
  }

  public static void sendPacket(final IPacket<?> p)
  {
    AtomicMC.sendPacket(p.serializeWithoutFormat());
  }

  public static void sendPacket(final String f)
  {
    Main.SERVER.getClients().keySet().forEach(i -> i.send(f));
  }

  public static void sendPacket(final String f, final WebSocket... cs)
  {
    for (final WebSocket i : cs) {
      i.send(f);
    }
  }

  public static void sendPacket(final IPacket<?> p, final WebSocket... cs)
  {
    AtomicMC.sendPacket(p.serializeWithoutFormat(), cs);
  }

  public static UUID runCommand(final String command)
  {
    final PacketCommandRequest packetCommandRequest = new PacketCommandRequest(command);
    AtomicMC.sendPacket(packetCommandRequest);
    return packetCommandRequest.header.requestId;
  }

  public static UUID runCommand(final String command, final WebSocket... cs)
  {
    final PacketCommandRequest packetCommandRequest = new PacketCommandRequest(command);
    AtomicMC.sendPacket(packetCommandRequest, cs);
    return packetCommandRequest.header.requestId;
  }

  public static PluginLoader getPlugin(final String name)
  {
    return Main.getPlugins().parallelStream().filter(i -> Objects.equals(new File(i.dir).getName(), name)).findFirst()
        .orElse(null);
  }
  
  public static AtomicMCScheduler getScheduler()
  {
    return AtomicMCScheduler.getInstance();
  }
  
  public static PacketSubscribeEvent regEventPacket(String e)
  {
    return new PacketSubscribeEvent(e);
  }
  
  public static PacketSubscribeEvent regEventPacket(EventType e)
  {
    return new PacketSubscribeEvent(e);
  }
  public static PacketUnsubscribeEvent unregEventPacket(String e)
  {
    return new PacketUnsubscribeEvent(e);
  }
  public static PacketUnsubscribeEvent unregEventPacket(EventType e)
  {
    return new PacketUnsubscribeEvent(e);
  }
}
