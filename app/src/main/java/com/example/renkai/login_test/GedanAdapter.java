package com.example.renkai.login_test;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.List;


public class GedanAdapter extends ArrayAdapter {
    private final int resourceId;
    public GedanAdapter(Context context, int textViewResourceId, List<Gequ> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Gequ gequ = (Gequ) getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.gequName = (TextView) view.findViewById(R.id.gequming);
            viewHolder.Number = (TextView) view.findViewById(R.id.xuhao);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.gequName.setText(gequ.getName());
        Log.d(getContext().getPackageName().toString(), gequ.getName());
        viewHolder.Number.setText(String.valueOf(gequ.getNumber()));
        return view;

    }
}
