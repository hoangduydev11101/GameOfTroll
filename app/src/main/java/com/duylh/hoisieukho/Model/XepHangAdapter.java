package com.duylh.hoisieukho.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duylh.thanhtrolltrotai.R;

import java.util.List;

/**
 * Created by Admin on 28/02/2017.
 */

public class XepHangAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Xephang> list;

    public XepHangAdapter(Context context, int layout, List<Xephang> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_xh, null);

        TextView txtName = (TextView) convertView.findViewById(R.id.txtNameRank);
        TextView txtPoint = (TextView) convertView.findViewById(R.id.txtPointRank);

        txtName.setText(list.get(position).Player);
        txtPoint.setText(list.get(position).Point+"");

        return convertView;
    }
}
