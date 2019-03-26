package app.vp.cn.profession.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.vp.cn.profession.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : by
 * date: 2019/2/27 0027  下午 2:29.
 * describe
 */

public class RcvAdapter extends RecyclerView.Adapter {

    private List<String> list;

    public RcvAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv_text.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_text)
        TextView tv_text;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
