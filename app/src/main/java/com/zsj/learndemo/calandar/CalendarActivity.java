package com.zsj.learndemo.calandar;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zsj.learndemo.R;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import io.reactivex.functions.Consumer;

public class CalendarActivity extends AppCompatActivity {

    private String TAG = "zsj";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        new RxPermissions(this)
                .request(Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR)
                .subscribe(grander->{
                    Log.d("zsj", "onCreate: "+grander);
                });

        String[] ss = TimeZone.getAvailableIDs();

        String sss = TimeZone.getDefault().getID();
        Log.d(TAG, "onCreate: "+sss);
//        for (String s : ss){
//            Log.d(TAG, "onCreate: "+s);
//        }
    }

    public void onAddAction(View view){
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2019, 7, 8, 10, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2019, 7, 8, 11, 30);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, "Yoga")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
        startActivity(intent);
    }

    public void onAddActio1(View view){
        CalendarReminderUtils.addCalendarEvent(this,"ss","xx",System.currentTimeMillis(),1);
//        CalendarReminderUtils.updateCalendarEvent(this,"ss");
    }

}
