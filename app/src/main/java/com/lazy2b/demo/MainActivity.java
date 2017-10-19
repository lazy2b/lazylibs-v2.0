package com.lazy2b.demo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lazy2b.demo.adapter.MainLvAdapter;
import com.lazy2b.demo.model.MainLvItemModel;
import com.lazy2b.demo.model.RespAppLineModel;
import com.lazy2b.libs.app.AbsBaseActivity;
import com.lazy2b.libs.model.RespBaseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AbsBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @BindView(R.id.tv_log)
    TextView tv_log;

    @BindView(R.id.lv_list)
    PullToRefreshListView listView;

    @Override
    public void findView() {
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_log})
    void load(View view) {
        tv_log.setText("loadding...");
//        for (int i = 0; i < 100; i++) {
        get(action() + ".001", "app_line.js", RespAppLineModel.class);
//        }
    }

    @Override
    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        for (int i = 0; i < 30; i++) {
            modelList.add(new MainLvItemModel("title" + i, 0));
        }

        if (adapter == null) {
            adapter = new MainLvAdapter(mCxt, modelList, null);
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mCxt, OtherActivity.class);
                intent.putExtra("position", position - 1);
                startActivity(intent);
            }
        });
    }

    MainLvAdapter adapter = null;

    List<MainLvItemModel> modelList = new ArrayList<>();

    public static MainActivity inst;

    @Override
    public void initData() {
        inst = this;
        super.initData();
    }

    public void uState(int postion) {
        modelList.get(postion).state = 10086;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(RespBaseModel resp) {
        tv_log.setText(resp.toString());
//        KLog.d(resp.toString() + "onSuccess");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            load(null);
//            Call call = new Retrofit.Builder()
//                    .client(Http.inst())
//                    .baseUrl("http://byyapp.oss-cn-shenzhen.aliyuncs.com/")
//                    .addConverterFactory(FastJsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build().create(DemoHttpService.class).loadData();
//            call.enqueue(new BaseReqCallBack<RespAppLineModel>() {
//                @Override
//                public void onResponse(Call<RespAppLineModel> call, Response<RespAppLineModel> response) {
//                    if (response.body() == null) {
//                        onFailure(call, new Throwable("body为空"));
//                        return;
//                    }
//                    KLog.json(JSON.toJSONString(response.body()));
//                }
//
//                @Override
//                public void onFailure(Call<RespAppLineModel> call, Throwable t) {
//                    KLog.i(t.getMessage());
//                }
//            });
//            call.cancel();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
