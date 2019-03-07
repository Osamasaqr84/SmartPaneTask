package com.parashot_trader.codesroots.smartpanetask.presentation.features.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.parashot_trader.codesroots.smartpanetask.R;
import com.parashot_trader.codesroots.smartpanetask.entities.User;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.MainActivity;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView login, register;
    EditText username, password;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        findViewsFromXml();
        loginViewModel = ViewModelProviders.of(this,getViewModelFactory()).get(LoginViewModel.class);
        loginViewModel.loginLiveData.observe(this,loginResponse  ->
                {
                    login.setText(getText(R.string.login));
                    login.setEnabled(true);
                    assert loginResponse != null;
                    if (loginResponse.getId()>0) {
                        showToast("login success");
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                    else if (loginResponse.getSuccess().matches("error"))
                        showToast(loginResponse.getMessage());
                    else
                        showToast("error occure, please try again");
                }
        );

        loginViewModel.errorLiveData.observe(this,throwable ->
        {
            login.setText(getText(R.string.login));
            login.setEnabled(true);
            assert throwable != null;
            if (throwable.getCause()!=null)
                showToast(throwable.getCause().toString());
        });

        loginViewModel.validateLiveDate.observe(this, message ->
        {
            login.setText(getText(R.string.login));
            login.setEnabled(true);
            showToast(message);
        });
    }


    private void findViewsFromXml() {
        register= findViewById(R.id.register);
        login= findViewById(R.id.login);
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        register.setOnClickListener(this);
        login.setOnClickListener(this);

    }
    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new LoginViewModelFactory(getApplication());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register :
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login:
                login.setText("wait ...");
                login.setEnabled(false);
                User user = new User(username.getText().toString(),password.getText().toString() );
                loginViewModel.login(user);
                break;

        }
    }



    //// show message
    private void showToast(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}

