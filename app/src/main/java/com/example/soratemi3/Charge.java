package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Charge extends AppCompatActivity {
    private Button btn_nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charge);

//      화면 아무 곳이나 터치
        btn_nxt=findViewById(R.id.charge);
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CareService.class); // 액티비티 사이에서 서로를 호출하기 위해 필요
            startActivity(intent);
            finish();
        });
    }
}
