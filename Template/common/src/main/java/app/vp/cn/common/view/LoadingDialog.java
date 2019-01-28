package app.vp.cn.common.view;


import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.widget.TextView;

import app.vp.cn.common.R;
import app.vp.cn.common.util.UIUtils;


/**
 * @author
 */
public class LoadingDialog extends Dialog {

    private TextView tips_loading_msg;

    private String message = null;
    private Activity context;


    public LoadingDialog(Activity context) {
        super(context, R.style.customDialog);
        this.context = context;
        this.setCanceledOnTouchOutside(false);
        this.setContentView(R.layout.dialog);
      /*  pro_load = (ProgressBar) findViewById(R.id.pro_load);
        pro_load.setVisibility(View.VISIBLE);*/
        tips_loading_msg = (TextView) findViewById(R.id.id_tv_loadingmsg);
        if (!TextUtils.isEmpty(message)) {
            tips_loading_msg.setText(this.message);
        }
    }

    public void setText(int resId) {
        setText(getContext().getResources().getString(resId));
    }

    public void setText(String message) {
        this.message = message;
        tips_loading_msg.setText(this.message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 显示默认对话框
     *
     * @MethodName: showDialog
     * @Description: TODO
     * @author Kyla
     * @date 2014年5月21日 下午5:19:50
     */
    public void showDialog() {
        if (context != null && !context.isFinishing()) {
            this.show();
        }
        if (tips_loading_msg != null && !TextUtils.isEmpty(message)) {
            tips_loading_msg.setText(this.message);
        }
        this.setCanceledOnTouchOutside(false);
    }

    /**
     * 显示带字符串对话框
     *
     * @param resId
     * @MethodName: showDialog
     * @Description: TODO
     * @author Kyla
     * @date 2014年5月21日 下午5:20:18
     */
    public void showDialog(int resId) {
        this.message = UIUtils.getContext().getResources().getString(resId);
        this.setMessage(message);
        if (context != null && !context.isFinishing()) {
            this.show();
        }

    }

    public void showDialog(String msg) {
        this.setMessage(msg);
        if (tips_loading_msg != null && !TextUtils.isEmpty(message)) {
            tips_loading_msg.setText(this.message);
        }
        if (context != null && !context.isFinishing()) {
            this.show();
        }
    }

    /**
     * 显示是否可按返回键取消对话框
     *
     * @param isback
     * @MethodName: showDialog
     * @Description: TODO
     * @author Kyla
     * @date 2014年5月21日 下午5:20:43
     */
    public void showDialog(boolean isback) {
        this.setCancelable(!isback);
        if (tips_loading_msg != null && !TextUtils.isEmpty(message)) {
            tips_loading_msg.setText(this.message);
        }
        if (context != null && !context.isFinishing()) {
            this.show();
        }
    }

    /**
     * 显示帶字符串并設置是否可按返回鍵取消对话框
     *
     * @param resId
     * @param isback true是不相应back的
     * @MethodName: showDialog
     * @Description: TODO
     * @author Kyla
     * @date 2014年5月21日 下午5:21:17
     */
    public void showDialog(int resId, boolean isback) {
        this.message = getContext().getResources().getString(resId);
        this.setMessage(message);
        if (tips_loading_msg != null && !TextUtils.isEmpty(message)) {
            tips_loading_msg.setText(this.message);
        }
        this.setCancelable(!isback);
        if (context != null && !context.isFinishing()) {
            this.show();
        }
    }

    /**
     * 关闭对话框
     *
     * @MethodName: cancelDialog
     * @Description: TODO
     * @author Kyla
     * @date 2014年5月21日 下午5:21:59
     */
    public void cancelDialog() {
        if (this != null) {
            try {
                this.dismiss();
            } catch (Exception e) {
            }
        }
    }
}
