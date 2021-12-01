package com.example.uniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Button btnReport, btnExistingProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReport= findViewById(R.id.btnReport);

        btnExistingProblem= findViewById(R.id.btnExistingProblem);

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ReportProblem.class);
                startActivity(intent);
            }
        });

        btnExistingProblem.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this, ExistingProblem.class);
              startActivity(intent);
         }
   });
    }
}