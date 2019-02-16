package com.jackie.pokedex;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {
    private String _name;
    private int _number;
    private int _atk;
    private int _def;
    private String _flavorText;
    private int _hp;
    private int _spHp;
    private int _spDef;
    private String _species;
    private int _speed;
    private int _total;
    private String[] _type;

    public Pokemon(String name, JSONParser pokedex) {
        _name = name;
        try {
            JSONObject pokemon = pokedex.getPokedex().getJSONObject(name);
            _number = pokemon.getInt("#");
            _atk = pokemon.getInt("Attack");
            _def = pokemon.getInt("Defense");
            _flavorText = pokemon.getString("FlavorText");
            _hp = pokemon.getInt("HP");
            _spHp = pokemon.getInt("SP. Atk");
            _spDef = pokemon.getInt("Sp. Def");
            _species = pokemon.getString("Species");
            _speed = pokemon.getInt("Speed");
            _total = pokemon.getInt("Total");
            JSONArray typeArr = pokemon.getJSONArray("Type");
            _type = new String[typeArr.length()];
            for (int i = 0; i < typeArr.length(); i++) {
                _type[i] = typeArr.getString(i);
            }
        } catch (JSONException ex) {
            Log.e("ERROR", "Pokemon name not found.");
        }
    }

    /** <---------- GETTER METHODS. ---------->*/

    public String getName() {
        return _name;
    }

    public int getNumber() {
        return _number;
    }

    public int getAtk() {
        return _atk;
    }

    public int getDef() {
        return _def;
    }

    public String getFlavorText() {
        return _flavorText;
    }

    public int getHp() {
        return _hp;
    }

    public int getSpHp() {
        return _spHp;
    }

    public int getSpDef() {
        return _spDef;
    }

    public String getSpecies() {
        return _species;
    }

    public int getSpeed() {
        return _speed;
    }

    public int getTotal() {
        return _total;
    }

    public String[] getType() {
        return _type;
    }
}
