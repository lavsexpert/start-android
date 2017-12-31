package ru.startandroid.androidlessons3lists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    Intent launchIntent;
    ListView lvMain;
    String[] modules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Подключаем вызов модулей через список
        lvMain = (ListView) findViewById(R.id.lvMain);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.modules, android.R.layout.simple_list_item_1);
        lvMain.setAdapter(adapter);

        modules = getResources().getStringArray(R.array.modules);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(parent.getContext(), "Модуль: ru.startandroid." + modules[position], Toast.LENGTH_LONG).show();
                launchIntent = getPackageManager().getLaunchIntentForPackage("ru.startandroid." + modules[position]);
                if (launchIntent != null) {
                    startActivity(launchIntent); //null pointer check in case package name was not found
                }
            }
        });
    }
}
