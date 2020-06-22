package com.samoylenko.homework711;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    final static String MORNING = "morning";
    final static String AFTERNOON = "afternoon";
    final static String EVENING = "evening";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button_sync);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("H", new Locale("ru", "RU"));
                String text = dateFormat.format(date);
                Integer nowHour = Integer.parseInt(text);

                Intent intent = new Intent(Intent.ACTION_SYNC);
                String days = getTimeOfDayNow(nowHour);
                intent.setData(Uri.parse("http://" + days));
                startActivity(intent);
            }
        });
    }

    public String getTimeOfDayNow(Integer Hour) {
        if (Hour >= 6 && Hour < 14) {
            return MORNING;
        } else if (Hour > 13 && Hour < 16) {
            return AFTERNOON;
        } else if ((Hour > 15 && Hour < 24) | (Hour >= 0 && Hour < 6)) {
            return EVENING;
        } else {
            return null;
        }
    }

}
