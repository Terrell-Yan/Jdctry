package com.example.seanm.jdctry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.seanm.jdctry.R;

/**
 * Created by SeanM on 2017/10/26.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private int[] data;

    public GridAdapter(Context context, int[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HolderView holderView = null;
        if (view == null) {
            holderView = new HolderView();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_gride, null);
            holderView.iv_pic = (ImageView) view.findViewById(R.id.iv_adapter_grid_pic);
            view.setTag(holderView);
        }else{
            holderView=(HolderView)view.getTag();
        }
        holderView.iv_pic.setImageResource(data[i]);
        return view;
    }


    private class HolderView {
        private ImageView iv_pic;
    }
}
