
package org.slf4j.impl;

import java.util.Objects;
import java.util.logging.Level;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.Marker;

import top.dsbbs2.atomicmc.main.Main;
import top.dsbbs2.atomicmc.main.Settings;

public class StaticLoggerBinder implements ILoggerFactory
{
  private static final StaticLoggerBinder INSTANCE = new StaticLoggerBinder();
  private static final String PARAM = "\\{\\}";

  public static StaticLoggerBinder getSingleton()
  {
    return StaticLoggerBinder.INSTANCE;
  }

  public ILoggerFactory getLoggerFactory()
  {
    return StaticLoggerBinder.getSingleton()::getLogger;
  }

  public static StackTraceElement getCaller(final int i)
  {
    final StackTraceElement[] sts = new Throwable().getStackTrace();
    return sts[sts.length - i - 1];
  }

  public static String getCallerString(final int i)
  {
    final StackTraceElement stackTraceElement = StaticLoggerBinder.getCaller(i);
    return new StringBuilder(stackTraceElement.getFileName()).append(" ").append(stackTraceElement.getClassName())
        .append(" ").append(stackTraceElement.getMethodName()).append(":").append(stackTraceElement.getLineNumber())
        .toString();
  }

  public static void doLog(final Level level, final String s, final Object... param)
  {
    if (Settings.isNoslf4j()) {
      return;
    }
    String res = s;
    for (final Object i : param) {
      res = res.replaceFirst(StaticLoggerBinder.PARAM,
          i instanceof Throwable ? Main.throwableToString((Throwable) i) : Objects.toString(i));
    }
    Main.getLogger().log(level, "\nFrom: " + StaticLoggerBinder.getCallerString(0) + "\n" + res);
  }

  @Override
  public Logger getLogger(final String paramString)
  {
    return new Logger()
    {
      @Override
      public void warn(final Marker paramMarker, final String paramString, final Object paramObject1,
          final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString, paramObject1, paramObject2);
      }

      @Override
      public void warn(final Marker paramMarker, final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString, paramThrowable);
      }

      @Override
      public void warn(final Marker paramMarker, final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString, paramVarArgs);
      }

      @Override
      public void warn(final Marker paramMarker, final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString, paramObject);
      }

      @Override
      public void warn(final String paramString, final Object paramObject1, final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString, paramObject1, paramObject2);
      }

      @Override
      public void warn(final Marker paramMarker, final String paramString)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString);
      }

      @Override
      public void warn(final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString, paramThrowable);
      }

      @Override
      public void warn(final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString, paramVarArgs);
      }

      @Override
      public void warn(final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString, paramObject);
      }

      @Override
      public void warn(final String paramString)
      {
        StaticLoggerBinder.doLog(Level.WARNING, paramString);
      }

      @Override
      public void trace(final Marker paramMarker, final String paramString, final Object paramObject1,
          final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramObject1, paramObject2);
      }

      @Override
      public void trace(final Marker paramMarker, final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramThrowable);
      }

      @Override
      public void trace(final Marker paramMarker, final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramVarArgs);
      }

      @Override
      public void trace(final Marker paramMarker, final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramObject);
      }

      @Override
      public void trace(final String paramString, final Object paramObject1, final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramObject1, paramObject2);
      }

      @Override
      public void trace(final Marker paramMarker, final String paramString)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString);
      }

      @Override
      public void trace(final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramThrowable);
      }

      @Override
      public void trace(final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramVarArgs);
      }

      @Override
      public void trace(final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramObject);
      }

      @Override
      public void trace(final String paramString)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString);
      }

      @Override
      public boolean isWarnEnabled(final Marker paramMarker)
      {
        return true;
      }

      @Override
      public boolean isWarnEnabled()
      {
        return true;
      }

      @Override
      public boolean isTraceEnabled(final Marker paramMarker)
      {
        return Settings.isDebug();
      }

      @Override
      public boolean isTraceEnabled()
      {
        return Settings.isDebug();
      }

      @Override
      public boolean isInfoEnabled(final Marker paramMarker)
      {
        return true;
      }

      @Override
      public boolean isInfoEnabled()
      {
        return true;
      }

      @Override
      public boolean isErrorEnabled(final Marker paramMarker)
      {
        return true;
      }

      @Override
      public boolean isErrorEnabled()
      {
        return true;
      }

      @Override
      public boolean isDebugEnabled(final Marker paramMarker)
      {
        return Settings.isDebug();
      }

      @Override
      public boolean isDebugEnabled()
      {
        return Settings.isDebug();
      }

      @Override
      public void info(final Marker paramMarker, final String paramString, final Object paramObject1,
          final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString, paramObject1, paramObject2);
      }

      @Override
      public void info(final Marker paramMarker, final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString, paramThrowable);
      }

      @Override
      public void info(final Marker paramMarker, final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString, paramVarArgs);
      }

      @Override
      public void info(final Marker paramMarker, final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString, paramObject);
      }

      @Override
      public void info(final String paramString, final Object paramObject1, final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString, paramObject1, paramObject2);
      }

      @Override
      public void info(final Marker paramMarker, final String paramString)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString);
      }

      @Override
      public void info(final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString, paramThrowable);
      }

      @Override
      public void info(final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString, paramVarArgs);
      }

      @Override
      public void info(final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString, paramObject);
      }

      @Override
      public void info(final String paramString)
      {
        StaticLoggerBinder.doLog(Level.INFO, paramString);
      }

      @Override
      public String getName()
      {
        return paramString;
      }

      @Override
      public void error(final Marker paramMarker, final String paramString, final Object paramObject1,
          final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString, paramObject1, paramObject2);
      }

      @Override
      public void error(final Marker paramMarker, final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString, paramThrowable);
      }

      @Override
      public void error(final Marker paramMarker, final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString, paramVarArgs);
      }

      @Override
      public void error(final Marker paramMarker, final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString, paramObject);
      }

      @Override
      public void error(final String paramString, final Object paramObject1, final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString, paramObject1, paramObject2);
      }

      @Override
      public void error(final Marker paramMarker, final String paramString)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString);
      }

      @Override
      public void error(final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString, paramThrowable);
      }

      @Override
      public void error(final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString, paramVarArgs);
      }

      @Override
      public void error(final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString, paramObject);
      }

      @Override
      public void error(final String paramString)
      {
        StaticLoggerBinder.doLog(Level.SEVERE, paramString);
      }

      @Override
      public void debug(final Marker paramMarker, final String paramString, final Object paramObject1,
          final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramObject1, paramObject2);
      }

      @Override
      public void debug(final Marker paramMarker, final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramThrowable);
      }

      @Override
      public void debug(final Marker paramMarker, final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramVarArgs);
      }

      @Override
      public void debug(final Marker paramMarker, final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramObject);
      }

      @Override
      public void debug(final String paramString, final Object paramObject1, final Object paramObject2)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramObject1, paramObject2);
      }

      @Override
      public void debug(final Marker paramMarker, final String paramString)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString);
      }

      @Override
      public void debug(final String paramString, final Throwable paramThrowable)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramThrowable);
      }

      @Override
      public void debug(final String paramString, final Object... paramVarArgs)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramVarArgs);
      }

      @Override
      public void debug(final String paramString, final Object paramObject)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString, paramObject);
      }

      @Override
      public void debug(final String paramString)
      {
        StaticLoggerBinder.doLog(Level.FINEST, paramString);
      }
    };
  }

}
