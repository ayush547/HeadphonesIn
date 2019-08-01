package com.example.headphonesin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Boolean isRunning=false;
    Button switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchState = findViewById(R.id.flipState);
        switchState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRunning){
                    Intent serviceIntent = new Intent(getApplicationContext(),MyService.class);
                    startService(serviceIntent);
                }
                else{
                    Intent serviceIntent = new Intent(getApplicationContext(),MyService.class);
                    stopService(serviceIntent);
                }
                isRunning =! isRunning;
                if(isRunning) {
                    switchState.setText("Stop Service");
                }
                else {
                    switchState.setText("Start Service");
                }

            }
        });
    }
}
