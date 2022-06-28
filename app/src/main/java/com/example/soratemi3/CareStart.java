package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CareStart extends AppCompatActivity {
    private Button btn_nxt;
    private Button btn_use_service;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_start);

        //다음으로
        btn_nxt=findViewById(R.id.start_1);
        btn_nxt.setOnClickListener(v -> {
            databaseReference.child("care_state").setValue(1);
            Intent intent = new Intent(getApplicationContext(), CareStep1.class);
            startActivity(intent);
            finish();
        });

        btn_use_service=findViewById(R.id.use_service);
        btn_use_service.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CareService.class);
            startActivity(intent);
            finish();
        });
    }
}
