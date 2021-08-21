package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginAndSignUpActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private Button btnLogin;
    private Button btnSignUp;
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
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG, "Signing up user!");
                SignUpUser();

            }

        });
    }

    private void SignUpUser() {
        ParseUser user = new ParseUser();
        user.setUsername("my name"); //get username to be what user typed in
        user.setPassword("my pass"); //get pass to be what user typed in
        //user.setEmail("email@example.com");

        // other fields can be set just like with ParseObject
        //user.put("phone", "650-253-0000");

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    goToMainActivity();
                    //Telling user login was successful
                    Toast.makeText(LoginAndSignUpActivity.this, "Successful login!", Toast.LENGTH_SHORT).show();


                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG, "Sign up not successful", e);
                    Toast.makeText(LoginAndSignUpActivity.this, "Unsuccessful signup", Toast.LENGTH_SHORT).show();
                }
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
                Toast.makeText(LoginAndSignUpActivity.this, "Unsuccessful login", Toast.LENGTH_SHORT).show();
                return;
            }
            goToMainActivity();
            //Telling user login was successful
            Toast.makeText(LoginAndSignUpActivity.this, "Successful login!", Toast.LENGTH_SHORT).show();
        });

    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}