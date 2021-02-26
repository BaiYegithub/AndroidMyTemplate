package app.vp.cn.profession.timePicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;


/**
 * Created by pan on 2017/5/11.
 */
public abstract class BaseWheelPick
        extends LinearLayout
        implements OnWheelChangedListener
        , OnWheelScrollListener {

    protected int textColor = 0xffdddddd;
    protected int selectColor = 0xff444444;
    protected int split = 0xffbbbbbb;
    protected float splitHeight = 0.5f;
    protected Context ctx;
    private GenWheelText genView;

    public BaseWheelPick(Context context) {
        this(context, null);
    }

    public BaseWheelPick(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs(context, attrs);
        init(context);
    }

    protected abstract void setAttrs(Context context, AttributeSet attrs);

    /*public void setSelectTextColor(int textColor, int selectTextColor){
         this.selectColor=selectTextColor;
         this.textColor=textColor;
    }*/
    private void init(Context context) {
        genView = new GenWheelText(textColor);
        this.ctx = context;
        LayoutInflater.from(context).inflate(getLayout(), this);
    }

    protected void setWheelListener(WheelView wheelView, Object[] data, boolean isCyclic) {
        WheelGeneralAdapter viewAdapter = new WheelGeneralAdapter(ctx, genView);
        if (data[0] instanceof Integer) {
            viewAdapter.setData(convertData(wheelView, (Integer[]) data));
        } else if (data[0] instanceof String) {
            viewAdapter.setData(convertData(wheelView, (String[]) data));
        } else {
            viewAdapter.setData(data);
        }
        //没有单独设置的情况就设置
        if (wheelView.getTextColor() == 0 && wheelView.getSelectTextColor() == 0) {
            wheelView.setSelectTextColor(textColor, selectColor);
        }
        wheelView.setCyclic(isCyclic);
        wheelView.setViewAdapter(viewAdapter);
        wheelView.addChangingListener(this);
        wheelView.addScrollingListener(this);
    }

    protected String[] convertData(WheelView wheelView, Integer[] data) {
        return new String[0];
    }

    protected String[] convertData(WheelView wheelView, String[] data) {
        return new String[0];
    }

    protected abstract int getLayout();

    protected abstract int getItemHeight();

    protected abstract void setData(Object[] datas);

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(splitHeight);
        paint.setColor(split);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        int itemHeight = getItemHeight();

        for (int i = 0; i < 5; i++) {
            canvas.drawLine(0, (i + 1) * itemHeight, getWidth(), (i + 1) * itemHeight, paint);
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {

    }

}
