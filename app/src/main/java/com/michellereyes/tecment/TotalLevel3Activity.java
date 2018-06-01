package com.michellereyes.tecment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class TotalLevel3Activity extends AppCompatActivity {

    private TextView txtTotal,txtCorrect,txtStudentName,txtStudentCareer,txtStudentControlNumber;
    private Button btnDone;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final DatabaseReference ref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        txtTotal = (TextView)findViewById(R.id.txtTotal);
        txtCorrect = (TextView)findViewById(R.id.txtCorrect);
        txtStudentName = (TextView)findViewById(R.id.txtStudentName);
        txtStudentCareer = (TextView)findViewById(R.id.txtStudentCarrer);
        txtStudentControlNumber = (TextView)findViewById(R.id.txtStudentControlNumber);

        String numberCorrectAnswers = getIntent().getStringExtra("CORRECT_ANSWERS");
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Usuario", 0);
        String names = pref.getString("Nombres",null);
        String firstLastName = pref.getString("ApellidoPaterno",null);
        String secondLastName = pref.getString("ApellidoMaterno",null);
        String numeroControl = pref.getString("NumeroControl",null);
        String Carrera = pref.getString("Carrera",null);

        String CompleteName = names + " " + firstLastName + " " + secondLastName;

        if(numberCorrectAnswers != null){

            int correct = Integer.parseInt(numberCorrectAnswers);
            int grade  = correct*100 / 20;
            HashMap<String, String> datosHash = new HashMap<String, String>();
            datosHash.put("Calificacion", String.valueOf(grade));
            datosHash.put("RespuestasCorrectas", numberCorrectAnswers);

            ref.child(user.getUid()).child("nivelTres/Calificacion").setValue(String.valueOf(grade));
            ref.child(user.getUid()).child("nivelTres/RespuestasCorrectas").setValue(numberCorrectAnswers);
            ref.child(user.getUid()).child("nivelTres/hecho").setValue("true");

            txtCorrect.setText(String.valueOf(grade));
            txtTotal.setText(numberCorrectAnswers + " OUT OF 20");
            txtStudentName.setText(CompleteName);
            txtStudentCareer.setText(Carrera);
            txtStudentControlNumber.setText(numeroControl);

            SharedPreferences.Editor editor = pref.edit();

            editor.putString("Calificacion",String.valueOf(grade) );
            editor.putString("RespuestasCorrectas", numberCorrectAnswers );
            editor.commit();


        }else{
            String Calificacion = pref.getString("nivelTresCal",null);
            String RespuestasCorrectas = pref.getString("nivelTresRes",null);

            txtStudentName.setText(CompleteName);
            txtStudentCareer.setText(Carrera);
            txtStudentControlNumber.setText(numeroControl);

            if(Calificacion != "" && RespuestasCorrectas != ""){
                txtCorrect.setText(Calificacion);
                txtTotal.setText(RespuestasCorrectas +" OUT OF 20");
            }else{
                txtCorrect.setText("It hasn't been graded.");
                txtTotal.setText("");
            }


        }


        btnDone = (Button) findViewById(R.id.btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TotalLevel3Activity.this, LevelsActivity.class));
            }
        });


    }
}