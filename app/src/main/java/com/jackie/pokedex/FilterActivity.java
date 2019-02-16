package com.jackie.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class FilterActivity extends AppCompatActivity {

    private String[] listOfTypes = {"Normal", "Fire", "Water",
            "Electric", "Grass", "Ice",
            "Fighting", "Poison","Ground",
            "Flying", "Psychic", "Bug",
            "Rock", "Ghost", "Dragon",
            "Dark", "Steel", "Fairy"};

    private int[] listOfImgs = { R.drawable.normal, R.drawable.fire, R.drawable.water,
            R.drawable.electric, R.drawable.grass, R.drawable.ice,
            R.drawable.fighting, R.drawable.poison, R.drawable.ground,
            R.drawable.flying, R.drawable.psychic, R.drawable.bug,
            R.drawable.rock, R.drawable.ghost, R.drawable.dragon,
            R.drawable.dark, R.drawable.steel, R.drawable.fairy
    };

    private GridView _gridView;
    private EditText _atk;
    private EditText _def;
    private EditText _hp;
    private Button _randomize;
    private Button _search;
    private Button _submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        // SEARCH BUTTON -------
        _search = (Button)findViewById(R.id.searchBtn);

        _search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilterActivity.this, SearchActivity.class));
            }
        });

        // GRID VIEW -------
        _gridView = (GridView) findViewById(R.id.gridView);
        final GridAdapter adapter = new GridAdapter(this, listOfImgs, listOfTypes);
        _gridView.setAdapter(adapter);
        _gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedIndex = adapter.getSelectedPositions().indexOf(position);
                if (selectedIndex > -1) {
                    adapter.removePos(selectedIndex);
                    ((CustomGridView) view).display(false);
                } else {
                    if (adapter.getSelectedPositions().size() < 2) {
                        adapter.addPos(position);
                        ((CustomGridView) view).display(true);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "You can only select up to 2 types.",
                                Toast.LENGTH_SHORT);

                        toast.show();
                    }

                }
            }
        });
    }


}
