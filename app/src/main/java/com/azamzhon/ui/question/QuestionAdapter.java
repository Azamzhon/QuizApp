package com.azamzhon.ui.question;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.azamzhon.R;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        fillColorButton(holder,  R.drawable.ic_rectangle, 0, R.style.item_btn_text_pressed_false);
        fillColorButton(holder, R.drawable.ic_rectangle, 1, R.style.item_btn_text_pressed_false);
        fillColorButton(holder,  R.drawable.ic_rectangle, 2, R.style.item_btn_text_pressed_false);
        fillColorButton(holder,  R.drawable.ic_rectangle, 3, R.style.item_btn_text_pressed_false);

        QuestionModel question = questions.get(position);
        holder.binding.setListener(listener);
        holder.binding.setQuestion(question);
        if (question.getType().equals("multiple")) {
            if (question.isClicked()) {
                holder.binding.btn1.setClickable(false);
                holder.binding.btn2.setClickable(false);
                holder.binding.btn3.setClickable(false);
                holder.binding.btn4.setClickable(false);
            }
            holder.binding.btn1.setText(question.getIncorrectAnswers().get(0));
            holder.binding.btn2.setText(question.getIncorrectAnswers().get(1));
            holder.binding.btn3.setText(question.getIncorrectAnswers().get(2));
            holder.binding.btn4.setText(question.getIncorrectAnswers().get(3));
        } else {
            if (question.isClicked()) {
                holder.binding.btn5.setClickable(false);
                holder.binding.btn6.setClickable(false);
            }
            holder.binding.btn5.setText(question.getIncorrectAnswers().get(0));
            holder.binding.btn6.setText(question.getIncorrectAnswers().get(1));
        }
        if (questions.get(position).isClicked()) {
            fillColorButton(holder, R.drawable.btn_red, question.getSelectQuestionPosition(), R.style.item_btn_text_pressed_true);
            Log.e("onBindTrueClicked", "onBindViewHolder: " +  question.getSelectQuestionPosition() + " " + questions.get(position).isClicked());
            showCorrectAnswer(holder, question);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showCorrectAnswer(@NonNull QuestionHolder holder, QuestionModel question) {
        String correctAns = question.getCorrectAnswer();
        for (int i = 0; i < question.getIncorrectAnswers().size(); i++) {
            if (correctAns.equals(question.getIncorrectAnswers().get(i))) {
                fillColorButton(holder, R.drawable.btn_green, i, R.style.item_btn_text_pressed_true);
                return;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fillColorButton(QuestionHolder holder, int drawable, int pos, int style) {
        switch (pos) {
            case 0:
                holder.binding.btn1.setBackgroundResource(drawable);
                holder.binding.btn1.setTextAppearance(style);

                holder.binding.btn5.setBackgroundResource(drawable);
                holder.binding.btn5.setTextAppearance(style);
                break;
            case 1:
                holder.binding.btn2.setBackgroundResource(drawable);
                holder.binding.btn2.setTextAppearance(style);

                holder.binding.btn6.setBackgroundResource(drawable);
                holder.binding.btn6.setTextAppearance(style);
                break;
            case 2:
                holder.binding.btn3.setBackgroundResource(drawable);
                holder.binding.btn3.setTextAppearance(style);
                break;
            case 3:
                holder.binding.btn4.setBackgroundResource(drawable);
                holder.binding.btn4.setTextAppearance(style);
                break;
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

    public class QuestionHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

        ListQuestionsItemBinding binding;

        public QuestionHolder(@NonNull View view) {
            super(view);
            binding = DataBindingUtil.bind(view);

            onClickInit();
        }

        @SuppressLint("ClickableViewAccessibility")
        private void onClickInit() {
            binding.btn1.setOnTouchListener(this);
            binding.btn2.setOnTouchListener(this);
            binding.btn3.setOnTouchListener(this);
            binding.btn4.setOnTouchListener(this);
            binding.btn5.setOnTouchListener(this);
            binding.btn6.setOnTouchListener(this);

            binding.btn1.setOnClickListener(v -> listener.onClick(getAdapterPosition(), 0));
            binding.btn2.setOnClickListener(v -> listener.onClick(getAdapterPosition(), 1));
            binding.btn3.setOnClickListener(v -> listener.onClick(getAdapterPosition(), 2));
            binding.btn4.setOnClickListener(v -> listener.onClick(getAdapterPosition(), 3));
            binding.btn5.setOnClickListener(v -> listener.onClick(getAdapterPosition(), 0));
            binding.btn6.setOnClickListener(v -> listener.onClick(getAdapterPosition(), 1));
        }

        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Button button = (Button) view;
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    button.setBackgroundResource(R.drawable.btn_pressed_true);
                    button.setTextAppearance(R.style.item_btn_text_pressed_true);
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    button.setBackgroundResource(R.drawable.btn_pressed_false);
                    button.setTextAppearance(R.style.item_btn_text_pressed_false);
                    return false;
            }
            return false;
        }

    }
}