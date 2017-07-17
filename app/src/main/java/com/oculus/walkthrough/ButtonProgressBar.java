package com.oculus.walkthrough;

import android.content.Context;
import android.view.View;

/**
 * Created by karan on 14/7/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import github.ishaan.buttonprogressbar.R.dimen;
import github.ishaan.buttonprogressbar.R.drawable;
import github.ishaan.buttonprogressbar.R.styleable;

public class ButtonProgressBar extends View {
    private Paint mTextPaint;
    private Paint mBackgroundPaint;
    private Paint mProgressPaint;
    private String mInitialText = "";
    private int mTextSize;
    private int mTextColor;
    private int mBackgroundColor;
    private int mProgressColor;
    private int mCornerRadius = 0;
    private Bitmap mTickBitmap;
    public int indeterminateState = -1;
    private final int topX = 0;
    private final int topY = 0;
    private final int MIN = 0;
    private final int MAX = 100;
    private float mProgress = 0.0F;
    private github.ishaan.buttonprogressbar.ButtonProgressBar.Type mLoaderType;
    private int mProgressIncrementFactor;
    private int mCounterFactor;
    private int mMaxCounterFactor;
    private static final int STATE_RESET = -1;
    private static final int STATE_START = 1;
    private static final int STATE_STOP = 0;
    private static final int API_LEVEL_LOLLIPOP = 21;
    private static final int DEFAULT_BGCOLOR = Color.parseColor("#0E8DD4");
    private static final int DEFAULT_PROGCOLOR = Color.parseColor("#0399E5");
    private static final float[] INDETERMINATE_ANIMATION_VALUES = new float[]{0.0F, 1.0E-4F, 2.0E-4F, 5.0E-4F, 9.0E-4F, 0.0014F, 0.002F, 0.0027F, 0.0036F, 0.0046F, 0.0058F, 0.0071F, 0.0085F, 0.0101F, 0.0118F, 0.0137F, 0.0158F, 0.018F, 0.0205F, 0.0231F, 0.0259F, 0.0289F, 0.0321F, 0.0355F, 0.0391F, 0.043F, 0.0471F, 0.0514F, 0.056F, 0.0608F, 0.066F, 0.0714F, 0.0771F, 0.083F, 0.0893F, 0.0959F, 0.1029F, 0.1101F, 0.1177F, 0.1257F, 0.1339F, 0.1426F, 0.1516F, 0.161F, 0.1707F, 0.1808F, 0.1913F, 0.2021F, 0.2133F, 0.2248F, 0.2366F, 0.2487F, 0.2611F, 0.2738F, 0.2867F, 0.2998F, 0.3131F, 0.3265F, 0.34F, 0.3536F, 0.3673F, 0.381F, 0.3946F, 0.4082F, 0.4217F, 0.4352F, 0.4485F, 0.4616F, 0.4746F, 0.4874F, 0.5F, 0.5124F, 0.5246F, 0.5365F, 0.5482F, 0.5597F, 0.571F, 0.582F, 0.5928F, 0.6033F, 0.6136F, 0.6237F, 0.6335F, 0.6431F, 0.6525F, 0.6616F, 0.6706F, 0.6793F, 0.6878F, 0.6961F, 0.7043F, 0.7122F, 0.7199F, 0.7275F, 0.7349F, 0.7421F, 0.7491F, 0.7559F, 0.7626F, 0.7692F, 0.7756F, 0.7818F, 0.7879F, 0.7938F, 0.7996F, 0.8053F, 0.8108F, 0.8162F, 0.8215F, 0.8266F, 0.8317F, 0.8366F, 0.8414F, 0.8461F, 0.8507F, 0.8551F, 0.8595F, 0.8638F, 0.8679F, 0.872F, 0.876F, 0.8798F, 0.8836F, 0.8873F, 0.8909F, 0.8945F, 0.8979F, 0.9013F, 0.9046F, 0.9078F, 0.9109F, 0.9139F, 0.9169F, 0.9198F, 0.9227F, 0.9254F, 0.9281F, 0.9307F, 0.9333F, 0.9358F, 0.9382F, 0.9406F, 0.9429F, 0.9452F, 0.9474F, 0.9495F, 0.9516F, 0.9536F, 0.9556F, 0.9575F, 0.9594F, 0.9612F, 0.9629F, 0.9646F, 0.9663F, 0.9679F, 0.9695F, 0.971F, 0.9725F, 0.9739F, 0.9753F, 0.9766F, 0.9779F, 0.9791F, 0.9803F, 0.9815F, 0.9826F, 0.9837F, 0.9848F, 0.9858F, 0.9867F, 0.9877F, 0.9885F, 0.9894F, 0.9902F, 0.991F, 0.9917F, 0.9924F, 0.9931F, 0.9937F, 0.9944F, 0.9949F, 0.9955F, 0.996F, 0.9964F, 0.9969F, 0.9973F, 0.9977F, 0.998F, 0.9984F, 0.9986F, 0.9989F, 0.9991F, 0.9993F, 0.9995F, 0.9997F, 0.9998F, 0.9999F, 0.9999F, 1.0F, 1.0F};

    public ButtonProgressBar(Context context) {
        super(context);
        this.mLoaderType = github.ishaan.buttonprogressbar.ButtonProgressBar.Type.DETERMINATE;
        this.mProgressIncrementFactor = 2;
        this.mCounterFactor = 0;
        this.mMaxCounterFactor = INDETERMINATE_ANIMATION_VALUES.length - 1;
    }

    public ButtonProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLoaderType = github.ishaan.buttonprogressbar.ButtonProgressBar.Type.DETERMINATE;
        this.mProgressIncrementFactor = 2;
        this.mCounterFactor = 0;
        this.mMaxCounterFactor = INDETERMINATE_ANIMATION_VALUES.length - 1;
        this.init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        this.mTextSize = this.getResources().getDimensionPixelSize(dimen.text_size_default);
        TypedArray attributeArray = this.getContext().getTheme().obtainStyledAttributes(attrs, styleable.ButtonProgressBar, 0, 0);
        this.mTickBitmap = this.getBitmapFromVectorDrawable(context, drawable.ic_done);

        try {
            this.mBackgroundColor = attributeArray.getColor(styleable.ButtonProgressBar_bgColor, DEFAULT_BGCOLOR);
            this.mProgressColor = attributeArray.getColor(styleable.ButtonProgressBar_progColor, DEFAULT_PROGCOLOR);
            this.mTextColor = attributeArray.getColor(styleable.ButtonProgressBar_textColor, -1);
            this.mTextSize = attributeArray.getDimensionPixelSize(styleable.ButtonProgressBar_textSize, this.mTextSize);
            if(attributeArray.getString(styleable.ButtonProgressBar_text) != null) {
                this.mInitialText = attributeArray.getString(styleable.ButtonProgressBar_text);
            }

            int type = attributeArray.getInt(styleable.ButtonProgressBar_type, 0);
            switch(type) {
                case 0:
                    this.mLoaderType = github.ishaan.buttonprogressbar.ButtonProgressBar.Type.DETERMINATE;
                    break;
                case 1:
                    this.mLoaderType = github.ishaan.buttonprogressbar.ButtonProgressBar.Type.INDETERMINATE;
            }
        } finally {
            attributeArray.recycle();
        }

        this.backgroundPaint();
        this.progressPaint();
    //    this.textPaint();
    }

    public void backgroundPaint() {
        this.mBackgroundPaint = new Paint(1);
        this.mBackgroundPaint.setColor(this.mBackgroundColor);
        this.mBackgroundPaint.setStyle(Style.FILL_AND_STROKE);
    }

    public void progressPaint() {
        this.mProgressPaint = new Paint(1);
        this.mProgressPaint.setColor(this.mProgressColor);
        this.mProgressPaint.setStyle(Style.FILL_AND_STROKE);
    }

    public void textPaint() {
        this.mTextPaint = new Paint(1);
        this.mTextPaint.setColor(this.mTextColor);
        this.mTextPaint.setTextSize((float)this.mTextSize);
        this.mTextPaint.setStyle(Style.FILL_AND_STROKE);
    }

    public void setBackgroundColor(int bgColor) {
        this.mBackgroundColor = bgColor;
        this.mBackgroundPaint.setColor(bgColor);
        this.invalidate();
    }

    public void setProgressColor(int progColor) {
        this.mProgressColor = progColor;
        this.mProgressPaint.setColor(progColor);
        this.invalidate();
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
        this.mTextPaint.setColor(textColor);
        this.invalidate();
    }

    public void setTextSize(int size) {
        this.mTextSize = size;
        this.mTextPaint.setTextSize((float)size);
        this.invalidate();
    }

    private Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(context, drawableId);
        if(VERSION.SDK_INT < 21) {
            drawable = DrawableCompat.wrap(drawable).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height;
        switch(heightMode) {
            case -2147483648:
            case 0:
            default:
                height = this.getResources().getDimensionPixelSize(dimen.default_height);
                break;
            case 1073741824:
                height = specHeight;
        }

        int width;
        switch(widthMode) {
            case -2147483648:
            case 0:
            default:
                width = this.getResources().getDimensionPixelSize(dimen.default_width);
                break;
            case 1073741824:
                width = specWidth;
        }

        this.setMeasuredDimension(width, height);
    }

    public void setLoaderType(github.ishaan.buttonprogressbar.ButtonProgressBar.Type mLoaderType) {
        this.mLoaderType = mLoaderType;
    }

    public github.ishaan.buttonprogressbar.ButtonProgressBar.Type getLoaderType() {
        return this.mLoaderType;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.mLoaderType == github.ishaan.buttonprogressbar.ButtonProgressBar.Type.DETERMINATE) {
            this.drawDeterminateProgress(canvas);
        } else {
            this.drawIndeterminateProgress(canvas);
        }

    }

    private void drawIndeterminateProgress(Canvas canvas) {
        switch(this.indeterminateState) {
            case -1:
                this.onDrawInit(canvas);
                break;
            case 0:
                this.onDrawFinished(canvas);
                break;
            case 1:
                this.updateProgress();
                this.onDrawProgress(canvas);
                this.invalidate();
        }

    }

    public void updateProgress() {
        if(this.mProgressIncrementFactor == 4) {
            this.mProgressIncrementFactor = 1;
        }

        if(this.mCounterFactor >= this.mMaxCounterFactor) {
            this.mCounterFactor = 1;
        }

        this.mProgress = INDETERMINATE_ANIMATION_VALUES[this.mCounterFactor] * 100.0F;
        if(this.mProgress <= 1.0F) {
            this.mProgress = 0.0F;
        }

        this.mCounterFactor += this.mProgressIncrementFactor;
        ++this.mProgressIncrementFactor;
    }

    private void drawDeterminateProgress(Canvas canvas) {
        if(this.mProgress == 0.0F) {
            this.onDrawInit(canvas);
        } else if(this.mProgress > 0.0F && this.mProgress < 100.0F) {
            this.onDrawProgress(canvas);
        } else {
            this.onDrawFinished(canvas);
        }

    }

    public void onDrawInit(Canvas canvas) {
        RectF bgRectf = new RectF(0.0F, 0.0F, (float)canvas.getWidth(), (float)canvas.getHeight());
        canvas.drawRoundRect(bgRectf, (float)this.mCornerRadius, (float)this.mCornerRadius, this.mBackgroundPaint);
        Rect bounds = new Rect();
//        this.mTextPaint.getTextBounds(this.mInitialText, 0, this.mInitialText.length(), bounds);
        int xPos = (canvas.getWidth() - bounds.width()) / 2;
//        int textAdjust = (int)(this.mTextPaint.descent() + this.mTextPaint.ascent()) / 2;
   //     int yPos = canvas.getHeight() / 2 - textAdjust;
     //   canvas.drawText(this.mInitialText, (float)xPos, (float)yPos, this.mTextPaint);
    }

    public void onDrawProgress(Canvas canvas) {
        RectF bgRectf = new RectF(0.0F, 0.0F, (float)canvas.getWidth(), (float)canvas.getHeight());
        canvas.drawRoundRect(bgRectf, (float)this.mCornerRadius, (float)this.mCornerRadius, this.mBackgroundPaint);
        float progressPoint = (float)canvas.getWidth() / 100.0F * this.mProgress;
        RectF progRect = new RectF(0.0F, 0.0F, progressPoint, (float)canvas.getHeight());
        canvas.drawRoundRect(progRect, (float)this.mCornerRadius, (float)this.mCornerRadius, this.mProgressPaint);
    }

    public void onDrawFinished(Canvas canvas) {
        RectF bgRectf = new RectF(0.0F, 0.0F, (float)canvas.getWidth(), (float)canvas.getHeight());
        canvas.drawRoundRect(bgRectf, (float)this.mCornerRadius, (float)this.mCornerRadius, this.mBackgroundPaint);
        RectF progRect = new RectF(0.0F, 0.0F, (float)canvas.getWidth(), (float)canvas.getHeight());
        canvas.drawRoundRect(progRect, (float)this.mCornerRadius, (float)this.mCornerRadius, this.mProgressPaint);
//        if(this.mTickBitmap != null) {
//            int centerX = (canvas.getWidth() - this.mTickBitmap.getWidth()) / 2;
//            int centerY = (canvas.getHeight() - this.mTickBitmap.getHeight()) / 2;
//            canvas.drawBitmap(this.mTickBitmap, (float)centerX, (float)centerY, (Paint)null);
//        }

    }

    public void setProgress(int currentProgress) {
        if(this.mLoaderType == github.ishaan.buttonprogressbar.ButtonProgressBar.Type.DETERMINATE) {
            this.mProgress = (float)currentProgress;
            this.invalidate();
        }

    }

    public void  startLoader() {
        this.indeterminateState = 1;
        this.invalidate();
    }

    public void stopLoader() {
        this.indeterminateState = 0;
        this.invalidate();
    }

    public void reset() {
        if(this.mLoaderType == github.ishaan.buttonprogressbar.ButtonProgressBar.Type.INDETERMINATE) {
            this.indeterminateState = -1;
        }

        this.mProgress = 0.0F;
        this.invalidate();
    }

    public static enum Type {
        DETERMINATE,
        INDETERMINATE;

        private Type() {
        }
    }
}
