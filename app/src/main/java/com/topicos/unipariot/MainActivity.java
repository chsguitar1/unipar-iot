package com.topicos.unipariot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.ed_email)
    TextInputLayout edEmail;
    @BindView(R.id.ed_senha)
    TextInputLayout edSenha;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_registrar)
    Button btRegistrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }

    void login(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent it = new Intent(MainActivity.this,FuncoesAcitivity.class);
                            startActivity(it);

                        } else {
                            // If sign in fails, display a message to the user.
                            ;
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    @OnClick({R.id.bt_login, R.id.bt_registrar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                login(edEmail.getEditText().getText().toString(),edSenha.getEditText().getText().toString());
                break;
            case R.id.bt_registrar:
                Intent it = new Intent(MainActivity.this,RegisterActiviry.class);
                startActivity(it);
                break;
        }
    }
}