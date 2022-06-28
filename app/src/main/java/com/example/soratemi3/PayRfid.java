package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PayRfid extends AppCompatActivity {
    private Button btn_nxt;
    private TextView final_sum;
    int s = PayCheck.s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_rfid);

        final_sum=findViewById(R.id.final_sum2);
        final_sum.setText(s+" ￦");

        //다음으로
        btn_nxt=findViewById(R.id.put_rfid);
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PayReceipt.class);
            startActivity(intent);
            finish();
        });
    }
}
