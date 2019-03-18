package app.vp.cn.frame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.common.util.GlideUtil;
import app.vp.cn.common.view.CircleImageView;
import app.vp.cn.frame.R;
import app.vp.cn.frame.bean.QaBean;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * author : by
 * date: 2018/11/28 0028  下午 5:29.
 * describe
 */

public class QaDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QaBean> list = new ArrayList<>();
    private Context context;

    private final int MINE = 0;  //右侧我的布局
    private final int TEACHER = 1; //左侧教师布局

    public QaDetailAdapter(Context context) {
        this.context = context;
    }

    public void setQaBeanList(List<QaBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == MINE) {
            return new MineViewHolder(LayoutInflater.from(context).inflate(R.layout.item_qa_right, parent, false));
        } else {
            return new TeacherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_qa_left, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MineViewHolder) {
            MineViewHolder mineViewHolder = (MineViewHolder) holder;
            mineViewHolder.tv_time_mine.setText(list.get(position).getTime());
            mineViewHolder.tv_question.setText(list.get(position).getContent());
            GlideUtil.setImageTiling(mineViewHolder.iv_mine, list.get(position).getUrl(), R.drawable.headimg_default);

        } else {
            TeacherViewHolder teacherViewHolder = (TeacherViewHolder) holder;
            teacherViewHolder.tv_teacherName.setText(list.get(position).getTeacherName());
            teacherViewHolder.tv_time_teacher.setText(list.get(position).getTime());
            teacherViewHolder.tv_answer_left.setText(list.get(position).getContent());
            GlideUtil.setImageTiling(teacherViewHolder.iv_teacher, list.get(position).getUrl(), R.drawable.headimg_default);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getComeFrom() == 0 ? MINE : TEACHER;

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time_mine_itemQa)
        TextView tv_time_mine;
        @BindView(R.id.iv_mine_itemQa)
        CircleImageView iv_mine;
        @BindView(R.id.tv_question_mine_itemQa)
        TextView tv_question;

        public MineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TeacherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time_left_itemQa)
        TextView tv_time_teacher;
        @BindView(R.id.iv_left_itemQa_left)
        CircleImageView iv_teacher;
        @BindView(R.id.tv_teacherName_itemQa_left)
        TextView tv_teacherName;
        @BindView(R.id.tv_answer_left_itemQa)
        TextView tv_answer_left;

        public TeacherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
