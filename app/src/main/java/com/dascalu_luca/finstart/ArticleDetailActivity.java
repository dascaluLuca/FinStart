package com.dascalu_luca.finstart;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ArticleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        // Buton back în toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        // Primește datele trimise prin Intent
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String category = getIntent().getStringExtra("category");

        TextView tvCategory = findViewById(R.id.tv_detail_category);
        TextView tvTitle = findViewById(R.id.tv_detail_title);
        TextView tvContent = findViewById(R.id.tv_detail_content);

        tvCategory.setText(category);
        tvTitle.setText(title);
        tvContent.setText(content);
    }

    // Butonul back din toolbar întoarce la ecranul anterior
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}