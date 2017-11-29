package com.example.seanm.jdctry.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.seanm.jdctry.R;
import com.example.seanm.jdctry.presenter.HomeIndex;
import com.example.seanm.jdctry.recy.BaseRecycleAdapter;
import com.example.seanm.jdctry.recy.RecyBsViewHolder;
import com.example.seanm.jdctry.view.ExpandImageView;
import com.example.seanm.jdctry.view.MyGridView;
import com.example.seanm.jdctry.view.bannerview.CircleFlowIndicator;
import com.example.seanm.jdctry.view.bannerview.ImagePagerAdapter;
import com.example.seanm.jdctry.view.bannerview.ViewFlow;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by SeanM on 2017/10/31.
 */

public class Recy_default_adapter extends RecyclerView.Adapter<RecyBsViewHolder> {
    private static final int TYPE_1 = 0xff01;
    private static final int TYPE_2 = 0xff02;
    private static final int TYPE_3 = 0xff03;
    private static final int TYPE_4 = 0xff04;
    private static final int TYPE_5 = 0xff05;
    private static final int TYPE_6 = 0xff06;
    private List<String> data;
    private Context context;
    ViewFlow mViewFlow;
    CircleFlowIndicator mFlowIndicator;
    private ArrayList<String> imageUrlList = new ArrayList<String>();
    //热门市场的九宫格
    private MyGridView my_gridView_hot;
    private GridAdapter adapter;
    // 分类九宫格的资源文件
    private int[] pic_path_classify = {R.drawable.menu_guide_1, R.drawable.menu_guide_2, R.drawable.menu_guide_3, R.drawable.menu_guide_4, R.drawable.menu_guide_5, R.drawable.menu_guide_6, R.drawable.menu_guide_7, R.drawable.menu_guide_8};
    // 热门市场的资源文件
    private List<HomeIndex.ItemInfoListBean> itemInfoList;

    public Recy_default_adapter(Context context, List<String> data, List<HomeIndex.ItemInfoListBean> itemInfoList) {
        this.context = context;
        this.data = data;
        this.itemInfoList = itemInfoList;
    }

    @Override
    public void onBindViewHolder(RecyBsViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            bindType1((MyViewHolder) viewHolder, i);
        } else if (viewHolder instanceof MyViewHolder2) {
            bindType2((MyViewHolder2) viewHolder, i);
        } else if (viewHolder instanceof MyViewHolder3) {
            bindType3((MyViewHolder3) viewHolder, i);
        } else if (viewHolder instanceof MyViewHolder4) {
            bindType4((MyViewHolder4) viewHolder, i);
        } else if (viewHolder instanceof MyViewHolder5) {
            bindType5((MyViewHolder5) viewHolder, i);
        } else if (viewHolder instanceof MyViewHolder6) {
            bindType6((MyViewHolder6) viewHolder, i);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_1;
        } else if (position == 1) {
            return TYPE_2;
        } else if (position == 2) {
            return TYPE_3;
        } else if (position == 3) {
            return TYPE_4;
        } else if (position == 4) {
            return TYPE_5;
        } else if (position == 5) {
            return TYPE_6;
        }
        return super.getItemViewType(position);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public RecyBsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_1:
                return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.type, viewGroup, false));
            case TYPE_2:
                return new MyViewHolder2(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.type2, viewGroup, false));
            case TYPE_3:
                return new MyViewHolder3(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.type3, viewGroup, false));
            case TYPE_4:
                return new MyViewHolder4(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.type4, viewGroup, false));
            case TYPE_5:
                return new MyViewHolder5(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.type5, viewGroup, false));
            case TYPE_6:
                return new MyViewHolder6(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.type6, viewGroup, false));
            default:
        }
        return null;
    }

    class MyViewHolder extends RecyBsViewHolder implements View.OnClickListener {

        public MyViewHolder(View itemView) {
            super(itemView, context);
            //轮播图
            //lun
            mViewFlow = (ViewFlow) itemView.findViewById(R.id.viewflow);
            mFlowIndicator = (CircleFlowIndicator) itemView.findViewById(R.id.viewflowindic);
            //热门市场的九宫格
            my_gridView_hot = itemView.findViewById(R.id.cart_grid);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
        }
    }

    class MyViewHolder2 extends RecyBsViewHolder implements View.OnClickListener {

        public MyViewHolder2(View itemView) {
            super(itemView, context);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
        }
    }

    private List<String> hordata;
    private RecyclerView recyclerView_hor;

    class MyViewHolder3 extends RecyBsViewHolder implements View.OnClickListener {

        public MyViewHolder3(final View itemView) {
            super(itemView, context);
            recyclerView_hor = itemView.findViewById(R.id.hor_recy);
            hordata = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                hordata.add("cao" + i);
            }


        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
        }
    }

    private ImageView iv1, iv2, iv3;

    class MyViewHolder4 extends RecyBsViewHolder implements View.OnClickListener {

        public MyViewHolder4(View itemView) {
            super(itemView, context);
            iv1 = itemView.findViewById(R.id.show_event_left_img);
            iv2 = itemView.findViewById(R.id.show_event_middle_img);
            iv3 = itemView.findViewById(R.id.show_event_right_img);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
        }
    }

    private ImageView exImg1, exImg2;

    class MyViewHolder5 extends RecyBsViewHolder implements View.OnClickListener {

        public MyViewHolder5(View itemView) {
            super(itemView, context);
            exImg1 = itemView.findViewById(R.id.find_good_stuff_left_img);
            exImg2 = itemView.findViewById(R.id.find_good_stuff_right_img);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
        }
    }


    private TextView type6_tv1, type6_tv2;
    private RecyclerView recyclerView2;
    private HomeIndex.ItemInfoListBean item_type6;
    private List<String> data_type6;

    class MyViewHolder6 extends RecyBsViewHolder implements View.OnClickListener {

        public MyViewHolder6(View itemView) {
            super(itemView, context);
            data_type6=new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                data_type6.add("特价" + i);
            }
            recyclerView2 = itemView.findViewById(R.id.type_6_recy);

            type6_tv1 = itemView.findViewById(R.id.recommended_title);
            type6_tv2 = itemView.findViewById(R.id.recommended_price);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindType1(MyViewHolder holder, int position) {
        //初始化轮播图
        imageUrlList
                .add("http://bmob-cdn-9958.b0.upaiyun.com/2017/04/27/ce70a4d2403bbe9a808295f1c689e23f.jpg");
        imageUrlList
                .add("http://bmob-cdn-9958.b0.upaiyun.com/2017/04/27/d8e17f7740dfe8618030e5280b289b3f.jpg");
        imageUrlList
                .add("http://bmob-cdn-9958.b0.upaiyun.com/2017/04/27/94b40561403ee5d5802741fbb8e9a31c.jpg");
        imageUrlList
                .add("http://bmob-cdn-9958.b0.upaiyun.com/2017/04/27/5736d59c40794b8380d74e5cfbee902a.jpg");
        mViewFlow.setAdapter(new ImagePagerAdapter(context, imageUrlList
        ).setInfiniteLoop(true));
        mViewFlow.setmSideBuffer(imageUrlList.size()); // 实际图片张数，
        // 我的ImageAdapter实际图片张数为3

        mViewFlow.setFlowIndicator(mFlowIndicator);
        mViewFlow.setTimeSpan(4500);
        mViewFlow.setSelection(imageUrlList.size() * 1000); // 设置初始位置
        mViewFlow.startAutoFlowTimer(); // 启动自动播放


        adapter = new GridAdapter(context, pic_path_classify);

        my_gridView_hot.setAdapter(adapter);
    }

    private void bindType2(MyViewHolder2 holder, int position) {

    }

    private void bindType3(MyViewHolder3 holder, int position) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_hor.setLayoutManager(layoutManager);

        BaseRecycleAdapter baseRecycleAdapter = new BaseRecycleAdapter(context, R.layout.hor, hordata);
        recyclerView_hor.setAdapter(baseRecycleAdapter);
        baseRecycleAdapter.setCallBack(new BaseRecycleAdapter.CallBack() {
            @Override
            public <T> void convert(RecyBsViewHolder holder, T bean, int position) {

                holder.setText(R.id.hor_text, "心" + position);
            }
        });

    }

    private void bindType4(MyViewHolder4 holder, int position) {

        Glide
                .with(context)
                .load("https://m.360buyimg.com/mobilecms/jfs/t5011/52/11315246/33679/3af01dc4/58d914ccN6070524e.jpg!q70.jpg")
                .into(iv1);
        Glide
                .with(context)
                .load("https://m.360buyimg.com/mobilecms/jfs/t4261/164/2985229152/21008/6cee35d7/58d93ef1Nf05dc380.jpg!q70.jpg")
                .into(iv2);
        Glide
                .with(context)
                .load("https://m.360buyimg.com/mobilecms/jfs/t4678/353/1126087781/29490/bd75b7c9/58d91540N13ccd112.jpg!q70.jpg")
                .into(iv3);

    }

    private void bindType5(MyViewHolder5 holder, int position) {
        Glide
                .with(context)
                .load("https://m.360buyimg.com/mobilecms/s220x220_jfs/t4573/49/77501525/294199/65cf62f/58c93bb7N6ee68c13.jpg!q70.jpg")
                .into(exImg1);
        Glide
                .with(context)
                .load("https://m.360buyimg.com/mobilecms/s220x220_jfs/t4459/60/496148966/255134/5fac7ade/58d0bcdcNe426a111.jpg!q70.jpg")
                .into(exImg2);

    }

    @Inject
    HomeIndex homeIndex;

    private void bindType6(MyViewHolder6 holder, int position) {
//        if (!"".equals(item_type6.itemContentList.get(position).imageUrl)&&!"".equals(item_type6.itemContentList.get(position).itemTitle)&&!"".equals(item_type6.itemContentList.get(position).itemSubTitle)){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager);

        BaseRecycleAdapter baseRecycleAdapter = new BaseRecycleAdapter(context, R.layout.type6_recy, data_type6);
        recyclerView2.setAdapter(baseRecycleAdapter);
        baseRecycleAdapter.setCallBack(new BaseRecycleAdapter.CallBack() {
            @Override
            public <T> void convert(RecyBsViewHolder holder, T bean, int position) {

                holder.setText(R.id.hoe_iv, "Phone" + position);
                holder.setText(R.id.hoe_iv2, "熊" + position+1);
                holder.setImageUrl(R.id.hor_iv3,"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4675/88/704144946/137165/bbbe8a4e/58d3a160N621fc59c.jpg!q70.jpg");
            }
        });
    }
}
