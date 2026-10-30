package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaAvioaneActivity extends AppCompatActivity {

    private ArrayList<Avion> listaAvioane;
    private ArrayAdapter<Avion> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_avioane);

        listaAvioane = (ArrayList<Avion>) getIntent().getSerializableExtra(MainActivity.CHEIE_LISTA);
        if (listaAvioane == null) {
            listaAvioane = new ArrayList<>();
        }

        ListView lvAvioane = findViewById(R.id.lvAvioane);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAvioane);
        lvAvioane.setAdapter(adapter);

        lvAvioane.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Avion avion = listaAvioane.get(position);
                Toast.makeText(ListaAvioaneActivity.this, avion.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

        lvAvioane.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Avion avion = listaAvioane.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(ListaAvioaneActivity.this, "Avion sters: " + avion.getModel(),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra(MainActivity.CHEIE_LISTA, listaAvioane);
                setResult(RESULT_OK, intent);
                return true;
            }
        });
    }
}
