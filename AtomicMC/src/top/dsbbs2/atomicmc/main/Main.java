
package top.dsbbs2.atomicmc.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.java_websocket.server.WebSocketServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import top.dsbbs2.atomicmc.plugin.PluginList;
import top.dsbbs2.atomicmc.plugin.PluginLoader;
import top.dsbbs2.atomicmc.scheduler.AtomicMCScheduler;
import top.dsbbs2.atomicmc.websocket.WSServer;

public class Main
{
  public static final ConsoleHandler CONSOLE_HANDLER = new ConsoleHandler();
  static {
    Main.CONSOLE_HANDLER.setLevel(Level.FINEST);
    Logger.getGlobal().addHandler(Main.CONSOLE_HANDLER);
  }
  public static final Gson GSON = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().setLenient()
      .create();
  public static final Gson GSON_NOFORMAT = new GsonBuilder().enableComplexMapKeySerialization().setLenient()
      .create();
  public static final Thread mainThread = Thread.currentThread();
  private static final Logger LOGGER = Logger.getLogger("AtomicMC");
  static {
    if (Settings.isDebug()) {
      Main.LOGGER.setLevel(Level.ALL);
    } else {
      Main.LOGGER.setLevel(Level.INFO);
    }
  }
  protected static final PluginList plugins = new PluginList();
  public static final WSServer SERVER = new WSServer(Settings.getPort())
  {
    {
      this.setTcpNoDelay(true);
      this.setReuseAddr(true);
      this.setConnectionLostTimeout(5);
    }
  };
  static {
    Runtime.getRuntime().addShutdownHook(new Thread(() ->
    {
      try {
        Main.SERVER.stop();
      } catch (final Throwable e) {
        Main.getLogger().severe("\n" + Main.throwableToString(e));
      }
    }));
  }
  public static final AtomicBoolean CLOSED;
  static {
    try {
      final Field t = WebSocketServer.class.getDeclaredField("isclosed");
      t.setAccessible(true);
      CLOSED = (AtomicBoolean) t.get(Main.SERVER);
    } catch (final Throwable e) {
      throw new RuntimeException(e);
    }
  }

  public static PluginList getPlugins()
  {
    return Main.plugins;
  }

  public static Logger getLogger()
  {
    return Main.LOGGER;
  }

  public static String throwableToString(final Throwable t)
  {
    try {
      final ByteArrayOutputStream out = new ByteArrayOutputStream();
      final PrintStream temp = new PrintStream(out, true, "UTF8");
      t.printStackTrace(temp);
      return new String(out.toByteArray(), StandardCharsets.UTF_8);
    } catch (final Throwable e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(final String[] args) throws Throwable
  {
    try {
      new Thread(() ->
      {
        Main.getLogger().info("WatchDog Thread Started");
        while (Main.mainThread.isAlive()) {
          try {
            Thread.sleep(1);
          } catch (final Throwable e) {
            Main.getLogger().severe("\n" + Main.throwableToString(e));
            System.exit(-1);
          }
        }
        System.exit(0);
      })
      {
        {
          this.setName("WATCHDOG Thread");
          this.setDaemon(false);
        }
      }.start();
      Main.getLogger().info("Starting server...");
      Main.SERVER.start();
      Main.getLogger().info("Waiting server to be started...");
      Main.SERVER.future.sync();
      Main.getLogger().info("Server started");
      final File pls = new File("plugins");
      if (!pls.isDirectory()) {
        pls.mkdirs();
      }
      for (final File i : pls.listFiles()) {
        if (i.isDirectory()) {
          Main.getLogger().info("Loading plugin \""+i.getName()+"\"");
          new PluginLoader(i.getName());
        }
      }
      while (!Main.CLOSED.get()) {
        AtomicMCScheduler.getInstance().handleTasks();
        Thread.sleep(1);
      }
    } catch (final Throwable e) {
      Main.getLogger().severe("\n" + Main.throwableToString(e));
    }
  }
}
