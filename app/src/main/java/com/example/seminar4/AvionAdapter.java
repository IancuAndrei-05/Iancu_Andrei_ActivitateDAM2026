package com.example.seminar4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class AvionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Avion> listaAvioane;
    @Override
    public int getCount() {
        return listaAvioane.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAvioane.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public AvionAdapter(Context context, ArrayList<Avion> listaAvioane) {
        this.context = context;
        this.listaAvioane = listaAvioane;
    }
}
