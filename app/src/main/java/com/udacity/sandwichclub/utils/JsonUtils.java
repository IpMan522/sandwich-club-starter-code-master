package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich = new Sandwich();
        JSONObject got_sandwich = new JSONObject(json);

        JSONObject names = new JSONObject();
        names = got_sandwich.getJSONObject("name");

        JSONArray alsoknown = new JSONArray();
        alsoknown = names.getJSONArray("alsoKnownAs");

        JSONArray ingridients = new JSONArray();
        ingridients = got_sandwich.getJSONArray("ingredients");

        sandwich.setMainName(names.getString("mainName"));

        List<String> S = new ArrayList<>();
        for (int i = 0; i < alsoknown.length(); i++) {
            S.add(alsoknown.getString(i));
        }

        sandwich.setAlsoKnownAs(S);

        sandwich.setPlaceOfOrigin(got_sandwich.getString("placeOfOrigin"));
        sandwich.setDescription(got_sandwich.getString("description"));
        sandwich.setImage(got_sandwich.getString("image"));

        S = new ArrayList<>();
        for (int i = 0; i < ingridients.length(); i++) {
            S.add(ingridients.getString(i));
        }

        sandwich.setIngredients(S);

        return sandwich;
    }
}
