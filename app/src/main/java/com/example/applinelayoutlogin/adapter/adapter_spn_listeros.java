package com.example.applinelayoutlogin.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.applinelayoutlogin.R;
import java.util.Hashtable;


public class  adapter_spn_listeros extends BaseAdapter {
    protected LayoutInflater inflater;
    protected LinearLayout lay;
    int cont, par;
    protected Activity context;
    protected String[] obj;

    public adapter_spn_listeros(@NonNull Activity context, Hashtable<String, String> objects) {

        obj = new String[objects.values().size()];
        int i = 0;
        for (String s : objects.values()) {
            obj[i] = s;
            i++;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.obj.length;
    }

    @Override
    public Object getItem(int i) {
        return obj[i];
    }

    @Override
    public long getItemId(int i) {return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        TextView tv1;

        if(rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.layout_spn, parent, true);
        }

        //getter
        tv1 = rowView.findViewById(R.id.id_layout_spn_tv);

        //setter
        tv1.setText(this.obj[position]);
        tv1.setTextSize(24);

        return rowView;

    }
}
