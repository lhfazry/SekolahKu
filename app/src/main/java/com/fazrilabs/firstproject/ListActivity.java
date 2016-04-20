package com.fazrilabs.firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.fazrilabs.firstproject.adapter.SiswaListAdapter;
import com.fazrilabs.firstproject.model.Siswa;

import java.util.List;


public class ListActivity extends AppCompatActivity {
    SiswaListAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("Data Siswa");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ambil view dari layout
        ListView listView = (ListView) findViewById(R.id.listView);

        // ambil data dari database
        List<Siswa> siswaList = new Select().from(Siswa.class).execute();
        // definisikan array nama siswa sebanyak jumlah siswa
        String[] namaSiswaArray = new String[siswaList.size()];

        // copy semua nama siswa ke dalam namaSiswaArray
        for(int i=0; i<siswaList.size(); i++) {
            Siswa siswa = siswaList.get(i);
            namaSiswaArray[i] = siswa.nama;
        }

        // mendefinisikan adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                namaSiswaArray);

        // pakai custom adapter
        adapter2 = new SiswaListAdapter(this, siswaList);

        //pasangkan adapter ke listView
        listView.setAdapter(adapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Siswa siswa = adapter2.getItem(position);

                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("id", siswa.getId());
                startActivity(intent);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listView) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Siswa siswa = (Siswa) lv.getItemAtPosition(acmi.position);

            menu.add(Menu.NONE, 1, Menu.NONE, "Delete");
            menu.add(Menu.NONE, 2, Menu.NONE, "Update");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == 1) {
            // delete
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Siswa siswa = adapter2.getItem(info.position);

            // delete dari listView
            adapter2.remove(siswa);

            // delete dari database
            new Delete().from(Siswa.class).where("id=?", siswa.getId()).execute();

            // refresh listView
            adapter2.notifyDataSetChanged();
        }
        else if(item.getItemId() == 2) {
            // update
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add) {
            // pindah dari scren list(ListActivity) ke screen form (MainActivity)
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}