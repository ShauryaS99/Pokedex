package com.jackie.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        ArrayList<String> types = getIntent().getStringArrayListExtra("test");
        String minAtk = getIntent().getStringExtra("MinAtk");
        String minDef = getIntent().getStringExtra("MinDef");
        String minHp = getIntent().getStringExtra("MinHp");
    }
}
