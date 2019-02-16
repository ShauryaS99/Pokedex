package com.jackie.pokedex;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.SearchManager;
import android.support.v7.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;


import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<Pokemon> _pokemon;
    // private ArrayList<PokemonItem> exampleList = new ArrayList<>();
    private SearchAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        JSONParser parser = new JSONParser(this);
        _pokemon = parser.getPokedex();
        Log.e("whatever", "Size of pokemon is: " + _pokemon.size());
        // fillExampleList();
        setUpRecyclerView();
    }

//    private void fillExampleList() {
//        exampleList = new ArrayList<>();
//        exampleList.add(new PokemonItem(R.drawable.ic_search, "One", "Ten"));
//        exampleList.add(new PokemonItem(R.drawable.ic_search, "Two", "Eleven"));
//        exampleList.add(new PokemonItem(R.drawable.ic_search, "Three", "Twelve"));
//        exampleList.add(new PokemonItem(R.drawable.ic_search, "Four", "Thirteen"));
//        exampleList.add(new PokemonItem(R.drawable.ic_search, "Five", "Fourteen"));
//        exampleList.add(new PokemonItem(R.drawable.ic_search, "Six", "Fifteen"));
//        exampleList.add(new PokemonItem(R.drawable.ic_search, "Seven", "Sixteen"));
//        exampleList.add(new PokemonItem(R.drawable.ic_search, "Eight", "Seventeen"));
//        exampleList.add(new PokemonItem(R.drawable.ic_search, "Nine", "Eighteen"));
//    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        _adapter = new SearchAdapter(_pokemon, getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(_adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                _adapter.getFilter().filter(text);
                return false;
            }
        });
        return true;
    }

}
