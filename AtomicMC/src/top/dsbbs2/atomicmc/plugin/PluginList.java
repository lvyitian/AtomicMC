
package top.dsbbs2.atomicmc.plugin;

import java.util.Collection;
import java.util.Vector;
import java.util.stream.Collectors;

public class PluginList extends Vector<PluginLoader>
{

  /**
   * 
   */
  private static final long serialVersionUID = -2162251099316171449L;

  @Override
  public synchronized boolean add(PluginLoader e)
  {
    if (this.contains(e)) {
      return false;
    }
    return super.add(e);
  }

  @Override
  public synchronized void setElementAt(PluginLoader obj, int index)
  {
    if (this.contains(obj)) {
      return;
    }
    super.setElementAt(obj, index);
  }

  @Override
  public synchronized void insertElementAt(PluginLoader obj, int index)
  {
    if (this.contains(obj)) {
      return;
    }
    super.insertElementAt(obj, index);
  }

  @Override
  public synchronized void addElement(PluginLoader obj)
  {
    if (this.contains(obj)) {
      return;
    }
    super.addElement(obj);
  }

  @Override
  public synchronized PluginLoader set(int index, PluginLoader element)
  {
    if (this.contains(element)) {
      return element;
    }
    return super.set(index, element);
  }

  @Override
  public void add(int index, PluginLoader element)
  {
    if (this.contains(element)) {
      return;
    }
    super.add(index, element);
  }

  @Override
  public synchronized boolean addAll(Collection<? extends PluginLoader> c)
  {
    return super.addAll(c.stream().filter(i->!this.contains(i)).collect(Collectors.toList()));
  }

  @Override
  public synchronized boolean addAll(int index, Collection<? extends PluginLoader> c)
  {
    return super.addAll(index, c.stream().filter(i->!this.contains(i)).collect(Collectors.toList()));
  }

}
