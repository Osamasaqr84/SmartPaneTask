package com.parashot_trader.codesroots.smartpanetask.presentation.features.register;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parashot_trader.codesroots.smartpanetask.R;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.User;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.MainActivity;

import org.json.JSONException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    EditText nametxt,usernametxt,mailtxt,phonetxt,passwordtxt,repasswordtxt;
    TextView save,female,male;
    int gender =-1;
    LoginButton loginButton;
    private static final String EMAIL = "email";
    CallbackManager callbackManager;

    RegisterViewModel registerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        findViewsFromXml();
        PackageInfo info;
        registerViewModel = ViewModelProviders.of(this,getViewModelFactory()).get(RegisterViewModel.class);
        registerViewModel.registerLiveData.observe(this,registerResponse ->
                {
                    save.setText(getText(R.string.register));
                    save.setEnabled(true);
                    assert registerResponse != null;
                    if (registerResponse.getSuccess().matches("OK")&&registerResponse.getCustomerId()>0) {
                        showToast("register success");
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                    else
                        showToast("error occure, please try again");
                }
                );

        registerViewModel.errorLiveData.observe(this,throwable ->
                {
                    save.setText(getText(R.string.register));
                    save.setEnabled(true);
                    assert throwable != null;
                    if (throwable.getCause()!=null)
                        showToast(throwable.getCause().toString());
                });
        registerViewModel.validateLiveDate.observe(this, message ->
                {
                    save.setText(getText(R.string.register));
                    save.setEnabled(true);
                    showToast(message);
                });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("fb", loginResult.toString());
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        (object, response) -> {
                            Log.v("LoginActivity", response.toString());
                            try {

                               // String email = object.getString("email");
                                String username = object.getString("name");
                                usernametxt.setText(username);
                                nametxt.setText(username);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("fb", "Canceled");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("fb", exception.getMessage());
            }
        });
    }


    private void findViewsFromXml() {
        mailtxt= findViewById(R.id.mail);
        nametxt= findViewById(R.id.name);
        female= findViewById(R.id.female);
        male= findViewById(R.id.male);
        save= findViewById(R.id.register);
        usernametxt= findViewById(R.id.username);
        passwordtxt= findViewById(R.id.password);
        repasswordtxt= findViewById(R.id.repassword);
        phonetxt= findViewById(R.id.phone);
        female.setOnClickListener(this);
        save.setOnClickListener(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList("public_profile", EMAIL/*, "user_birthday"*/));
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new RegisterViewModelFactory(getApplication());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.male :
                male.setTextColor(getResources().getColor(R.color.colorPrimary));
                female.setTextColor(getResources().getColor(R.color.black));
                gender=1;
                break;
            case R.id.female :
                male.setTextColor(getResources().getColor(R.color.black));
                female.setTextColor(getResources().getColor(R.color.colorPrimary));
                gender=0;
                break;
            case R.id.register:
                    save.setText("wait ...");
                    save.setEnabled(false);
                    User user = new User(nametxt.getText().toString(),
                            usernametxt.getText().toString(),phonetxt.getText().toString()
                            , mailtxt.getText().toString()
                            ,passwordtxt.getText().toString()
                            ,repasswordtxt.getText().toString()
                            , gender );

                    registerViewModel.saveUser(user);
                break;
        }
    }

    //// show message
    private void showToast(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
