package ru.startandroid.p0361sqlitequery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    // 3 массива, в которых хранятся: Название страны, население страны, регион страны
    String name[] = { "Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада" };
    int people[] = { 1400, 311, 195, 142, 128, 82, 80, 60, 66, 35 };
    String region[] = { "Азия", "Америка", "Америка", "Европа", "Азия",
            "Европа", "Африка", "Европа", "Европа", "Америка" };

    // Экранные элемены
    Button btnAll, btnFunc, btnPeople, btnSort, btnGroup, btnHaving;
    EditText etFunc, etPeople, etRegionPeople;
    RadioGroup rgSort;

    // Объект для управления базой данных и сама база
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Подключаем обработчики к экранным элементам и определяем их
        btnAll = findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);
        btnFunc = findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(this);
        btnPeople = findViewById(R.id.btnPeople);
        btnPeople.setOnClickListener(this);
        btnSort = findViewById(R.id.btnSort);
        btnSort.setOnClickListener(this);
        btnGroup = findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);
        btnHaving = findViewById(R.id.btnHaving);
        btnHaving.setOnClickListener(this);
        etFunc = findViewById(R.id.etFunc);
        etPeople = findViewById(R.id.etPeople);
        etRegionPeople = findViewById(R.id.etRegionPeople);
        rgSort = findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this);

        // Подключаемся к базе
        db = dbHelper.getWritableDatabase();

        // Проверяем существование записей
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            // заполним таблицу
            for (int i = 0; i < 10; i++) {
                cv.put("name", name[i]);
                cv.put("people", people[i]);
                cv.put("region", region[i]);
                Log.d(LOG_TAG, "id = " + db.insert("mytable", null, cv));
            }
        }
        c.close();
        dbHelper.close();

        // Эмулируем нажатие кнопки btnAll
        onClick(btnAll);
    }

    public void onClick(View v) {

        // Подключаемся к базе
        db = dbHelper.getWritableDatabase();

        // Читаем данные с экрана
        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionPeople = etRegionPeople.getText().toString();

        // Описываем переменные для query и курсор
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor c = null;

        // Определяем нажатую кнопку
        switch (v.getId()) {

            // Все записи - вывод всех записей.
            case R.id.btnAll:
                Log.d(LOG_TAG, "--- Все записи ---");
                c = db.query("mytable", null, null, null, null, null, null);
                break;

            // Функция - вывод значения агрегатной функции (или любого поля).
            case R.id.btnFunc:
                Log.d(LOG_TAG, "--- Функция " + sFunc + " ---");
                columns = new String[] { sFunc };
                c = db.query("mytable", columns, null, null, null, null, null);
                break;

            // Население - вывод стран с населением больше введенного на экране количества.
            case R.id.btnPeople:
                Log.d(LOG_TAG, "--- Население больше " + sPeople + " ---");
                selection = "people > ?";
                selectionArgs = new String[] { sPeople };
                c = db.query("mytable", null, selection, selectionArgs, null, null,
                        null);
                break;

            // Население по региону - группировка стран по регионам и вывод общее количество населения.
            case R.id.btnGroup:
                Log.d(LOG_TAG, "--- Население по региону ---");
                columns = new String[] { "region", "sum(people) as people" };
                groupBy = "region";
                c = db.query("mytable", columns, null, null, groupBy, null, null);
                break;

            // Население по региону больше чем - вывод регионов с населением больше указанного числа.
            case R.id.btnHaving:
                Log.d(LOG_TAG, "--- Регионы с населением больше " + sRegionPeople
                        + " ---");
                columns = new String[] { "region", "sum(people) as people" };
                groupBy = "region";
                having = "sum(people) > " + sRegionPeople;
                c = db.query("mytable", columns, null, null, groupBy, having, null);
                break;

            // Сортировка
            case R.id.btnSort:
                // сортировка по
                switch (rgSort.getCheckedRadioButtonId()) {
                    // наименование
                    case R.id.rName:
                        Log.d(LOG_TAG, "--- Сортировка по наименованию ---");
                        orderBy = "name";
                        break;
                    // население
                    case R.id.rPeople:
                        Log.d(LOG_TAG, "--- Сортировка по населению ---");
                        orderBy = "people";
                        break;
                    // регион
                    case R.id.rRegion:
                        Log.d(LOG_TAG, "--- Сортировка по региону ---");
                        orderBy = "region";
                        break;
                }
                c = db.query("mytable", null, null, null, null, null, orderBy);
                break;
        }

        // Далее мы проверяем, что он существует и в нем есть записи (moveToFirst).
        // Если все ок, то мы запускаем перебор записей в цикле do … while (c.moveToNext()).
        // Для каждой записи перебираем названия полей (getColumnNames),
        // получаем по каждому полю его номер и извлекаем данные методом getString.
        // Формируем список полей и значений в переменную str, которую потом выводим в лог.
        // После всего этого закрываем соединение.
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);

                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");

        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement," + "name text,"
                    + "people integer," + "region text" + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
