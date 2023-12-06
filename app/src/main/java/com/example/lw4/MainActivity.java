package com.example.lw4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {
    Tester tester = new Tester();
    TextView fioTV;
    private static final String PREFS_FILE = "Account";
    private static final String PREF_ID = "Id";
    private static final String PREF_FIO = "Fio";
    private static final String PREF_EXPERIENCE = "Experience";
    private static final String PREF_STACK = "Stack";
    private static final String PREF_AGE = "Age";
    SharedPreferences settings;
    final static String nameVariableKey = "NAME_VARIABLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView idTV = findViewById(R.id.idTV);
        fioTV = findViewById(R.id.fioTV);
        TextView experienceTV = findViewById(R.id.experienceTV);
        TextView stackTV = findViewById(R.id.stackTV);
        TextView ageTV = findViewById(R.id.ageTV);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        idTV.setText("Id: " + settings.getString(PREF_ID,""));
        fioTV.setText("ФИО: " + settings.getString(PREF_FIO,""));
        experienceTV.setText("Стаж: " + settings.getString(PREF_EXPERIENCE,""));
        stackTV.setText("Стэк: " + settings.getString(PREF_STACK,""));
        ageTV.setText("Возраст: " + settings.getString(PREF_AGE,""));
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(nameVariableKey, tester.FIO);
        super.onSaveInstanceState(outState);
    }
    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tester.FIO = savedInstanceState.getString(nameVariableKey);
        fioTV.setText("ФИО: " + tester.FIO);
    }
    public void SaveInstance(View view) {
        EditText id = findViewById(R.id.idText);
        EditText fio = findViewById(R.id.FioText);
        EditText experience = findViewById(R.id.ExperienceText);
        EditText stack = findViewById(R.id.StackText);
        EditText age = findViewById(R.id.AgeText);
        tester.Id = Integer.parseInt(id.getText().toString());
        tester.FIO = fio.getText().toString();
        tester.Experience = Float.valueOf(experience.getText().toString());
        tester.Stack = stack.getText().toString();
        tester.Age = Integer.parseInt(age.getText().toString());
        TextView idTV = findViewById(R.id.idTV);
        fioTV = findViewById(R.id.fioTV);
        TextView experienceTV = findViewById(R.id.experienceTV);
        TextView stackTV = findViewById(R.id.stackTV);
        TextView ageTV = findViewById(R.id.ageTV);
        idTV.setText("Id: " + tester.Id);
        fioTV.setText("ФИО: " + tester.FIO);
        experienceTV.setText("Стаж: " + Float.toString(tester.Experience));
        stackTV.setText("Стэк: " + tester.Stack);
        ageTV.setText("Возраст: " + tester.Age);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString(PREF_ID, Integer.toString(tester.Id));
        prefEditor.putString(PREF_FIO, tester.FIO);
        prefEditor.putString(PREF_EXPERIENCE, Float.toString(tester.Experience));
        prefEditor.putString(PREF_STACK, tester.Stack);
        prefEditor.putString(PREF_AGE, Integer.toString(tester.Age));
        prefEditor.apply();
    }
}