package top.dsbbs2.atomicmc.main;

public final class Settings
{
  private Settings() throws Throwable{
    throw new Throwable();
  }
  private static volatile int port;
  private static volatile boolean debug;
  private static volatile boolean noslf4j;
  public static boolean isDebug()
  {
    return debug;
  }
  public static void setDebug(boolean debug)
  {
    Settings.debug = debug;
    Main.getLogger().info("DEBUG MODE: "+Settings.isDebug());
  }
  public static boolean isNoslf4j()
  {
    return noslf4j;
  }
  public static void setNoslf4j(boolean noslf4j)
  {
    Settings.noslf4j = noslf4j;
    Main.getLogger().info("NoSlf4j: "+Settings.noslf4j);
  }
  public static int getPort()
  {
    return port;
  }
  public static void setPort(int port)
  {
    if(Main.CLOSED!=null && !Main.CLOSED.get())
      throw new RuntimeException("Server not closed");
    Settings.port = port;
    Main.getLogger().info("Port: "+Settings.getPort());
  }
  static {
    Settings.setPort(Integer.parseInt(System.getProperty("top.dsbbs2.atomicmc.port","32778")));
    Settings.setDebug(Boolean.parseBoolean(System.getProperty("top.dsbbs2.atomicmc.debug", "false")));
    Settings.setNoslf4j(Boolean.parseBoolean(System.getProperty("top.dsbbs2.atomicmc.noslf4j", "false")));
  }
}
