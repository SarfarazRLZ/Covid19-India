package com.sarfaraz.covid19_india;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sarfaraz.covid19_india.adapter.StateAdapter;
import com.sarfaraz.covid19_india.model.data.Statewise;

import java.util.ArrayList;
import java.util.List;

public class StateListActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    List<Statewise> statewiseList = new ArrayList<>();
    RecyclerView recyclerView;
    StateAdapter stateAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.state_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                stateAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);
        recyclerView = findViewById(R.id.state_recycle_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All States");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (getIntent().getExtras().getSerializable("statelist") != null) {
            statewiseList = (List<Statewise>) getIntent().getExtras().getSerializable("statelist");
        }

        Log.i(TAG, "onCreate: " + statewiseList.get(0));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stateAdapter = new StateAdapter(this, statewiseList);
        recyclerView.setAdapter(stateAdapter);

        stateAdapter.setOnStateClickListener(position -> {
            Intent intent = new Intent(this, DistrictActivity.class);
            intent.putExtra("statecode", statewiseList.get(position).getStatecode());
            intent.putExtra("state", statewiseList.get(position).getState());
            startActivity(intent);
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
