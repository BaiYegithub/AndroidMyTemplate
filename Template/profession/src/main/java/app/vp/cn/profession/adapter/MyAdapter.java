package app.vp.cn.profession.adapter;

import android.content.Context;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.vp.cn.profession.R;
import app.vp.cn.profession.dslv.DragSortListView;

public class MyAdapter extends DragAdapter implements DragSortListView.DropListener{

    private List<String> data;
    private Context context;

    public MyAdapter(List<String> data, Context context) {
        super(data);
        this.data = data;
        this.context = context;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(context, R.layout.drag_list_item, null);
            holder.tvDrag = (TextView) convertView.findViewById(R.id.tv_drag_list_item_text);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_drag_list_item_text);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tvName.setText(data.get(position));

        return convertView;
    }

    @Override
    public void drop(int from, int to) {
        Log.i("baiye", "drop:交换之前的顺序是 "+from+"交换之后的顺序是"+to);
    }

    private class Holder {
        TextView tvName;
        TextView tvDrag;
    }


}
