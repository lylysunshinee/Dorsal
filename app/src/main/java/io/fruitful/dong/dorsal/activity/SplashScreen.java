package io.fruitful.dong.dorsal.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import io.fruitful.dong.dorsal.R;

public class SplashScreen extends Activity {
    protected boolean mActive = true;
    protected int mSplashTime = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screens);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (mActive && (waited < mSplashTime)) {
                        sleep(100);
                        if (mActive) {
                            waited += 100;
                        }
                    }
                } catch (Exception e) {

                } finally {

                    startActivity(new Intent(SplashScreen.this,
                            MainActivity.class));
                    finish();
                }
            }
        };
        splashTread.start();
    }

}
