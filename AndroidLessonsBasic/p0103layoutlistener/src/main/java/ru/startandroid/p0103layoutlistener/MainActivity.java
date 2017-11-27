package ru.startandroid.p0103layoutlistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = (TextView) findViewById(R.id.tvOut);
    }

    public void onClickStart(View v) {
        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
            case R.id.btnOk:
                // кнопка ОК
                tvOut.setText("Нажата кнопка ОК");
                break;
            case R.id.btnCancel:
                // кнопка Cancel
                tvOut.setText("Нажата кнопка Cancel");
                break;
        }
    }

}
