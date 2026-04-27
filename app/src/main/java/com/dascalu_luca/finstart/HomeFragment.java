package com.dascalu_luca.finstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DataManager dm = DataManager.getInstance(requireContext());

        // Quote
        TextView tvQuote = view.findViewById(R.id.tv_quote);
        tvQuote.setText(dm.getRandomQuote());

        // Articol featured
        Article featured = dm.getFeaturedArticle();
        if (featured != null) {
            TextView tvCategory = view.findViewById(R.id.tv_featured_category);
            TextView tvTitle = view.findViewById(R.id.tv_featured_title);
            TextView tvSummary = view.findViewById(R.id.tv_featured_summary);
            CardView btnRead = view.findViewById(R.id.btn_read_featured);

            tvCategory.setText(featured.getCategory());
            tvTitle.setText(featured.getTitle());
            tvSummary.setText(featured.getSummary());

            view.findViewById(R.id.btn_read_featured).setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                intent.putExtra("title", featured.getTitle());
                intent.putExtra("content", featured.getContent());
                intent.putExtra("category", featured.getCategory());
                startActivity(intent);
            });
        }

        return view;
    }
}