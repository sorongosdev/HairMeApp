package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class LoadingDrinkHere extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener {
    private Button btn_drink;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    int cnt = 0;

    //Temi Setting
    Robot robot;
    final RoboTemi roboTemi = new RoboTemi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_drinkhere);
        robot = Robot.getInstance();


        //다음으로
        btn_drink=findViewById(R.id.loading_drinkhere_btn);
        btn_drink.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), LoadingFollow.class);
//            startActivity(intent);
//            finish();
            roboTemi.speak("자리를 안내해 드리겠습니다.");
            roboTemi.goTo("point4");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    databaseReference.child("state").setValue("3");
                }
            }, 100);

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Listener 객체추가, this 추가

        robot.addOnGoToLocationStatusChangedListener(this);
        roboTemi.speak("주문하신 음료가 나왔습니다.");

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

            case "going"://robot.speak(TtsRequest.create("Going", false));
                break;

            case "complete":
                //robot.speak(TtsRequest.create("Completed", false));
//                roboTemi.speak("자리에 앉아주세요");
//                Intent intent = new Intent(getApplicationContext(), CheckHair.class);
//                startActivity(intent);
//                finish();
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
