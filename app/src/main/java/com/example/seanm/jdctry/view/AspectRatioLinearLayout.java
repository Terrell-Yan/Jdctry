package com.example.seanm.jdctry.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.seanm.jdctry.R;


/**
 * @author：admin on 2017/3/29 11:54.
 */

public class AspectRatioLinearLayout extends LinearLayout {

    /**
     * 纵横比测量
     */
    private final BannerAspectRatioMeasure.Spec mMeasureSpec = new BannerAspectRatioMeasure.Spec();
    /**
     * 宽高显示比例
     */
    private float mAspectRatio = 0;

    public AspectRatioLinearLayout(Context context) {
        this(context,null);
    }

    public AspectRatioLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AspectRatioLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomAttrs(context, attrs);
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioLayout);
        mAspectRatio = typedArray.getFloat(R.styleable.AspectRatioLayout_Layout_viewAspectRatio,0);
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureSpec.width = widthMeasureSpec;
        mMeasureSpec.height = heightMeasureSpec;
        BannerAspectRatioMeasure.updateMeasureSpec(
                mMeasureSpec,
                mAspectRatio,
                getLayoutParams(),
                getPaddingLeft() + getPaddingRight(),
                getPaddingTop() + getPaddingBottom());
        super.onMeasure(mMeasureSpec.width, mMeasureSpec.height);
    }
}
