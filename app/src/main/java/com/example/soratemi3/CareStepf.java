package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CareStepf extends AppCompatActivity {
    private Button btn_nxt;
    private Button btn_use_service;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_stepf);

//        다음으로
        btn_nxt=findViewById(R.id.stepf_recommend);
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Recommend.class); // 액티비티 사이에서 서로를 호출하기 위해 필요
            startActivity(intent);
            finish();
        });
        btn_use_service=findViewById(R.id.stepf_order);
        btn_use_service.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CareService.class);
            startActivity(intent);
            finish();
        });
    }
}
