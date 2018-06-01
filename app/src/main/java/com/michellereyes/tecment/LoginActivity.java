package com.michellereyes.tecment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final DatabaseReference ref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        //if (auth.getCurrentUser() != null) {
          //  startActivity(new Intent(LoginActivity.this, TestActivity.class));
            //finish();
        //}
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {

                                    ref.child(user.getUid()+"/").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String names = dataSnapshot.child("Nombres").getValue().toString();
                                            String firstLastName = dataSnapshot.child("ApellidoPaterno").getValue().toString();
                                            String secondLastName = dataSnapshot.child("ApellidoMaterno").getValue().toString();
                                            String controlNumber = dataSnapshot.child("NumeroControl").getValue().toString();
                                            String career = dataSnapshot.child("Carrera").getValue().toString();

                                            String levelOneCal = dataSnapshot.child("nivelUno/Calificacion").getValue().toString();
                                            String levelOneAns = dataSnapshot.child("nivelUno/RespuestasCorrectas").getValue().toString();
                                            String levelOneDone = dataSnapshot.child("nivelUno/hecho").getValue().toString();

                                            String levelTwoCal = dataSnapshot.child("nivelDos/Calificacion").getValue().toString();
                                            String levelTwoAns = dataSnapshot.child("nivelDos/RespuestasCorrectas").getValue().toString();
                                            String levelTwoDone = dataSnapshot.child("nivelDos/hecho").getValue().toString();

                                            String levelThreeCal = dataSnapshot.child("nivelTres/Calificacion").getValue().toString();
                                            String levelThreeAns = dataSnapshot.child("nivelTres/RespuestasCorrectas").getValue().toString();
                                            String levelThreeDone = dataSnapshot.child("nivelTres/hecho").getValue().toString();

                                            String levelFourCal = dataSnapshot.child("nivelCuatro/Calificacion").getValue().toString();
                                            String levelFourAns = dataSnapshot.child("nivelCuatro/RespuestasCorrectas").getValue().toString();
                                            String levelFourDone = dataSnapshot.child("nivelCuatro/hecho").getValue().toString();

                                            SharedPreferences pref = getApplicationContext().getSharedPreferences("Usuario", 0); // 0 - for private mode
                                            SharedPreferences.Editor editor = pref.edit();

                                            editor.putString("Nombres", names); // Storing boolean - true/false
                                            editor.putString("ApellidoPaterno", firstLastName);
                                            editor.putString("ApellidoMaterno", secondLastName);
                                            editor.putString("NumeroControl", controlNumber);
                                            editor.putString("Carrera",career);

                                            editor.putString("nivelUnoCal",levelOneCal);
                                            editor.putString("nivelUnoRes",levelOneAns);
                                            editor.putString("nivelUnoHecho",levelOneDone);

                                            editor.putString("nivelDosCal",levelTwoCal);
                                            editor.putString("nivelDosRes",levelTwoAns);
                                            editor.putString("nivelDosHecho",levelTwoDone);

                                            editor.putString("nivelTresCal",levelThreeCal);
                                            editor.putString("nivelTresRes",levelThreeAns);
                                            editor.putString("nivelTresHecho",levelThreeDone);

                                            editor.putString("nivelCuatroCal",levelFourCal);
                                            editor.putString("nivelCuatroRes",levelFourCal);
                                            editor.putString("nivelCuatroHecho",levelFourDone);

                                            editor.commit();

                                            Toast.makeText(LoginActivity.this,names,Toast.LENGTH_LONG).show();

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    Intent intent = new Intent(LoginActivity.this, LevelsActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

    }
}
