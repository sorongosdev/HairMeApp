package com.example.soratemi3.temi;

import android.view.View;

import com.robotemi.sdk.BatteryData;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;

public class RoboTemi  {

    private Robot robot;

    public RoboTemi() {
        this.robot =  Robot.getInstance();
    }

    public Robot getRobot() {
        return robot;
    }

    public void speak(String text){
        //말하려는 것의 앞뒤 띄어쓰기를 제거하고 , 말하는 내용을 화면에 보여줄지 여부
        TtsRequest ttsRequest = TtsRequest.create(text.trim(), false);
        robot.speak(ttsRequest);// 말하기
    }

    public void saveLocation(String location) {
        String loc = location.toLowerCase().trim();// 장소 관련 이름을 소문자로 만들고 앞뒤 띄어쓰기를 제거
        boolean result = robot.saveLocation(loc);// 장소를 저장
        if (result) {// 결과 확인
            speak(loc+" 장소 저장에 성공하였습니다." );
        } else {
            speak(loc +" 장소 저장에 실패하였습니다." );
        }
    }

    public void goTo(String destination) {//저장된 장소로 이동하라
        for (String location : robot.getLocations()) {
            if (location.equals(destination.toLowerCase().trim())) {
                robot.goTo(destination.toLowerCase().trim());
            }
        }
    }

    public void stopMovement() {// 이동중인 것을 중단
        robot.stopMovement();
        speak("정지하였습니다." );
    }

    public void followMe() {// 앞의 대상을 따라가기
        robot.beWithMe();
        speak("테미가 따라갑니다." );
    }


    public void callOwner() {// 주인에게 전화하기
        robot.startTelepresence(robot.getAdminInfo().getName(), robot.getAdminInfo().getUserId());
    }

    public void tiltAngle(int i, float vect) {//본체의 방향을 제자리에서 회전
        // 각도(ex 90), 속도(ex 5.3F)
        robot.tiltAngle(i,vect);
    }

    public void turnBy(int angle, float vel) {//테미가 이동을 하면서 회전
        // 각도(ex 180), 속도(ex 6.2F)
        robot.turnBy(angle, vel);
    }

    public void tiltBy(int i, float vect) {//테미 화면이 달린 부분을 얼굴 처럼 기웃 하는 기능
        // 각도(ex 70), 속도(ex 1.2F)
        robot.tiltBy(i,vect);
    }

    public void getBatteryData() {//현재의 배터리 상태를 반환
        BatteryData batteryData = robot.getBatteryData();
        if (batteryData.isCharging()) {// 충전중
            speak(batteryData.getBatteryPercentage() + " percent battery and charging.");
        } else {
            speak(batteryData.getBatteryPercentage() + " percent battery and not charging.");
        }
    }

    public void skidJoy(float v,float a) {//이동
        //v: 앞뒤 방향 이동 속도 -1.0 ~ 1.0
        //a :좌우 방향 각속도
        robot.skidJoy(v, a);
    }
    public void skidJoy(float vel , int sec) {// 이동 예시
        //정면 방향으로 sec 초간 이동하라
        long t = System.currentTimeMillis();
        long end = t + 1000*sec;
        while (System.currentTimeMillis() < end) {
            robot.skidJoy(vel, 0F);
        }
    }

    public void privacyModeOn(View view) {
        robot.setPrivacyMode(true);
        //Toast.makeText(this, robot.getPrivacyMode() + "", Toast.LENGTH_SHORT).show();
    }

    public void privacyModeOff(View view) {
        robot.setPrivacyMode(false);
        //Toast.makeText(this, robot.getPrivacyMode() + "", Toast.LENGTH_SHORT).show();
    }

    public void getPrivacyModeState(View view) {
        //Toast.makeText(this, robot.getPrivacyMode() + "", Toast.LENGTH_SHORT).show();
    }

    public void isHardButtonsEnabled(View view) {
        //Toast.makeText(this, robot.isHardButtonsDisabled() + "", Toast.LENGTH_SHORT).show();
    }

    public void disableHardButtons(View view) {
        robot.setHardButtonsDisabled(true);
        //Toast.makeText(this, robot.isHardButtonsDisabled() + "", Toast.LENGTH_SHORT).show();
    }

    public void enableHardButtons(View view) {
        robot.setHardButtonsDisabled(false);
        //Toast.makeText(this, robot.isHardButtonsDisabled() + "", Toast.LENGTH_SHORT).show();
    }


}
