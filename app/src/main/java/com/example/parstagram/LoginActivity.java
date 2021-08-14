package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goToMainActivity();
        }

        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Logging in user " + username);

        //Navigating to main activity if the user has signed in properly
        //Note: always want to do in background so user can still user your app while process is happening in background
        ParseUser.logInInBackground(username, password, (user, e) -> {
            //ParseException should return null if login succeeded
            if (e != null) {
                //TODO: Better error Handling
                Log.e(TAG, "Login not successful", e);
                Toast.makeText(LoginActivity.this, "Unsuccessful login", Toast.LENGTH_SHORT).show();
                return;
            }
            goToMainActivity();
            //Telling user login was successful
            Toast.makeText(LoginActivity.this, "Successful login!", Toast.LENGTH_SHORT).show();
        });

    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}