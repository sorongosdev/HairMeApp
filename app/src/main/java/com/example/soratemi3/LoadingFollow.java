package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingFollow extends AppCompatActivity {
    private Button btn_nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_follow);

        //다음으로
        btn_nxt=findViewById(R.id.loading_follow_btn);
        btn_nxt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CheckHair.class);
            startActivity(intent);
            finish();
        });
    }
}
