package com.jackie.pokedex;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JSONParser {
    private Context mContext;
    private JSONObject pokedex;

    public JSONParser(Context context) {
        mContext = context;
        try {
            pokedex = new JSONObject(loadJSON(mContext));
        } catch (JSONException ex) {
            Log.e("Error", "Unexpected JSON error.");
        }
    }

    public JSONObject getPokedex() {
        return pokedex;
    }

    private String loadJSON(Context context) {
        String json = "";
        try {
            InputStream is = context.getAssets().open("pokeDex.json");
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
