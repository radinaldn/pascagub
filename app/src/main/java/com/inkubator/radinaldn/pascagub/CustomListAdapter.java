package com.inkubator.radinaldn.pascagub;

/**
 * Created by radinaldn on 13/03/18.
 */

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CustomListAdapter extends ArrayAdapter<Kegiatan>{

    //get server connection
    Koneksi koneksi = new Koneksi();
    String image_dir = koneksi.getImagesDir();

    ArrayList<Kegiatan> kegiatan;
    Context context;
    int resource;
    public CustomListAdapter(Context context, int resource, ArrayList<Kegiatan> kegiatan) {
        super(context, resource, kegiatan);
        this.kegiatan = kegiatan;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_layout, null, true);
        }
        Kegiatan kegiatan = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewKegiatan);
        Picasso.with(context).load(image_dir+"kegiatan/"+kegiatan.getFoto()).into(imageView);

        TextView tvnamakeg = (TextView) convertView.findViewById(R.id.tvnamakeg);
        tvnamakeg.setText(kegiatan.getNama_keg());

        TextView tvtanggalkeg = (TextView) convertView.findViewById(R.id.tvtanggalkeg);
        tvtanggalkeg.setText(kegiatan.getTanggal_keg());

        TextView tvjeniskeg = (TextView) convertView.findViewById(R.id.tvjeniskeg);
        tvjeniskeg.setText(kegiatan.getJenis_keg());

        TextView tvnamakeldesa = (TextView) convertView.findViewById(R.id.tvkelurahan);
        tvnamakeldesa.setText(kegiatan.getNama_keldesa());

        return convertView;
    }
}
