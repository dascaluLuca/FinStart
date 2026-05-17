package com.dascalu_luca.finstart;
import com.google.android.material.button.MaterialButton;
import java.util.List;
import android.view.View;
import android.content.Intent;

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

        String articleId = getIntent().getStringExtra("id");
        MaterialButton btnQuiz = findViewById(R.id.btn_start_quiz);

        List<Question> questions = DataManager.getInstance(this)
                .getQuestionsForArticle(articleId != null ? articleId : "");

        if (questions == null || questions.isEmpty()) {
            btnQuiz.setVisibility(View.GONE);
        } else {
            btnQuiz.setVisibility(View.VISIBLE);
            btnQuiz.setOnClickListener(v -> {
                Intent quizIntent = new Intent(this, QuizActivity.class);
                quizIntent.putExtra("article_id", articleId);
                startActivity(quizIntent);
            });
        }
    }

    // Butonul back din toolbar întoarce la ecranul anterior
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}