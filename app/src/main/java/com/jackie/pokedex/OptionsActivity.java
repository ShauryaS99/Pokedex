package com.jackie.pokedex;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Random;

public class OptionsActivity extends AppCompatActivity {

    private boolean gridView = false;
    private RecyclerView recyclerView;
    private Context context;
    private ImageButton gridBtn;
    private ImageButton linearBtn;
    private ArrayList<Pokemon> _filteredPokemon;
    private String minAtk;
    private String minDef;
    private String minHp;
    private ArrayList<String> types;
    private boolean randomize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        randomize = getIntent().getBooleanExtra("Randomize", false);

        minAtk = getIntent().getStringExtra("MinAtk");
        if (minAtk == null || minAtk.equals("")) {
            minAtk = "0";
        }
        minDef = getIntent().getStringExtra("MinDef");
        if (minDef == null || minDef.equals("")) {
            minDef = "0";
        }
        minHp = getIntent().getStringExtra("MinHp");
        if (minHp == null || minHp.equals("")) {
            minHp = "0";
        }
        types = getIntent().getStringArrayListExtra("Types");
        _filteredPokemon = new ArrayList<Pokemon>();
        recyclerView = (RecyclerView) findViewById(R.id.options_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        gridBtn = (ImageButton) findViewById(R.id.gridBtn);

        gridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView = true;
                recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            }
        });

        linearBtn = (ImageButton) findViewById(R.id.linearBtn);

        linearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView = false;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
        });

        if (randomize) {
            generateRandom();
        } else {
            filterPokemon();
        }

        recyclerView.setAdapter(new OptionsAdapter(_filteredPokemon, getApplicationContext(), gridView));
    }

    void filterPokemon() {
        JSONParser parser = new JSONParser(this);
        ArrayList<Pokemon> pokemon = parser.getPokedex();
        for (Pokemon p : pokemon) {
            if (p.getAtk() > Integer.parseInt(minAtk) &&
                    p.getDef() > Integer.parseInt(minDef) &&
                    p.getHp() > Integer.parseInt(minHp)) {
                boolean flag = true;
                for (String type : p.getType()) {
                    if (!types.contains(type)) {
                        flag = false;
                    }
                }
                if (flag) {
                    _filteredPokemon.add(p);
                }

            }
        }

    }

    void generateRandom() {
        JSONParser parser = new JSONParser(this);
        ArrayList<Pokemon> pokemon = parser.getPokedex();
        for (int i = 0; i < 20; i++) {
            Random r = new Random();
            int num = r.nextInt(pokemon.size());
            _filteredPokemon.add(pokemon.get(num));
        }
    }

}

//public class OptionsActivity extends AppCompatActivity {
//
//    private ImageButton gridBtn;
//    private ImageButton linearBtn;
//    // Either 0 (Grid Layout) or 1 (Linear Layout)
//    private int currLayout;
//
//    private OptionsAdapter _adapter;
//    private ArrayList<Pokemon> _pokemon;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_options);
//
//        ArrayList<String> types = getIntent().getStringArrayListExtra("test");
//        String minAtk = getIntent().getStringExtra("MinAtk");
//        String minDef = getIntent().getStringExtra("MinDef");
//        String minHp = getIntent().getStringExtra("MinHp");
//
//
//        gridBtn = findViewById(R.id.gridBtn);
//        linearBtn = findViewById(R.id.linearBtn);
//        currLayout = 1;
//        JSONParser parser = new JSONParser(this);
//        _pokemon = parser.getPokedex();
//        setUpRecyclerView(currLayout);
//
//
//    }
//
//    private void setUpRecyclerView(int currLayout) {
//        RecyclerView recyclerView = findViewById(R.id.options_recycler);
//        recyclerView.setHasFixedSize(true);
//        if (currLayout == 0) {
//            RecyclerView.LayoutManager grid = new GridLayoutManager(this, 3);
//            _adapter = new OptionsAdapter(_pokemon, getApplicationContext());
//            recyclerView.setLayoutManager(grid);
//            recyclerView.setAdapter(_adapter);
//        } else {
//            RecyclerView.LayoutManager linear = new LinearLayoutManager(this);
//            _adapter = new OptionsAdapter(_pokemon, getApplicationContext());
//            recyclerView.setLayoutManager(linear);
//            recyclerView.setAdapter(_adapter);
//        }
//
//
//    }
//
//    void colorOn(int currLayout) {
//
//
//    }
//}
