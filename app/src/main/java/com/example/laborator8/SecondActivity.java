package com.example.laborator8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Lab 2-3: a doua activitate, setata in AndroidManifest ca activitate de lansare.
 * Butonul deschide ThirdActivity printr-un Intent si trimite un mesaj + doua intregi printr-un Bundle.
 * La intoarcere afiseaza (Toast) mesajul primit si suma calculata.
 */
public class SecondActivity extends AppCompatActivity {

    public static final String CHEIE_MESAJ = "mesaj";
    public static final String CHEIE_VAL1 = "valoare1";
    public static final String CHEIE_VAL2 = "valoare2";
    public static final String CHEIE_SUMA = "suma";

    private static final String TAG = "SecondActivity";

    private final ActivityResultLauncher<Intent> lansatorActivitate3 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle b = result.getData().getExtras();
                    if (b != null) {
                        String mesaj = b.getString(CHEIE_MESAJ);
                        int suma = b.getInt(CHEIE_SUMA);
                        Log.i(TAG, "Primit: " + mesaj + " suma=" + suma);
                        Toast.makeText(this, mesaj + " | Suma = " + suma, Toast.LENGTH_LONG).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Insets.aplica(this);
        setContentView(R.layout.activity_second);
        Insets.padding(this);

        Button btnDeschide = findViewById(R.id.btnDeschideActivitate3);
        btnDeschide.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(CHEIE_MESAJ, "Salut din SecondActivity!");
            bundle.putInt(CHEIE_VAL1, 12);
            bundle.putInt(CHEIE_VAL2, 30);
            intent.putExtras(bundle);
            lansatorActivitate3.launch(intent);
        });

        Button btnAvioane = findViewById(R.id.btnAplicatieAvioane);
        btnAvioane.setOnClickListener(v ->
                startActivity(new Intent(SecondActivity.this, MainActivity.class)));
    }
}
