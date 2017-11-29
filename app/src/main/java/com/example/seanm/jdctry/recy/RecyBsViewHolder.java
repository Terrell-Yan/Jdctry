package com.example.seanm.jdctry.recy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by SeanM on 2017/6/21.
 */

public class RecyBsViewHolder  extends RecyclerView.ViewHolder {
    View convertView;
    Context context;

    public RecyBsViewHolder(View itemView, Context context) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
    }

    public View getItemView() {
        return convertView;
    }

    public void setText(int id, String text) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);
    }

    public void setText(int id, String text, final OnClickListener onClickListener) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClickListner(v);
            }
        });
    }


    public void setImageListner(int id, final OnClickListener onClickListener) {
        ImageView img = (ImageView) convertView.findViewById(id);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClickListner(v);
            }
        });

    }

    public void setImageResource(int id, int resouceId) {
        ImageView img = (ImageView) convertView.findViewById(id);
        img.setImageResource(resouceId);
    }

    public void setImageResource(int id, int resouceId, final OnClickListener onClickListener) {
        ImageView img = (ImageView) convertView.findViewById(id);
        img.setImageResource(resouceId);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClickListner(v);
            }
        });
    }
public void setImageUrl(int id,String url)
{
    ImageView img = (ImageView) convertView.findViewById(id);
    Glide
            .with(context)
            .load(url)
            .into(img);
}
    public interface OnClickListener {
        void onClickListner(View v);
    }

}
