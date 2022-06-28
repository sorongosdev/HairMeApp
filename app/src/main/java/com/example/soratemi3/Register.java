package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.soratemi3.temi.RoboTemi;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    //Define DatabaseReference Instance
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    //Temi Setting
    Robot robot;
    final RoboTemi roboTemi = new RoboTemi();

    private Button btn_nxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        int rg_state = 1;

        robot = Robot.getInstance();

        //다음으로
        btn_nxt=findViewById(R.id.btn_nxt);
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DesignSel.class);
            startActivity(intent);
            finish();
        });

        //RFID 테그하면 화면 넘어가기
        databaseReference.child("register").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object state = snapshot.getValue();
                boolean bool_state = ((Boolean) state).booleanValue();
                if(bool_state){
                    if(rg_state==1){
                        Intent intent = new Intent(getApplicationContext(), DesignSel.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });

        databaseReference.child("RFID/tag").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object tag = snapshot.getValue();
                int tag_int = Integer.parseInt(tag.toString());
                switch(tag_int){
                    case  1:
                        roboTemi.speak("김걸휘님 안녕하세요");
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
    }
}
