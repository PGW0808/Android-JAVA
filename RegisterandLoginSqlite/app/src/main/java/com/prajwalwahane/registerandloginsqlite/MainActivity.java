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
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.prajwalwahane.registerandloginsqlite.SQLiteDB.DBHelper;

public class MainActivity extends AppCompatActivity {

    TextView textViewRegister;
    EditText editTextUsername, editTextPassword;
    Button buttonLogin, buttonCancel;
    DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);

        buttonCancel = findViewById(R.id.cancle);
        textViewRegister = findViewById(R.id.registerHere);

        String text = "If not registered then Register Here.";
        SpannableStringBuilder spannableString = new SpannableStringBuilder(text);

        // Find the start and end indices of "Register Here"
        int start = text.indexOf("Register Here");
        int end = start + "Register Here".length();

        // Apply bold style using StyleSpan
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start, end, 0);
        // Change the text color to red (or any color you prefer)
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), start, end, 0);

        // Set the styled text to the TextView
        textViewRegister.setText(spannableString);

        textViewRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        buttonLogin = findViewById(R.id.login);
        dbHelper = new DBHelper(this);
        buttonLogin.setOnClickListener(v -> {
            String user = editTextUsername.getText().toString();
            String pass = editTextPassword.getText().toString();
            if (user.equals("") || pass.equals("")) {
                editTextUsername.setError("Please enter all the fields");
            }else{
                Boolean checkuserpass = dbHelper.checkpassword(user, pass);
                if (checkuserpass == true){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else {
                    editTextUsername.setError("Invalid Credentials");
                    editTextPassword.setError("Invalid Credentials");
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}