package app.vp.cn.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.ui.R;

/**
 * Created by baiye
 * Date: 2020/3/23
 * Time: 17:43
 * Description:
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> stringList = new ArrayList<>();

    public ItemAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.tvItem.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{

        private  TextView tvItem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item_rcv);
        }
    }
}
