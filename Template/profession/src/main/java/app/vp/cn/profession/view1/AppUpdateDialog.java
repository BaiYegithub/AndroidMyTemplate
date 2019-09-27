package app.vp.cn.profession.view1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;


import java.util.List;

import app.vp.cn.common.util.UIUtils;
import app.vp.cn.profession.R;

/**
* @author Mr.Bai
* @version 创建时间：2017年2月13日
*/
public class AppUpdateDialog extends Dialog{

	ViewGroup updateContainer;
	TextView updateTxt;
	
	public AppUpdateDialog(Context context) {
		super(context, R.style.dialog_style_appUpdate);
		
		
		View v = View.inflate(context, R.layout.layout_dialog_app_update, null);
		
		setContentView(v,
				new ViewGroup.LayoutParams(UIUtils.dip2px(context, 240),
				LayoutParams.WRAP_CONTENT));
		
		setCancelable(false);
		
		v.findViewById(R.id.btn_close)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
						if(null != onUpdateListener){
							onUpdateListener.onCancel();
						}
					}
				});
		updateTxt = (TextView) findViewById(R.id.dialog_update_txt);
		updateContainer = (ViewGroup) findViewById(R.id.content_container);
		
		v.findViewById(R.id.btn_update)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if(null != onUpdateListener){
							onUpdateListener.onUpdate();
						}
					}
				});
	}
	public void setUpdateContent(List<String> updateContents){
		if(null == updateContents ||
				updateContents.isEmpty()){
			return;
		}
		updateContainer.removeAllViews();
		for(String s: updateContents){
			setUpdateContent(s);
		}
	}
	public void setUpdateContent(String s){
		TextView tv = new TextView(getContext());
		tv.setPadding(0,UIUtils.dip2px(getContext(), 10), 0, 0);
		tv.setTextColor(Color.parseColor("#333333"));
		tv.setText(s);
		updateContainer.addView(tv);
	}
	private UpdateListener onUpdateListener;
	public void setOnUpdateListener(UpdateListener onUpdateListener){
		this.onUpdateListener = onUpdateListener;
	}
	public interface UpdateListener{
		void onUpdate();
		
		void onCancel();
	}
}
