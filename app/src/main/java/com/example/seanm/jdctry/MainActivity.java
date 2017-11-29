package com.example.seanm.jdctry;

import android.app.Fragment;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seanm.jdctry.base.BaseActivity;
import com.example.seanm.jdctry.fragment.Cart_F;
import com.example.seanm.jdctry.fragment.Discover_F;
import com.example.seanm.jdctry.fragment.Home_F;
import com.example.seanm.jdctry.fragment.Tao_F;
import com.example.seanm.jdctry.fragment.User_F;
import com.example.seanm.jdctry.inteface.IBtnCallListener;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements View.OnClickListener,IBtnCallListener {
    //界面底部的菜单按钮
    private TextView[] bt_tv = new TextView[5];
    private ImageView[] bt_menu_img = new ImageView[5];
    private LinearLayout[] bt_menu = new LinearLayout[5];
    private int[] tv_id = {R.id.activity_tv, R.id.activity_tv2, R.id.activity_tv3, R.id.activity_tv4, R.id.activity_tv5};
    private int[] lin_id = {R.id.lin, R.id.lin2, R.id.lin3, R.id.lin4, R.id.lin5};
    //界面底部的图片ID
    private int[] bt_menu_id = {R.id.iv_menu_0, R.id.iv_menu_1, R.id.iv_menu_2, R.id.iv_menu_3, R.id.iv_menu_4};
    //底部按钮选中菜单按钮资源
    private int select_on[] = {R.mipmap.guide_home_on, R.mipmap.guide_tfaccount_on, R.mipmap.guide_discover_on, R.mipmap.guide_cart_on, R.mipmap.guide_account_on};
    //底部按钮未选中资源
    private int select_off[] = {R.drawable.bt_menu_0_select, R.drawable.bt_menu_1_select, R.drawable.bt_menu_2_select, R.drawable.bt_menu_3_select, R.drawable.bt_menu_4_select};
    private Home_F home_f;
    private Tao_F tao_f;
    private Discover_F discover_f;
    private Cart_F cart_f;
    private User_F user_f;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < bt_menu.length; i++) {
            Log.d("Log", "message:" + i);
            bt_menu[i] = (LinearLayout) findViewById(lin_id[i]);
            bt_menu_img[i] = (ImageView) findViewById(bt_menu_id[i]);
            bt_tv[i] = (TextView) findViewById(tv_id[i]);
            bt_menu[i].setOnClickListener(this);
        }
        if (home_f == null) {
            home_f = new Home_F();
            addFragment(home_f);
            showFragment(home_f);
        } else {
            showFragment(home_f);
        }
        bt_menu_img[0].setImageResource(select_on[0]);
        bt_tv[0].setTextColor(Color.GREEN);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin:


                // 主界面
                if (home_f == null) {
                    home_f = new Home_F();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(home_f);
                    showFragment(home_f);
                } else {
                    if (home_f.isHidden()) {
                        showFragment(home_f);
                    }
                }

                break;
            case R.id.lin2:
                // 微淘界面
                if (tao_f == null) {
                    tao_f = new Tao_F();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    if (!tao_f.isHidden()) {
                        addFragment(tao_f);
                        showFragment(tao_f);
                    }
                } else {
                    if (tao_f.isHidden()) {
                        showFragment(tao_f);
                    }
                }

                break;
            case R.id.lin3:
                // 发现界面
                if (discover_f == null) {
                    discover_f = new Discover_F();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    if (!discover_f.isHidden()) {
                        addFragment(discover_f);
                        showFragment(discover_f);
                    }
                } else {
                    if (discover_f.isHidden()) {
                        showFragment(discover_f);
                    }
                }

                break;
            case R.id.lin4:
                // 购物车界面
                if (cart_f != null) {
                    removeFragment(cart_f);
                    cart_f = null;
                }
                cart_f = new Cart_F();
                // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                addFragment(cart_f);
                showFragment(cart_f);

                break;
            case R.id.lin5:
                // 我的淘宝界面
                if (user_f == null) {
                    user_f = new User_F();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    if (!user_f.isHidden()) {
                        addFragment(user_f);
                        showFragment(user_f);
                    }
                } else {
                    if (user_f.isHidden()) {
                        showFragment(user_f);
                    }
                }

                break;
        }

        // 设置按钮的选中和未选中资源
        for (int i = 0; i < bt_menu.length; i++) {
            bt_menu_img[i].setImageResource(select_off[i]);
            bt_tv[i].setTextColor(Color.BLACK);
            if (v.getId() == lin_id[i]) {
                bt_menu_img[i].setImageResource(select_on[i]);
                bt_tv[i].setTextColor(Color.GREEN);
            }
        }
    }

    private void addFragment(android.support.v4.app.Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.show_layout, fragment);
        ft.commit();
    }

    private void showFragment(android.support.v4.app.Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);
        //判断页面是否已经创建，如果已经创建那么就隐藏掉
        if (home_f != null) {
            ft.hide(home_f);
        }
        if (tao_f != null) {
            ft.hide(tao_f);
        }
        if (discover_f != null) {
            ft.hide(discover_f);
        }
        if (cart_f != null) {
            ft.hide(cart_f);
        }
        if (user_f != null) {
            ft.hide(user_f);
        }
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 响应从Fragment中传过来的消息
     */
    @Override
    public void transferMsg() {
        if (home_f == null) {
            home_f = new Home_F();
            addFragment(home_f);
            showFragment(home_f);
        } else {
            showFragment(home_f);
        }
        bt_menu_img[3].setImageResource(select_off[3]);
        bt_menu_img[0].setImageResource(select_on[0]);

        System.out.println("由Fragment中传送来的消息");
    }

    /**
     * 删除Fragment
     **/
    public void removeFragment(android.support.v4.app.Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }


        CountDownTimer timer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                bt_tv[0].setText((millisUntilFinished / 1000) + "秒后可重发");
            }

            @Override
            public void onFinish() {
                bt_tv[0].setEnabled(false);
                bt_tv[0].setText("获取验证码");
            }

        };



}
