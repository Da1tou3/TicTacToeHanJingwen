package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView image = (ImageView) findViewById(R.id.school);
        image.setImageResource(R.drawable.school);
        image.animate().scaleX(1.5f).scaleY(1.5f).setDuration(4000);
    }
}