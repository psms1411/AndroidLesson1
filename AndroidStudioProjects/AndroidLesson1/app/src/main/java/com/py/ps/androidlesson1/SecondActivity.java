package com.py.ps.androidlesson1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by pooja.sharma on 02/01/18.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        Intent intent = getIntent();

        String enteredMsg = intent.getStringExtra(MainActivity.EXTRA_MSG);
        TextView textView = findViewById(R.id.textView); //WHy can't direct search by ID?
        textView.setText(enteredMsg);

    }

}
