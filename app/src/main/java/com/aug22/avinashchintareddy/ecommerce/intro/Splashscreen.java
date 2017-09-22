package com.aug22.avinashchintareddy.ecommerce.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aug22.avinashchintareddy.ecommerce.R;
/*
* Initial splashscren activity
* */
public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Log.i("Test","entered thread");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                callActivity();
            }
        }).start();
        }

public void callActivity()  {
        //  try {
        // Thread.sleep(500);
        // }
        //  catch(InterruptedException e){/     e.printStackTrace();
        //  }
        //   runOnUiThread(new Runnable() {
        // @Override
        // public void run() {
        Log.i("Test","entered call");
        Intent intent= new Intent(Splashscreen.this,SecondActivity.class);
        startActivity(intent);


    }
}
