package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);
        LinkedList<String> sandwichesList = new LinkedList<String>(Arrays.asList(sandwiches));

        // Simplification: Using a ListView instead of a RecyclerView
        RecyclerView recyclerView = findViewById(R.id.sandwiches_recyclerview);
        SandwichListAdapter mAdapter = new SandwichListAdapter(this, sandwichesList);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.getAdapter().notifyItemInserted(sandwichesList.size());
//        // Scroll to the bottom.
//        recyclerView.smoothScrollToPosition(sandwichesList.size());

    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }
}
