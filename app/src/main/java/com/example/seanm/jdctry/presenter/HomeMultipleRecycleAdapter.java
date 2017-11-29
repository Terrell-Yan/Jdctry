package com.example.seanm.jdctry.presenter;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.seanm.jdctry.R;
import com.example.seanm.jdctry.view.ExpandImageView;
import com.facebook.drawee.view.SimpleDraweeView;



/**
 * @author admin
 */
public class HomeMultipleRecycleAdapter extends BaseMultiItemQuickAdapter<HomeIndex.ItemInfoListBean, BaseViewHolder> implements BaseQuickAdapter.SpanSizeLookup, BaseQuickAdapter.OnItemChildClickListener {

    private CountDownTimer timer;
    private int maxHasLoadPosition;
    /**
     * 当前position监听
     */
    private PositionChangedListener listener;

    public void setListener(PositionChangedListener listener) {
        this.listener = listener;
    }

    public void resetMaxHasLoadPosition() {
        maxHasLoadPosition = 0;
    }

    public HomeMultipleRecycleAdapter() {
        setSpanSizeLookup(this);

        addItemType(Constant.TYPE_SHOW_EVENT_3, R.layout.layout_hoe);

    }

    @Override
    protected void convert(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        if (listener != null) {
            listener.currentPosition(position);
        }
        if (maxHasLoadPosition < position) {
            maxHasLoadPosition = position;
        }

      if ("showEvent".equals(item.itemType)) {
            bindRecommendedWareData(helper, item, position);
        }
    }

    private void bindRecommendedWareData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
//        ((ExpandImageView) helper.getView(R.id.hoe_iv)).setImageURI("https://m.360buyimg.com/mobilecms/s220x220_jfs/t4573/49/77501525/294199/65cf62f/58c93bb7N6ee68c13.jpg!q70.jpg");
        Log.d("Log","dadjalkdj--------======="+item.itemContentList.get(0).imageUrl);
        helper.setText(R.id.hoe_iv, "Fuction"+"acticon");
    }










    private void bindFindGoodStuffData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        ((ExpandImageView) helper.getView(R.id.find_good_stuff_left_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.find_good_stuff_right_img)).setImageURI(item.itemContentList.get(1).imageUrl);
    }

    private void bindShowEventData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        ((ExpandImageView) helper.getView(R.id.show_event_left_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.show_event_middle_img)).setImageURI(item.itemContentList.get(1).imageUrl);
        ((ExpandImageView) helper.getView(R.id.show_event_right_img)).setImageURI(item.itemContentList.get(2).imageUrl);
    }




    private void bindJDSpikeHeaderData(final BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        helper.setText(R.id.spike_time_field, item.itemContentList.get(0).itemTitle);
        helper.setText(R.id.spike_header_desc, item.itemContentList.get(0).itemSubTitle);
        String time = item.itemContentList.get(0).itemRecommendedLanguage;
        if (TextUtils.isEmpty(time) || !time.matches("^[0-9]*$")) return;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(Long.parseLong(time), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long temp = millisUntilFinished / 1000;
                long hours = temp / 3600;
                long minutes = (temp - (3600 * hours)) / 60;
                long seconds = temp - (3600 * hours) - (60 * minutes);
                helper.setText(R.id.spike_time_hour, hours > 9 ? "" + hours : "0" + hours);
                helper.setText(R.id.spike_time_minute, minutes > 9 ? "" + minutes : "0" + minutes);
                helper.setText(R.id.spike_time_seconds, seconds > 9 ? "" + seconds : "0" + seconds);

            }

            @Override
            public void onFinish() {
                helper.setText(R.id.spike_time_hour, "00");
                helper.setText(R.id.spike_time_minute, "00");
                helper.setText(R.id.spike_time_seconds, "00");
            }
        }.start();
    }











    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return mData.get(position).getSpanSize();
    }


    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


        return false;
    }
}


