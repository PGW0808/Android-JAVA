package com.prajwalwahane.registerandloginsqlite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.prajwalwahane.registerandloginsqlite.SQLiteDB.DBHelper;

public class MainActivity extends AppCompatActivity {

    TextView textViewRegister;
    EditText editTextUsername, editTextPassword;
    Button buttonLogin, buttonCancel;
    DBHelper dbHelper;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        buttonCancel = findViewById(R.id.cancle);
        textViewRegister = findViewById(R.id.registerHere);
        googleBtn = findViewById(R.id.googleBtn); // Ensure you have this in your layout

        dbHelper = new DBHelper(this);

        String text = "If not registered then Register Here.";
        SpannableStringBuilder spannableString = new SpannableStringBuilder(text);

        int start = text.indexOf("Register Here");
        int end = start + "Register Here".length();
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start, end, 0);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), start, end, 0);
        textViewRegister.setText(spannableString);

        textViewRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        buttonLogin = findViewById(R.id.login);
        buttonLogin.setOnClickListener(v -> {
            String user = editTextUsername.getText().toString();
            String pass = editTextPassword.getText().toString();
            if (user.equals("") || pass.equals("")) {
                editTextUsername.setError("Please enter all the fields");
            } else {
                Boolean checkuserpass = dbHelper.checkpassword(user, pass);
                if (checkuserpass) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    editTextUsername.setError("Invalid Credentials");
                    editTextPassword.setError("Invalid Credentials");
                }
            }
        });

        buttonCancel.setOnClickListener(v -> finish());

        // Google Authentication Setup
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        googleBtn.setOnClickListener(v -> signIn());
    }

    void signIn() {
        Intent signIntent = gsc.getSignInIntent();
        startActivityForResult(signIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(com.google.android.gms.common.api.ApiException.class);

                if (account != null) {
                    String personName = account.getDisplayName();
                    String personEmail = account.getEmail();

                    // Insert Google Account Data into SQLite
                    boolean checkuser = dbHelper.checkUsername(personName);
                    if (!checkuser) {
                        boolean insert = dbHelper.insertData(personName, personEmail, "google_password_placeholder");
                        if (insert) {
                            Toast.makeText(this, "Google Sign-In Successful", Toast.LENGTH_SHORT).show();
                            navigateToHomeActivity();
                        } else {
                            Toast.makeText(this, "Database Error: Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Welcome back, " + personName, Toast.LENGTH_SHORT).show();
                        navigateToHomeActivity();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToHomeActivity() {
        finish();
        startActivity(new Intent(this, HomeActivity.class));
    }
}
