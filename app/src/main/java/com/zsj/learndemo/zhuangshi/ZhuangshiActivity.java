package com.zsj.learndemo.zhuangshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zsj.learndemo.R;

public class ZhuangshiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuangshi);

        Person person = new Boy();
        PersonCloth chothCheap = new CheapCloth(person);
        chothCheap.dressed();

        PersonCloth clothExpensive = new ExpensiveCloth(person);
        clothExpensive.dressed();


    }
}
