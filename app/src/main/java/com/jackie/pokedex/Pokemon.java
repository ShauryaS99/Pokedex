package com.jackie.pokedex;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {
    /** Pokemon attributes. */
    private String _name;
    private String _number;
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
    private String _imgUrl;

    public Pokemon(String name, JSONObject pokemon) {
        _name = name;
        try {
            _number = pokemon.getString("#");
            _atk = pokemon.getInt("Attack");
            _def = pokemon.getInt("Defense");
            _flavorText = pokemon.getString("FlavorText");
            _hp = pokemon.getInt("HP");
            _spHp = pokemon.getInt("Sp. Atk");
            _spDef = pokemon.getInt("Sp. Def");
            _species = pokemon.getString("Species");
            _speed = pokemon.getInt("Speed");
            _total = pokemon.getInt("Total");
            JSONArray typeArr = pokemon.getJSONArray("Type");
            _type = new String[typeArr.length()];
            for (int i = 0; i < typeArr.length(); i++) {
                _type[i] = typeArr.getString(i);
            }
            String imageName = _name.toLowerCase().trim();
            if (_name.contains("(")) {
                imageName = imageName.substring(0, name.indexOf("("));
            }
            imageName = imageName.trim();
            _imgUrl = "https://img.pokemondb.net/artwork/" + imageName + ".jpg";
        } catch (JSONException ex) {
            Log.e("ERROR", "Pokemon name not found: " + name);
        }
    }

    /** <---------- GETTER METHODS. ---------->*/

    public String getName() {
        return _name;
    }

    public String getNumber() {
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

    public String getImgUrl() {
        return _imgUrl;
    }
}
