package com.parashot_trader.codesroots.smartpanetask.presentation.features.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.parashot_trader.codesroots.smartpanetask.domain.repositries.RegisterRepository;
import com.parashot_trader.codesroots.smartpanetask.entities.RegisterResponse;
import com.parashot_trader.codesroots.smartpanetask.entities.User;


public class RegisterViewModel extends ViewModel {

    private RegisterRepository registerRepository;
    MutableLiveData<RegisterResponse> registerLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<String> validateLiveDate = new MutableLiveData<>();

    public RegisterViewModel() {
    }

    public void saveUser(User user) {
        if (validate(user)) {
            registerRepository.registerUser(user);
        }
    }

    private boolean validate(User user) {
        if (user.getName().matches("") || user.getUsernam().matches("") || user.getPassword().matches("") || user.getMail().matches("")
                || user.getMobile().matches("") || user.getGender() == -1 || user.getRepassword().matches("")) {
            validateLiveDate.postValue("please commplete empty data");
            return false;
        } else if (!user.getPassword().matches(user.getRepassword())) {
            validateLiveDate.postValue("password and re-password not equil");
            return false;
        }
        else return true;
    }

    public RegisterViewModel(final RegisterRepository repository) {

        repository.setOnSuccess(model -> registerLiveData.postValue(model));
        repository.setOnError(throwable -> errorLiveData.postValue(throwable));

        this.registerRepository = repository;
    }


}
