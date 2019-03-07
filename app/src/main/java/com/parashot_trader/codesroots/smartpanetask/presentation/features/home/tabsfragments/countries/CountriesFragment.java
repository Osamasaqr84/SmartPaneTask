package com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.countries;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.parashot_trader.codesroots.smartpanetask.R;
import com.parashot_trader.codesroots.smartpanetask.entities.Countries;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.countries.adapter.CountriesAdapter;

import java.util.List;

public class CountriesFragment extends Fragment {

    private CountriesViewModel countriesViewModel;
    private RecyclerView countriesrecyclerView;
    private FrameLayout progress;
    SearchView searchView;
    CountriesAdapter countriesAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.countries_fragment, container, false);
        countriesrecyclerView = view.findViewById(R.id.countries);
        progress = view.findViewById(R.id.progress);
        countriesViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(CountriesViewModel.class);
        countriesViewModel.countriesMutableLiveData.observe(this, new Observer<List<Countries>>() {
            @Override
            public void onChanged(@Nullable List<Countries> countries) {
                if (countries != null)
                {
                    countriesAdapter = new CountriesAdapter(getActivity(), countries);
                }
                    countriesrecyclerView.setAdapter(countriesAdapter);
                progress.setVisibility(View.GONE);
            }
        });

        countriesViewModel.throwableMutableLiveData.observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {
                if (throwable != null)
                    Toast.makeText(getActivity(),throwable.getCause().toString(),Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }
        });

        SearchView searchView =  view.findViewById(R.id.search_view);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    private CountriesViewModelFactory getViewModelFactory() {
        return new CountriesViewModelFactory(this.getActivity().getApplication());
    }
}
