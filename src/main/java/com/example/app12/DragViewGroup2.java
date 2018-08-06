package com.example.app12;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * autour : lbing
 * date : 2018/8/6 0006 13:47
 * className :
 * version : 1.0
 * description :
 */


public class DragViewGroup2 extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private View mMainView;
    private View mMenuView;
    private MarginLayoutParams lp2;
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        //拖动结束后使用
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            int left = releasedChild.getLeft();
            //显示出来
            if(left>-releasedChild.getMeasuredWidth()/2){
                mViewDragHelper.settleCapturedViewAt(lp2.leftMargin,releasedChild.getTop());
            }else{//隐藏
                mViewDragHelper.settleCapturedViewAt(-(releasedChild.getMeasuredWidth()+lp2.rightMargin),releasedChild.getTop());
            }
            invalidate();
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
        }


        //这里需要调用setEdgeTrackingEnabled()后才能回调
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
//            super.onEdgeDragStarted(edgeFlags, pointerId);
            Log.e("TAG", "边界拖动");
            //进行制定view里面会调用接口onViewCaptured()，然后将View回调出来
            mViewDragHelper.captureChildView(mMenuView, pointerId);
        }

        //何时进行检测触摸事件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mMenuView == child;
        }

        //处理横向滑动
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.e("TAG", "left===" + left);
            if (left >= 0) {
                left = 0;
            } else if (left < -(lp2.rightMargin + mMenuView.getMeasuredWidth())) {
                left = lp2.rightMargin + mMenuView.getMeasuredWidth();
            }
            return left;
        }

        //处理纵向滑动
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

    };


    public DragViewGroup2(@NonNull Context context) {
        this(context, null);
    }


    public DragViewGroup2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragViewGroup2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
        //这个必须进行设置，否则就边界拖动的回调方法就不能使用
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT | ViewDragHelper.EDGE_RIGHT);
//        mViewDragHelper.setMinVelocity(400/1080);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        MarginLayoutParams lp1 = (MarginLayoutParams) mMainView.getLayoutParams();

        mMainView.layout(lp1.leftMargin, lp1.topMargin, lp1.leftMargin + mMainView.getMeasuredWidth(), lp1.topMargin + mMainView.getMeasuredHeight());

        lp2 = (MarginLayoutParams) mMenuView.getLayoutParams();

        mMenuView.layout(-(mMenuView.getMeasuredWidth() + lp2.rightMargin), lp2.topMargin, -lp2.rightMargin, lp2.topMargin + mMenuView.getMeasuredHeight());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMainView = getChildAt(0);
        mMenuView = getChildAt(1);
    }

}
