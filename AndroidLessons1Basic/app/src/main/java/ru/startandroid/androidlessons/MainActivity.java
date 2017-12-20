package ru.startandroid.androidlessons;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    Intent launchIntent;

    // Через кнопки
    Button p0031firstproject;
    Button p0041basicviesws;
    Button p0051layoutfiles;
    Button p0061layouts;
    Button p0072layoutprop;
    Button p0081viewbyid;

    // Через лист
    ListView lvMain;
    String[] modules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Подключаем вызов модулей через кнопки
        p0031firstproject = (Button) findViewById(R.id.p0031firstproject);
        p0041basicviesws = (Button) findViewById(R.id.p0041basicviesws);
        p0051layoutfiles = (Button) findViewById(R.id.p0051layoutfiles);
        p0061layouts = (Button) findViewById(R.id.p0061layouts);
        p0072layoutprop = (Button) findViewById(R.id.p0072layoutprop);
        p0081viewbyid = (Button) findViewById(R.id.p0081viewbyid);

        p0031firstproject.setOnClickListener(this);
        p0041basicviesws.setOnClickListener(this);
        p0051layoutfiles.setOnClickListener(this);
        p0061layouts.setOnClickListener(this);
        p0072layoutprop.setOnClickListener(this);
        p0081viewbyid.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (view.getId()) {
            case R.id.p0031firstproject:
                Toast toast3 = Toast.makeText(getApplicationContext(),
                        "Урок 3. Создание AVD. Первое приложение. Структура Android-проекта.", Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastContainer = (LinearLayout) toast3.getView();
                ImageView catImageView = new ImageView(getApplicationContext());
                catImageView.setImageResource(R.drawable.toast_cat);
                toastContainer.addView(catImageView, 0);
                toast3.show();

                //Toast.makeText(this, "Урок 3. Создание AVD. Первое приложение. Структура Android-проекта.", Toast.LENGTH_LONG).show();
                launchIntent = getPackageManager().getLaunchIntentForPackage("ru.startandroid.p0031firstproject");
                break;
            case R.id.p0041basicviesws:
                Toast.makeText(this, "Урок 4. Компоненты экрана и их свойства", Toast.LENGTH_LONG).show();
                launchIntent = getPackageManager().getLaunchIntentForPackage("ru.startandroid.p0041basicviews");
                break;
            case R.id.p0051layoutfiles:
                Toast.makeText(this, "Урок 5. Layout-файл в Activity. XML представление. Смена ориентации экрана.", Toast.LENGTH_LONG).show();
                launchIntent = getPackageManager().getLaunchIntentForPackage("ru.startandroid.p0051layoutfiles");
                break;

            case R.id.p0061layouts:
                Toast.makeText(this, "Урок 6. Виды Layouts. Ключевые отличия и свойства.", Toast.LENGTH_LONG).show();
                launchIntent = getPackageManager().getLaunchIntentForPackage("ru.startandroid.p0061layouts");
                break;
            case R.id.p0072layoutprop:
                Toast.makeText(this, "Урок 7. Layout параметры для View-элементов.", Toast.LENGTH_LONG).show();
                launchIntent = getPackageManager().getLaunchIntentForPackage("ru.startandroid.p0072layoutprop");
                break;
            case R.id.p0081viewbyid:
                Toast.makeText(this, "Урок 8. Работаем с элементами экрана из кода", Toast.LENGTH_LONG).show();
                launchIntent = getPackageManager().getLaunchIntentForPackage("ru.startandroid.p0081viewbyid");
                break;
        }

        if (launchIntent != null) {
            startActivity(launchIntent); //null pointer check in case package name was not found
        }
    }
}
