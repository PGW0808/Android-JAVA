package com.prajwalwahane.registerandloginsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.prajwalwahane.registerandloginsqlite.SQLiteDB.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText editPersonname, editEmail, EditPassword, editCpassword;
    Button submitButton;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        editPersonname = findViewById(R.id.personname);
        editEmail = findViewById(R.id.email);
        EditPassword = findViewById(R.id.password);
        editCpassword = findViewById(R.id.cpassword);
        submitButton = findViewById(R.id.submitBtn);
        dbHelper = new DBHelper(this);

        submitButton.setOnClickListener(v -> {
            String personname = editPersonname.getText().toString();
            String email = editEmail.getText().toString();
            String password = EditPassword.getText().toString();
            String cpassword = editCpassword.getText().toString();

            if (personname.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();

            }else{
                if (password.equals(cpassword)){
                    Boolean checkuser = dbHelper.checkpassword(personname, password);
                    if (checkuser == false){
                        Boolean insert = dbHelper.insertData(personname, email, password);
                        if (insert == true){
                            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            editPersonname.setText("");
                            editEmail.setText("");
                            EditPassword.setText("");
                            editCpassword.setText("");
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}