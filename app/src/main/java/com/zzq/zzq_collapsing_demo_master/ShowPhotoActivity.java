package com.zzq.zzq_collapsing_demo_master;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zzq.zzq_collapsing_demo_master.base.AppActivity;
import com.zzq.zzq_collapsing_demo_master.base.BaseFragment;
import com.zzq.zzq_collapsing_demo_master.fragment.ShowPhotoFragment;
import com.zzq.zzq_collapsing_demo_master.utils.StatusBarUtil;

/**
 * Created by 志强 on 2017.4.13.
 */

public class ShowPhotoActivity extends AppActivity {

    private Toolbar toolbar;
    private ShowPhotoFragment showPhotoFragment;
    private View mViewNeedOffset;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_showphoto;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.girl_fragment;
    }


    @Override
    protected BaseFragment getFirstFragment() {
        showPhotoFragment = ShowPhotoFragment.newInstance(getIntent().getParcelableArrayListExtra("girls"), getIntent().getIntExtra("current", 0));
        return showPhotoFragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("图片展示页面");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mViewNeedOffset = findViewById(R.id.view_need_offset);
        StatusBarUtil.setTranslucentForImageView(this, 0, mViewNeedOffset);
        ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_girl, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
            showPhotoFragment.shareGirl();
//            Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_save) {
            showPhotoFragment.saveGirl();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
