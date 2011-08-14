/**
 * Wadoku Keizai Android Application
 * Copyright (C) 2011 WadokuKeizai All Rights Reserved.
 * http://www.wadokukeizai.de/
 */
package de.wadokukeizai.app.android;

import android.app.Activity;
import android.os.Bundle;
/**
 * @author "Yoshikazu Miyoshi <yoshikazum@gmail.com>"
 *
 */
public class WadokukeizaiActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}