
package top.dsbbs2.atomicmc.websocket;

public class Reference<T>
{
  public volatile T value;
  public Reference(T value)
  {
    this.value=value;
  }
}
