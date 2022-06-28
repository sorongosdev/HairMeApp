package com.example.soratemi3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseTest extends AppCompatActivity {

    //Define DatabaseReference Instance
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebasetest_layout);

        //Define switchCompat and distance text
        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.switchLed);
        TextView distanceText = (TextView)findViewById(R.id.textDistance);
        Button buttonDistance = (Button)findViewById(R.id.buttonDistance);
        TextView distanceText2 =(TextView)findViewById(R.id.textDistance2);

        //write data
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    databaseReference.child("led").setValue(true);
                    switchCompat.setText("LED ON");
                } else {
                    databaseReference.child("led").setValue(false);
                    switchCompat.setText("LED OFF");
                }
            }
        });

        //read data
        databaseReference.child("number/distance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object myText = snapshot.getValue();
                distanceText.setText("Distance: "+myText.toString());
                Log.d("tag", "distance is " + myText);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });

        buttonDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("number/distance").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Object myText = snapshot.getValue();
                        distanceText2.setText("Distance: "+myText.toString());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
    }
}