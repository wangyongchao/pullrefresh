package com.handmark.pulltorefresh.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn1) {
            startActivity(new Intent(this, PullToRefreshListActivity.class));

        } else if (view.getId() == R.id.btn2) {
            startActivity(new Intent(this, PullToRefreshScrollViewActivity.class));
        } else if (view.getId() == R.id.btn3) {
            startActivity(new Intent(this, RecyclerViewActivity.class));
        }

    }
}
