package com.example.calculatorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

public class CalculatorDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "calculator";
    private static final int DATABASE_VERSION = 4;
    static final String TABLE_CALCULATIONS = "calculations";
    private static final String COLUMN_ID = "_id";
    static final String COLUMN_NUMBER_1 = "number_1";
    static final String COLUMN_NUMBER_2 = "number_2";
    static final String COLUMN_OPERATOR = "operator";
    static final String COLUMN_RESULT = "result";

    private Context mContext;

    public CalculatorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_CALCULATIONS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUMBER_1 + " REAL, " +
                COLUMN_NUMBER_2 + " REAL, " +
                COLUMN_OPERATOR + " TEXT, " +
                COLUMN_RESULT + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database schema here
    }

    public void addCalculation(int number1, int number2, String operation, int result) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER_1, number1);
        values.put(COLUMN_NUMBER_2, number2);
        values.put(COLUMN_OPERATOR, operation);
        values.put(COLUMN_RESULT, result);
        long newRowId = db.insert(TABLE_CALCULATIONS, null, values);
        if (newRowId == -1) {
            Toast.makeText(mContext, "Failed!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Succeeded!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}