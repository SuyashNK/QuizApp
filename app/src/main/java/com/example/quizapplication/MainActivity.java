package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsText, questionText;
    Button optionA, optionB, optionC, optionD, submit;
    int score = 0;
    int totalQuestion = answerQuestion.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsText = findViewById(R.id.totalQuestions);
        questionText = findViewById(R.id.question);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        submit = findViewById(R.id.submit);

        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestionsText.setText("Total question:" +totalQuestion);
        loadNewQuestion();

    }

    @Override
    public void onClick(View view){
        optionA.setBackgroundColor(Color.WHITE);
        optionB.setBackgroundColor(Color.WHITE);
        optionC.setBackgroundColor(Color.WHITE);
        optionD.setBackgroundColor(Color.WHITE);
        Button clickedButton = (Button) view;



        if(clickedButton.getId()==R.id.submit){
            if(selectedAnswer.equals(answerQuestion.answers[currentQuestionIndex]))
            
            {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else{
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        questionText.setText(answerQuestion.question[currentQuestionIndex]);
        optionA.setText(answerQuestion.choices[currentQuestionIndex][0]);
        optionB.setText(answerQuestion.choices[currentQuestionIndex][1]);
        optionC.setText(answerQuestion.choices[currentQuestionIndex][2]);
        optionD.setText(answerQuestion.choices[currentQuestionIndex][3]);
    }

    void finishQuiz(){
        String passStatus = "";
        if(score>totalQuestion*0.6){
            passStatus = "Passed";
        }
        else{
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + "out of" +totalQuestion)
                .setPositiveButton("Restart Quiz", ((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();

    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

}