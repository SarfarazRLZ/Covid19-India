package com.sarfaraz.covid19_india;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sarfaraz.covid19_india.adapter.DistrictAdapter;
import com.sarfaraz.covid19_india.model.districtwise.AllDistricts;
import com.sarfaraz.covid19_india.model.districtwise.DistrictDatum;
import com.sarfaraz.covid19_india.model.zone.Zone;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DistrictActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private List<AllDistricts> districts;
    private Gson gson = new Gson();
    private String stateCode = "";
    private String state = "";
    private List<DistrictDatum> districtData = new ArrayList<>();
    private List<Zone> zones = new ArrayList<>();
    private RecyclerView recyclerView;
    private DistrictAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);

        stateCode = getIntent().getStringExtra("statecode");
        state = getIntent().getStringExtra("state");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(state);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.district_recycle);

        fetchDistrictData();
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DistrictAdapter(this, districtData, zones);
        recyclerView.setAdapter(adapter);
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

    public void fetchDistrictData() {
        final String API = "https://api.covid19india.org/v2/state_district_wise.json";
        final String ZONE_API = "https://api.covid19india.org/zones.json";

        StringRequest request1 = new StringRequest(Request.Method.GET, API, response -> {
            Type type = new TypeToken<ArrayList<AllDistricts>>() {
            }.getType();
            districts = gson.fromJson(response, type);
            Log.i(TAG, "fetchDistrictData: " + stateCode);
            Log.i(TAG, "fetchDistrictData: " + districts.size());
            for (AllDistricts distr : districts) {
                if (distr.getStatecode().equals(stateCode)) {
                    districtData = distr.getDistrictData();
                }
            }
        }, error -> {
            Log.i(TAG, "onCreate: VollyRequest" + error.networkResponse);
        });
        JsonObjectRequest objectRequest = new JsonObjectRequest(0, ZONE_API, null, response -> {
            Type type = new TypeToken<List<Zone>>() {
            }.getType();
            try {
                zones = gson.fromJson(response.getJSONArray("zones").toString(), type);
                initAdapter();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Log.i(TAG, "fetchDistrictData: " + error.getMessage());

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request1);
        requestQueue.add(objectRequest);
    }

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
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
