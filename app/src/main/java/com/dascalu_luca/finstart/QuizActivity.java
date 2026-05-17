package com.dascalu_luca.finstart;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private boolean answered = false;

    private TextView tvProgress, tvQuestion, tvFeedbackTitle, tvFeedbackExplanation;
    private ProgressBar progressBar;
    private MaterialButton[] optionButtons;
    private CardView cardFeedback;
    private MaterialButton btnNext;
    private View layoutQuestion, layoutResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.quiz_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Test cunoștințe");
        }

        // Primește id-ul articolului
        String articleId = getIntent().getStringExtra("article_id");
        DataManager dm = DataManager.getInstance(this);
        questions = dm.getQuestionsForArticle(articleId);

        // Găsește views
        tvProgress = findViewById(R.id.tv_progress);
        tvQuestion = findViewById(R.id.tv_question);
        tvFeedbackTitle = findViewById(R.id.tv_feedback_title);
        tvFeedbackExplanation = findViewById(R.id.tv_feedback_explanation);
        progressBar = findViewById(R.id.progress_bar);
        cardFeedback = findViewById(R.id.card_feedback);
        btnNext = findViewById(R.id.btn_next);
        layoutQuestion = findViewById(R.id.layout_question);
        layoutResult = findViewById(R.id.layout_result);

        optionButtons = new MaterialButton[]{
                findViewById(R.id.btn_option_0),
                findViewById(R.id.btn_option_1),
                findViewById(R.id.btn_option_2),
                findViewById(R.id.btn_option_3)
        };

        // Click pe opțiuni
        for (int i = 0; i < optionButtons.length; i++) {
            final int index = i;
            optionButtons[i].setOnClickListener(v -> checkAnswer(index));
        }

        // Click pe Next
        btnNext.setOnClickListener(v -> {
            currentIndex++;
            if (currentIndex < questions.size()) {
                showQuestion();
            } else {
                showResult();
            }
        });

        // Buton finish
        findViewById(R.id.btn_finish).setOnClickListener(v -> finish());

        showQuestion();
    }

    private void showQuestion() {
        answered = false;
        cardFeedback.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);

        Question q = questions.get(currentIndex);

        // Progress
        int progress = (int) (((float)(currentIndex + 1) / questions.size()) * 100);
        progressBar.setProgress(progress);
        tvProgress.setText("Întrebarea " + (currentIndex + 1) + " din " + questions.size());
        tvQuestion.setText(q.getQuestion());

        // Opțiuni
        List<String> options = q.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            if (i < options.size()) {
                optionButtons[i].setVisibility(View.VISIBLE);
                optionButtons[i].setText(options.get(i));
                optionButtons[i].setEnabled(true);
                // Reset culori precedente
                optionButtons[i].setBackgroundTintList(
                        androidx.core.content.ContextCompat.getColorStateList(this, R.color.white));
                optionButtons[i].setTextColor(getColor(R.color.text_primary));
            } else {
                optionButtons[i].setVisibility(View.GONE);
            }
        }
    }

    private void checkAnswer(int selectedIndex) {
        if (answered) return;
        answered = true;

        Question q = questions.get(currentIndex);
        boolean isCorrect = selectedIndex == q.getCorrectIndex();

        if (isCorrect) {
            score++;
            optionButtons[selectedIndex].setBackgroundTintList(
                    androidx.core.content.ContextCompat.getColorStateList(this, android.R.color.holo_green_light));
            tvFeedbackTitle.setText("✅ Corect!");
            tvFeedbackTitle.setTextColor(getColor(android.R.color.holo_green_dark));
            cardFeedback.setCardBackgroundColor(getColor(android.R.color.holo_green_light));
        } else {
            optionButtons[selectedIndex].setBackgroundTintList(
                    androidx.core.content.ContextCompat.getColorStateList(this, android.R.color.holo_red_light));
            optionButtons[q.getCorrectIndex()].setBackgroundTintList(
                    androidx.core.content.ContextCompat.getColorStateList(this, android.R.color.holo_green_light));
            tvFeedbackTitle.setText("❌ Greșit!");
            tvFeedbackTitle.setTextColor(getColor(android.R.color.holo_red_dark));
            cardFeedback.setCardBackgroundColor(getColor(android.R.color.holo_red_light));
        }

        for (MaterialButton btn : optionButtons) btn.setEnabled(false);

        tvFeedbackExplanation.setText(q.getExplanation());
        cardFeedback.setVisibility(View.VISIBLE);

        if (currentIndex == questions.size() - 1) {
            btnNext.setText("Vezi rezultatul");
        } else {
            btnNext.setText("Următoarea întrebare");
        }
        btnNext.setVisibility(View.VISIBLE);
    }

    private void showResult() {
        layoutQuestion.setVisibility(View.GONE);
        layoutResult.setVisibility(View.VISIBLE);

        TextView tvScore = findViewById(R.id.tv_score);
        TextView tvMessage = findViewById(R.id.tv_score_message);
        TextView tvEmoji = findViewById(R.id.tv_score_emoji);

        tvScore.setText(score + " din " + questions.size());

        float percent = (float) score / questions.size();
        if (percent == 1.0f) {
            tvEmoji.setText("🎉");
            tvMessage.setText("Perfect! Ai înțeles totul!");
        } else if (percent >= 0.6f) {
            tvEmoji.setText("👍");
            tvMessage.setText("Bine! Mai recitește articolul pentru o înțelegere completă.");
        } else {
            tvEmoji.setText("📖");
            tvMessage.setText("Recitește articolul și încearcă din nou!");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}