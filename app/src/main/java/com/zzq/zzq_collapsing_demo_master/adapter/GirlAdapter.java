package com.zzq.zzq_collapsing_demo_master.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zzq.zzq_collapsing_demo_master.R;
import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by 志强 on 2017.4.14.
 */

public class GirlAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<WelfareEntity.ResultsEntity> mDatas;
    private LayoutInflater layoutInflater;
    private View mCurrentView;

    public GirlAdapter(Context context, ArrayList<WelfareEntity.ResultsEntity> datas) {
        mContext = context;
        mDatas = datas;
        layoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView = (View) object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        final String imageUrl = mDatas.get(position).getUrl();
        View view = layoutInflater.inflate(R.layout.item_girl_detail, container, false);
        PhotoView imageView = (PhotoView) view.findViewById(R.id.img);
        Glide.with(mContext)
                .load(imageUrl)
                .thumbnail(0.2f)
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
