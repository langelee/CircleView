package wuliao.in.studio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.BitSet;

/**
 * Created by Administrator on 2014-08-23.
 */
public class CircleImageView  extends View {
    private Bitmap bitmap;
    private int borderWidth=0;
    private int borderColor= Color.BLACK;
    private int width,height;
    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setBitmap(Bitmap bitmap){
        this.bitmap=bitmap;
    }
    public void setBorderWidth(int borderWidth){
        this.borderWidth=borderWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height=getHeight();
        width=getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        bitmap=initBitmap(bitmap);
        BitmapShader bitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        canvas.drawCircle(width/2,height/2,Math.min(width/2,height/2),paint);
        if(borderWidth>0){
            Paint borderPaint=new Paint();
            borderPaint.setStyle(Paint.Style.STROKE);
            borderPaint.setColor(borderColor);
            paint.setAntiAlias(true);
            borderPaint.setStrokeWidth(borderWidth);
            canvas.drawCircle(width/2,height/2,Math.min(width/2,height/2)-borderWidth/2,borderPaint);
        }


    }

    private Bitmap initBitmap(Bitmap bitmap) {
        float scale=1.0f;
        int bitmapWidth=bitmap.getWidth();
        int bitmapHeight=bitmap.getHeight();
        if(bitmapWidth>width||bitmapHeight>height){
            float dy=(float)height/(float)bitmapHeight;
            float dx=(float)width/(float)bitmapWidth;
            scale=Math.max(dx,dy);
        }
        Matrix matrix=new Matrix();
        matrix.postScale(scale,scale);
        return Bitmap.createBitmap(bitmap,0,0,bitmapWidth,bitmapHeight,matrix,true);

    }

    public void setBorderColor(int color) {
        borderColor=color;
    }
}
