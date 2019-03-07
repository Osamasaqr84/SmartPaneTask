package com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.countries.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.parashot_trader.codesroots.smartpanetask.R;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.Countries;

import java.util.ArrayList;
import java.util.List;


public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder>  implements Filterable {

    private Context context;
   private List<Countries> countries;
   private List<Countries> AllCountries;

    public CountriesAdapter(Context mcontext, List<Countries> countries1) {
        context = mcontext;
        countries = countries1;
        AllCountries = new ArrayList<>(countries1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.countryname.setText(countries.get(position).getName());
        holder.capitalname.setText(countries.get(position).getCapital());

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Countries> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(AllCountries);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Countries item : AllCountries) {
                    if (item.getCapital().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countries.clear();
            countries.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private TextView capitalname,countryname;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            capitalname = view.findViewById(R.id.capital_name);
            countryname = view.findViewById(R.id.country_name);
        }
    }
}