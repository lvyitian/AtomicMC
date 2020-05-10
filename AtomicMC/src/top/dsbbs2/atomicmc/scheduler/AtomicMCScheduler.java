
package top.dsbbs2.atomicmc.scheduler;

import java.util.Objects;
import java.util.TreeMap;
import java.util.UUID;

import top.dsbbs2.atomicmc.main.Main;

public final class AtomicMCScheduler
{
  private static final AtomicMCScheduler scheduler=new AtomicMCScheduler();
  private final TreeMap<UUID, Runnable> tasks=new TreeMap<>();
  public final Object lock=new Object();
  private AtomicMCScheduler() {
    if(AtomicMCScheduler.scheduler!=null)
      throw new RuntimeException("This class can only have one instance!");
  }
  public static AtomicMCScheduler getInstance()
  {
    return AtomicMCScheduler.scheduler;
  }
  @SuppressWarnings("unchecked")
  public TreeMap<UUID, Runnable> getTasks()
  {
    synchronized (lock) {
      return (TreeMap<UUID, Runnable>)this.tasks.clone();
    }
  }
  public UUID runTask(Runnable r)
  {
    synchronized (lock) {
      UUID ret=UUID.randomUUID();
      this.tasks.put(ret, Objects.requireNonNull(r));
      return ret;
    }
  }
  public Runnable getTask(UUID u)
  {
    synchronized (lock) {
      return this.tasks.get(u);
    }
  }
  public boolean cancelTask(UUID u)
  {
    synchronized (lock) {
      return this.tasks.remove(u)!=null;
    }
  }
  public void handleTasks()
  {
    if(!Objects.equals(Main.mainThread,Thread.currentThread()))
      throw new RuntimeException("async handle tasks!");
    synchronized (lock) {
      for(Runnable r : this.tasks.values())
      {
        r.run();
      }
      this.tasks.clear();
    }
  }
}
