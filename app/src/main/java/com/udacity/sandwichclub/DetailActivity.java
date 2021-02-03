package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView alsoKnownAs = findViewById(R.id.also_known_tv);
        TextView descrpition = findViewById(R.id.description_tv);
        TextView placeOfOrigin = findViewById(R.id.origin_tv);
        TextView ingridients = findViewById(R.id.ingredients_tv);

        alsoKnownAs.setText(listToString(sandwich.getAlsoKnownAs()));
        ingridients.setText(listToString(sandwich.getIngredients()));
        descrpition.setText(sandwich.getDescription());
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());




    }

    private String listToString (List<String> array) {
        String output = "";
        for (int i = 0; i < array.size(); i++) {
            if (i != array.size()-1) {
                output = output + array.get(i)+", ";
            } else {
                output = output + array.get(i);
            }
        }
        return output;
    }
}
