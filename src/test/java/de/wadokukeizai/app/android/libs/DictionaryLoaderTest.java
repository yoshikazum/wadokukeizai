package de.wadokukeizai.app.android.libs;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import de.wadokukeizai.app.android.libs.DictionaryLoader;
import de.wadokukeizai.app.android.libs.Log4jLog;

public class DictionaryLoaderTest extends TestCase {

  DictionaryLoader loader;
  Log4jLog log;

  public DictionaryLoaderTest() {
    log = new Log4jLog(DictionaryLoader.class.toString());
    loader = new DictionaryLoader();
    loader.setLog(log);
  }

  @Test
  public void testLoad() {
    URL url = null;
    try {
      url = new URL("http://d.hatena.ne.jp/Yoshikazu/files/WDK.zip?d=download");
      // "https://raw.github.com/yoshikazum/self-setting/master/README");
    } catch (MalformedURLException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    }
    boolean result = loader.load(url);
    assertTrue(result);
  }

  @Test
  public void testUnzip() {
    boolean result = loader.unzip("WDK.zip");
    assertTrue(result);
  }
  
  @Test
  public void testHttpGet(){
    String url = new String(
          "http://d.hatena.ne.jp/Yoshikazu/files/WDK.zip?d=download"
        );
    loader.httpGet(url);
  }

}
