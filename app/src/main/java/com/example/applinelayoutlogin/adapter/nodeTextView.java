package com.example.applinelayoutlogin.adapter;

import android.widget.TextView;

import com.example.applinelayoutlogin.R;

public class nodeTextView {
    public TextView tv;
    /**
     * Apariencia del TextView
     * */
    public int mark;
    /**
     * indica si esta marcado o no
     * */
    public boolean _mark;
    //Metodos
    public nodeTextView() {
        this.tv = null;
        this._mark = false;
        this.mark = R.drawable.edit_mostrar_rounded;
    }

    public nodeTextView(TextView tv) {
        this.tv = tv;
        this._mark = false;
        this.mark = R.drawable.edit_mostrar_rounded;
    }

    public void f_mark() {
        this.mark = R.drawable.edittext_selecte;
        if(this.tv != null) {
            this.tv.setBackgroundResource(mark);
            this._mark = true;
        }
    }

    public void f_unmark() {
        this.mark = R.drawable.edit_mostrar_rounded;
        if(this.tv != null) {
            this.tv.setBackgroundResource(mark);
            this._mark = false;
        }
    }
}
