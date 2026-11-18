package com.example.laborator8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Lab 2-3: a treia activitate. Preia informatiile din Bundle, le afiseaza intr-un Toast,
 * iar butonul trimite inapoi un alt mesaj si suma celor doua valori.
 */
public class ThirdActivity extends AppCompatActivity {

    private int valoare1;
    private int valoare2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Insets.aplica(this);
        setContentView(R.layout.activity_third);
        Insets.padding(this);

        TextView tvPrimit = findViewById(R.id.tvDatePrimite);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String mesaj = bundle.getString(SecondActivity.CHEIE_MESAJ);
            valoare1 = bundle.getInt(SecondActivity.CHEIE_VAL1);
            valoare2 = bundle.getInt(SecondActivity.CHEIE_VAL2);
            String text = mesaj + "\nValoare 1 = " + valoare1 + "\nValoare 2 = " + valoare2;
            tvPrimit.setText(text);
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

        Button btnTrimite = findViewById(R.id.btnTrimiteInapoi);
        btnTrimite.setOnClickListener(v -> {
            Intent rezultat = new Intent();
            Bundle b = new Bundle();
            b.putString(SecondActivity.CHEIE_MESAJ, "Raspuns din ThirdActivity");
            b.putInt(SecondActivity.CHEIE_SUMA, valoare1 + valoare2);
            rezultat.putExtras(b);
            setResult(RESULT_OK, rezultat);
            finish();
        });
    }
}
