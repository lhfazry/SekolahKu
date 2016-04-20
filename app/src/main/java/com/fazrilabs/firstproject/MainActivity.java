package com.fazrilabs.firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.fazrilabs.firstproject.model.Siswa;

public class MainActivity extends AppCompatActivity {
    EditText namaEditText;
    EditText hpEditText;
    RadioGroup genderRadioGroup;
    Spinner jenjangSpinner;
    CheckBox membacaCheckBox;
    CheckBox menulisCheckBox;
    CheckBox menggambarCheckBox;
    EditText alamatEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setTitle("Input Data Siswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ambil view dari layout
        namaEditText = (EditText) findViewById(R.id.nama);
        hpEditText = (EditText) findViewById(R.id.hp);
        genderRadioGroup = (RadioGroup) findViewById(R.id.gender);
        jenjangSpinner = (Spinner) findViewById(R.id.jenjang);
        membacaCheckBox = (CheckBox) findViewById(R.id.membaca);
        menulisCheckBox = (CheckBox) findViewById(R.id.menulis);
        menggambarCheckBox = (CheckBox) findViewById(R.id.menggambar);
        alamatEditText = (EditText) findViewById(R.id.alamat);
        Button daftarButton = (Button) findViewById(R.id.button);

        // action ketika button diklik
        daftarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add) {
            // ambil data nama
            String nama = namaEditText.getText().toString();
            // ambil data hp
            String hp = hpEditText.getText().toString();
            // ambil data gender
            int genderId = genderRadioGroup.getCheckedRadioButtonId();
            String gender = null;

            if(genderId == R.id.pria) {
                gender = "Pria";
            }
            else if(genderId == R.id.wanita) {
                gender = "Wanita";
            }

            // ambil data jenjang
            String jenjang = jenjangSpinner.getSelectedItem().toString();
            // ambil data hobi
            String hobi = "";

            if(membacaCheckBox.isChecked()) {
                hobi += "Membaca, ";
            }

            if(menulisCheckBox.isChecked()) {
                hobi += "Menulis, ";
            }

            if(menggambarCheckBox.isChecked()) {
                hobi += "Menggambar";
            }

            // ambil data alamat
            String alamat = alamatEditText.getText().toString();

            // nama wajib diisi
            if(nama.isEmpty()) {
                namaEditText.setError("Nama harus diisi");
                return true;
            }

            // gender wajib diisi
            if(genderRadioGroup.getCheckedRadioButtonId() == -1)
            {
                Toast.makeText(MainActivity.this, "Gender harus diisi", Toast.LENGTH_SHORT).show();
                return true;
            }

            // tampilkan alert
                /*Toast.makeText(MainActivity.this,
                        "Nama: " + nama + "\n" +
                        "HP: " + hp + "\n" +
                        "Gender: " + gender + "\n" +
                        "Jenjang: " + jenjang + "\n" +
                        "Hobi: " + hobi + "\n" +
                        "Alamat: " + alamat,

                        Toast.LENGTH_LONG).show(); */

            // save ke database
            Siswa siswa = new Siswa();
            siswa.nama = nama;
            siswa.hp = Long.parseLong(hp);
            siswa.gender = gender;
            siswa.jenjang = jenjang;
            siswa.hobi = hobi;
            siswa.alamat = alamat;
            siswa.save();

            // destroy main activity dan kembali ke list
            finish();

            // kirim parameter dari screen MainActivity ke screen DetailActivity
            /*
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("nama", nama);

            try {
                intent.putExtra("hp", Integer.parseInt(hp));
            }
            catch (Exception e) {
                Toast.makeText(MainActivity.this, "number error:" + hp, Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "number error");
            }

            intent.putExtra("gender", gender);
            intent.putExtra("jenjang", jenjang);
            intent.putExtra("hobi", hobi);
            intent.putExtra("alamat", alamat);
            startActivity(intent); */
        }
        else if(item.getItemId() == android.R.id.home) {
            // destroy list activity
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}