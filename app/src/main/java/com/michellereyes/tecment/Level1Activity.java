package com.michellereyes.tecment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Level1Activity extends AppCompatActivity {

    private TextView question;
    private RadioButton answerA, answerB, answerC, answerD,selected;
    private RadioGroup radioGroup;
    private Button nextButton;
    private int count = 0;
    private int correct = 0;

    private String correctS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        final String questions[] = {"Roberta ___ from The United States.",
                                    "What's ___ name.",
                                    "My friend ___ in London",
                                    "Where___?",
                                    "I___ coffe.",
                                    "A vegetarian is someone___doesn't eat meat.",
                                    "Harry can___English.",
                                    "I'm not interested___sports.",
                                    "Who___in that house?",
                                    "Do you want___take a picture?",
                                    "___these days.",
                                    "I___watch TV tonight.",
                                    "I wish I___more money.",
                                    "___be famous one day?",
                                    "I don't know where___last night.",
                                    "Did___this morning?",
                                    "I___dinner when the fire started.",
                                    "Where___?",
                                    "___come, but he will.",
                                    "The cafeteria is___the restaurant.",
        };
        final String answers[][] ={{"are","is","am","be"},
                                   {"you","his","him","he"},
                                   {"living","live","lives","is live"},
                                   {"works Tom","Tom works","Tom does work","does Tom work"},
                                   {"no like","not like","like don't","don't like"},
                                   {"who","what","which","whose"},
                                   {"to speak","speaking","speak","speaks"},
                                   {"for","about","in","to"},
                                   {"does live","lives","does he live","he lives"},
                                   {"me","I","me to","that"},
                                   {"I never a newspaper buy","I never buy a newspaper","I buy never a newspaper","Never I buy a newspaper"},
                                    {"am","go to","going to","am going to"},
                                    {"have","had","would have","was having"},
                                    {"Would you like","Would you like to","Do you like","Do you like to"},
                                    {"did he go","he did go","went he","he went"},
                                    {"Erika drove to work","Erika driving to work","Erika drive to work","drive to work Erika"},
                                    {"Have cooked","am cooking","was cooking","Cooked"},
                                    {"I have to go","should I go","I had to go","I should go"},
                                    {"She not to","She'll won't","she'll not to","She won't"},
                                    {"cheaper than","cheaper","more cheap than","cheap than"}
        };
        final String correctAnwers[] = {"is",
                                        "his",
                                        "lives",
                                        "does Tom work",
                                        "don't like",
                                        "who",
                                        "speak",
                                        "in",
                                        "lives",
                                        "me to",
                                        "I never buy a newspaper",
                                        "am going to",
                                        "had",
                                        "Would you like to",
                                        "he went",
                                        "Erika drive to work",
                                        "am cooking",
                                        "I had to go",
                                        "She won't",
                                        "cheaper than",
        };

        question = (TextView)findViewById(R.id.txtPregunta);
        answerA = (RadioButton)findViewById(R.id.rbtnRespuestaA);
        answerB = (RadioButton)findViewById(R.id.rbtnRespuestaB);
        answerC = (RadioButton)findViewById(R.id.rbtnRespuestaC);
        answerD = (RadioButton)findViewById(R.id.rbtnRespuestaD);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        nextButton = (Button)findViewById(R.id.btn_next_question);


        try{

            question.setText(questions[0]);
            answerA.setText(answers[0][0]);
            answerB.setText(answers[0][1]);
            answerC.setText(answers[0][2]);
            answerD.setText(answers[0][3]);

        }catch (Exception ex){

            ex.getMessage();

        }


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count != questions.length-1){

                    int selectedId = radioGroup.getCheckedRadioButtonId();


                    selected = (RadioButton) findViewById(selectedId);
                    String selectedAnswer = selected.getText().toString();

                    if(selectedAnswer == correctAnwers[count]){
                        correct += 1;
                    }

                    count += 1;
                    answerA.setChecked(true);
                    question.setText(questions[count]);
                    answerA.setText(answers[count][0]);
                    answerB.setText(answers[count][1]);
                    answerC.setText(answers[count][2]);
                    answerD.setText(answers[count][3]);

                    correctS = String.valueOf(correct);

                }else{

                    Intent intent = new Intent(getBaseContext(), TotalActivity.class);
                    intent.putExtra("CORRECT_ANSWERS", correctS);
                    startActivity(intent);
                }
            }
        });



    }
}
