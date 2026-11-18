package com.example.laborator8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import java.util.List;


public class ListaAvioaneActivity extends AppCompatActivity {

    private final List<Avion> avioane = MainActivity.listaAvioane;
    private AvionAdapter adapter;

    private final ActivityResultLauncher<Intent> lansatorEditare = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    Avion modificat = IntentCompat.getParcelableExtra(
                            data, AddAvionActivity.CHEIE_AVION, Avion.class);
                    int pozitie = data.getIntExtra(AddAvionActivity.CHEIE_POZITIE, -1);
                    if (modificat != null && pozitie >= 0 && pozitie < avioane.size()) {
                        // NU adaugam un obiect nou - modificam obiectul selectat
                        avioane.get(pozitie).actualizeazaDin(modificat);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Modificat: " + avioane.get(pozitie), Toast.LENGTH_LONG).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Insets.aplica(this);
        setContentView(R.layout.activity_lista_avioane);
        Insets.padding(this);

        ListView lv = findViewById(R.id.lvAvioane);
        lv.setEmptyView(findViewById(R.id.tvListaGoala));
        adapter = new AvionAdapter(this, avioane);
        lv.setAdapter(adapter);

        // Click scurt: Toast cu obiectul + deschidere activitate de editare (Lab 5 + Lab 6)
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Avion selectat = avioane.get(position);
            Toast.makeText(this, selectat.toString(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, AddAvionActivity.class);
            intent.putExtra(AddAvionActivity.CHEIE_AVION, selectat);   // Parcelable
            intent.putExtra(AddAvionActivity.CHEIE_POZITIE, position);
            lansatorEditare.launch(intent);
        });

        // Click lung: stergere din ListView si din lista (Lab 5)
        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            Avion sters = avioane.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Sters: " + sters.getModel(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
