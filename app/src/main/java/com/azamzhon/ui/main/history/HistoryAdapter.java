package com.azamzhon.ui.main.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azamzhon.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private List<String> data = new ArrayList<>();

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder {

        private final TextView category, correctAnswers, difficulty, date;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category_var);
            correctAnswers = itemView.findViewById(R.id.correct_answers_var);
            difficulty = itemView.findViewById(R.id.difficulty_var);
            date = itemView.findViewById(R.id.date_tv);
        }

        public void onBind(String s) {
        }
    }
}