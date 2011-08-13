package de.wadokukeizai.app.android.libs;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

public class DictionaryLoaderTest extends TestCase {

	DictionaryLoader loader;
	Log4jLog log;
	public DictionaryLoaderTest(){
		log = new Log4jLog(DictionaryLoader.class.toString());
	}
	@Test
	public void testLoad() {
		loader = new DictionaryLoader();
		loader.setLog(log);
		URL url = null;
		try {
			url = new URL(
					"http://d.hatena.ne.jp/Yoshikazu/files/WDK.zip?d=download");
					//"https://raw.github.com/yoshikazum/self-setting/master/README");
		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		boolean result = loader.load(url);
		assertTrue(result);
	}

}
