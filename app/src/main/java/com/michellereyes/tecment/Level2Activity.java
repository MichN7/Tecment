package com.michellereyes.tecment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Level2Activity extends AppCompatActivity {

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

        final String questions[] = {"___to Australia,Ginny? 'Yes, two years ago.'",
                "Tokyo is ___ city I've ever lived in.",
                "Harry __ his father's car when the accident.",
                "I think Joey must ___ late tonight. His office light is still on.",
                "John tells me Jack's going out with Helen, ___ I find hard to belive.",
                "The weather has been awful. We've had very ___.",
                "Did you hear what happened to Kate? She___.",
                "I'll call you when I ___ home.",
                "A:What did Rebecca say?\n" + "B:She said she ___ everyone.",
                "We won't start the meeting until___.",
                "She likes___expensive clothes.",
                "I'm so hungry! If onli Bill__all the food in the fridge.",
                "I regret___harder in school.",
                "Harriet is so knowledgeable.She can talk about___subject that comes up.",
                "I was wondering___tell me when the next plane from Chicago arrives?",
                "I'm so tired___to bed earlier last night.",
                "A:I made an apple pie yesterday.\n" + "B:Really?__one before?",
                "He let___his car.",
                "This soup__hot or cold.",
                "I'm glad you called me.If you___,I would've worried about you.",
        };
        final String answers[][] ={{"Did you ever go","Do you ever go","Have you ever been","Are you ever going"},
                {"the most big","the bigger","the biggest","the more big"},
                {"was driving","drove","had driven","has been driving"},
                {"have worked","work","be working","to work"},
                {"which","who","whose","that"},
                {"little","a little","few","a few"},
                {"is arrested","arrested","has been arrested","is being arrested"},
                {"get","I'll get","I'll have got","I'm getting"},
                {"have invited","isn't invited","was invited","had invited"},
                {"you arrive","you're going to arrive","you'll arrive","you will have arrived"},
                {"wearing","to wearing","wear","is wearing"},
                {"wasn't eating","didn't eat","hadn't eaten","hasn't eaten"},
                {"not studying","not to study","to not study","not have studied"},
                {"whatever","whenever","wherever","whoever"},
                {"could you","can you","if you could","if could you"},
                {"I should've gone","I should've to gone","I should gone","I should to gone"},
                {"were you ever made","Did you eber made","have you ever been making","Had you ever made"},
                {"to borrow","me borrow","me to borrow","me borrowed"},
                {"can be eaten","can eaten","can eat","can be eating"},
                {"wouldn't call","haven't called","aren't calling","hadn't called"}
        };
        final String correctAnwers[] = {"Have you ever been",
                "the biggest",
                "was driving",
                "have worked",
                "which",
                "little",
                "has been arrested",
                "get",
                "had invited",
                "you arrive",
                "wearing",
                "hadn't eaten",
                "not studying",
                "whatever",
                "if you could",
                "I should've gone",
                "I should've gone",
                "me borrow",
                "can eat",
                "wouldn't call",
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
