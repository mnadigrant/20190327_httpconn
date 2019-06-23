package com.example.a20190327_httpconn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskRecyclerViewAdapter.OnTicketListener {

    private Button clickbtn;
    private RecyclerView myTaskrecyclerview;
    private List<Tasks> mtaskdata = Tasks.getAllTasks();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickbtn = (Button) findViewById(R.id.fetchbtn);

        clickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new fetchData(MainActivity.this).execute();
            }
        });



        myTaskrecyclerview = findViewById(R.id.task_recycler_view);

        TaskRecyclerViewAdapter taskRecyclerViewAdapter = new TaskRecyclerViewAdapter(getApplicationContext(),mtaskdata,this);
        myTaskrecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myTaskrecyclerview.setAdapter(taskRecyclerViewAdapter);


    }


    @Override
    public void onTicketClick(int position) {
        Toast.makeText(getApplicationContext(),"position" + position,Toast.LENGTH_SHORT).show();
    }

}
