package com.example.lw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        Bundle arguments = getIntent().getExtras();
        String tel = arguments.get("tel").toString();

        EditText callET = findViewById(R.id.smsPhoneET);
        callET.setText(tel);
    }

    public void call(View view){
        EditText smsET = findViewById(R.id.smsPhoneET);
        String tel = smsET.getText().toString();

        String toDial="tel:"+tel;
        Intent call = new Intent(Intent.ACTION_CALL, Uri.parse(toDial));
        startActivity(call);
    }
    public void sms(View view){
        EditText callET = findViewById(R.id.smsPhoneET);
        String tel = callET.getText().toString();
        EditText smsET = findViewById(R.id.smsText);
        String text = smsET.getText().toString();

        String toSms="smsto:"+tel;
        String messageText= text;
        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));

        sms.putExtra("sms_body", messageText);
        startActivity(sms);
    }
}