package com.example.soratemi3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.soratemi3.temi.RoboTemi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robotemi.sdk.Robot;

public class MainActivity extends AppCompatActivity {

    //Define DatabaseReference Instance
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    static String Sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting);
        Robot robot;
        final RoboTemi roboTemi = new RoboTemi();

        TextView numText = (TextView) findViewById(R.id.waiting_num);
        TextView waitText = (TextView) findViewById(R.id.waiting_text);

        //대기 등록
        Button btn_reg = findViewById(R.id. btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class); // 액티비티 사이에서 서로를 호출하기 위해 필요
                startActivity(intent);
                finish();
            }
        });
        //firebase로 data 받아오기
//        databaseReference.child("count").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Object myText = snapshot.getValue();
//                numText.setText("00"+myText.toString());
//                int number= Integer.parseInt(myText.toString());
//                waitText.setText("예상대기시간은 "+ number*15 +"분 입니다.");
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w("tag", "Failed to read value.", error.toException());
//            }
//        });
        firebaseDatabase.getReference("count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object myText = snapshot.getValue();
                numText.setText("00"+myText.toString());
                int number= Integer.parseInt(myText.toString());
                if(number > 3){
                    roboTemi.speak("Juno hair에 오신걸 환영합니다");
                }
                waitText.setText("예상대기시간은 "+ number*15 +"분 입니다.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Fail to read value.", error.toException());
            }
        });
    }
}