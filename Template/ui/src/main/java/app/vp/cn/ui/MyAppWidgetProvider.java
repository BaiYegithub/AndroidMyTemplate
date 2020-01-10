package app.vp.cn.ui;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.SystemClock;
import android.support.annotation.Px;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RemoteViews;

import app.vp.cn.common.app.BaseApp;
import app.vp.cn.common.util.UIUtils;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class MyAppWidgetProvider extends AppWidgetProvider {

    private static final String CLICK_ACTION = "app.vp.cn.common.app.BaseApp.action.CLICK";


    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);


     /*   AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget_skin);

        remoteViews.setTextColor(R.id.tv_clock, BaseApp.getmContext().getResources().getColor(R.color.colorPrimary));

        remoteViews.setInt(R.id.tv_location,"setMaxLines",1);
        remoteViews.setInt(R.id.tv_location,"setMaxEms",5);
        remoteViews.setCharSequence(R.id.tv_clock,"setFormat12Hour","E");

        //remoteViews.setViewPadding(R.id.tv_clock,100,100,0,0);
        remoteViews.setTextViewTextSize(R.id.tv_clock, COMPLEX_UNIT_SP, 30);

        remoteViews.setImageViewBitmap(R.id.iv_1, translateBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pic_2), UIUtils.dip2px(BaseApp.getmContext(), 230), UIUtils.dip2px(BaseApp.getmContext(), 12)));
        remoteViews.setImageViewBitmap(R.id.iv_2, translateBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pic_1), UIUtils.dip2px(BaseApp.getmContext(), 180), UIUtils.dip2px(BaseApp.getmContext(), 65)));
        remoteViews.setImageViewBitmap(R.id.iv_weather, translateBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.weather_103), UIUtils.dip2px(BaseApp.getmContext(), 180), UIUtils.dip2px(BaseApp.getmContext(), 15)));

        //设置时间并移动
        Bitmap timeBitmap = Bitmap.createBitmap(UIUtils.dip2px(BaseApp.getmContext(), 140), UIUtils.dip2px(BaseApp.getmContext(), 50), Bitmap.Config.ARGB_8888);
        drawText(remoteViews, timeBitmap, R.id.iv_time, UIUtils.dip2px(BaseApp.getmContext(), 50), 1, context.getResources().getColor(R.color.white), "10:58", UIUtils.dip2px(BaseApp.getmContext(), 0),
                UIUtils.dip2px(BaseApp.getmContext(), 40));
        remoteViews.setImageViewBitmap(R.id.iv_time, translateBitmap(timeBitmap, UIUtils.dip2px(BaseApp.getmContext(), 8), UIUtils.dip2px(BaseApp.getmContext(), 18)));

        Bitmap outBitmap = Bitmap.createBitmap(UIUtils.dip2px(BaseApp.getmContext(), 308), UIUtils.dip2px(BaseApp.getmContext(), 163), Bitmap.Config.ARGB_8888);

        drawText(remoteViews, outBitmap, R.id.iv_bg, UIUtils.dip2px(BaseApp.getmContext(), 14), 1, context.getResources().getColor(R.color.white), "雷阵雨 -28/-16", UIUtils.dip2px(BaseApp.getmContext(), 0),
                UIUtils.dip2px(BaseApp.getmContext(), 95));

        drawText(remoteViews, outBitmap, R.id.iv_bg, UIUtils.dip2px(BaseApp.getmContext(), 14), 1, context.getResources().getColor(R.color.white), "08/10 星期三", UIUtils.dip2px(BaseApp.getmContext(), 0),
                UIUtils.dip2px(BaseApp.getmContext(), 120));

        drawText(remoteViews, outBitmap, R.id.iv_bg, UIUtils.dip2px(BaseApp.getmContext(), 14), 1, context.getResources().getColor(R.color.white), "乌鲁木齐牧试站", UIUtils.dip2px(BaseApp.getmContext(), 0),
                UIUtils.dip2px(BaseApp.getmContext(), 145));

        appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidgetProvider.class), remoteViews);
*/
      /*  Bitmap textBitmap = Bitmap.createBitmap(UIUtils.dip2px(BaseApp.getmContext(), 260), UIUtils.dip2px(BaseApp.getmContext(), 200), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(textBitmap);
        Paint paint = new TextPaint();
        paint.setTextSize(UIUtils.dip2px(BaseApp.getmContext(), 20));
        paint.setAntiAlias(true);
        paint.setDither(false);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.GREEN);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawColor(context.getResources().getColor(R.color.white));
        canvas.drawText("我新写的一个文字", 500, 200, paint);
        canvas.drawText("来来来", 100, 100, paint);

        remoteViews.setImageViewBitmap(R.id.iv_bg, textBitmap);*/


        if(intent.getAction().equals(CLICK_ACTION)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcbBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.haha);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.layout_widget);
                        remoteViews.setImageViewBitmap(R.id.iv,rotateBitmap(context,srcbBitmap,12));
                        Intent intentClick = new Intent();
                        intentClick.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentClick,0);
                        remoteViews.setOnClickPendingIntent(R.id.iv,pendingIntent);
                        appWidgetManager.updateAppWidget(new ComponentName(context,MyAppWidgetProvider.class),remoteViews);
                        SystemClock.sleep(300);
                }
            }).start();
        }
    }

    private Bitmap rotateBitmap(Context context, Bitmap srcbBitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap tmpBitmap = Bitmap.createBitmap(srcbBitmap,0,0,srcbBitmap.getWidth(),srcbBitmap.getHeight(),matrix,true);
        return tmpBitmap;
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        final int counter = appWidgetIds.length;
        for (int i = 0; i < counter; i++) {
            int appWidgetId = appWidgetIds[i];
            onAppWidgetUpdate(context, appWidgetManager, appWidgetId);
        }
    }

    private Bitmap translateBitmap(Bitmap baseBitmap, int x, int y) {
        Bitmap afterBitmap = Bitmap.createBitmap((int) (baseBitmap.getWidth() + x),
                (int) (baseBitmap.getHeight() + y), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 设置移动的距离
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        matrix.setTranslate(x, y);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        return afterBitmap;
    }

    private void drawText(RemoteViews remoteViews, Bitmap bitmap, int id, float textSize, float strokeWidth, int textColor, String textContent, float xPosition, float yPosition) {

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new TextPaint();
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        paint.setDither(false);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        //注意这里的坐标是文字的偏左下角的位置，baseline 的位置
        canvas.drawText(textContent, xPosition, yPosition, paint);
      //  canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        remoteViews.setImageViewBitmap(id, bitmap);
    }

    /**
     * 桌面小部件更新
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetId
     */
    private void onAppWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
        //“桌面小部件” 点击事件发送的intent广播
        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv_1, pendingIntent);
        appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidgetProvider.class), remoteViews);
    }
}
