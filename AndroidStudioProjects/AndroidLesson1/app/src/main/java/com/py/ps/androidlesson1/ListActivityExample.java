package com.py.ps.androidlesson1;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivityExample extends ListActivity {
    static final String[] ACTIVITY_CHOICE = new String[] {
            "Open Website Example",
            "Open Contacts",
            "Open Phone Dialer example",
            "Search google",
            "Start Voice Command"
    };

    final String searchTerm = "superman";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ACTIVITY_CHOICE));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setTextFilterEnabled(true);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.android.com/")));
                        finish();  /** Finish the current one and push the old one into the stack **/
                        break;
                    case 1:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/")));
                        break;
                    case 2:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:12125551212")));
                        break;
                    case 3:
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        startActivity(intent);
                        break;
                    case 4:
                        startActivity(new Intent(Intent.ACTION_VOICE_COMMAND));
                        break;
                    default: break;
                }

            }
        });
    }

}
