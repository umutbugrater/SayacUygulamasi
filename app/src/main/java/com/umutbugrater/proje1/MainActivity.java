package com.umutbugrater.proje1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView value;
    Button minus,plus,ayar;
    int upperLimit,lowerLimit,currentValue;
    boolean upperVib;
    boolean lowerVib;
    boolean upperSound;
    boolean lowerSound;
    SetupClass setupClass;
    Vibrator vibrator = null;
    MediaPlayer player = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = (TextView) findViewById(R.id.value);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        ayar = (Button) findViewById(R.id.setup);

        Context context = getApplicationContext();
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        player = MediaPlayer.create(context,R.raw.ses);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueUpdate(1);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueUpdate(-1);
            }
        });
        ayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SetupActivity.class));
            }
        });
    }

    private void valueUpdate(int step){
        if(step<0)
        {
            if(currentValue+step<lowerLimit){
                currentValue = lowerLimit;
                if(upperSound){
                    player.start();
                }
                if(upperVib){
                    if (vibrator.hasVibrator()){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(100,VibrationEffect.DEFAULT_AMPLITUDE));
                            Toast.makeText(getApplicationContext(),"Titreştim",Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        }else{
            if(currentValue+step>upperLimit)
                currentValue = upperLimit;
            if(upperSound){
                player.start();
            }
            if(upperVib){
                if (vibrator.hasVibrator()){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(100,VibrationEffect.DEFAULT_AMPLITUDE));
                        Toast.makeText(getApplicationContext(),"Titreştim",Toast.LENGTH_LONG).show();
                    }
                }
            }
            else
                currentValue += step;
        }
        value.setText(String.valueOf(currentValue));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupClass.loadValues();
        getPreferences();
    }
    public void getPreferences(){
        currentValue = setupClass.currentValue;
        upperLimit = setupClass.upperLimit;
        lowerLimit = setupClass.lowerLimit;
        upperSound = setupClass.upperSound;
        upperVib = setupClass.upperVib;
        lowerVib = setupClass.lowerVib;
        lowerSound = setupClass.lowerSound;
        Intent intent = getIntent();
        upperVib = intent.getBooleanExtra("upVibb",false);
        upperSound  = intent.getBooleanExtra("upSoundd",false);
        lowerSound = intent.getBooleanExtra("lowSoundd",false);
        lowerVib = intent.getBooleanExtra("lowVibb",false);
        upperLimit = intent.getIntExtra("upperLimit",upperLimit);
        lowerLimit = intent.getIntExtra("lowerLimit",lowerLimit);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN)
                    valueUpdate(5);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN)
                    valueUpdate(-5);
                return true;
        }
        return super.dispatchKeyEvent(event);
    }
}