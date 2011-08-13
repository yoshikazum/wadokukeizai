package de.wadokukeizai.app.android.libs;

import org.apache.commons.logging.Log;

public class AndroidLog implements Log {

  private String tag;

  public AndroidLog(String tag) {
    this.tag = tag;
  }

  public void debug(Object arg0) {
    android.util.Log.d(tag, arg0.toString());
  }

  public void debug(Object arg0, Throwable arg1) {
    android.util.Log.d(tag, arg0.toString(), arg1);

  }

  public void error(Object arg0) {
    android.util.Log.e(tag, arg0.toString());

  }

  public void error(Object arg0, Throwable arg1) {
    android.util.Log.e(tag, arg0.toString(), arg1);
  }

  public void fatal(Object arg0) {
    android.util.Log.e(tag, arg0.toString());

  }

  public void fatal(Object arg0, Throwable arg1) {
    android.util.Log.e(tag, arg0.toString(), arg1);

  }

  public void info(Object arg0) {
    android.util.Log.i(tag, arg0.toString());

  }

  public void info(Object arg0, Throwable arg1) {
    android.util.Log.i(tag, arg0.toString(), arg1);
  }

  public boolean isDebugEnabled() {
    return true;
  }

  public boolean isErrorEnabled() {
    return true;
  }

  public boolean isFatalEnabled() {
    return true;
  }

  public boolean isInfoEnabled() {
    return true;
  }

  public boolean isTraceEnabled() {
    return true;
  }

  public boolean isWarnEnabled() {
    return true;
  }

  public void trace(Object arg0) {
    android.util.Log.v(tag, arg0.toString());
  }

  public void trace(Object arg0, Throwable arg1) {
    android.util.Log.v(tag, arg0.toString(), arg1);
  }

  public void warn(Object arg0) {
    android.util.Log.w(tag, arg0.toString());

  }

  public void warn(Object arg0, Throwable arg1) {
    android.util.Log.w(tag, arg0.toString(), arg1);

  }

}
