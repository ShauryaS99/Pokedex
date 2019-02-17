package com.jackie.pokedex;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.ViewHolder> {

    private final List<Pokemon> mValues;
    private boolean isGrid;
    private Context context;

    public OptionsAdapter(List<Pokemon> items, Context context, boolean isGrid) {
        mValues = items;
        this.context = context;
        this.isGrid = isGrid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (isGrid) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.grid_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.linear_layout, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemonImg;
        TextView pokemonName;
        TextView pokemonNum;

        public ViewHolder(View view) {
            super(view);
            if (isGrid) {
                pokemonImg = view.findViewById(R.id.gridFilteredImg);
                pokemonName = view.findViewById(R.id.gridFilteredName);
                pokemonNum = view.findViewById(R.id.gridFilteredNum);
            } else {
                pokemonImg = view.findViewById(R.id.image_view);
                pokemonName = view.findViewById(R.id.pokemon_name);
                pokemonNum = view.findViewById(R.id.pokemon_number);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ProfileActivity.class);
                    String name = pokemonName.getText().toString();
                    int position = getLayoutPosition();
                    Pokemon currPokemon = mValues.get(position);
                    intent.putExtra("Pokemon", currPokemon);
                    v.getContext().startActivity(intent);
                    context.startActivity(intent);
                }
            });
        }

        void bind(int position) {
            Pokemon currPokemon = mValues.get(position);
            String url = currPokemon.getImgUrl();
            Glide.with(context).load(url).centerCrop().placeholder(R.drawable.normal).into(pokemonImg);
            pokemonName.setText(currPokemon.getName());
            pokemonNum.setText(currPokemon.getNumber());

        }
    }
}

//public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder> {
//    private ArrayList<Pokemon> _pokemon;
//    private ArrayList<Pokemon> _filteredPokemon;
//    private Context _context;
//
//    public OptionsAdapter(ArrayList<Pokemon> pokemon, Context context) {
//        _filteredPokemon = pokemon;
//        _pokemon = new ArrayList<>(pokemon);
//        _context = context;
//    }
//
//    @Override
//    public OptionsViewHolder onCreateViewHolder(ViewGroup parent, int i) {
//        return new OptionsAdapter.OptionsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_layout, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(OptionsViewHolder holder, int index) {
//        holder.bind(index);
//    }
//
//    @Override
//    public int getItemCount() {
//        return _filteredPokemon.size();
//    }
//
//    class OptionsViewHolder extends RecyclerView.ViewHolder {
//        ImageView pokemonImg;
//        TextView pokemonName;
//        TextView pokemonNum;
//
//        OptionsViewHolder(View v) {
//            super(v);
//            // bind instance variables by looking up ids
//            pokemonImg = v.findViewById(R.id.image_view);
//            pokemonName = v.findViewById(R.id.pokemon_name);
//            pokemonNum = v.findViewById(R.id.pokemon_number);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, ProfileActivity.class);
//                    String name = pokemonName.getText().toString();
//                    int position = getLayoutPosition();
//                    Pokemon currPokemon = _filteredPokemon.get(position);
//                    intent.putExtra("Pokemon", currPokemon);
//                    v.getContext().startActivity(intent);
//                    context.startActivity(intent);
//                }
//            });
//        }
//
//        void bind(int position) {
//            Pokemon currPokemon = _filteredPokemon.get(position);
//            String url = currPokemon.getImgUrl();
//            Glide.with(_context).load(url).centerCrop().placeholder(R.drawable.normal).into(pokemonImg);
//            pokemonName.setText(currPokemon.getName());
//            pokemonNum.setText(currPokemon.getNumber());
//
//        }
//
//    }
//
//}
