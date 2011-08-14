/**
 * Wadoku Keizai Android Application
 * Copyright (C) 2011 WadokuKeizai All Rights Reserved.
 * http://www.wadokukeizai.de/
 */

package de.wadokukeizai.app.db;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * WSDKSearch 辞書検索クラス
 * 
 * @author "Yoshikazu Miyoshi <yoshikazum@gmail.com>"
 * 
 */
public class WDKSearch {
  SQLiteDatabase db;

  /**
   * コンストラクタ
   * 
   * @param db
   */
  public WDKSearch(SQLiteDatabase db) {
    this.db = db;
  }

  /**
   * Cursorクラス クエリーを取得して辞書から単語を検索する
   * 
   * @param query
   * @return
   */
  public Cursor search(String query) {
    Cursor c = null;
    try {
      //@formatter:off
      c = db.rawQuery("select * from dictionary where " +
                      "de like '%"      + query+ "%' or " +
                      "kanji like '%"   + query+ "%' or " +
                      "hiragana like '%"+ query+ "%' or " +
                      "romaji like '%"  + query+ "%';", null);
      //@formatter:on
    } catch (SQLException e) {
      throw e;
    }
    return c != null ? c : null;
  }
}
