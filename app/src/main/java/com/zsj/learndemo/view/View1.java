package com.zsj.learndemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangshijie on 2019/5/8;
 */
public class View1 extends View {

    private Paint paint = new Paint();

    public View1(Context context) {
        super(context);
    }

    public View1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public View1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        int left = 100;
        int top = 100;
//        canvas.drawCircle(200,200,40,paint);
//
//        canvas.drawRect(400,400,500,500,paint);
//
//        paint.setStrokeWidth(20);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPaint(paint);

        paint.setTextSize(50);
        canvas.drawText("J&T", left, top, paint);

        canvas.drawRect(left + 100, top - 30, left + 300, top + 50, paint);

        paint.setTextSize(21);
        canvas.drawText("Thasya Ariedhana", left + 350, top, paint);

        canvas.drawText("085811794743", left + 350, top + 40, paint);

        //getWidth()表示绘制多宽后换行
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(30);
        textPaint.setColor(Color.BLACK);
        String data = "asdhkjh k aksdjfhkjdas kajshdkfjh adskjfhskdjhfkjsdhflk kajshdfkjhaskdjf hkajsdhfkj khaksjdfh kjashdlkjhlakjsdhf kjashdflkjhaslfdkjhasjkdhfljkashfdjkasdhfljkashdf jafd ";
        StaticLayout sl = new StaticLayout(data, textPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        //从0,0开始绘制
        canvas.translate(left, top+100);
        sl.draw(canvas);



    }
}
