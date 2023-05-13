package com.android.networkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.networkbook.databinding.ActivityQuizPageBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class QuizPage extends AppCompatActivity {
    private int pos=0;
    private String title="";
    private Context context=this;
    private ActivityQuizPageBinding binding;
    private ArrayList<QuizModel> quizModels=new ArrayList<>();
    int index=0;
    int right=0,wrong=0;
    private String selected="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityQuizPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pos=getIntent().getIntExtra("pos",0);
        getJson();
        setClick(binding.aW,binding.aText,"a");
        setClick(binding.bW,binding.bText,"b");
        setClick(binding.cW,binding.cText,"c");
        setClick(binding.dW,binding.dText,"d");
        binding.answer.setText("Dogry: "+right+" / "+"Yalnysh: "+wrong);
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index<quizModels.size()) {
                    QuizModel current = quizModels.get(index);
                    if(current.getAnswer().equals(selected)){
                        right++;
                    } else {
                        wrong++;
                    }
                }
                binding.answer.setText("Dogry: "+right+" / "+"Yalnysh: "+wrong);
                index++;
                setQuiz();
            }
        });

        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setClick(RelativeLayout aW, TextView aText, String a) {
        aW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearButtons();
                selected=a;
                setSelected(aW,aText);
            }
        });
    }

    private void setSelected(RelativeLayout aW, TextView aText) {
        aW.setBackgroundResource(R.drawable.quiz_gradient_button);
        aText.setTextColor(context.getResources().getColor(R.color.white));
    }

    private void getJson() {
        String jsonString = null;
        try {
            InputStream inputStream = getAssets().open("data/quiz.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
            convertToObject(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void convertToObject(String jsonString) {
        try {
            quizModels.clear();
            JSONArray array = new JSONArray(jsonString);
            for(int i=0;i<array.length();i++){
                JSONObject quiz = array.getJSONObject(i);
                if(quiz.getInt("unit")==pos) {
                    quizModels.add(new QuizModel(
                            quiz.getString("question"),
                            quiz.getString("a"),
                            quiz.getString("b"),
                            quiz.getString("c"),
                            quiz.getString("d"),
                            quiz.getString("answer"),
                            quiz.getInt("unit")
                    ));
                }
            }
            index=0;
            setQuiz();
            // Use the data in the JSONObject to test your application
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setQuiz() {
        clearButtons();
        if(index<quizModels.size()){
            int pp = index+1;
            binding.pos.setText(pp+" / "+quizModels.size());
            QuizModel current = quizModels.get(index);
            binding.question.setText(current.getQuestion());
            binding.aText.setText(current.getA());
            binding.bText.setText(current.getB());
            binding.cText.setText(current.getC());
            binding.dText.setText(current.getD());
        } else {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            builder.setTitle("Test tamamlandy!");

            builder.setMessage("Dogry: "+right+" / "+"Yalnysh: "+wrong);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    finish();
                }
            });
            builder.show();
        }
    }

    private void clearButtons() {
        binding.aW.setBackgroundResource(R.drawable.quiz_gradient_button_passive);
        binding.aText.setTextColor(context.getResources().getColor(R.color.primary_light));

        binding.bW.setBackgroundResource(R.drawable.quiz_gradient_button_passive);
        binding.bText.setTextColor(context.getResources().getColor(R.color.primary_light));

        binding.cW.setBackgroundResource(R.drawable.quiz_gradient_button_passive);
        binding.cText.setTextColor(context.getResources().getColor(R.color.primary_light));

        binding.dW.setBackgroundResource(R.drawable.quiz_gradient_button_passive);
        binding.dText.setTextColor(context.getResources().getColor(R.color.primary_light));
    }


}