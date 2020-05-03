package com.sarfaraz.covid19_india.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarfaraz.covid19_india.R;
import com.sarfaraz.covid19_india.model.data.Statewise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> implements Filterable {
    private final String TAG = getClass().getSimpleName();
    private LayoutInflater layoutInflater;
    private List<Statewise> statewiseList;
    private List<Statewise> filteredList;
    private OnStateClickListener clickListener;

    public StateAdapter(Context context, List<Statewise> statewises) {
        this.layoutInflater = LayoutInflater.from(context);
        this.statewiseList = statewises;
        this.filteredList = new ArrayList<>(statewises);
    }

    public void setOnStateClickListener(OnStateClickListener onStateClickListener) {
        this.clickListener = onStateClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.state_list_item, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Statewise s = statewiseList.get(position);
        holder.stateName.setText(s.getState());
        holder.stateCode.setText(s.getStatecode());

        holder.active.setText(s.getActive());
        holder.deaths.setText(s.getDeaths());
        holder.confirmed.setText(s.getConfirmed());
        holder.recovered.setText(s.getRecovered());

        holder.newDeaths.setText(s.getDeltadeaths().equals("0") ? "" : String.format("(+%s)", s.getDeltadeaths()));
        holder.newConfirmed.setText(s.getDeltaconfirmed().equals("0") ? "" : String.format("(+%s)", s.getDeltaconfirmed()));
        holder.newRecovered.setText(s.getDeltarecovered().equals("0") ? "" : String.format("(+%s)", s.getDeltarecovered()));

    }

    @Override
    public int getItemCount() {
        return statewiseList.size();
    }

    @Override
    public Filter getFilter() {
        return stateSearchFilter;
    }

    private Filter stateSearchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Statewise> filterState = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterState.addAll(filteredList);
            } else {
                String searchTerm = constraint.toString().toLowerCase().trim();
                for (Statewise sw : filteredList) {
                    if (sw.getState().toLowerCase().contains(searchTerm)) {
                        filterState.add(sw);
                    }
                    if (sw.getStatecode().toLowerCase().contains(searchTerm)) {
                        filterState.add(sw);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterState;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            statewiseList.clear();
            statewiseList.addAll((Collection<? extends Statewise>) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stateName, stateCode, active, deaths, confirmed, recovered, newDeaths, newRecovered, newConfirmed;

        public ViewHolder(@NonNull View itemView, final OnStateClickListener listener) {
            super(itemView);
            stateName = itemView.findViewById(R.id.state_name);
            stateCode = itemView.findViewById(R.id.state_code);

            active = itemView.findViewById(R.id.active);
            deaths = itemView.findViewById(R.id.deaths);
            confirmed = itemView.findViewById(R.id.confirmed);
            recovered = itemView.findViewById(R.id.recovered);
            newDeaths = itemView.findViewById(R.id.deltadeaths);
            newRecovered = itemView.findViewById(R.id.deltarecovered);
            newConfirmed = itemView.findViewById(R.id.deltaconfirmed);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.OnStateClicked(pos);
                    }
                }
            });

        }
    }

    public interface OnStateClickListener {
        void OnStateClicked(int position);
    }
}
