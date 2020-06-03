package com.example.basagrisitakibi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    EditText loginEmail,loginPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Intent loginToMain = new Intent(LoginActivity.this,MainActivity.class);

            startActivity(loginToMain);
            finish();
        }

    }

    public void signupTextClick (View view) {
        Intent loginToSignup = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(loginToSignup);
    }


    public void loginButtonClick (View view) {
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent loginToMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(loginToMain);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();

            }
        });
    }


}
