package com.zsj.learndemo.snap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zsj.learndemo.A;
import com.zsj.learndemo.R;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class SnapActivity extends AppCompatActivity {

    private List<String> a = new ArrayList<>();
    private RecyclerView recyclerView;
    private SnapAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap);

        initData();

        adapter = new SnapAdapter(a);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        Log.d("zsj", "onCreate:A.AAA = "+ A.AAA);
    }

    public void initData() {
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
        a.add("222");
    }
}
