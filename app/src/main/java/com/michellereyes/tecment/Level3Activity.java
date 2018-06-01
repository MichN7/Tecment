package com.michellereyes.tecment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Level3Activity extends AppCompatActivity {

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

        final String questions[] = {"It's my birthday ___ friday.",
                "I__eighteen years old.",
                "I__a headache.",
                "How many__of jeans have you got?",
                "I usually ___ up at about 7:30.",
                "Could you ___ me that book for a couple of days,please?",
                "I always___milk in my coffe.",
                "We have a ___cat.",
                "You can't see it. It's___.",
                "I like vegetables.I want___.",
                "I want to take a bath. I need a___.",
                "He's in the ___. He's making breakfast.",
                "At a concert, I like to sit near the ___.",
                "In a restaurant.I usually order only one or two.",
                "Would you please ___? I can't see.",
                "The movie was really___.You should see it.",
                "I don't her real name.I only know her___.",
                "A:Do you need some help?\n" + "B:Yes.Could you__me change this,tire?",
                "Alexander Graham Bell___the telephone.",
                "It was cold last night.I___worn a jacket.",
        };
        final String answers[][] ={{"on","in","at","by"},
                {"am","have","have got","be"},
                {"am","do","have","got"},
                {"items","pairs","sets","times"},
                {"go","be","do","get"},
                {"lend","owe","borrow","rent"},
                {"have","drink","mix","make"},
                {"green","purple","black","pink"},
                {"in front of you","next to you","behind you","right over there"},
                {"a carrot","an apple","a banana","an orange"},
                {"blanket","sheet","towel","pillow"},
                {"kitchen","bathroom","closet","living room"},
                {"audience","screen","cast","stage"},
                {"waiters","locations","courses","classes"},
                {"get along","make up","break down","move over"},
                {"excited","exciting","boring","bored"},
                {"surname","nickname","given name","middle name"},
                {"help","make","let","have"},
                {"discovered","solved","invented","investigated"},
                {"must have","may have","will have","should have"}
        };
        final String correctAnwers[] = {"on",
                "am",
                "have",
                "pairs",
                "get",
                "lend",
                "have",
                "purple",
                "next to you",
                "a carrot",
                "towel",
                "kitchen",
                "stage",
                "courses",
                "move over",
                "exciting",
                "nickname",
                "help",
                "invented",
                "should have",
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
                    answerA.setChecked(false);
                    answerB.setChecked(false);
                    answerC.setChecked(false);
                    answerD.setChecked(false);
                    question.setText(questions[count]);
                    answerA.setText(answers[count][0]);
                    answerB.setText(answers[count][1]);
                    answerC.setText(answers[count][2]);
                    answerD.setText(answers[count][3]);

                    correctS = String.valueOf(correct);

                }else{

                    Intent intent = new Intent(getBaseContext(), TotalLevel3Activity.class);
                    intent.putExtra("CORRECT_ANSWERS", correctS);
                    startActivity(intent);
                }
            }
        });



    }
}
