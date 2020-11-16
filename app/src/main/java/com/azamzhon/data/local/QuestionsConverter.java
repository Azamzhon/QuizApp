package com.azamzhon.data.local;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.azamzhon.data.models.QuestionModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuestionsConverter {
    @TypeConverter
    public static ArrayList<QuestionModel> fromRaw(@Nullable String raw){
        if (raw == null) return null;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QuestionModel>>(){}.getType();
        return gson.fromJson(raw,type);
    }
    @TypeConverter
    public static String toRaw(@Nullable ArrayList<QuestionModel> questions){
        if (questions == null) return null;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QuestionModel>>(){}.getType();
        return gson.toJson(questions,type);
    }
}