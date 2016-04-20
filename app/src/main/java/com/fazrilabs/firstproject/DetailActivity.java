package com.fazrilabs.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.fazrilabs.firstproject.model.Siswa;

import org.w3c.dom.Text;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // mengganti judul activity
        setTitle("Halaman Detail");

        // menampilkan tombol back di kiri atas
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ambil view dari layout
        TextView namaTextView = (TextView) findViewById(R.id.nama);
        TextView hpTextView = (TextView) findViewById(R.id.hp);
        TextView genderTextView = (TextView) findViewById(R.id.gender);
        TextView jenjangTextView = (TextView) findViewById(R.id.jenjang);
        TextView hobiTextView = (TextView) findViewById(R.id.hobi);
        TextView alamatTextView = (TextView) findViewById(R.id.alamat);

        // ambil data yang dikirim dari MainActivity
        long id = getIntent().getLongExtra("id", 0);

        Siswa siswa = new Select().from(Siswa.class).where("id=?", id).executeSingle();

        String nama = siswa.nama; //getIntent().getStringExtra("nama");
        long hp = siswa.hp;//getIntent().getIntExtra("hp", 0);
        String gender = siswa.gender;//getIntent().getStringExtra("gender");
        String jenjang = siswa.jenjang;//getIntent().getStringExtra("jenjang");
        String hobi = siswa.hobi;//getIntent().getStringExtra("hobi");
        String alamat = siswa.alamat;//getIntent().getStringExtra("alamat");

        // Set data ke view
        namaTextView.setText(nama);
        hpTextView.setText(Long.toString(hp));
        genderTextView.setText(gender);
        jenjangTextView.setText(jenjang);
        hobiTextView.setText(hobi);
        alamatTextView.setText(alamat);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // check apakah home button
        if(item.getItemId() == android.R.id.home) {
            // destroy detail activty
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
