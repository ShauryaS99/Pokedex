package com.jackie.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity {

    private ImageButton gridBtn;
    private ImageButton linearBtn;
    // Either 0 (Grid Layout) or 1 (Linear Layout)
    private int currLayout;

    private OptionsAdapter _adapter;
    private ArrayList<Pokemon> _pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        ArrayList<String> types = getIntent().getStringArrayListExtra("test");
        String minAtk = getIntent().getStringExtra("MinAtk");
        String minDef = getIntent().getStringExtra("MinDef");
        String minHp = getIntent().getStringExtra("MinHp");

        gridBtn = findViewById(R.id.gridBtn);
        linearBtn = findViewById(R.id.linearBtn);
        currLayout = 0;
        JSONParser parser = new JSONParser(this);
        _pokemon = parser.getPokedex();
        setUpRecyclerView(currLayout);


    }

    private void setUpRecyclerView(int currLayout) {
        RecyclerView recyclerView = findViewById(R.id.options_recycler);
        recyclerView.setHasFixedSize(true);
        if (currLayout == 0) {
            RecyclerView.LayoutManager grid = new GridLayoutManager(this, 3);
            _adapter = new OptionsAdapter(_pokemon, getApplicationContext());
            recyclerView.setLayoutManager(grid);
            recyclerView.setAdapter(_adapter);
        } else {
            RecyclerView.LayoutManager linear = new LinearLayoutManager(this);
            _adapter = new OptionsAdapter(_pokemon, getApplicationContext());
            recyclerView.setLayoutManager(linear);
            recyclerView.setAdapter(_adapter);
        }


    }

    void colorOn(int currLayout) {

        
    }
}
