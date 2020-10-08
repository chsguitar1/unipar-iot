package com.topicos.unipariot;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActiviry extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.ed_email_resgistrar)
    TextInputLayout edEmailResgistrar;
    @BindView(R.id.ed_senha_registrar)
    TextInputLayout edSenhaRegisdtrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activiry);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
    }


    void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            edEmailResgistrar.getEditText().setText("");
                            edSenhaRegisdtrar.getEditText().setText("");


                        } else {
                            // If sign in fails, display a message to the user.


                        }

                        // ...
                    }
                });
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        createUser(edEmailResgistrar.getEditText().getText().toString(),edSenhaRegisdtrar.getEditText().getText().toString());
    }
}