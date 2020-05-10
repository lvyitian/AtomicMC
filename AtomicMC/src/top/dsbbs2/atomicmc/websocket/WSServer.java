
package top.dsbbs2.atomicmc.websocket;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import top.dsbbs2.atomicmc.AtomicMC;
import top.dsbbs2.atomicmc.main.Main;
import top.dsbbs2.atomicmc.main.Settings;
import top.dsbbs2.atomicmc.plugin.PluginLoader;

public class WSServer extends WebSocketServer
{
  protected final CustomMap<WebSocket,InetSocketAddress> clients = new CustomMap<>();
  public WSServer(int port)
  {
    super(new InetSocketAddress(port));
  }
  public CustomMap<WebSocket,InetSocketAddress> getClients()
  {
    return this.clients;
  }
  @Override
  public void onOpen(WebSocket conn, ClientHandshake handshake)
  {
    this.clients.put(conn,conn.getRemoteSocketAddress());
    Main.getLogger().info("connection from " + conn.getRemoteSocketAddress().toString() + " established");   
    AtomicMC.runCommand(
        "tellraw @a {\"rawtext\":[{\"text\":\"¡ìb[Server INFO] AtomicMCÒÑÁ¬½Ó\"}]}"
        ,conn);
    for(PluginLoader i : Main.getPlugins())
      i.tryInvokeFunction("onConnect",conn,handshake);
  }

  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote)
  {
    InetSocketAddress t=this.clients.get(conn);
    this.clients.remove(conn);
    Main.getLogger().info("connection from " + t.toString() + " disconnected");
    for(PluginLoader i : Main.getPlugins())
      i.tryInvokeFunction("onDisconnect",t,conn,code,reason,remote);
  }

  @Override
  public void onMessage(WebSocket conn, String message)
  {
    if(Settings.isDebug())
    {
      Main.getLogger().info(message);
    }
    for(PluginLoader i : Main.getPlugins())
      i.tryInvokeFunction("onMessage",conn,message);
  }

  @Override
  public void onError(WebSocket conn, Exception ex)
  {
    if(conn!=null)
      conn.close();
    Main.getLogger().severe("\n" + Main.throwableToString(ex));
    for(PluginLoader i : Main.getPlugins())
      i.tryInvokeFunction("onException",conn,ex);
  }

  @Override
  public void onStart()
  {
    Main.getLogger().info(String.format("Server started on %s", Main.SERVER.getAddress().toString()));
  }

}
