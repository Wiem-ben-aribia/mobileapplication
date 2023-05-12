package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class resultat extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        int result = getIntent().getIntExtra("result", 0);


        TextView textViewResult = findViewById(R.id.textView);

        textViewResult.setText(String.valueOf(result));


}
}