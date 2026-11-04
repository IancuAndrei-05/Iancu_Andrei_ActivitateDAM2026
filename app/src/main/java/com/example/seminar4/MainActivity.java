package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String CHEIE_AVION = "avion";
    public static final String CHEIE_LISTA = "lista_avioane";

    private TextView tvRezultat;
    private ArrayList<Avion> listaAvioane = new ArrayList<>();

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Avion avion = (Avion) result.getData().getParcelableExtra(CHEIE_AVION);
                    if (avion != null) {
                        listaAvioane.add(avion);
                        actualizeazaRezultat();
                        Toast.makeText(this, "Avion adaugat: " + avion.getModel(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

    private final ActivityResultLauncher<Intent> launcherLista = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    ArrayList<Avion> listaNoua =
                             result.getData().getParcelableArrayListExtra(CHEIE_LISTA);
                    if (listaNoua != null) {
                        listaAvioane = listaNoua;
                        actualizeazaRezultat();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRezultat = findViewById(R.id.tvRezultat);
        Button btnAdauga = findViewById(R.id.btnAdauga);
        Button btnAdaugaAI = findViewById(R.id.btnAdaugaAI);
        Button btnLista = findViewById(R.id.btnLista);

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new Intent(MainActivity.this, AddAvionActivity.class));
            }
        });

        btnAdaugaAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new Intent(MainActivity.this, AddAvionAIActivity.class));
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaAvioaneActivity.class);
                intent.putParcelableArrayListExtra(CHEIE_LISTA, listaAvioane);
                launcherLista.launch(intent);
            }
        });
    }

    private void actualizeazaRezultat() {
        if (listaAvioane.isEmpty()) {
            tvRezultat.setText("Niciun avion adaugat");
        } else {
            tvRezultat.setText("Avioane in lista: " + listaAvioane.size() + "\nUltimul adaugat: "
                    + listaAvioane.get(listaAvioane.size() - 1).toString());
        }
    }
}
