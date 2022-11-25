package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword;
    public static SharedPreferences mSharedPref;
    private final String sharedPrefFile = "com.example.sharedpreference";
    public static final String LOGIN_STATUS = "LOGIN_STATUS";
    private boolean isLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);

        mSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        isLoggedIn = mSharedPref.getBoolean(LOGIN_STATUS, false);

        Intent intent = new Intent(this, SecondActivity.class);

        if (isLoggedIn) {
            startActivity(intent);
            finish();
        }

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();

                if (username.equals("admin") && password.equals("admin")) {
                    startActivity(intent);
                    setLoggedIn();
                    finish();
                }
            }
        });
    }


    private void setLoggedIn() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(LOGIN_STATUS, true);
        editor.apply();
    }

    public static void setLoggedOut() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(LOGIN_STATUS, false);
        editor.apply();
    }
}