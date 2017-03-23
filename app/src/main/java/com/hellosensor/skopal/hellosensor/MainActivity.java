package com.hellosensor.skopal.hellosensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer =(Button)findViewById(R.id.accelerometer_button);

        accelerometer.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Accelerometer.class));
            }
        });
    }


}
