package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soratemi3.temi.RoboTemi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.robotemi.sdk.Robot;

public class Recommend extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    Robot robot;
    final RoboTemi roboTemi = new RoboTemi();

    private Button btn_nxt;
    private Button btn_prod1;
    private Button btn_prod2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);

        btn_nxt=findViewById(R.id.pay_product);
        btn_prod1=findViewById(R.id.product1);
        btn_prod2=findViewById(R.id.product2);

//        다음으로
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PayCheck.class); // 액티비티 사이에서 서로를 호출하기 위해 필요
            startActivity(intent);
            finish();
        });

        btn_prod1.setOnClickListener(v -> {
            databaseReference.child("Product").setValue("product1");
            roboTemi.speak("미쟝센 퍼펙트세럼 오리지널 선택");
        });

        btn_prod2.setOnClickListener(v -> {
            databaseReference.child("Product").setValue("product2");
            roboTemi.speak("제이숲 컬링 에센스 선택");
        });


    }
}
