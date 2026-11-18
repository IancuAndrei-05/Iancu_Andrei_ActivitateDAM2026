package com.example.laborator8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;


public class AvionAdapter extends BaseAdapter {

    private final Context context;
    private final List<Avion> avioane;
    private final LayoutInflater inflater;

    public AvionAdapter(Context context, List<Avion> avioane) {
        this.context = context;
        this.avioane = avioane;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return avioane.size();
    }

    @Override
    public Avion getItem(int position) {
        return avioane.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder h;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_avion, parent, false);
            h = new ViewHolder();
            h.tvInitiala = convertView.findViewById(R.id.tvInitiala);
            h.tvModel = convertView.findViewById(R.id.tvModel);
            h.tvDetalii = convertView.findViewById(R.id.tvDetalii);
            h.tvStatus = convertView.findViewById(R.id.tvStatus);
            h.rating = convertView.findViewById(R.id.rbItem);
            convertView.setTag(h);
        } else {
            h = (ViewHolder) convertView.getTag();
        }

        Avion a = getItem(position);
        h.tvInitiala.setText(a.getProducator().isEmpty() ? "?" : a.getProducator().substring(0, 1));
        h.tvModel.setText(context.getString(R.string.item_titlu, a.getProducator(), a.getModel()));
        h.tvDetalii.setText(context.getString(R.string.item_detalii, a.getTip(), a.getNrLocuri(),
                a.isAreWifi() ? context.getString(R.string.cu_wifi) : context.getString(R.string.fara_wifi)));
        h.tvStatus.setText(a.isInServiciu() ? R.string.in_serviciu : R.string.la_sol);
        h.tvStatus.setBackgroundResource(a.isInServiciu() ? R.drawable.bg_status_activ : R.drawable.bg_status_inactiv);
        h.rating.setRating(a.getRating());
        return convertView;
    }

    private static class ViewHolder {
        TextView tvInitiala, tvModel, tvDetalii, tvStatus;
        RatingBar rating;
    }
}
