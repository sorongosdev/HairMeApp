package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soratemi3.temi.RoboTemi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;


public class JuiceSel extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener{
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    //Temi Setting
    Robot robot;
    final RoboTemi roboTemi = new RoboTemi();

    private Button btn_nxt;
    private Button btn_americano;
    private Button btn_mochaice;
    private Button btn_cappuccino;
    private Button btn_latte;
    private Button btn_frappuccino;
    private Button btn_frappe;
    private Button btn_flat_white;
    private Button btn_water;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juice_sel);
        robot = Robot.getInstance();

        btn_americano=findViewById(R.id.americano);
        btn_mochaice=findViewById(R.id.mochaice);
        btn_cappuccino=findViewById(R.id.cappuccino);
        btn_latte=findViewById(R.id.latte);
        btn_frappuccino=findViewById(R.id.frappuccino);
        btn_frappe=findViewById(R.id.frappe);
        btn_flat_white=findViewById(R.id.flat_white);
        btn_water=findViewById(R.id.water);

        btn_americano.setOnClickListener(v -> {
            databaseReference.child("Drink").setValue("americano");
            roboTemi.speak("americano 선택");
        });
        btn_mochaice.setOnClickListener(v -> {
            databaseReference.child("Drink").setValue("mochaice");
            roboTemi.speak("mocha ice 선택");
        });
        btn_cappuccino.setOnClickListener(v -> {
            databaseReference.child("Drink").setValue("cappuccino");
            roboTemi.speak("cappuccino 선택");
        });
        btn_latte.setOnClickListener(v -> {
            databaseReference.child("Drink").setValue("latte");
            roboTemi.speak("latte 선택");
        });
        btn_frappuccino.setOnClickListener(v -> {
            databaseReference.child("Drink").setValue("frappuccino");
            roboTemi.speak("frappuccino 선택");
        });
        btn_frappe.setOnClickListener(v -> {
            databaseReference.child("Drink").setValue("frappe");
            roboTemi.speak("프라페 선택");
        });
        btn_flat_white.setOnClickListener(v -> {
            databaseReference.child("Drink").setValue("flat_white");
            roboTemi.speak("flat white 선택");
        });
        btn_water.setOnClickListener(v -> {
            databaseReference.child("Drink").setValue("water");
            roboTemi.speak("물 선택");
        });


        //다음으로
        btn_nxt=findViewById(R.id.juice_btn_nxt);
        btn_nxt.setOnClickListener(v -> {
            roboTemi.goTo("point3");
                databaseReference.child("state").setValue("1");
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Listener 객체추가, this 추가
        robot.addOnGoToLocationStatusChangedListener(this);
    }

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        //저장된 지점으로 이동할때의 행동 변화시 호출
        /*
        시작, 계산중, 이동중, 완료, 중단
         */
        switch (status) {
            case "start":
                //robot.speak(TtsRequest.create("Starting", false));
//                setContentView(R.layout.loading_empty);
                break;

            case "calculating":
                //robot.speak(TtsRequest.create("Calculating", false));
                break;

            case "going":
                //robot.speak(TtsRequest.create("Going", false));
                break;

            case "complete":
                databaseReference.child("state").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Object sta = snapshot.getValue();
                        int state = Integer.parseInt(sta.toString());
                        switch(state){
                            case  1:
                                Intent intent = new Intent(getApplicationContext(), LoadingEmpty.class);
                                startActivity(intent);
                                finish();
                                break;
                            case 2:
                                Intent intent2 = new Intent(getApplicationContext(), LoadingDrinkHere.class);
                                startActivity(intent2);
                                finish();
                                break;

                            case 3:
                                Intent intent3 = new Intent(getApplicationContext(), CheckHair.class);
                                startActivity(intent3);
                                finish();
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
                //robot.speak(TtsRequest.create("Completed", false));
                break;

            case "abort":
                // robot.speak(TtsRequest.create("Cancelled", false));
                break;
        }
    }

    @Override
    public void onRobotReady(boolean b) {

    }
}
