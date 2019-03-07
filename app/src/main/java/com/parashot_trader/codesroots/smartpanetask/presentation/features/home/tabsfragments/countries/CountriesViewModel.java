package com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.countries;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.ServerGateway;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.Countries;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class CountriesViewModel extends ViewModel {


    public MutableLiveData<List<Countries>> countriesMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private  ServerGateway serverGateway;

    public CountriesViewModel(ServerGateway serverGateway1) {
        serverGateway = serverGateway1;
        getData();
    }

    public void getData() {
        getObservable().subscribeWith(getObserver());
    }


    @SuppressLint("CheckResult")
    public Observable<List<Countries>> getObservable() {
        Observable<List<Countries>> photographersData = serverGateway.getAllCountries();
        photographersData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return photographersData;
    }

    public DisposableObserver<List<Countries>> getObserver() {
        return new DisposableObserver<List<Countries>>() {
            @Override
            public void onNext(@NonNull List<Countries> result) {
                if (countriesMutableLiveData!=null)
                countriesMutableLiveData.postValue(result);
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("Errors", "Error" + e);
                e.printStackTrace();
                throwableMutableLiveData.postValue(e);
            }
            @Override
            public void onComplete() {
            }
        };
    }
}
