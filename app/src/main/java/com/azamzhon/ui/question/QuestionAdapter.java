package com.azamzhon.ui.question;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azamzhon.R;
import com.azamzhon.data.models.QuestionModel;
import com.azamzhon.databinding.ListQuestionsItemBinding;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    private OnAnswerClickListener listener;
    private List<QuestionModel> data = new ArrayList<>();

    public void setData(List<QuestionModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setAnswerClickListener(OnAnswerClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionHolder(ListQuestionsItemBinding.bind(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_questions_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {
        ListQuestionsItemBinding binding;

        public QuestionHolder(@NonNull ListQuestionsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            initListener();
        }

        private void initListener() {
            binding.setListener(pos -> listener.OnAnswerClick (data.get(getAdapterPosition()).getAllAnswers()[pos].equals(data.get(getAdapterPosition()).getCorrectAnswer())));
        }


        public void onBind(QuestionModel questionModel) {
            binding.setQuestion(questionModel);
        }
    }
}
