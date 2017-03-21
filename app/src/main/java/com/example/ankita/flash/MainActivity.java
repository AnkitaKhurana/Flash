package com.example.ankita.flash;

import java.util.*;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    ImageView im1;
    android.hardware.Camera cam;
    boolean x = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        im1 = (ImageView) findViewById(R.id.imageView);

        TextView tv = (TextView) findViewById(R.id.val);
        String val = tv.getText().toString();
        final long value = Long.parseLong(val);

        im1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                    if (x) {
                        im1.setImageResource(R.drawable.on);
                        cam = android.hardware.Camera.open();
                        Camera.Parameters p = cam.getParameters();
                        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        cam.setParameters(p);


                        Runnable mUpdateTimeTask = new Runnable() {
                            public void run() {
                                // Do some stuff that you want to do here
                                while (x) {
                                    cam.startPreview();
                                    mHandler.postDelayed(this, value);
                                    cam.stopPreview();
                                    // You could do this call if you wanted it to be periodic:
                                }

                            }
                        };

                        cam.startPreview();
                        x = false;


                    } else {
                        im1.setImageResource(R.drawable.off);
                        cam.stopPreview();
                        cam.release();
                        x = true;
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Sorry!Flash light not  Available", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}



