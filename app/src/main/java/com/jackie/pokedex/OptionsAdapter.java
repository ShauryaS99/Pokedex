package com.jackie.pokedex;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class OptionsAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private ArrayList<Pokemon> _pokemon;
    private ArrayList<Pokemon> _filteredPokemon;
    private Context _context;

    public OptionsAdapter(ArrayList<Pokemon> pokemon, Context context) {
        _filteredPokemon = pokemon;
        _pokemon = new ArrayList<>(pokemon);
        _context = context;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.SearchViewHolder searchViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemonImg;
        TextView pokemonName;
        TextView pokemonNum;

        SearchViewHolder(View v) {
            super(v);
            // bind instance variables by looking up ids
            pokemonImg = v.findViewById(R.id.image_view);
            pokemonName = v.findViewById(R.id.pokemon_name);
            pokemonNum = v.findViewById(R.id.pokemon_number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ProfileActivity.class);
                    String name = pokemonName.getText().toString();
                    int position = getLayoutPosition();
                    Pokemon currPokemon = _filteredPokemon.get(position);
                    intent.putExtra("Pokemon", currPokemon);
                    v.getContext().startActivity(intent);
                    context.startActivity(intent);
                }
            });
        }

        void bind(int position) {
            Pokemon currPokemon = _filteredPokemon.get(position);
            String url = currPokemon.getImgUrl();
            Glide.with(_context).load(url).centerCrop().placeholder(R.drawable.normal).into(pokemonImg);
            pokemonName.setText(currPokemon.getName());
            pokemonNum.setText(currPokemon.getNumber());

        }

    }
}
