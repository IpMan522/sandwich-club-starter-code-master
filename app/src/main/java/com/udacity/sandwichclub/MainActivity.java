package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RVAdapter(this, sandwiches));

    }

}
