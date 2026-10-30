package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddAvionActivity extends AppCompatActivity {

    private EditText etCompanie, etModel;
    private Spinner spTip;
    private RadioButton rbEconomy;
    private CheckBox cbWifi;
    private Switch swInternational;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_avion);

        etCompanie = findViewById(R.id.etCompanie);
        etModel = findViewById(R.id.etModel);
        spTip = findViewById(R.id.spTip);
        rbEconomy = findViewById(R.id.rbEconomy);
        cbWifi = findViewById(R.id.cbWifi);
        swInternational = findViewById(R.id.swInternational);
        ratingBar = findViewById(R.id.ratingBar);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companie = etCompanie.getText().toString().trim();
                String model = etModel.getText().toString().trim();
                if (companie.isEmpty() || model.isEmpty()) {
                    Toast.makeText(AddAvionActivity.this, "Completeaza toate campurile",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                String clasa = rbEconomy.isChecked() ? "Economy" : "Business";
                Avion avion = new Avion(companie, model, spTip.getSelectedItem().toString(),
                        clasa, cbWifi.isChecked(), swInternational.isChecked(),
                        ratingBar.getRating());

                Intent intent = new Intent();
                intent.putExtra(MainActivity.CHEIE_AVION, avion);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
