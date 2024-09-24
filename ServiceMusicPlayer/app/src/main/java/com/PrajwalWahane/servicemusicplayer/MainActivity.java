package com.PrajwalWahane.servicemusicplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    AppCompatButton playBtn, pauseBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.playButton);
        pauseBtn = findViewById(R.id.pauseButton);

        playBtn.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(getApplicationContext(), MyCustomServiceClass.class);
            startService(serviceIntent);
        });

        pauseBtn.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(getApplicationContext(), MyCustomServiceClass.class);
            stopService(serviceIntent);
        });

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        AirplaneModeReceiver airplaneModeReceiver = new AirplaneModeReceiver();
        registerReceiver(airplaneModeReceiver, intentFilter);


    }
}