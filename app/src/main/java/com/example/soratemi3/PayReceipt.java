package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PayReceipt extends AppCompatActivity {
    private Button btn_nxt;
    private TextView rest;
    int m = 300000-PayCheck.s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_receipt);

        rest=findViewById(R.id.rest_money);
        rest.setText(m+" ￦");
    }
}
