package com.system.amto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.system.amto.comm.Cache;

import java.io.IOException;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
            Cache.init(getApplicationContext());

        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, BakeGroudServer.class);
        startService(intent);

    }
}
