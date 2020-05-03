package com.sarfaraz.covid19_india.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sarfaraz.covid19_india.R;
import com.sarfaraz.covid19_india.model.districtwise.DistrictDatum;
import com.sarfaraz.covid19_india.model.zone.Zone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder> implements Filterable {
    private final String TAG = getClass().getSimpleName();
    private LayoutInflater layoutInflater;
    private List<DistrictDatum> data;
    private List<DistrictDatum> filterData;
    private Context context;
    private List<Zone> zoneList;
    private Map<String, String> zoneMap;
    private String zo = "";

    public DistrictAdapter(Context context, List<DistrictDatum> datalist, List<Zone> zoneList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = datalist;
        filterData = new ArrayList<>(datalist);
        this.context = context;
        this.zoneList = zoneList;
        zoneMap = setupZoneMap();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.district_tems, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DistrictDatum s = data.get(position);
        holder.districtName.setText(s.getDistrict());
        holder.active.setText(String.valueOf(s.getActive()));
        holder.deaths.setText(String.valueOf(s.getDeceased()));
        holder.confirmed.setText(String.valueOf(s.getConfirmed()));
        holder.recovered.setText(String.valueOf(s.getRecovered()));
        holder.newDeaths.setText(s.getDelta().getDeceased() == 0 ? "" : String.format("(+%s)", s.getDelta().getDeceased()));
        holder.newConfirmed.setText(s.getDelta().getConfirmed() == 0 ? "" : String.format("(+%s)", s.getDelta().getConfirmed()));
        holder.newRecovered.setText(s.getDelta().getRecovered() == 0 ? "" : String.format("(+%s)", s.getDelta().getRecovered()));

        zo = zoneMap.get(s.getDistrict());
        Log.i(TAG, "onBindViewHolder: ZoneBug : " + zo + " of " + s.getDistrict());
        switch (zo) {
            case "Orange":
                holder.cardView.setCardBackgroundColor(context.getColor(R.color.orage));
                holder.zone_tag.setText("Orange");
                break;
            case "Red":
                holder.cardView.setCardBackgroundColor(context.getColor(R.color.confirmed));
                holder.zone_tag.setText("Red");
                break;
            case "Green":
                holder.cardView.setCardBackgroundColor(context.getColor(R.color.recovered));
                holder.zone_tag.setText("Green");
                break;
            default:
                holder.cardView.setCardBackgroundColor(context.getColor(R.color.deaths));
                holder.zone_tag.setText("None");
                break;
        }
    }

    private Map<String, String> setupZoneMap() {
        Map<String, String> zoneMap = new HashMap<>();
        for (Zone zone : zoneList) {
            for (DistrictDatum datum : data) {
                if (zone.getDistrict().equals(datum.getDistrict()))
                    zoneMap.put(datum.getDistrict(), zone.getZone());
                if (datum.getDistrict().equals("Unknown"))
                    zoneMap.put("Unknown", "Unknown");
            }
        }
        return zoneMap;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return districtFilter;
    }

    private Filter districtFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DistrictDatum> filterDist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterDist.addAll(filterData);
            } else {
                String searchTerm = constraint.toString().toLowerCase().trim();
                for (DistrictDatum sw : filterData) {
                    if (sw.getDistrict().toLowerCase().contains(searchTerm)) {
                        filterDist.add(sw);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterDist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((Collection<? extends DistrictDatum>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView districtName, active, deaths, confirmed, recovered, newDeaths, newRecovered, newConfirmed, zone_tag, zone_update;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            districtName = itemView.findViewById(R.id.district_name);
            cardView = itemView.findViewById(R.id.zone_status);
            zone_tag = itemView.findViewById(R.id.zone_tag);
            zone_update = itemView.findViewById(R.id.zone_update);

            active = itemView.findViewById(R.id.active);
            deaths = itemView.findViewById(R.id.deaths);
            confirmed = itemView.findViewById(R.id.confirmed);
            recovered = itemView.findViewById(R.id.recovered);
            newDeaths = itemView.findViewById(R.id.deltadeaths);
            newRecovered = itemView.findViewById(R.id.deltarecovered);
            newConfirmed = itemView.findViewById(R.id.deltaconfirmed);
        }
    }
}
