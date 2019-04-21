package com.udacity.sandwichclub.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String INGREDIENTS = "ingredients";

    //JSONObject form
    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwichInstance = new Sandwich();
        JSONObject sandwichJsonObject = null;
        try {
            sandwichJsonObject = new JSONObject(json);
            JSONObject name;
            if (sandwichJsonObject.has(NAME) && !sandwichJsonObject.isNull(NAME)) {
                name = sandwichJsonObject.getJSONObject(NAME);
                sandwichInstance.getName().setMainName(name.getString(MAIN_NAME));
                if (name.has(ALSO_KNOWN_AS)) {
                    List<String> alsoKnownAs = new ArrayList<>();
                    JSONArray alsoKnownAsArray = name.getJSONArray(ALSO_KNOWN_AS);
                    if (alsoKnownAsArray != null) {
                        for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                            alsoKnownAs.add(alsoKnownAsArray.getString(i));
                        }
                        sandwichInstance.getName().setAlsoKnownAs(alsoKnownAs);
                    }
                }
            }

            if (sandwichJsonObject.has(PLACE_OF_ORIGIN) && !sandwichJsonObject.isNull(PLACE_OF_ORIGIN)) {
                sandwichInstance.setPlaceOfOrigin(sandwichJsonObject.getString(PLACE_OF_ORIGIN));
            }

            if (sandwichJsonObject.has(DESCRIPTION) && !sandwichJsonObject.isNull(DESCRIPTION)) {
                sandwichInstance.setDescription(sandwichJsonObject.getString(DESCRIPTION));
            }

            if (sandwichJsonObject.has(IMAGE) && !sandwichJsonObject.isNull(IMAGE)) {
                sandwichInstance.setImage(sandwichJsonObject.getString(IMAGE));
            }


            if (sandwichJsonObject.has(INGREDIENTS)) {
                List<String> ingredients = new ArrayList<>();
                JSONArray ingredientsArray = sandwichJsonObject.getJSONArray(INGREDIENTS);
                if (ingredientsArray != null) {
                    for (int i = 0; i < ingredientsArray.length(); i++) {
                        ingredients.add(ingredientsArray.getString(i));
                    }
                    sandwichInstance.setIngredients(ingredients);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwichInstance;
    }

    //Gson form
    public static Sandwich parseSandwichJsonGson(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObj = (JsonObject) jsonParser.parse(json);
        return new Gson().fromJson(jsonObj, Sandwich.class);
    }
}

