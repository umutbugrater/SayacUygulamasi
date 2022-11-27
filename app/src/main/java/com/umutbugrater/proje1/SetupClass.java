package com.umutbugrater.proje1;

import android.content.Context;
import android.content.SharedPreferences;

public class SetupClass {
    int upperLimit, lowerLimit,currentValue;

    boolean upperVib,upperSound,lowerVib,lowerSound;

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static SetupClass setupClass = null;

    private SetupClass(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("setup",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static SetupClass getInstance(Context context){
        if (setupClass == null) {
            setupClass = new SetupClass(context);
        }
        return setupClass;
    }

    public void loadValues(){
        upperLimit = sharedPreferences.getInt("UpperLimit",20);
        lowerLimit = sharedPreferences.getInt("LowerLimit",0);
        currentValue = sharedPreferences.getInt("currentValue",0);
        upperVib = sharedPreferences.getBoolean("upVibb",false);
        upperSound = sharedPreferences.getBoolean("upSoundd",false);
        lowerVib = sharedPreferences.getBoolean("lowVibb",false);
        lowerSound = sharedPreferences.getBoolean("lowSoundd",false);
    }
}
