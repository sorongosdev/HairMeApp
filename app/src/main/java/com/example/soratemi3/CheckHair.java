package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.example.soratemi3.temi.RoboTemi;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;


public class CheckHair extends AppCompatActivity implements OnRobotReadyListener, OnGoToLocationStatusChangedListener{
    private Button btn_nxt;
    private Button btn_call;
    Robot robot;
    final RoboTemi roboTemi = new RoboTemi();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_hair);
        robot = Robot.getInstance();

        btn_call=findViewById(R.id.call_btn);
        btn_call.setOnClickListener(v -> {
            roboTemi.callOwner();
        });

//        다음으로
        btn_nxt=findViewById(R.id.check_hair_btn);
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Agreement.class); // 액티비티 사이에서 서로를 호출하기 위해 필요
            startActivity(intent);
            finish();
        });
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
