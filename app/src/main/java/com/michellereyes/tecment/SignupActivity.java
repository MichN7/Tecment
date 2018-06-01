package com.michellereyes.tecment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText inputEmail,inputPassword, inputControlNumber, inputNames, inputFirstLastName, inputSecondLastName;
    private Button btnSignIn, btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignIn = (Button)findViewById(R.id.sign_in_button);
        btnSignUp = (Button)findViewById(R.id.sign_up_button);
        inputEmail = (EditText)findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        inputNames = (EditText)findViewById(R.id.names);
        inputFirstLastName = (EditText)findViewById((R.id.firstLastName));
        inputSecondLastName = (EditText)findViewById((R.id.secondLastName));
        inputControlNumber = (EditText)findViewById((R.id.controlNumber));
        inputNames.requestFocus();

        mAuth = FirebaseAuth.getInstance();

        try{
            String[] arraySpinner = new String[] {
                    "Career","Systems", "Industrial", "Civil", "Electronic", "Electromechanical " , "Managment"
            };
            Spinner s = (Spinner) findViewById(R.id.carreras_spinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, arraySpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s.setAdapter(adapter);
        }catch (Exception e){
            Log.e("MYAPP", "exception", e);
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                final String nombres  = inputNames.getText().toString().trim();
                final String firstLastName = inputFirstLastName.getText().toString().trim();
                final String secondLastName = inputSecondLastName.getText().toString().trim();
                final String controlNumber = inputControlNumber.getText().toString().trim();
                Spinner s = (Spinner) findViewById(R.id.carreras_spinner);
                final String career = s.getSelectedItem().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter password!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nombres)){
                    Toast.makeText(getApplicationContext(),"Enter names!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(firstLastName)){
                    Toast.makeText(getApplicationContext(),"Enter first last name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(secondLastName)){
                    Toast.makeText(getApplicationContext(),"Enter second last name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(controlNumber)){
                    Toast.makeText(getApplicationContext(),"Enter control number!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(career == "Career"){
                    Toast.makeText(getApplicationContext(),"Enter a Career!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"Password too short,enter minimum 6 characters!",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(SignupActivity.this,"Your account has been created!",Toast.LENGTH_SHORT).show();
                                if(!task.isSuccessful()){
                                    Toast.makeText(SignupActivity.this,"Authentication failed."+task.getException(),Toast.LENGTH_SHORT).show();
                                }else{
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    String encodedEmail = encodeUserEmail(email);
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    DatabaseReference ref = database.getReference(user.getUid());

                                    HashMap<String, String> datosHash = new HashMap<String, String>();
                                    datosHash.put("Nombres", nombres);
                                    datosHash.put("ApellidoPaterno", firstLastName);
                                    datosHash.put("ApellidoMaterno", secondLastName);
                                    datosHash.put("NumeroControl", controlNumber);
                                    datosHash.put("Carrera", career);

                                    datosHash.put("nivelUno/hecho","false");
                                    datosHash.put("nivelUno/Calificacion","");
                                    datosHash.put("nivelUno/RespuestasCorrectas","");

                                    datosHash.put("nivelDos/hecho","false");
                                    datosHash.put("nivelDos/Calificacion","");
                                    datosHash.put("nivelDos/RespuestasCorrectas","");

                                    datosHash.put("nivelTres/hecho","false");
                                    datosHash.put("nivelTres/Calificacion","");
                                    datosHash.put("nivelTres/RespuestasCorrectas","");

                                    datosHash.put("nivelCuatro/hecho","false");
                                    datosHash.put("nivelCuatro/Calificacion","");
                                    datosHash.put("nivelCuatro/RespuestasCorrectas","");


                                    ref.setValue(datosHash);

                                    SharedPreferences pref = getApplicationContext().getSharedPreferences("Usuario", 0); // 0 - for private mode
                                    SharedPreferences.Editor editor = pref.edit();

                                    editor.putString("Nombres", nombres); // Storing boolean - true/false
                                    editor.putString("ApellidoPaterno", firstLastName);
                                    editor.putString("ApellidoMaterno", secondLastName);
                                    editor.putString("NumeroControl", controlNumber);
                                    editor.putString("Carrera",career);
                                    editor.commit();

                                    startActivity(new Intent(SignupActivity.this,LevelsActivity.class));
                                    finish();
                                }
                            }
                        });

            }


        });


    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }

}
