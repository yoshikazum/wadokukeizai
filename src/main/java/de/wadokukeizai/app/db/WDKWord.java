/**
 * Wadoku Keizai Android Application
 * Copyright (C) 2011 WadokuKeizai All Rights Reserved.
 * http://www.wadokukeizai.de/
 */
package de.wadokukeizai.app.db;

/**
 * @author "Yoshikazu Miyoshi <yoshikazum@gmail.com>"
 * 
 */
public class WDKWord {
  private String mId;       //単語ID
  private String mGenera;   //性
  private String mDe;       //独
  private String mKanji;    //漢字
  private String mHiragana; //ひらがな
  private String mRomaji;   //ローマ字

  /**
   * コンストラクター
   */
  public WDKWord(
      String id,
      String genera,
      String de,
      String kanji,
      String hiragana,
      String romaji) {
    mId       = id;
    mGenera   = genera;
    mDe       = de;
    mKanji    = kanji;
    mHiragana = hiragana;
    mRomaji   = romaji;
  }

  public String getId() {
    return (mId!=null) ? mId:"";
  }

  public String getGenera() {
    return (mGenera!=null)? mGenera:"";
  }

  public String getDe() {
    return mDe!=null ? mDe:"";
  }

  public String getKanji() {
    return mKanji!=null ? mKanji:"";
  }

  public String getHiragana() {
    return mHiragana!=null ? mHiragana:"";
  }

  public String getRomaji() {
    return mRomaji!=null ? mRomaji:"";
  }
}
