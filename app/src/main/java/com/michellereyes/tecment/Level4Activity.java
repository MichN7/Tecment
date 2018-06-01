package com.michellereyes.tecment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Level4Activity extends AppCompatActivity {

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

        final String questions[] = {"A:Are you an engineer?\n" + "B:No, I'm not___.",
                "A:What is the address?\n" + "B:It's___.",
                "A:Are you Allen?\n" + "B:___.\n",
                "A:This is my friend Cathy.\n" + "B:___.",
                "A:Sorry,I can´t go swimming today.\n" + "B:___.\n",
                "A:___.\n" + "B:Sure.Right over there..",
                "A:Do you have a minute?\n" + "B:___.",
                "A:___.\n" + "B:Ok.I'll take it.",
                "A:How's it going?\n" + "B:___.",
                "A:___.\n" + "B:Well.You could see a doctor.",
                "A:Would you like me to wash the windshield?\n" + "B:___.",
                "A:Could you do me a favor?\n" + "B:___.",
                "A:How do you feel about fast food?\n" + "B:___.",
                "A:Guess who I just saw!\n" + "B:___.",
                "A:Ken went home early.How come?\n" + "B:___.",
                "A:Promise me you won't do that again.\n" + "B:___.",
                "A:If you ask me, dogs are destroying the park.\n" + "B:___.",
                "A:Want to see a movie?\n" + "B:___.",
                "A:How was your dinner?\n" + "B:___.",
                "A:I'm really sleepy.\n" + "B:___.",
        };
        final String answers[][] ={{"I'm married","I'm Teresa","I'm a writer","I'm a woman"},
                {"Los Angeles","at school","at school","55 First Avenue"},
                {"A-L-L-E-N","No,I'm not.I'm Paulo","I'm a teacher","I'm from Brazil"},
                {"It's for you","What about you?","See you there","Nice to meet you"},
                {"How about swimming?","Let's go swimming.","Ok.Maybe some other time.","Why don't you ask Richard?"},
                {"Can I help you","Excuse me","Do you have any bathing suits","Do you need help?"},
                {"Sure.What's up","It's a quarter past six","Why don't you ask roger?","Sorry I can't"},
                {"These running shoes are really comfortable","Do you have any wind breakers","How much is it?","It's fifteen dollars"},
                {"Not too well","Not really","I never go anywhere","To Washington, DC."},
                {"What do you think they will do?","What do you think I should do?","What do you want to do?","What do you think will happen?"},
                {"Don't worry","Please","I'd like to","Be glad to"},
                {"Sure.What do you need?","Sure you can","Sure I do","So what?"},
                {"Not at all","I'd rather not","Fine thanks","It depends on the food"},
                {"I guess so","No problem","I give up","You must be kidding"},
                {"He should go home","He might be sick","He'd rather be sick","He'd better go home"},
                {"I'll say","You can say that again","I give you my word","I know what you mean"},
                {"They have a right to be upset","Well that's one way to look at it","You bet they shouls","Yes.They're all set"},
                {"Well, I'm supposed to do my homework","Sorry.I'm drawing a blank","No wonder","No offhand"},
                {"It was great! I'm swamped","It's on the tip of my tongue","A piece of cake","Out of this world"},
                {"Calm down","Why don't you take a nap?","Well, don't jump to conclusions","I see what you mean"}
        };
        final String correctAnwers[] = {"I'm a writer",
                "55 First Avenue",
                "No,I'm not.I'm Paulo",
                "Nice to meet you",
                "Ok.Maybe some other time.",
                "Do you have any bathing suits",
                "Sure.What's up",
                "It's fifteen dollars",
                "Not too well",
                "What do you think I should do?",
                "Please",
                "Sure.What do you need?",
                "Fine thanks",
                "I give up",
                "He might be sick",
                "I give you my word",
                "Well that's one way to look at it",
                "Well, I'm supposed to do my homework",
                "Out of this world",
                "Why don't you take a nap?",
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

                    Intent intent = new Intent(getBaseContext(), TotalLevel4Activity.class);
                    intent.putExtra("CORRECT_ANSWERS", correctS);
                    startActivity(intent);
                }
            }
        });



    }
}
