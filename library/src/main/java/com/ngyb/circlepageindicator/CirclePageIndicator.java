package com.ngyb.circlepageindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/25 15:39
 */
public class CirclePageIndicator extends View {
    int radius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());//半径
    int cd = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());//圆外距
    private Paint paint;
    private ViewPager viewPager;
    public float offset;
    public int positions;
    private static final String TAG = "CirclePageIndicator";

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = viewPager.getAdapter().getCount();
        int width = 2 * count * radius + cd * (count - 1);
        int height = 2 * radius;
        setMeasuredDimension(width, height);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = viewPager.getAdapter().getCount();
        int distance = 2 * radius + cd;
        for (int i = 0; i < count; i++) {
            int cy = radius;
            int cx = radius + distance * i;
            paint.setColor(Color.WHITE);
            canvas.drawCircle(cx, cy, radius, paint);
        }
        int cy = radius;
        int cx = (int) (radius + positions * distance + offset * distance);
        paint.setColor(Color.RED);
        canvas.drawCircle(cx, cy, radius, paint);
//        super.onDraw(canvas);
    }

    public void setAdapter(ViewPager viewpager) {
        this.viewPager = viewpager;
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                positions = i;
                offset = i1;
                invalidate();
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
