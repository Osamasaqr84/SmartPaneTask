package com.parashot_trader.codesroots.smartpanetask.presentation.features.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.parashot_trader.codesroots.smartpanetask.R;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.MainActivity;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }, 5000);
    }

}
