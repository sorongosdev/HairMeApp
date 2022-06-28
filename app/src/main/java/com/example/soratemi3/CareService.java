package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CareService extends AppCompatActivity {
    private Button btn_staff;
    private Button btn_order;
    private Button btn_charge;
    private Button btn_nxt;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_service);

        //직원호출
//        btn_staff=findViewById(R.id.service_btn_nxt);
//        btn_nxt.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), LoadingStaff.class);
//            startActivity(intent);
//            finish();
//        });
        //음료주문
        btn_order=findViewById(R.id.service_order);
        btn_order.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), JuiceSel.class);
            startActivity(intent);
            finish();
        });
        //충전서비스
        btn_charge=findViewById(R.id.charge);
        btn_charge.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Charge.class);
            startActivity(intent);
            finish();
        });
        //다음으로 누르면 돌아가기
        btn_nxt=findViewById(R.id.service_btn_nxt);
        btn_nxt.setOnClickListener(v -> {
            databaseReference.child("care_state").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Object car_sta = snapshot.getValue();
                    int care_state = Integer.parseInt(car_sta.toString());
                    switch(care_state){
                        case  0:
                            Intent intent = new Intent(getApplicationContext(), CareStart.class);
                            startActivity(intent);
                            finish();
                            break;
                        case 1:
                            Intent intent2 = new Intent(getApplicationContext(), CareStep1.class);
                            startActivity(intent2);
                            finish();
                            break;

                        case 2:
                            Intent intent3 = new Intent(getApplicationContext(), CareStep2.class);
                            startActivity(intent3);
                            finish();
                            break;
                        case 3:
                            Intent intent4 = new Intent(getApplicationContext(), CareStepf.class);
                            startActivity(intent4);
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
            Intent intent = new Intent(getApplicationContext(), CareStart.class);
            startActivity(intent);
            finish();
        });
    }
}
