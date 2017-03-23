package com.zzq.zzq_collapsing_demo_master.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zzq.zzq_collapsing_demo_master.R;
import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;

import java.util.List;

/**
 * Created by 志强 on 2017.3.9.
 */

public class MyHomeAdapter extends RecyclerView.Adapter<MyHomeAdapter.ViewHolder> {

    private Context mContext;
    private List<WelfareEntity.ResultsBean> welfareEntity;
    private View view;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public MyHomeAdapter(Context mContext, List<WelfareEntity.ResultsBean> welfareEntity) {
        this.mContext = mContext;
        this.welfareEntity = welfareEntity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_masonry_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext).load(welfareEntity.get(position).getUrl()).error(R.mipmap.ic_launcher).into(holder.home_item_cd_img);

        holder.itemView.setTag(welfareEntity.get(position).getUrl());
        holder.itemView.setId(position);

//        Glide.with(mContext).load(welfareEntity.get(position).getUrl()).error(R.mipmap.ic_launcher).into(holder.home_item_cd_img);
//        Glide.with(mContext).load(aboutEntity.getStories().get(position).getImages().get(0)).error(R.drawable.ic_link_white_24dp).into(holder.pic);
    }

    /**
     * 声明RecyclerView item点击事件接口
     */
    private OnMyHomeRcViewItemOnClickListener mOnMyHomeRcViewItemOnClickListener;

    public static interface OnMyHomeRcViewItemOnClickListener {
        void onMHRCViewItemOnclick(View view, String data, int position);
    }

    public void setOnMyHomeRcViewItemOnClickListener(OnMyHomeRcViewItemOnClickListener listener) {
        this.mOnMyHomeRcViewItemOnClickListener = listener;
    }


    @Override
    public int getItemCount() {
        return welfareEntity == null ? 0 : welfareEntity.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView home_item_cd_img;
        public CardView MyHome_cv_CardView;

        public ViewHolder(View itemView) {
            super(itemView);
            home_item_cd_img = (ImageView) itemView.findViewById(R.id.home_item_cd_img);
            MyHome_cv_CardView = (CardView) itemView.findViewById(R.id.MyHome_cv_CardView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnMyHomeRcViewItemOnClickListener != null) {
                        mOnMyHomeRcViewItemOnClickListener.onMHRCViewItemOnclick(v, (String) v.getTag(), v.getId());
                    }
                }
            });
        }
    }

}
