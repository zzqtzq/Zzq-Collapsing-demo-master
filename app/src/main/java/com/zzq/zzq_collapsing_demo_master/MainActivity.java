package com.zzq.zzq_collapsing_demo_master;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zzq.zzq_collapsing_demo_master.adapter.MyHomeAdapter;
import com.zzq.zzq_collapsing_demo_master.base.RxBaseActivity;
import com.zzq.zzq_collapsing_demo_master.entity.Image;
import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;
import com.zzq.zzq_collapsing_demo_master.p.IHomePsersenter;
import com.zzq.zzq_collapsing_demo_master.utils.ACache;
import com.zzq.zzq_collapsing_demo_master.utils.LogUtils;
import com.zzq.zzq_collapsing_demo_master.utils.MyToast;
import com.zzq.zzq_collapsing_demo_master.v.AHomeContract;
import com.zzq.zzq_collapsing_demo_master.widget.refresh.RefreshRecyclerView;

import java.util.ArrayList;

public class MainActivity extends RxBaseActivity<IHomePsersenter> implements AHomeContract.View, SwipeRefreshLayout.OnRefreshListener, RefreshRecyclerView.OnLoadMoreListener {
    //    ABoutContract

    private CollapsingToolbarLayout collapsingToolbar;//
    private Toolbar toolbar;
    private RefreshRecyclerView mRecyclerView;
    private MyHomeAdapter myHomeAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ACache aCache;
    boolean isLoading;
    private long exitTime = 0;
    private ImageView iv_content;
    private String homeImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        aCache = ACache.get(MainActivity.this);
        homeImageUrl = getIntent().getStringExtra("homeImageUrl");

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_home;
    }

    @Override
    protected void initVariables() {

//        WelfareEntity welfareEntity = (WelfareEntity) aCache.getAsObject("mWelfareEntity");
//        if (welfareEntity == null) {
        getPersenter().sendHomeResult(20, page);

//        } else {
//            myHomeAdapter = new MyHomeAdapter(mActivity, welfareEntity);
//            mRecyclerView.setAdapter(myHomeAdapter);
//        }
    }

    @Override
    public void initView() {
        setUpWindowTrisience();
    }

    private void setUpWindowTrisience() {
        TransitionSet mtransitionset = new TransitionSet();
        mtransitionset.addTransition(new ChangeBounds());
        mtransitionset.addTransition(new ChangeImageTransform());
//        mtransitionset.addTransition(new Fade());
        mtransitionset.setDuration(250);
        getWindow().setEnterTransition(mtransitionset);
        getWindow().setExitTransition(mtransitionset);
        getWindow().setSharedElementEnterTransition(mtransitionset);
        getWindow().setSharedElementExitTransition(mtransitionset);
        getWindow().setAllowEnterTransitionOverlap(false);
    }

    @Override
    protected void initDataAndLoadData() {
//        images = MrSharedState.getInstance().getImages();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.home_swRefresh_view);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RefreshRecyclerView) findViewById(R.id.my_recycler_view);
        iv_content = (ImageView) findViewById(R.id.iv_content);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //给页面设置工具栏
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if (collapsingToolbar != null) {
            //设置隐藏图片时候ToolBar的颜色
            collapsingToolbar.setContentScrimColor(Color.parseColor("#11B7F3"));
            //设置工具栏标题
            collapsingToolbar.setTitle("编程是一种信仰");
        }
        //设置layoutManager
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setLoadMoreEnable(true);//允许加载更多
        mRecyclerView.setFooterResource(R.layout.item_footer);
        //创建一个布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        //滑动方向
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        //设置布局管理器
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);

        //设置item之间的间隔
//        SpacesItemDecoration decoration = new SpacesItemDecoration(5);
//        mRecyclerView.addItemDecoration(decoration);
//        MasonryAdapter
        //设置下拉进度的背景颜色  默认是白色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置下拉进度的主题颜色  这里只设置一种颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        //设置刷新加载监听方法
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setOnLoadMoreListener(this);
        if (homeImageUrl != null) {
            Glide.with(mActivity).load(homeImageUrl).into(iv_content);
        } else {
            MyToast.showToast(mActivity, "加载默认图片");
        }
    }

    int page = 1;
    int countPage = 0;

    @Override
    public IHomePsersenter getPersenter() {
        return new IHomePsersenter(mActivity, this);
    }

    //    String[] a = {"zzq", "tdos"};
//    WelfareEntity welfareEntity = new WelfareEntity();//实例化实体类
    //    List<ResultsBean>
    ArrayList<WelfareEntity.ResultsEntity> resultsBeenList = new ArrayList<>();
    private ObservableArrayList<Image> images;

    /**
     * 接收请求返回数据方法
     *
     * @param mWelfareEntity
     */

    boolean state = false;

    @Override
    public void getHomeResult(WelfareEntity mWelfareEntity) {

        Gson gson = new Gson();
        String json = gson.toJson(mWelfareEntity);
        LogUtils.i("打印图片json", "json:" + json);
//        welfareEntity.setResults(mWelfareEntity.getResults());//赋值
        mSwipeRefreshLayout.setRefreshing(false);
        if (mWelfareEntity != null) {
            if (!state) {
                page = 1;
                //如果是刷新  直接传递最后返回的实体
                if (resultsBeenList.size() > 0) {
                    resultsBeenList.clear();//清空刷新之前保存的数据
                }
                resultsBeenList.addAll(mWelfareEntity.getResults());//赋值最新一次的数据
                myHomeAdapter = new MyHomeAdapter(mActivity, resultsBeenList);
                mRecyclerView.setAdapter(myHomeAdapter);
            } else {
                //如果是加载  最新获取的数据+最新一次加载的数据
                if (mWelfareEntity.getResults().size() > 0) {
                    resultsBeenList.addAll(mWelfareEntity.getResults());
                    myHomeAdapter.notifyDataSetChanged();
                    mRecyclerView.notifyData();
                } else {
                    MyToast.showToast(mActivity, "没有更多数据了~");
                    countPage = page;
                    mRecyclerView.notifyData();
                }
            }
            myHomeAdapter.setOnMyHomeRcViewItemOnClickListener(new MyHomeAdapter.OnMyHomeRcViewItemOnClickListener() {
                @Override
                public void onMHRCViewItemOnclick(View itemView, View view, String data, int position) {
//                    Log.i("", "回调结果:" + data + "|||=" + position);
//                    Toast.makeText(mActivity, "" + position, Toast.LENGTH_SHORT).show();
//============================================================================
                    Intent intent = new Intent(mActivity, ShowPhotoActivity.class);
                    intent.putParcelableArrayListExtra("girls", resultsBeenList);
                    intent.putExtra("current", position);
//                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, view.findViewById(R.id.home_item_cd_img), "flag");
                    final ActivityOptionsCompat options;

                    if (Build.VERSION.SDK_INT >= 21) {
                        options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                MainActivity.this, itemView, "flag");
                    } else {
                        options = ActivityOptionsCompat.makeScaleUpAnimation(
                                itemView, 0, 0, itemView.getWidth(), itemView.getHeight());
                    }
                    ActivityCompat.startActivity(mActivity, intent, options.toBundle());
//============================================================================

//                    startActivity(intent, options.toBundle());
//                    startActivity(intent);
//                    startActivity(new Intent(MainActivity.this, ShowPhotoActivity.class));


//                    final Intent intent = new Intent(MainActivity.this, ViewerActivity.class);
//                    intent.putExtra("index", position);
//                    intent.putParcelableArrayListExtra("girls", resultsBeenList);
//
//                    final ActivityOptionsCompat options;
//
//                    if (Build.VERSION.SDK_INT >= 21) {
//                        options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                                MainActivity.this, itemView, "sharname");
//                    } else {
//                        options = ActivityOptionsCompat.makeScaleUpAnimation(
//                                itemView, 0, 0, itemView.getWidth(), itemView.getHeight());
//                    }
//
//                    startActivity(intent, options.toBundle());


                }
            });
        } else {
            Toast.makeText(mActivity, "null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //点击了返回箭头
                break;
            case R.id.about://作者
                new AlertDialog.Builder(this).setMessage("作者:zzqtdos\nEmail:zzqtdos@gmail.com\n敬请期待后续功能").show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 刷新操作
     */
    @Override
    public void onRefresh() {
        state = false;
        // 开始刷新，设置当前为刷新状态
        getPersenter().sendHomeResult(20, page);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 加載更多操作
     */
    @Override
    public void loadMoreListener() {
        state = true;
        if (countPage > 0) {
            if (countPage <= countPage) {
                MyToast.showToast(mActivity, "没有更多数据了");
                mRecyclerView.notifyData();
            }
        } else {
            page++;
//              MyToast.showToast(mActivity, "执行加载更多方法");
            getPersenter().sendHomeResult(20, page);
        }
    }

    /**
     * 关闭页面退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                MyToast.showToast(mActivity, "再次点击退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        resultsBeenList.clear();
    }
}
