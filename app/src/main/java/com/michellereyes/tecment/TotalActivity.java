package com.michellereyes.tecment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TotalActivity extends AppCompatActivity {

    private TextView txtTotal,txtCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        txtTotal = (TextView)findViewById(R.id.txtTotal);
        txtCorrect = (TextView)findViewById(R.id.txtCorrect);

        String numberCorrectAnswers = getIntent().getStringExtra("CORRECT_ANSWERS");

        int correct = Integer.parseInt(numberCorrectAnswers);
        int grade  = (correct/20)*100;

        txtCorrect.setText(String.valueOf(grade));

        txtTotal.setText(numberCorrectAnswers + " OUT OF 20");

    }
}
