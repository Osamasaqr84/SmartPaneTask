package com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.phonecontacts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.parashot_trader.codesroots.smartpanetask.R;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.contact;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.countries.CountriesViewModelFactory;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.countries.adapter.CountriesAdapter;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.phonecontacts.adapter.ContactsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class PhoneContactsFragment extends Fragment {

    private PhoneContactsViewModel phoneContactsViewModel;
    private RecyclerView contactsRecyclerView;
    private FrameLayout progress;
    SearchView searchView;
    ContactsAdapter countriesAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.phone_contacts_fragment, container, false);
        findViewsFromXml(view);  /////initialize views
        phoneContactsViewModel = ViewModelProviders.of(this,getViewModelFactory()).get(PhoneContactsViewModel.class);
        phoneContactsViewModel.contactsdataMutableLiveData.observe(this, contacts -> {
            countriesAdapter = new ContactsAdapter(getActivity(),contacts);
            contactsRecyclerView.setAdapter(countriesAdapter);
            progress.setVisibility(View.GONE);
        });

        return view;
    }

    private void findViewsFromXml(View view) {
        contactsRecyclerView = view.findViewById(R.id.contacts);
        progress = view.findViewById(R.id.progress);
        searchView =  view.findViewById(R.id.search_view);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(onQueryTextListener);
    }


    //////search action
    private SearchView.OnQueryTextListener onQueryTextListener =new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }
        @Override
        public boolean onQueryTextChange(String newText) {
            if (countriesAdapter!=null)
                countriesAdapter.getFilter().filter(newText);
            return false;
        }
    };

    ////// initialize consyructor of viewmodel
    private PhonesViewModelFactory getViewModelFactory() {
        return new PhonesViewModelFactory(getActivity());
    }

}
