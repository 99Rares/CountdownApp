package com.gabriel.dan.rares.countdown;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, GestureDetector.OnGestureListener {
    EditText date, name;
    TextView T1, T2, T3, T4;
    CountdownView countdownView;
    CountdownView countdownView2;
    CountdownView countdownView3;
    CountdownView countdownView4;
    Button[] buttons = new Button[5];
    Button[] buttons2 = new Button[5];
    TextView[] space = new TextView[5];
    Date futureDate;
    long dif, FutureDate1, FutureDate2, FutureDate3, FutureDate4;
    String S1, S2, S3, S4, Name, Name2, Name3, Name4;
    int k = 0, i = 0;
    LinearLayout parent;
    private GestureDetector gestureDetector;
    Date currentDate = new Date();
    long currentTime = currentDate.getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdownView = findViewById(R.id.countview);
        countdownView2 = findViewById(R.id.countview2);
        countdownView3 = findViewById(R.id.countview3);
        countdownView4 = findViewById(R.id.countview4);
        gestureDetector = new GestureDetector(this);
        String[] bt_name = {"Start Countdown 1", "Start Countdown 2", "Start Countdown 3", "Start Countdown 4"};
        String[] bt_del_name = {"Reset Countdown 1", "Reset Countdown 2", "Reset Countdown 3", "Reset Countdown 4"};
        T1 = findViewById(R.id.home);
        T2 = findViewById(R.id.home2);
        T3 = findViewById(R.id.home3);
        T4 = findViewById(R.id.home4);
        parent = findViewById(R.id.parentLayout);

        date = findViewById(R.id.editText);
        name = findViewById(R.id.editText2);
        //countdownView.setVisibility(View.GONE);
        while (k < 4) {
            buttons[k] = new Button(MainActivity.this);
            buttons[k].setId(k + 1);
            buttons[k].setTag(k + 1);
            buttons[k].setText(bt_name[k]);
            buttons[k].setOnClickListener(MainActivity.this);
            buttons[k].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            buttons[k].setTextColor(getResources().getColor(R.color.colorText));
            parent.addView(buttons[k]);
            buttons[k].setVisibility(View.GONE);

            space[k] = new TextView(MainActivity.this);
            space[k].setText("\n");
            space[k].setTextSize(8);
            parent.addView(space[k]);
            space[k].setVisibility(View.GONE);

            buttons2[k] = new Button(MainActivity.this);
            buttons2[k].setId(k + 11);
            buttons2[k].setTag(k + 11);
            buttons2[k].setText(bt_del_name[k]);
            buttons2[k].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            buttons2[k].setTextColor(getResources().getColor(R.color.colorText));
            buttons2[k].setOnClickListener(MainActivity.this);
            parent.addView(buttons2[k]);
            buttons2[k].setVisibility(View.GONE);

            k++;
        }
        buttons[0].setVisibility(View.VISIBLE);
        space[0].setVisibility(View.VISIBLE);
        buttons2[0].setVisibility(View.VISIBLE);
        Button button = findViewById(R.id.buttonDel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FutureDate1=FutureDate2=FutureDate3=FutureDate4=1;
                Name=Name2=Name3=Name4 = "";
                T4.setText(Name4);
                T1.setText(Name4);
                T2.setText(Name4);
                T3.setText(Name4);
                countdownView4.start(1);
                countdownView.start(1);
                countdownView2.start(1);
                countdownView3.start(1);
                Toast.makeText(MainActivity.this, "Reset all", Toast.LENGTH_LONG).show();
            }
        });
        onStart();
        T1.setText(Name);
        countdownView.start(FutureDate1 - currentTime);
        T2.setText(Name2);
        countdownView2.start(FutureDate2 - currentTime);
        T3.setText(Name3);
        countdownView3.start(FutureDate3 - currentTime);
        T4.setText(Name4);
        countdownView4.start(FutureDate4 - currentTime);
        Toast.makeText(this, "Swipe LEFT or RIGHT to select the countdown.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        String srt = v.getTag().toString();
        switch (srt) {
            case "1":
                Toast.makeText(this, "Start Countdown 1", Toast.LENGTH_SHORT).show();
                currentDate = new Date(); currentTime = currentDate.getTime();
                Name= name.getText().toString(); S1 = date.getText().toString();
                FutureDate1 = init(S1);
                start(FutureDate1,countdownView,Name,T1);
                break;
            case "2":
                Toast.makeText(this, "Start Countdown 2", Toast.LENGTH_SHORT).show();
                currentDate = new Date(); currentTime = currentDate.getTime();
                Name2 = name.getText().toString(); S2 = date.getText().toString();
                FutureDate2 = init(S2);
                start(FutureDate2,countdownView2,Name2,T2);
                break;
            case "3":
                Toast.makeText(this, "Start Countdown 3", Toast.LENGTH_SHORT).show();
                currentDate = new Date(); currentTime = currentDate.getTime();
                Name3 = name.getText().toString(); S3 = date.getText().toString();
                FutureDate3 = init(S3);
                start(FutureDate3,countdownView3,Name3,T3);
                break;
            case "4":
                Toast.makeText(this, "Start Countdown 4", Toast.LENGTH_SHORT).show();
                currentDate = new Date(); currentTime = currentDate.getTime();
                Name4 = name.getText().toString(); S4 = date.getText().toString();
                FutureDate4 = init(S4);
                start(FutureDate4,countdownView4,Name4,T4);
                break;
            case "11":
                Toast.makeText(this, "Reset Countdown 1", Toast.LENGTH_SHORT).show();
                FutureDate1=1; Name="";
                start(1,countdownView,"",T1);
                break;
            case "12":
                Toast.makeText(this, "Reset Countdown 2", Toast.LENGTH_SHORT).show();
                FutureDate2=1; Name2="";
                start(1,countdownView2,"",T2);
                break;
            case "13":
                Toast.makeText(this, "Reset Countdown 3", Toast.LENGTH_SHORT).show();
                FutureDate3=1; Name3="";
                start(1,countdownView3,"",T3);
                break;
            case "14":
                Toast.makeText(this, "Reset Countdown 4", Toast.LENGTH_SHORT).show();
                FutureDate4=1; Name4="";
                start(1,countdownView4,"",T4);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences Difference = getSharedPreferences("Difference", MODE_PRIVATE);
        SharedPreferences.Editor editor = Difference.edit();

        editor.putLong("Date", FutureDate1);
        editor.putLong("Date2", FutureDate2);
        editor.putLong("Date3", FutureDate3);
        editor.putLong("Date4", FutureDate4);
        editor.putString("Name", Name);
        editor.putString("Name2", Name2);
        editor.putString("Name3", Name3);
        editor.putString("Name4", Name4);
        editor.apply();

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences Difference = getSharedPreferences("Difference", MODE_PRIVATE);
        FutureDate1 = Difference.getLong("Date", 1);
        FutureDate2 = Difference.getLong("Date2", 1);
        FutureDate3 = Difference.getLong("Date3", 1);
        FutureDate4 = Difference.getLong("Date4", 1);
        Name = Difference.getString("Name", "");
        Name2 = Difference.getString("Name2", "");
        Name3 = Difference.getString("Name3", "");
        Name4 = Difference.getString("Name4", "");
    }

    long init(String S) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        try {
            futureDate = dateFormat.parse(S);
            return futureDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    void start(long FutureDate,CountdownView countdownView,String Name,TextView T){
        currentDate = new Date();
        currentTime = currentDate.getTime();
        dif = FutureDate - currentTime;
        if (dif < 0) dif = 1;
        T.setText(Name);
        countdownView.setVisibility(View.GONE);
        countdownView.setVisibility(View.VISIBLE);
        countdownView.start(dif);

    }



    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            //right or left swipe
            if (Math.abs(diffX) > 100 && Math.abs(velocityX) > 100) {
                if (diffX > 0) { onSwipeRight();
                } else {
                    onSwipeLeft(); }
                result = true;
            } }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void onSwipeRight() {
        Toast.makeText(this, "swipe right", Toast.LENGTH_SHORT).show();

        if (i < 4 && i > 0) {
            buttons[i - 1].setVisibility(View.VISIBLE);
            buttons[i].setVisibility(View.GONE);
            space[i - 1].setVisibility(View.VISIBLE);
            space[i].setVisibility(View.GONE);
            buttons2[i - 1].setVisibility(View.VISIBLE);
            buttons2[i].setVisibility(View.GONE);
            i--;
        } else {
            buttons[i].setVisibility(View.GONE);
            space[i].setVisibility(View.GONE);
            buttons2[i].setVisibility(View.GONE);
            i = 3;
            buttons[i].setVisibility(View.VISIBLE);
            space[i].setVisibility(View.VISIBLE);
            buttons2[i].setVisibility(View.VISIBLE);

        }
    }

    private void onSwipeLeft() {
        Toast.makeText(this, "swipe left", Toast.LENGTH_SHORT).show();
        if (i < 3 && i > -1) {
            buttons[i + 1].setVisibility(View.VISIBLE);
            buttons[i].setVisibility(View.GONE);
            space[i + 1].setVisibility(View.VISIBLE);
            space[i].setVisibility(View.GONE);
            buttons2[i + 1].setVisibility(View.VISIBLE);
            buttons2[i].setVisibility(View.GONE);
            i++;
        } else {
            buttons[i].setVisibility(View.GONE);
            space[i].setVisibility(View.GONE);
            buttons2[i].setVisibility(View.GONE);
            i = 0;
            buttons[i].setVisibility(View.VISIBLE);
            space[i].setVisibility(View.VISIBLE);
            buttons2[i].setVisibility(View.VISIBLE);
        }
    }
}