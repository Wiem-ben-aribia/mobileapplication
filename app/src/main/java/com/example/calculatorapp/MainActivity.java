package com.example.calculatorapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner2);
        String[] tableauOperation = getResources().getStringArray(R.array.multiplication);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tableauOperation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        EditText editText1 = findViewById(R.id.numb1);
        EditText editText2 = findViewById(R.id.numb2);
        Button buttonCalculate = findViewById(R.id.button);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNumber1 = editText1.getText().toString();
                String strNumber2 = editText2.getText().toString();
                int number1 = Integer.parseInt(strNumber1);
                int number2 = Integer.parseInt(strNumber2);
                String operation = spinner.getSelectedItem().toString();
                int result = 0;

                switch (operation) {
                    case "+":
                        result = number1 + number2;
                        break;
                    case "-":
                        result = number1 - number2;
                        break;
                    case "*":
                        result = number1 * number2;
                        break;
                    case "/":
                        result = number1 / number2;
                        break;
                }
                Intent intent = new Intent(MainActivity.this, resultat.class);
                intent.putExtra("result", result);
                startActivity(intent);

                CalculatorDatabaseHelper dbHelper = new CalculatorDatabaseHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                dbHelper.addCalculation(number1, number2, operation, result);
            }
        });
    }
}
