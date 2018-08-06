package com.example.app12;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
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


public class DragViewGroup extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        //拖动结束后使用,这样实现回弹效果。
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            int left =  releasedChild.getLeft();
            int top =  releasedChild.getTop();
            //如果在碰到最左边
            if (releasedChild.getLeft() < 0) {
                left = 0;
            }
            //如果在碰到最右边，这里需要进行计算右边边缘的距里，
            if (releasedChild.getLeft() > (getWidth() - releasedChild.getWidth())) {
                left =getWidth() - releasedChild.getWidth();
            }
            //如果碰到做上边
            if (releasedChild.getTop() < 0) {
                top = 0;
            }
            //如果碰到做下边
            if (releasedChild.getTop() > (getHeight() - releasedChild.getHeight())) {
                top = getHeight() - releasedChild.getHeight();
            }

            //这是进行指定当前的view的位置，里面使用了Scroller ，因此需要刷新使用。并且重写computeScroll()
            mViewDragHelper.settleCapturedViewAt(left, top);
            ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);

        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);
        }

        //这个是如果设置点击事件后，能够拖动里面view
        @Override
        public int getViewHorizontalDragRange(View child) {
            return 1;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return 1;
        }

        //何时进行检测触摸事件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        //处理横向滑动
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        //处理纵向滑动
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }
    };

    public DragViewGroup(@NonNull Context context) {
        this(context, null);
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callback);

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
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
