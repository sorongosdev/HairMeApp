package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soratemi3.temi.RoboTemi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.robotemi.sdk.Robot;

public class DesignSel extends AppCompatActivity {

    //Define DatabaseReference Instance
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    Robot robot;
    final RoboTemi roboTemi = new RoboTemi();

    private Button btn_nxt;
    private Button btn_cut;
    private Button btn_dry;
    private Button btn_clinic;
    private Button btn_magic;
    private Button btn_perm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_sel);

        robot = Robot.getInstance();


        //다음으로
        btn_nxt=findViewById(R.id.design_btn_nxt);
        btn_cut=findViewById(R.id.cut);
        btn_dry=findViewById(R.id.dry);
        btn_clinic=findViewById(R.id.clinic);
        btn_magic=findViewById(R.id.magic);
        btn_perm=findViewById(R.id.perm);

        btn_cut.setOnClickListener(v -> {
            databaseReference.child("Design").setValue("cut");
            roboTemi.speak("cut 선택");
        });
        btn_dry.setOnClickListener(v -> {
            databaseReference.child("Design").setValue("dry");
            roboTemi.speak("dry 선택");
        });
        btn_clinic.setOnClickListener(v -> {
            databaseReference.child("Design").setValue("clinic");
            roboTemi.speak("clinic 선택");
        });
        btn_magic.setOnClickListener(v -> {
            databaseReference.child("Design").setValue("magic");
            roboTemi.speak("magic 선택");
        });
        btn_perm.setOnClickListener(v -> {
            databaseReference.child("Design").setValue("perm");
            roboTemi.speak("perm 선택");
        });
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), JuiceSel.class);
            startActivity(intent);
            finish();
        });
    }
    protected void onStart(){
        super.onStart();
        databaseReference.child("register").setValue(false);
//        databaseReference.child("register2").setValue(false);
    }

}
