package com.topicos.unipariot;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FuncoesAcitivity extends AppCompatActivity {

    @BindView(R.id.ed_msg)
    TextInputLayout edMsg;
    @BindView(R.id.bt_rele1)
    Button btRele1;
    @BindView(R.id.bt_rele2)
    Button btRele2;
    @BindView(R.id.bt_envia_msg)
    Button btEnviaMsg;
    private DatabaseReference mDatabase;
    Integer estadoRele1 = 0;
    Integer estadoRele2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcoes_acitivity);
        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("r1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("rele1",String.valueOf(snapshot.getValue(Integer.class)));
                estadoRele1  = snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mDatabase.child("r2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("rele1",String.valueOf(snapshot.getValue(Integer.class)));
                estadoRele2  = snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @OnClick(R.id.bt_rele1)
    public void onBtRele1Clicked() {

        if(estadoRele1 == 0){
            mDatabase.child("r1").setValue(1);
        }else{
            mDatabase.child("r1").setValue(0);
        }

        Aluno aluno = new Aluno("cristiano","123456");
        mDatabase.child("log").child("rele1").child("1234567859").setValue(aluno);
    }

    @OnClick(R.id.bt_rele2)
    public void onBtRele2Clicked() {

        if(estadoRele2 == 0){
            mDatabase.child("r2").setValue(1);
        }else{
            mDatabase.child("r2").setValue(0);
        }
    }

    @OnClick(R.id.bt_envia_msg)
    public void onBtEnviaMsgClicked() {
        String msg = edMsg.getEditText().getText().toString();
        mDatabase.child("msg").setValue(msg);

    }
}