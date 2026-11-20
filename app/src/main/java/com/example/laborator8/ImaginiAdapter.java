package com.example.laborator8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/** Lab 8: CustomAdapter pentru ListView - afiseaza imaginea si descrierea. */
public class ImaginiAdapter extends BaseAdapter {

    private final List<ImaginiDomeniu> imagini;
    private final LayoutInflater inflater;

    public ImaginiAdapter(Context context, List<ImaginiDomeniu> imagini) {
        this.imagini = imagini;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public ImaginiDomeniu getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder h;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_imagine, parent, false);
            h = new ViewHolder();
            h.ivImagine = convertView.findViewById(R.id.ivImagine);
            h.tvDescriere = convertView.findViewById(R.id.tvDescriere);
            convertView.setTag(h);
        } else {
            h = (ViewHolder) convertView.getTag();
        }

        ImaginiDomeniu item = getItem(position);
        h.tvDescriere.setText(item.getTextAfisat());
        if (item.getImagine() != null) {
            h.ivImagine.setImageBitmap(item.getImagine());
        } else {
            // imaginea inca se descarca (sau nu a putut fi descarcata)
            h.ivImagine.setImageResource(android.R.drawable.ic_menu_gallery);
        }
        return convertView;
    }

    private static class ViewHolder {
        ImageView ivImagine;
        TextView tvDescriere;
    }
}
