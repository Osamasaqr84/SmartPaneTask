package com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.phonecontacts.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.parashot_trader.codesroots.smartpanetask.R;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.Countries;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.contact;

import java.util.ArrayList;
import java.util.List;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<contact> contactList;
    private List<contact> AllCountries;

    public ContactsAdapter(Context mcontext, List<contact> contactList1) {
        context = mcontext;
        contactList = contactList1;
        AllCountries = new ArrayList<>(contactList1);

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
        holder.countryname.setText(contactList.get(position).getName());
        holder.capitalname.setText(contactList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<contact> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(AllCountries);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (contact item : AllCountries) {
                    if (item.getName().toLowerCase().startsWith(filterPattern)) {
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
            contactList.clear();
            contactList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private TextView capitalname, countryname;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            capitalname = view.findViewById(R.id.capital_name);
            countryname = view.findViewById(R.id.country_name);
        }
    }
}