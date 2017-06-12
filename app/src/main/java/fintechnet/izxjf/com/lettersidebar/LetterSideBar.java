package fintechnet.izxjf.com.lettersidebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by as on 2017/6/6.
 */

public class LetterSideBar extends View {

    private int mTextColor;
    private int mTextSize;
    private Paint mPaint;
    private String lastCurrentLetter;
    private static String[] mLetters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"
            , "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    private String currentLetter;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LetterSideBar);
        mTextColor = typedArray.getColor(R.styleable.LetterSideBar_mTextColor, Color.BLUE);
        mTextSize = typedArray.getDimensionPixelOffset(R.styleable.LetterSideBar_mTextSize, sp2px(15));
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(sp2px(mTextSize));
        mPaint.setColor(mTextColor);
    }


    public int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int textWidth = (int) mPaint.measureText("W");
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
        for (int i = 0; i < mLetters.length; i++) {
            int textWidth = (int) mPaint.measureText(mLetters[i]);
            int x = getWidth() / 2 - textWidth / 2;
            Paint.FontMetricsInt fontMetrisInt = mPaint.getFontMetricsInt();
            int dy = (fontMetrisInt.bottom - fontMetrisInt.top) / 2 - fontMetrisInt.bottom;
            int letterCenterY = itemHeight * i + itemHeight / 2 + getPaddingTop();
            int baseLine = letterCenterY + dy;
            if (mLetters[i].equals(currentLetter)) {
                mPaint.setColor(Color.RED);
            } else {

                mPaint.setColor(Color.BLUE);
            }
            canvas.drawText(mLetters[i], x, baseLine, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
                int moveY = (int) event.getY() / itemHeight;
                if (moveY < 0) {
                    moveY = 0;
                }
                if (moveY > mLetters.length - 1) {
                    moveY = mLetters.length - 1;
                }


                currentLetter = mLetters[moveY];
                if(mListener !=null){
                    mListener.onTouth(currentLetter,true);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:

                SystemClock.sleep(100);
                if(mListener !=null){
                    mListener.onTouth(currentLetter,false);
                }
                break;
        }
        return true;
    }
    public onLetterTouthListener mListener;
    public void setOnLetterTouthListener(onLetterTouthListener listener){
        this.mListener =listener;
    }
    public interface onLetterTouthListener{
        void onTouth(String currentLetter,boolean isTouth);
    }
}
