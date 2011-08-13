package de.wadokukeizai.app.android.libs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

      log.info("response code: " + conn.getResponseCode());
      Map<String, List<String>> fields = conn.getHeaderFields();

      String redirectedUrlString = new String();
      for (Map.Entry<String, List<String>> aField : fields.entrySet()) {
        log.info(aField.getKey() + ", " + aField.getValue());
        if (aField.getKey() == null)
          continue;
        if (aField.getKey().equalsIgnoreCase("Location")) {
          List<String> list = aField.getValue();
          redirectedUrlString = list.get(0);
        }
      }

      if (conn.getResponseCode() == 302) {
        // Redirect
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
      e.printStackTrace();
      return false;
    } finally {
      if (conn != null)
        conn.disconnect();
      if (in != null)
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        }
      if (f != null) {
        try {
          f.close();
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        }
      }
    }
    return true;

  }

  /**
   * unzip referened here:
   * http://java.sun.com/developer/technicalArticles/Programming/compression/
   * 
   * @param fileInputStreamName
   * @return
   */
  public boolean unzip(String fileInputStreamName) {
    try {
      final int BUFFER = 2048;
      BufferedOutputStream dest = null;
      FileInputStream fis = new FileInputStream(fileInputStreamName);
      CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
      ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));
      ZipEntry entry;

      while ((entry = zis.getNextEntry()) != null) {
        log.info("Extracting: " + entry);
        int count;
        byte data[] = new byte[BUFFER];
        // ファイルをディスクに書き込み
        FileOutputStream fos = new FileOutputStream(entry.getName());
        dest = new BufferedOutputStream(fos, BUFFER);
        while ((count = zis.read(data, 0, BUFFER)) != -1) {
          dest.write(data, 0, count);
        }
        dest.flush();
        dest.close();
        
      }
      zis.close();
      log.info("Checksum: " + checksum.getChecksum().getValue());
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
