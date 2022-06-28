package com.example.soratemi3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;

public class Agreement extends AppCompatActivity {
    private CheckBox agr_box;
    private CheckBox dis_box;
    private Button btn_nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agreement);

        agr_box = findViewById(R.id.agree);
        dis_box = findViewById(R.id.disagree);

        //다음으로
        btn_nxt = findViewById(R.id.agree_btn_nxt);
        btn_nxt.setOnClickListener(v -> {
            if (dis_box.isChecked()) {
                Toast.makeText(getApplicationContext(), "미동의 시 시술을 진행할 수 없습니다.", Toast.LENGTH_SHORT).show();
            } else if (agr_box.isChecked()) { // 동의 체크
                Intent intent = new Intent(getApplicationContext(), CareInform.class);
                startActivity(intent);
                finish();
            }
        });

    }
}