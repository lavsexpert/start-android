package ru.startandroid.p0152contextmenuxml;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvColor, tvSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvColor = (TextView) findViewById(R.id.tvColor);
        tvSize = (TextView) findViewById(R.id.tvSize);
        // для tvColor и tvSize необходимо создавать контекстное меню
        registerForContextMenu(tvColor);
        registerForContextMenu(tvSize);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()) {
            case R.id.tvColor:
                getMenuInflater().inflate(R.menu.mymenucolor, menu);
                break;
            case R.id.tvSize:
                getMenuInflater().inflate(R.menu.mymenusize, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getOrder()) {
            // пункты меню для tvColor
            case 1:
                tvColor.setTextColor(Color.RED);
                tvColor.setText("Text color = red");
                break;
            case 2:
                tvColor.setTextColor(Color.GREEN);
                tvColor.setText("Text color = green");
                break;
            case 3:
                tvColor.setTextColor(Color.BLUE);
                tvColor.setText("Text color = blue");
                break;
            // пункты меню для tvSize
            case 4:
                tvSize.setTextSize(22);
                tvSize.setText("Text size = 22");
                break;
            case 5:
                tvSize.setTextSize(26);
                tvSize.setText("Text size = 26");
                break;
            case 6:
                tvSize.setTextSize(30);
                tvSize.setText("Text size = 30");
                break;
        }
        return super.onContextItemSelected(item);
    }

}
