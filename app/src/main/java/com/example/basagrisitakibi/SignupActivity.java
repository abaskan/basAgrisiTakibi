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

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText signupUserName,signupUserSurname,signupEmail,signupPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();


        signupUserName = findViewById(R.id.signupUserName);
        signupUserSurname = findViewById(R.id.signupUserSurname);
        signupEmail = findViewById(R.id.signupEmail);
        signupPassword = findViewById(R.id.signupPassword);



    }

    public void signupButtonClick(View view) {
        String email = signupEmail.getText().toString();
        String password = signupPassword.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent signupToMain = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(signupToMain);
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
