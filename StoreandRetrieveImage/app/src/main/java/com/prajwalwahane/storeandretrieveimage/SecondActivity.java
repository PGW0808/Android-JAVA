package com.prajwalwahane.storeandretrieveimage;

import static com.prajwalwahane.storeandretrieveimage.DBHelper.*;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.prajwalwahane.storeandretrieveimage.DBHelper;

public class SecondActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imageViewProfile = findViewById(R.id.imageViewProfile);
        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewDob = findViewById(R.id.textViewDob);
        TextView textViewEmail = findViewById(R.id.textViewEmail);

        dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getProfile();

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String dob = cursor.getString(cursor.getColumnIndex(COLUMN_DOB));
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));

            textViewName.setText("Name: " + name);
            textViewDob.setText("DOB: " + dob);
            textViewEmail.setText("Email: " + email);

            if (imageBytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageViewProfile.setImageBitmap(bitmap);
            }
        }
        cursor.close();
    }
}
