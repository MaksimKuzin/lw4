package com.example.lw4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Objects;

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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fioTV = findViewById(R.id.fioTV);

        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        EditText idET = findViewById(R.id.idText);
        EditText fioET = findViewById(R.id.FioText);
        EditText expET = findViewById(R.id.ExperienceText);
        EditText stackET = findViewById(R.id.PhoneText);
        EditText ageET = findViewById(R.id.AgeText);
        idET.setText(String.valueOf(settings.getInt(PREF_ID, 0)));
        fioET.setText(settings.getString(PREF_FIO, ""));
        expET.setText(String.valueOf(settings.getFloat(PREF_EXPERIENCE, 0)));
        stackET.setText(settings.getString(PREF_STACK, ""));
        ageET.setText(String.valueOf(settings.getInt(PREF_AGE, 0)));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(nameVariableKey, tester.FIO);
        super.onSaveInstanceState(outState);
    }

    // получение ранее сохраненного состояния
    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tester.FIO = savedInstanceState.getString(nameVariableKey);
        fioTV.setText("ФИО: " + tester.FIO);
    }

    @SuppressLint("SetTextI18n")
    public void SaveInstance(View view) {
        EditText id = findViewById(R.id.idText);
        EditText fio = findViewById(R.id.FioText);
        EditText experience = findViewById(R.id.ExperienceText);
        EditText stack = findViewById(R.id.PhoneText);
        EditText age = findViewById(R.id.AgeText);
        tester.Id = Integer.parseInt(id.getText().toString());
        tester.FIO = fio.getText().toString();
        tester.Experience = Float.parseFloat(experience.getText().toString());
        tester.Phone = stack.getText().toString();
        tester.Age = Integer.parseInt(age.getText().toString());
        TextView idTV = findViewById(R.id.idTV);
        fioTV = findViewById(R.id.fioTV);
        TextView experienceTV = findViewById(R.id.experienceTV);
        TextView stackTV = findViewById(R.id.stackTV);
        TextView ageTV = findViewById(R.id.ageTV);
        idTV.setText("Id: " + tester.Id);
        fioTV.setText("ФИО: " + tester.FIO);
        experienceTV.setText("Стаж: " + tester.Experience);
        stackTV.setText("Стэк: " + tester.Phone);
        ageTV.setText("Возраст: " + tester.Age);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putInt(PREF_ID, tester.Id);
        prefEditor.putString(PREF_FIO, tester.FIO);
        prefEditor.putFloat(PREF_EXPERIENCE, tester.Experience);
        prefEditor.putString(PREF_STACK, tester.Phone);
        prefEditor.putInt(PREF_AGE, tester.Age);
        prefEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.open_activity) {
            try {
                if (!Objects.isNull(tester.Phone)) {
                    Intent intent = new Intent(this, SmsActivity.class);
                    intent.putExtra("tel", tester.Phone);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Сначала сохраните номер", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Сначала сохраните номер", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}