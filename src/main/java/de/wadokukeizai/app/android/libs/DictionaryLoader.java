package de.wadokukeizai.app.android.libs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class DictionaryLoader {
	org.apache.commons.logging.Log log;
	public org.apache.commons.logging.Log getLog() {
		return log;
	}

	public void setLog(org.apache.commons.logging.Log log) {
		this.log = log;
	}

	String downloadedFilePath = "WDK.zip";

	public DictionaryLoader() {
		log = new AndroidLog(DictionaryLoader.class.toString());
	}

	public boolean load(URL url) {
		HttpURLConnection conn = null;
		InputStream in = null;
		FileOutputStream f = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			log.info("response code: "+conn.getResponseCode());
			Map<String, List<String>> fields = conn.getHeaderFields();
			
			String redirectedUrlString = new String();
			for(Map.Entry<String, List<String>>aField: fields.entrySet()){
				log.info(aField.getKey()+ ", "+ aField.getValue());
				if(aField.getKey()==null)continue;
				if(aField.getKey().equalsIgnoreCase("Location")){
					List<String> list = aField.getValue();
					redirectedUrlString = list.get(0);
				}
			}
			
			if(conn.getResponseCode()==302){
				//Redirect
				conn.disconnect();
				url = null;
				url = new URL(redirectedUrlString);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
			}

			in = conn.getInputStream();
			f = new FileOutputStream(new File(downloadedFilePath));
			byte[] line = new byte[1024];
			int size;
			while (true) {
				size = in.read(line);
				if (size <= 0) {
					break;
				}
				f.write(line, 0, size);
				log.debug(new String(line));
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null)
				conn.disconnect();
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					return false;
				}
			if(f != null){
				try {
					f.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;

	}
}
