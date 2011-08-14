/**
 * Wadoku Keizai Android Application
 * Copyright (C) 2011 WadokuKeizai All Rights Reserved.
 * http://www.wadokukeizai.de/
 */
package de.wadokukeizai.app.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;

import de.wadokukeizai.app.android.libs.AndroidLog;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author yoshikazu
 * 
 */
public class WDKDBHelper extends SQLiteOpenHelper {

  private Log log;
  private static String DB_PATH =
      "/data/data/de.wadokukeizai.app.android/databases";
  private static String DB_NAME_ASSET = "wdk.db";
  private static String DB_NAME = "dictionary";
  static final int DB_VERSION = 1;
  static final String DB = "wdk.db";

  private SQLiteDatabase mDataBase;
  private final Context mContext;

  public WDKDBHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
    this.mContext = context;
    log = new AndroidLog(WDKDBHelper.class.getCanonicalName());
  }

  public Log getLog() {
    return log;
  }

  public void setLog(Log log) {
    this.log = log;
  }

  /**
   * assetに格納したデータベースをコピーするための空データベース作成 referenced here:
   * http://y-anz-m.blogspot.com/2011/01/android-sqline-database.html
   */
  public void createEmptyDataBase() throws IOException {
    boolean dbExist = checkDataBaseExists();

    if (dbExist)
      return;

    this.getReadableDatabase();

    try {
      copyDataBaseFromAsset();
    } catch (Exception e) {
      throw new Error("Error copying database");
    }
  }

  /**
   * assetに格納したデータベースをデフォルトの データベースパスに作成してからデータベースにコピーする
   */
  private void copyDataBaseFromAsset() throws IOException {
    InputStream input = mContext.getAssets().open(DB_NAME_ASSET);

    String outFileName = DB_PATH + DB_NAME;

    OutputStream output = new FileOutputStream(outFileName);

    // copy
    byte[] buffer = new byte[1024];
    int size;
    while ((size = input.read(buffer)) > 0) {
      output.write(buffer, 0, size);
    }

    output.flush();
    output.close();
    input.close();
  }

  public boolean checkDataBaseExists() {
    SQLiteDatabase checkDb = null;

    try {
      String dbPath = DB_PATH + DB_NAME;
      checkDb =
          SQLiteDatabase.openDatabase(
              dbPath,
              null,
              SQLiteDatabase.OPEN_READONLY);
    } catch (SQLException e) {
      // データベースなし
    }

    if (checkDb != null)
      checkDb.close();

    return checkDb != null ? true : false;
  }

  public SQLiteDatabase openDatabase() throws SQLException {
    String path = DB_PATH + DB_NAME;
    log.info(path);
    mDataBase =
        SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    return mDataBase;
  }

  /*
   * (非 Javadoc)
   * 
   * @see
   * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
   * .SQLiteDatabase)
   */
  @Override
  public void onCreate(SQLiteDatabase db) {

  }

  /*
   * (非 Javadoc)
   * 
   * @see
   * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
   * .SQLiteDatabase, int, int)
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    /*
     * db.execSQL("create table products(" + "  name text not null," +
     * "  price text" + ");");
     */
  }

}
