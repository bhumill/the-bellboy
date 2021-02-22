package com.bhumil.thebellboy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivLogo;

    private  long mBackPressedTime;
    private  int counter = 0;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ivLogo = (ImageView) findViewById(R.id.iv_splash_logo);


        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    //  Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_fade);
                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation);
                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation);
                    ivLogo.startAnimation(animation1);
                    sleep(3000);
                    Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }

    public void onBackPressed()
    {
        mBackPressedTime = System.currentTimeMillis();
        if(counter == 0)
        {
            Toast.makeText(SplashScreenActivity.this, getString(R.string.st_back_pressed), Toast.LENGTH_SHORT).show();
            counter = 1;
        }
        else
        {
            if (mBackPressedTime + 5000 > System.currentTimeMillis())
            {
                mIntent = new Intent(Intent.ACTION_MAIN);
                mIntent.addCategory(Intent.CATEGORY_HOME);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mIntent);
                finish();
                super.onBackPressed();

            } else
            {
                Toast.makeText(SplashScreenActivity.this, getString(R.string.st_back_pressed), Toast.LENGTH_SHORT).show();
            }
        }
    }
}