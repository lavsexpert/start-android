package ru.startandroid.p0571gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

    GridView gvMain;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, data);
        gvMain = (GridView) findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        adjustGridView();
    }

    private void adjustGridView() {
        //gvMain.setNumColumns(3); // 3 столбца
        //gvMain.setNumColumns(GridView.AUTO_FIT); // автоматическое разбиение на столбцы
        /* Автоматическое разбиение по указанной ширине столбцов
        gvMain.setNumColumns(GridView.AUTO_FIT);
        gvMain.setColumnWidth(50);
        */
        /* Горизонтальный и вертикальный отступ между ячейками
        gvMain.setNumColumns(GridView.AUTO_FIT);
        gvMain.setColumnWidth(180);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
        */
        gvMain.setNumColumns(GridView.AUTO_FIT);
        gvMain.setColumnWidth(180);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
        //gvMain.setStretchMode(GridView.NO_STRETCH);
        //gvMain.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        //gvMain.setStretchMode(GridView.STRETCH_SPACING);
        gvMain.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
    }
}
