package com.jackie.pokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import 	android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {
    private ArrayList<Pokemon> _pokemon;
    private ArrayList<Pokemon> _filteredPokemon;
    private Context _context;

    SearchAdapter(ArrayList<Pokemon> pokemon, Context context) {
        _filteredPokemon = pokemon;
        _pokemon = new ArrayList<>(pokemon);
        _context = context;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewtypes) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int index) {
        holder.bind(index);
    }

    @Override
    public int getItemCount() {
        return _filteredPokemon.size();
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
        }

        void bind(int position) {
            Pokemon currPokemon = _filteredPokemon.get(position);
            String url = currPokemon.getImgUrl();
            Glide.with(_context).load(url).centerCrop().placeholder(R.drawable.normal).into(pokemonImg);
            pokemonName.setText(currPokemon.getName());
            pokemonNum.setText(currPokemon.getNumber());

        }

    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Pokemon> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(_pokemon);
            } else {
                String prefix = constraint.toString().toLowerCase().trim();
                for (Pokemon p : _pokemon) {
                    if (p.getName().toLowerCase().startsWith(prefix) || p.getNumber().startsWith(prefix)) {
                        filteredList.add(p);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            _filteredPokemon.clear();
            _filteredPokemon.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
