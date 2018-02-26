package com.py.ps.androidlesson1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "com.py.ps.androidlesson1.Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);

    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        EditText tview = findViewById(R.id.editText);

        intent.putExtra(EXTRA_MSG, tview.getText().toString());
        startActivity(intent);

    }


    public void startService(View view) {
        startService(new Intent(this, MyService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(this, MyService.class));
    }

    public void broadcastMsg(View view) {
        Intent intent = new Intent();
        intent.setAction("com.py.ps.androidlesson1.CUSTOM_INTENT");
        sendBroadcast(intent);
    }


    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(MyContentProvider.NAME,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        values.put(MyContentProvider.GRADE,
                ((EditText)findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(
                MyContentProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.py.ps.androidlession1.MyContentProvider";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        String longList ="";
        if (c.moveToFirst()) {
            do{
                longList += c.getString(c.getColumnIndex(MyContentProvider._ID)) +
                        ", " +  c.getString(c.getColumnIndex( MyContentProvider.NAME)) +
                        ", " + c.getString(c.getColumnIndex( MyContentProvider.GRADE)) + " ";

               /* Toast.makeText(this,
                        c.getString(c.getColumnIndex(MyContentProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( MyContentProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( MyContentProvider.GRADE)),
                        Toast.LENGTH_LONG).show();*/
            } while (c.moveToNext());
        }

        Toast.makeText(this, longList, Toast.LENGTH_LONG).show();
    }
}
