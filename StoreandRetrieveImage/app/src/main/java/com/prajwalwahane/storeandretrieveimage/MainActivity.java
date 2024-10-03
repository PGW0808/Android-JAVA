package com.prajwalwahane.storeandretrieveimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewProfile;
    private Uri imageUri;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextDob = findViewById(R.id.editTextDob);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        Button buttonUpload = findViewById(R.id.buttonUpload);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        dbHelper = new DBHelper(this);

        buttonUpload.setOnClickListener(v -> openFileChooser());

        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String dob = editTextDob.getText().toString();
            String email = editTextEmail.getText().toString();

            if (name.isEmpty() || dob.isEmpty() || email.isEmpty() || imageUri == null) {
                Toast.makeText(MainActivity.this, "Please fill all fields and upload an image", Toast.LENGTH_SHORT).show();
            } else {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                byte[] imageInBytes = bitmapToByteArray(bitmap);
                dbHelper.insertProfile(name, dob, email, imageInBytes);

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("NAME", name);
                intent.putExtra("DOB", dob);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageViewProfile.setImageURI(imageUri);
            imageViewProfile.setVisibility(View.VISIBLE);
        }
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
