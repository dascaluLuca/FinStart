package com.dascalu_luca.finstart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verifică dacă userul a mai văzut onboarding-ul
        SharedPreferences prefs = getSharedPreferences("FinStartPrefs", MODE_PRIVATE);
        boolean hasSeenOnboarding = prefs.getBoolean("hasSeenOnboarding", false);

        if (hasSeenOnboarding) {
            // A mai deschis aplicația înainte → sare direct la MainActivity
            goToMain();
            return;
        }

        setContentView(R.layout.activity_onboarding);

        findViewById(R.id.btn_get_started).setOnClickListener(v -> {
            // Salvează că a văzut onboarding-ul
            prefs.edit().putBoolean("hasSeenOnboarding", true).apply();
            goToMain();
        });
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Onboarding nu mai e în back stack
    }
}