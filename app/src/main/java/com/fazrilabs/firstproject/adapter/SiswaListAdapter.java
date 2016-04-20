package com.fazrilabs.firstproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fazrilabs.firstproject.R;
import com.fazrilabs.firstproject.model.Siswa;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by fazri on 3/20/2016.
 */
public class SiswaListAdapter extends ArrayAdapter<Siswa> {
    public SiswaListAdapter(Context context, List<Siswa> siswaList) {
        super(context, R.layout.list_item_siswa, siswaList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_siswa, null);
        }

        // ambil view dari layout
        TextView namaTextView = (TextView) view.findViewById(R.id.nama);
        TextView jenjangTextView = (TextView) view.findViewById(R.id.jenjang);
        TextView genderTextView = (TextView) view.findViewById(R.id.gender);
        TextView hpTextView = (TextView) view.findViewById(R.id.hp);

        // ambil data pada posisi tertentu
        Siswa siswa = getItem(position);

        // set data ke view
        namaTextView.setText(siswa.nama);
        jenjangTextView.setText(siswa.jenjang);
        genderTextView.setText(siswa.gender);
        hpTextView.setText(Long.toString(siswa.hp));

        return view;
    }

    public void delete(int position) {

    }
}
