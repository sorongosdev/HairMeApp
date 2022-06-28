package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CareInform extends AppCompatActivity {
    private Button btn_nxt;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_inform);

        //다음으로
        btn_nxt=findViewById(R.id.care_inform_btn_nxt);
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CareStart.class);
            startActivity(intent);
            finish();
            databaseReference.child("care_state").setValue(0);
        });
    }
}
