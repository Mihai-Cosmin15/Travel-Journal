package com.mihainour.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.TextView;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView textViewAppTitle = findViewById(R.id.textViewAppTitle);
        int[] colors = {
                Color.rgb(73, 137, 244),
                Color.rgb(1, 168, 78)

        };
        float[] positions = null;
        Shader shader = new LinearGradient(0f, 0f, textViewAppTitle.getBaseline(),  textViewAppTitle.getTextSize(), colors, positions, Shader.TileMode.CLAMP);
        textViewAppTitle.getPaint().setShader(shader);

        Thread thread = new Thread() {

            @Override
            public void run() {

                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}