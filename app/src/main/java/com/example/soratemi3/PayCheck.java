package com.example.soratemi3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soratemi3.temi.RoboTemi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robotemi.sdk.Robot;

import java.util.Arrays;

public class PayCheck extends AppCompatActivity {

    //Define DatabaseReference Instance
    FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
    FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();

    //Temi
    Robot robot;
    final RoboTemi roboTemi = new RoboTemi();

    private Button btn_nxt;
    private TextView des_name; //받아 옴
    private TextView des_price;
    private TextView prod_name;
    private TextView prod_price;
    private TextView final_sum;
    String set_des_name,set_prod_name;
    static int s;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_check);
        robot = Robot.getInstance();
        int[] arr = new int[3];
        //다음으로
        btn_nxt=findViewById(R.id.btn_pay);
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PayRfid.class);
            startActivity(intent);
            finish();
        });

        final_sum=findViewById(R.id.final_sum);
        des_name=findViewById(R.id.des_name);
        des_price=findViewById(R.id.des_price);
        prod_name=findViewById(R.id.prod_name);
        prod_price=findViewById(R.id.prod_price);
        firebaseDatabase1.getReference("Design").addValueEventListener(new ValueEventListener() {
            Object myText1;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myText1 = snapshot.getValue();
                set_des_name=myText1.toString();

                if(set_des_name.equals("cut")){
                    des_name.setText("컷");
                    des_price.setText("30.000");
                    arr[0]=30000;
                }
                else if(set_des_name.equals("dry")){
                    des_name.setText("드라이");
                    des_price.setText("25.000");
                    arr[0]=25000;
                }
                else if(set_des_name.equals("clinic")){
                    des_name.setText("클리닉");
                    des_price.setText("200.000");
                    arr[0]=200000;
                }
                else if(set_des_name.equals("maigc")){
                    des_name.setText("매직");
                    des_price.setText("240.000");
                    arr[0]=240000;
                }
                else if(set_des_name.equals("perm")){
                    des_name.setText("펌");
                    des_price.setText("120.000");
                    arr[0]=120000;
                }
                s = arr[0]+arr[1];
                final_sum.setText(s+" ￦");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Fail to read value.", error.toException());
            }
        });

        firebaseDatabase2.getReference("Product").addValueEventListener(new ValueEventListener() {
            Object myText2;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myText2 = snapshot.getValue();
                set_prod_name=myText2.toString();
                if(set_prod_name.equals("product1")){
                    prod_name.setText("미쟝센 퍼펙트세럼 오리지널 80ml");
                    prod_price.setText("8.000");
                    arr[1] = 8000;
                }
                else if(set_prod_name.equals("product2")){
                    prod_name.setText("제이숲 컬링 에센스 250ml");
                    prod_price.setText("10.000");
                    arr[1] = 10000;
                }
                s = arr[0]+arr[1];
                final_sum.setText(s+"￦");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Fail to read value.", error.toException());
            }
        });
    }
}
