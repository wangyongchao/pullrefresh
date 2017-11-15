
package com.handmark.pulltorefresh.samples;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshNestedScrollView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity implements View.OnClickListener {
    private List<String> mData = new ArrayList<String>();

    private RecyclerView mRecyclerView;

    private MyAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;
    private PullToRefreshNestedScrollView mPullToRefreshScrollView;

    private RelativeLayout bgLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("RecyclerViewActivity onCreate");

        setContentView(R.layout.activity_recyclerview);

        bgLayout = findViewById(R.id.iv_sc_main_bg_layout);
//        bgLayout.scrollTo(0, 100);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mPullToRefreshScrollView = (PullToRefreshNestedScrollView) findViewById(R.id.pull_refresh_view);
        NestedScrollView nestedScrollView = mPullToRefreshScrollView.getRefreshableView();
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


            }
        });


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
//
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);// listview功能

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);


//        mRecyclerView.setLayoutManager(mLayoutManager);//gridview 功能
        // mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
        // StaggeredGridLayoutManager.HORIZONTAL));

        initData();

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(mData);
        System.out.println("RecyclerViewActivity oncreate after");

        findViewById(R.id.delete).setOnClickListener(this);

        nestedScrollView.smoothScrollTo(0, 0);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        System.out.println("attachBaseContext");
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("RecyclerViewActivity onresume");
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add("item" + i);
        }

    }

    private int distance = 0;

    @Override
    public void onClick(View v) {
        //正数可视区域向下，向右移动相当子view向上，向左移动。负数相反
        int scrollX = bgLayout.getScrollX();
        int scrollY = bgLayout.getScrollY();
        System.out.println("scrollX=" + scrollX + ",scrollY=" + scrollY);
//        testScrollto();

        testScrollby();
    }

    private void testScrollto() {
        distance = distance - 10;
        bgLayout.scrollTo(0, distance);//可视区域移动

    }

    private void testScrollby() {
        distance = distance + 10;
        bgLayout.scrollBy(0, 10);//可视区域移动

    }

}
