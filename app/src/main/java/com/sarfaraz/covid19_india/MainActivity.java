package com.sarfaraz.covid19_india;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.leo.simplearcloader.SimpleArcLoader;
import com.sarfaraz.covid19_india.model.data.CovidRoot;
import com.sarfaraz.covid19_india.model.data.Statewise;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private CovidRoot covidRoot;
    private Gson gson = new Gson();

    private TextView totalCases, totalActive, totalDeaths, totalRecovered, newDeaths, newRecovered, newCases, updateTime;
    private PieChart chart;
    private Button btnState;
    private List<Statewise> statewises = new ArrayList<>();
    private SimpleArcLoader arcLoader, summaryLoader;
    private LinearLayout summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCases = findViewById(R.id.total_cases);
        totalActive = findViewById(R.id.total_active);
        totalDeaths = findViewById(R.id.total_deaths);
        totalRecovered = findViewById(R.id.total_recovered);
        newCases = findViewById(R.id.new_cases);
        newDeaths = findViewById(R.id.new_deaths);
        newRecovered = findViewById(R.id.new_recovered);
        btnState = findViewById(R.id.show_state_btn);
        arcLoader = findViewById(R.id.pie_arcloader);
        summaryLoader = findViewById(R.id.summary_arcloader);
        summary = findViewById(R.id.summary);
        updateTime = findViewById(R.id.updat_time);

        chart = findViewById(R.id.pie_summary);
        chart.setDrawHoleEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawEntryLabels(false);
        chart.setDrawRoundedSlices(true);
        Legend legend = chart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setTextSize(16);
        legend.setForm(Legend.LegendForm.CIRCLE);


        fetchLiveData();
    }

    private void fetchLiveData() {
        final String API = "https://api.covid19india.org/data.json";
        StringRequest request1 = new StringRequest(Request.Method.GET, API, response -> {

            covidRoot = gson.fromJson(response, CovidRoot.class);
            Log.i(TAG, "onCreate: VollyRequest" + covidRoot.getStatewise().get(0).getConfirmed());
            statewises = covidRoot.getStatewise();
            setTotalData(statewises.get(0));

        }, error -> {
            Log.i(TAG, "onCreate: VollyRequest" + error.networkResponse);
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request1);
    }

    private void setTotalData(Statewise sw) {
        totalCases.setText(sw.getConfirmed());
        totalActive.setText(sw.getActive());
        totalDeaths.setText(sw.getDeaths());
        totalRecovered.setText(sw.getRecovered());
        newCases.setText(sw.getDeltaconfirmed());
        newDeaths.setText(sw.getDeltadeaths());
        newRecovered.setText(sw.getDeltarecovered());

        newDeaths.setText(sw.getDeltadeaths().equals("0") ? "" : String.format("(+%s)", sw.getDeltadeaths()));
        newCases.setText(sw.getDeltaconfirmed().equals("0") ? "" : String.format("(+%s)", sw.getDeltaconfirmed()));
        newRecovered.setText(sw.getDeltarecovered().equals("0") ? "" : String.format("(+%s)", sw.getDeltarecovered()));
        updateTime.setText(getTimeDiferrece(sw.getLastupdatedtime()) + " minutes ago");


        ArrayList<PieEntry> yValue = new ArrayList<>();
        yValue.add(new PieEntry(Integer.parseInt(sw.getActive()), "Active"));
        yValue.add(new PieEntry(Integer.parseInt(sw.getRecovered()), "Recovered"));
        yValue.add(new PieEntry(Integer.parseInt(sw.getDeaths()), "Deaths"));
        int[] colors = {getColor(R.color.active), getColor(R.color.recovered), getColor(R.color.deaths)};

        PieDataSet dataSet = new PieDataSet(yValue, "Total : " + sw.getConfirmed());
        dataSet.setSliceSpace(5f);
        dataSet.setSelectionShift(8f);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(16);

        chart.setData(data);
        chart.setVisibility(View.VISIBLE);
        summary.setVisibility(View.VISIBLE);
        arcLoader.setVisibility(View.GONE);
        summaryLoader.setVisibility(View.GONE);


    }

    public String getTimeDiferrece(String lastUpdate) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyy HH:mm:ss", Locale.ENGLISH);
        Date d1 = null;
        try {
            d1 = format.parse(lastUpdate);
        } catch (ParseException e) {
            return "";
        }
        Date d2 = new Date();
        long diff = d2.getTime() - d1.getTime();
        long seconds = diff / 1000;
        long minutes = (seconds / 60);
        long hours = minutes / 60;
//        return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
        return String.valueOf(minutes);
    }

    public void showStateList(View view) {
        Intent intent = new Intent(MainActivity.this, StateListActivity.class);
        if (!statewises.isEmpty()) {
            statewises.remove(0);
        }
        intent.putExtra("statelist", (Serializable) statewises);
        startActivity(intent);
    }

    public void reloadData(View view) {
        chart.setVisibility(View.GONE);
        summary.setVisibility(View.GONE);
        arcLoader.setVisibility(View.VISIBLE);
        summaryLoader.setVisibility(View.VISIBLE);
        fetchLiveData();
    }
}
