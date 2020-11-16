package com.azamzhon.ui.question;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.azamzhon.data.models.QuestionModel;
import com.azamzhon.databinding.ListQuestionsItemBinding;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    ArrayList<QuestionModel> questions = new ArrayList<>();
    OnAnswerClickListener listener;

    public void setListener(OnAnswerClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListQuestionsItemBinding binding = ListQuestionsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QuestionHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        QuestionModel question = questions.get(position);
        holder.itemBinding.setListener(listener);
        holder.itemBinding.setQuestion(question);
        if (question.getType().equals("multiple")){
            if (question.isClicked()){
                holder.itemBinding.btn1.setClickable(false);
                holder.itemBinding.btn2.setClickable(false);
                holder.itemBinding.btn3.setClickable(false);
                holder.itemBinding.btn4.setClickable(false);
            }
            holder.itemBinding.btn1.setText(question.getIncorrectAnswers().get(0).toString());
            holder.itemBinding.btn2.setText(question.getIncorrectAnswers().get(1).toString());
            holder.itemBinding.btn3.setText(question.getIncorrectAnswers().get(2).toString());
            holder.itemBinding.btn4.setText(question.getIncorrectAnswers().get(3).toString());
        }else {
            if (question.isClicked()){
                holder.itemBinding.btn5.setClickable(false);
                holder.itemBinding.btn6.setClickable(false);
            }
            holder.itemBinding.btn5.setText(question.getIncorrectAnswers().get(0).toString());
            holder.itemBinding.btn6.setText(question.getIncorrectAnswers().get(1).toString());
        }
    }

    public void setQuestions(ArrayList<QuestionModel> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {

        ListQuestionsItemBinding itemBinding;

        public QuestionHolder(@NonNull View view) {
            super(view);
            itemBinding = DataBindingUtil.bind(view);
            itemBinding.btn1.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),0);
            });
            itemBinding.btn2.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),1);
            });
            itemBinding.btn3.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),2);
            });
            itemBinding.btn4.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),3);
            });
            itemBinding.btn5.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),0);
            });
            itemBinding.btn6.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),1);
            });
        }
    }
}