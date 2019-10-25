package com.example.numberparservsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, Integer> countryCodes = new HashMap<>();
        Map<String, String> prefixes = new HashMap<>();
        countryCodes.put("GB", 44);
        prefixes.put("GB", "0");
        countryCodes.put("US", 1);
        prefixes.put("US", "1");
        NumberParser parser = new NumberParser(countryCodes, prefixes);

        String local1 = parser.parse("02079460056", "+441614960148");
        String local2 = parser.parse("+442079460056", "+441614960148");
        String local3 = parser.parse("02079460056", "+441614960148");

        Log.d("LOCALS", "local1: " + local1);
        Log.d("LOCALS", "local2: " + local2);
        Log.d("LOCALS", "local3: " + local3);

    }
}
