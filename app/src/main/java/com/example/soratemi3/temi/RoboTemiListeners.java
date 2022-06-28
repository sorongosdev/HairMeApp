package com.example.soratemi3.temi;

import android.util.Log;

import com.robotemi.sdk.NlpResult;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.activitystream.ActivityStreamPublishMessage;
import com.robotemi.sdk.listeners.OnBeWithMeStatusChangedListener;
import com.robotemi.sdk.listeners.OnConstraintBeWithStatusChangedListener;
import com.robotemi.sdk.listeners.OnDetectionStateChangedListener;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnLocationsUpdatedListener;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/*
  extends : 정의된 객체를 상속받았을 경우 해당 기능을 연장해서 사용한다는 뜻이다.

  implements : abstract 객체를 상속받고 그 내용들을 작성했다는 뜻이다.
              abstract 클래스의 경우 필수 기능의 메서드의 형태만 정의되어있기때문에
              상세 내용을 override를 통해서 반드시 정의해야하며 , 부모늬 클래스는 작성된 내용이 없기때문에
              super를 통하여 부모의 기능을 실핼 수 없다.
*/
public class RoboTemiListeners extends RoboTemi // extends는 Main2Activity 예제에서 사용한다.
        implements
       Robot.NlpListener,
       OnRobotReadyListener,
       Robot.ConversationViewAttachesListener,
       Robot.WakeupWordListener,
       Robot.ActivityStreamPublishListener,
       Robot.TtsListener,
       OnBeWithMeStatusChangedListener,
       OnGoToLocationStatusChangedListener,
       OnLocationsUpdatedListener,
       OnConstraintBeWithStatusChangedListener,
       OnDetectionStateChangedListener,
       Robot.AsrListener {

   private Robot robot;
   public RoboTemiListeners() {
       // this: 현재 객체를 가르킨다.
       // 같은 변수가 있을 경우 this는 class 전역을, 일반 변수는 괄호 내의 변수를 뜻한다.
       this.robot =  Robot.getInstance();
   }

   public void init(){
       robot.addOnRobotReadyListener(this);
       robot.addNlpListener(this);
       robot.addOnBeWithMeStatusChangedListener(this);
       robot.addOnGoToLocationStatusChangedListener(this);
       robot.addConversationViewAttachesListenerListener(this);
       robot.addWakeupWordListener(this);
       robot.addTtsListener(this);
       robot.addOnLocationsUpdatedListener(this);
       robot.addOnConstraintBeWithStatusChangedListener(this);
       robot.addOnDetectionStateChangedListener(this);
       robot.addAsrListener(this);
   }
   public void stop(){
       robot.removeOnRobotReadyListener(this);
       robot.removeNlpListener(this);
       robot.removeOnBeWithMeStatusChangedListener(this);
       robot.removeOnGoToLocationStatusChangedListener(this);
       robot.removeConversationViewAttachesListenerListener(this);
       robot.removeWakeupWordListener(this);
       robot.removeTtsListener(this);
       robot.removeOnLocationsUpdateListener(this);
       robot.removeDetectionStateChangedListener(this);
       robot.removeAsrListener(this);
       robot.stopMovement();
   }



   @Override
   public void onPublish(@NotNull ActivityStreamPublishMessage activityStreamPublishMessage) {
       //Activity 스트림 publish가 끝났을 때 호출
       //최근 행동들의 기록

   }

   @Override
   public void onAsrResult(@NotNull String s) {
       //자동 대화 인식 결과(Auto speech recognition)
       //인식 결과: 문자열 s
       Log.i("ASR RESULT",s);
   }

   @Override
   public void onConversationAttaches(boolean b) {
       // 대화가 시작되었을 경우
   }

   @Override
   public void onNlpCompleted(@NotNull NlpResult nlpResult) {
       // 자연 언어 처리 완료 후(natural language processing)
       String str = nlpResult.toString();
       Log.i("NLP RESULT",str);
   }

   @Override
   public void onTtsStatusChanged(@NotNull TtsRequest ttsRequest) {
       //테미가 말하는 것의 상태 변화(Text to Speedh)
   }

   @Override
   public void onWakeupWord(@NotNull String s, int i) {
       //테미를 불러서 깨웠을때
       speak("네 부르셨나요?");//roboTemi class를 상속했기 때문에 사용이 가능하다.
   }

   @Override
   public void onBeWithMeStatusChanged(@NotNull String status) {
       //테미가 따라오는 것의 행동 변화시 호출
       /*
        중단, 계산중, 포착, 검색, 시작, 추적
        */
       switch (status) {
           case "abort":
               robot.speak(TtsRequest.create("Abort", false));
               break;

           case "calculating":
               robot.speak(TtsRequest.create("Calculating", false));
               break;

           case "lock":
               robot.speak(TtsRequest.create("Lock", false));
               break;

           case "search":
               robot.speak(TtsRequest.create("search", false));
               break;

           case "start":
               robot.speak(TtsRequest.create("Start", false));
               break;

           case "track":
               robot.speak(TtsRequest.create("Track", false));
               break;
       }
   }

   @Override
   public void onConstraintBeWithStatusChanged(boolean b) {
       //제한된 따라가기
       //robot.constraintBeWith(); 의 호출로 차이가 난다.
   }

   @Override
   public void onDetectionStateChanged(int state) {
       // DETECTED, IDLE, LOST
       switch (state){
           case DETECTED:
               //대상 포착시 행동
               break;
           case IDLE:
               //대기상태
               break;
           case LOST:
               //대상을 잃어버림
               break;
       }
   }

   @Override
   public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
       //저장된 지점으로 이동할때의 행동 변화시 호출
       /*
       시작, 계산중, 이동중, 완료, 중단
        */
       switch (status) {
           case "start":
               robot.speak(TtsRequest.create("Starting", false));
               break;

           case "calculating":
               robot.speak(TtsRequest.create("Calculating", false));
               break;

           case "going":
               robot.speak(TtsRequest.create("Going", false));
               break;

           case "complete":
               robot.speak(TtsRequest.create("Completed", false));
               break;

           case "abort":
               robot.speak(TtsRequest.create("Cancelled", false));
               break;
       }
   }

   @Override
   public void onLocationsUpdated(@NotNull List<String> list) {
       //새롭게 저장된 곳이 생겼을때 전체 목록 호출
       /*for (String str: list) {
          //각 지점에 대해서 코드..
       }*/
   }

   @Override
   public void onRobotReady(boolean b) {
       //로봇이 준비된 상태에 들어갔을때
   }

}
