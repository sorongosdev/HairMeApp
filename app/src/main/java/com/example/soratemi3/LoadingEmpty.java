package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import com.example.soratemi3.temi.RoboTemi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingEmpty extends AppCompatActivity implements OnGoToLocationStatusChangedListener, OnRobotReadyListener {
    private Button btn_loding;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    Robot robot;
    final RoboTemi roboTemi = new RoboTemi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int cnt = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_empty);
        robot = Robot.getInstance();
        //다음으로
        btn_loding=findViewById(R.id.loading_empty_btn);
        btn_loding.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), LoadingDrinkHere.class);
//            startActivity(intent);
//            finish();
            roboTemi.goTo("point1");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    databaseReference.child("state").setValue("2");
                }
            }, 100);
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Listener 객체추가, this 추가
        firebaseDatabase.getReference("Drink").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object myText = snapshot.getValue();
                String txt = myText.toString();
                roboTemi.speak(txt+"주문이 있습니다.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Fail to read value.", error.toException());
            }
        });
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
                break;

            case "calculating":
                //robot.speak(TtsRequest.create("Calculating", false));
                break;

            case "going":
                //robot.speak(TtsRequest.create("Going", false));
                break;

            case "complete":
                //robot.speak(TtsRequest.create("Completed", false));
//                roboTemi.speak("주문하신 음료가 나왔습니다.");
//                Intent intent = new Intent(getApplicationContext(), LoadingDrinkHere.class);
//                startActivity(intent);
//                finish();
                break;

            case "abort":
                // robot.speak(TtsRequest.create("Cancelled", false));
                break;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
    @Override
    public void onRobotReady(boolean b) {

    }
}
