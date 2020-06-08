package com.wuc.gridpagerrecyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<DataSourceUtils.ItemData> mDataList;
    private final LayoutInflater mLayoutInflater;
    private final int mItemWidth;

    public RecyclerViewAdapter(Context context, List<DataSourceUtils.ItemData> dataList, int itemWidth) {
        mDataList = dataList;
        mLayoutInflater = LayoutInflater.from(context);
        this.mItemWidth = itemWidth;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.layout_item, parent, false), mItemWidth);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView, int itemWidth) {
            super(itemView);

            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.width = itemWidth;

            imageView = (ImageView) itemView.findViewById(R.id.view_item_icon);
            textView = (TextView) itemView.findViewById(R.id.view_item_title);
        }

        public void bindData(DataSourceUtils.ItemData itemData) {
            if (itemData != null) {
                itemView.setVisibility(View.VISIBLE);
                Glide.with(itemView).load(itemData.url).into(imageView);
                Log.e("url", itemData.url);
                textView.setText(itemData.title);
            } else {
                itemView.setVisibility(View.GONE);
            }
        }
    }
}
