package com.mari.android.circlesplit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by chinraam on 25/4/17.
 */

public class MultiColorCircle extends View
{
    private Paint paint=new Paint();
    private RectF oval=new RectF();
    private float out_rad;
    private int colorArc;

    int dividerWidth;
    int[] colorArray;

    public MultiColorCircle(Context context) {
        super(context);
    }

    public MultiColorCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MultiColorCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint.setStyle(Paint.Style.STROKE);
        TypedArray array=context.getTheme().obtainStyledAttributes(attrs,R.styleable.MultiColorCircle,0,0);
        try{
            colorArc=array.getColor(R.styleable.MultiColorCircle_circle_color, Color.parseColor("#5C6BC0"));
            out_rad=array.getDimension(R.styleable.MultiColorCircle_arc_radius,150);
            dividerWidth=array.getInt(R.styleable.MultiColorCircle_divider_width,3);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            array.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if(colorArray.length==0)
        {
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(colorArc);
            paint.setStyle(Paint.Style.FILL);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#e74c3c"));
            paint.setStrokeWidth(5);

            oval.set(getWidth() / 2 - out_rad, getHeight() / 2 - out_rad, getWidth() / 2 + out_rad, getHeight() / 2 + out_rad);

            canvas.drawArc(oval, 270, 90, false, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#3498db"));
            paint.setStrokeWidth(5);

            canvas.drawArc(oval, 0, 90, false, paint);


            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#FFC619"));
            paint.setStrokeWidth(5);

            canvas.drawArc(oval, 90, 90, false, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#009688"));
            paint.setStrokeWidth(5);

            canvas.drawArc(oval, 180, 90, false, paint);
        }
        else
        {
            int arcSeperator=360/colorArray.length;
            int startingAngle=270;

            paint.setFlags(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(colorArc);
            paint.setStyle(Paint.Style.FILL);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#e74c3c"));
            paint.setStrokeWidth(5);
            oval.set(getWidth() / 2 - out_rad, getHeight() / 2 - out_rad, getWidth() / 2 + out_rad, getHeight() / 2 + out_rad);

            for (int i=0;i<colorArray.length;i++)
            {
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(colorArray[i]);
                paint.setStrokeWidth(5);

                canvas.drawArc(oval,startingAngle,arcSeperator-dividerWidth,false, paint);
                startingAngle=startingAngle+arcSeperator;
                if(startingAngle==360)
                {
                    startingAngle=0;
                }
            }
        }
    }

    public void setColorList(int[] colorArray)
    {
        this.colorArray=colorArray;
        invalidate();
    }

    public void setDviderWidth(int dividerWidth)
    {
        this.dividerWidth=dividerWidth;
        invalidate();
    }
}
