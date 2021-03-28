package com.example.alarm;


import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alarm.adapter.MyAdapter;
import com.example.alarm.model.InformationAlarm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Initialize Variable



    RecyclerView recyclerView;
    MyAdapter adapter;

    String[] Time , medicine_name, When;

    ArrayList<InformationAlarm>  mlist = new ArrayList<>();

    TextView textView_medicinename,textView_when,textView_time;
    ImageView imageView_weathermoon,imageView_alarmdlt,imageView_weathersunny;
    TextView textView_alarm;
    Button button_add;

    int t1Hour,t1Minute;
    Ringtone r;
    TextClock textClock;
  //  Timer t = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = findViewById(R.id.Recycle1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        r = RingtoneManager.getRingtone(MainActivity.this,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));


        intFunction();
        intLisener();
        intSpinnerinfo();
        intMiddleStringChecker();

    }


    private void intMiddleStringChecker() {

        String stringChecker;
        stringChecker = textView_alarm.getText().toString();
        String match_am = "am";
     int position_am = stringChecker.indexOf(match_am);

     if(position_am >-1){
         imageView_weathermoon.setBackgroundDrawable(getResources().getDrawable(R.drawable.sunny));

     }
     else {
         imageView_weathermoon.setBackgroundDrawable(getResources().getDrawable(R.drawable.moon));

     }

     //    String match_pm = "pm";

//        int position_pm = stringChecker.indexOf(match_pm);

    // Log.d("intMiddleStringChecker", "intMiddleStringChecker: "+position_am + " "+position_pm);


    }


    //All Spinner array list
    private void intSpinnerinfo() {      //start array spinner


        // Spinner select medicine name


       medicine_name = new String[]{"Select Medicine", "ABACAVIR", "B12", "Bacillus of Calmette and Guer", "Daclizumab"};
        Time = new String[]{"Time", "before meal", "after meal"};
        When = new String[]{"When", "5 mins", "10 mins", "15 mins", "20 mins", "25 mins", "30mins", "60 mins"};

        textView_medicinename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), textView_medicinename);

                for (String s : medicine_name) {

                    popupMenu.getMenu().add(Menu.NONE, 0, Menu.NONE, s);
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        textView_medicinename.setText(item.getTitle().toString());


                        return false;
                    }
                });
                popupMenu.show();

            }
        });



        //when



        textView_when.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), textView_when);

                for (String s : When) {

                    popupMenu.getMenu().add(Menu.NONE, 0, Menu.NONE, s);
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        textView_when.setText(item.getTitle().toString());


                        return false;
                    }
                });
                popupMenu.show();

            }
        });

        //Time




        textView_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), textView_time);

                for (String s : Time) {

                    popupMenu.getMenu().add(Menu.NONE, 0, Menu.NONE, s);
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        textView_time.setText(item.getTitle().toString());


                        return false;
                    }
                });
                popupMenu.show();

            }
        });



    }

    private void intLisener() {
        button_add.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {


              String  P ,Q,R;

              P = textView_medicinename.getText().toString().trim();
              Q = textView_when.getText().toString().trim();
              R = textView_alarm.getText().toString().trim();


              Log.d("test", "onClick:pari na "+P);
              Q=textView_when.getText().toString();
              Log.d("test", "onClick: hoy na "+Q);
              R = textView_time.getText().toString();
              Log.d("test", "onClick: hobe na "+R);

                if ( P.trim().equals(medicine_name[0]))
                {
                    Toast.makeText(MainActivity.this, "please select medicine", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {


                }

                if ( Q.trim().equals(When[0]))
                {
                    Toast.makeText(MainActivity.this, "please select time duration ", Toast.LENGTH_SHORT).show();
                   return;
                }
                else
                {

                }

                if (R.trim().equals(Time[0]))
                {
                   Toast.makeText(MainActivity.this, "please select time ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {


                    mlist.add(new InformationAlarm(P,Q,R));
                    adapter = new MyAdapter(mlist,MainActivity.this);
                    recyclerView.setAdapter(adapter);

                    //When add button then 0 posiotionst
                    textView_medicinename.setText(medicine_name[0]);
                    textView_when.setText(When[0]);
                    textView_time.setText(Time[0]);




                }
            }});



        // 09.00 am alarm click
        textView_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Initialize time picker

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        // Initialize hour and minute

                        t1Hour = hourOfDay;
                        t1Minute = minute;

                        //Initialize Calender
                        Calendar calendar = Calendar.getInstance();

                        //set hour and minute

                        calendar.set(0,0,0,t1Hour,t1Minute);

                        // set selected time on text view

                        textView_alarm.setText(DateFormat.format("hh:mm aa",calendar));



                                // Ringtone

                                if (textView_alarm.getText().toString().trim().equals((textClock)))

                                {

                                    r.play();
                                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();

                                }else
                                {
                                    r.stop();
                                    Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                                }



                        intMiddleStringChecker();




                    }
                },12,0,false);

                 // Display previous selected time

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1Hour,t1Minute);

             timePickerDialog.show();

            }
        });




        }


    private void intFunction() {


        imageView_weathermoon =findViewById(R.id.weathermoon_id);
        imageView_alarmdlt = findViewById(R.id.alamrdlt_id);
        button_add = findViewById(R.id.addbuttom_id);
        textView_alarm = findViewById(R.id.tv_timer1);


        textView_medicinename = findViewById(R.id.spinnerid1);
        textView_when=findViewById(R.id.spinnerid2);
        textView_time =findViewById(R.id.spinnerid3);


    }
}