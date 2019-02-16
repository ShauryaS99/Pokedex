package com.jackie.pokedex;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONParser {
    private Context _mContext;
    private JSONObject _pokedexJSON;
    private ArrayList<Pokemon> _pokedex;

    public JSONParser(Context context) {
        _mContext = context;
        _pokedex = new ArrayList<>();
        try {
            _pokedexJSON = new JSONObject(loadJSON(_mContext));
            Iterator<String> keys = _pokedexJSON.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                if (_pokedexJSON.get(key) instanceof JSONObject) {
                    Pokemon p = new Pokemon(key, (JSONObject) _pokedexJSON.get(key));
                    _pokedex.add(p);
                }
            }
        } catch (JSONException ex) {
            Log.e("Error", "Unexpected JSON error.");
        }
    }

    public JSONObject getPokedexJSON() {
        return _pokedexJSON;
    }

    public ArrayList<Pokemon> getPokedex() {
        return _pokedex;
    }

    private String loadJSON(Context context) {
        String json = "";
        try {
            InputStream is = context.getResources().openRawResource(R.raw.pokedata);
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e("Error", "Cannot find JSON file.");
            return null;
        }
        return json;
    }
}
