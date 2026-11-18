package com.example.laborator8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CicluViata";

    public static final ArrayList<Avion> listaAvioane = new ArrayList<>();

    private TextView tvUltimulAvion;
    private TextView tvNumar;

    private final ActivityResultLauncher<Intent> lansatorAdaugare = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Avion avion = IntentCompat.getParcelableExtra(
                            result.getData(), AddAvionActivity.CHEIE_AVION, Avion.class);
                    if (avion != null) {
                        listaAvioane.add(avion);
                        tvUltimulAvion.setText(avion.toString());
                        actualizeazaNumar();
                        Toast.makeText(this, "Adaugat: " + avion, Toast.LENGTH_LONG).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Insets.aplica(this);
        setContentView(R.layout.activity_main);
        Insets.padding(this);
        Log.v(TAG, "onCreate - verbose");

        tvUltimulAvion = findViewById(R.id.tvUltimulAvion);
        tvNumar = findViewById(R.id.tvNumarAvioane);


        Button btnAdauga = findViewById(R.id.btnAdaugaAvion);
        btnAdauga.setOnClickListener(v ->
                lansatorAdaugare.launch(new Intent(this, AddAvionActivity.class)));

        Button btnAdaugaAI = findViewById(R.id.btnAdaugaAvionAI);
        btnAdaugaAI.setOnClickListener(v ->
                lansatorAdaugare.launch(new Intent(this, AddAvionAIActivity.class)));

        Button btnLista = findViewById(R.id.btnListaAvioane);
        btnLista.setOnClickListener(v ->
                startActivity(new Intent(this, ListaAvioaneActivity.class)));

        //buton deschidere noua activitate
        Button btnListaImagini = findViewById(R.id.btnPozeAvioane);
        btnListaImagini.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ListaAvioane.class)));
    }


    private void actualizeazaNumar() {
        tvNumar.setText(getString(R.string.numar_avioane, listaAvioane.size()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart - error");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume - warning");
        actualizeazaNumar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause - debug");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop - info");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy - verbose");
    }
}
