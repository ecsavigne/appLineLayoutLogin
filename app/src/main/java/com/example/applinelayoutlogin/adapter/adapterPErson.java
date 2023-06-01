package com.example.applinelayoutlogin.adapter;

import android.app.Activity;
import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.applinelayoutlogin.R;

public class adapterPErson extends BaseAdapter {
    protected LayoutInflater inflater;
    //protected LinearLayout lay;
    int len, cont;
    protected Activity context;
    protected String[] obj;

    public adapterPErson(@NonNull Activity context, String[] objects, int band) {
        //super(context, R.layout.layout_lv_test, objects);

        len = objects.length;
        this.obj = objects;
        cont = band;
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
        LinearLayout lay ;

        if(rowView == null ) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.layout_lv_test, parent, true);
        }

        lay = rowView.findViewById(R.id.id_linlay_lv_test_num);


        // Setup.
        TextView textView1 = new TextView(rowView.getContext());
        textView1.setText(this.obj[position]);
        textView1.setTextSize(24);
        textView1.setTextColor(Color.parseColor("#BAC2CD"));
        if(position % 2 == 0) {
            //textView1.setBackgroundColor(Color.parseColor("#FF303C4E"));
            //if(this.cont == 1) {
               // textView1.setBackgroundColor(Color.parseColor("#202732"));
           // } else {
                textView1.setBackgroundColor(Color.parseColor("#FF303C4E"));
            //}
            //par = 1;
        } else {
           // if(this.cont == 1) {
                textView1.setBackgroundColor(Color.parseColor("#202732"));
            //} else {
               // textView1.setBackgroundColor(Color.parseColor("#FF404C5E"));
           // }
            //par = 0;
        }

        cont ++;
        lay.addView(textView1);

        //Devolver la vista
        return rowView;

    }

}
