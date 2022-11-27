package com.umutbugrater.proje1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {

    int upLimit = 20, dLimit =0;
    Button uplus,uminus,dplus,dminus;
    EditText upValue, dValue;
    CheckBox upVib,upSound,lowVib,lowSound;
    boolean upSoundd,upVibb,lowSoundd,lowVibb;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        uplus = (Button) findViewById(R.id.up_plus);
        uminus = (Button) findViewById(R.id.up_minus);
        upValue = (EditText) findViewById(R.id.upperLimit);
        dplus = (Button) findViewById(R.id.low_plus);
        dminus = (Button) findViewById(R.id.low_minus);
        dValue = (EditText)findViewById(R.id.lowLimit);

        upVib = (CheckBox) findViewById(R.id.upVib);
        upSound = (CheckBox) findViewById(R.id.upSound);
        lowVib = (CheckBox) findViewById(R.id.lowVib);
        lowSound = (CheckBox) findViewById(R.id.lowSound);

        upVib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(upVib.isChecked())
                    upVibb=true;
            }
        });
        upSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(upSound.isChecked())
                    upSoundd=true;
            }
        });
        lowVib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(lowVib.isChecked())
                    lowVibb=true;
            }
        });
        lowSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(lowSound.isChecked())
                    lowSoundd=true;
            }
        });
        Context context = getApplicationContext() ;
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        upLimit = Integer.valueOf(upValue.getText().toString());
        dLimit = Integer.valueOf(dValue.getText().toString());
        editor.putInt("UpperLimit",upLimit);
        editor.putInt("LowerLimit",dLimit);
        editor.putBoolean("upVib",upVibb);
        editor.putBoolean("upSound",upSoundd);
        editor.putBoolean("lowVib",lowVibb);
        editor.putBoolean("lowSound",lowSoundd);
        editor.commit();
    }

    public void geriDon(View view) {
        Intent intent = new Intent(SetupActivity.this,MainActivity.class);
        intent.putExtra("upperLimit",upLimit);
        intent.putExtra("lowLimit",dLimit);
        intent.putExtra("upSoundd",upSoundd);
        intent.putExtra("upVibb",upVibb);
        intent.putExtra("lowSound",lowSoundd);
        intent.putExtra("lowVibb",lowVibb);
        startActivity(intent);
    }
}