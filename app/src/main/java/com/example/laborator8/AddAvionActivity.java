package com.example.laborator8;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;


public class AddAvionActivity extends AppCompatActivity {

    public static final String CHEIE_AVION = "avion";
    public static final String CHEIE_POZITIE = "pozitie";

    private EditText etModel;
    private EditText etLocuri;
    private Spinner spProducator;
    private RadioGroup rgTip;
    private CheckBox cbWifi;
    private CompoundButton swServiciu;
    private RatingBar ratingBar;

    private int pozitieEditata = -1;

    /** Layout-ul folosit; varianta cu design AI suprascrie aceasta metoda. */
    @LayoutRes
    protected int getLayoutId() {
        return R.layout.activity_add_avion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Insets.aplica(this);
        setContentView(getLayoutId());
        Insets.padding(this);

        TextView tvTitlu = findViewById(R.id.tvTitlu);
        etModel = findViewById(R.id.etModel);
        etLocuri = findViewById(R.id.etLocuri);
        spProducator = findViewById(R.id.spProducator);
        rgTip = findViewById(R.id.rgTip);
        cbWifi = findViewById(R.id.cbWifi);
        swServiciu = findViewById(R.id.swServiciu);
        ratingBar = findViewById(R.id.ratingBar);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.producatori, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProducator.setAdapter(adapter);

        // Mod editare (Lab 6)
        Intent intent = getIntent();
        Avion avionPrimit = IntentCompat.getParcelableExtra(intent, CHEIE_AVION, Avion.class);
        if (avionPrimit != null) {
            pozitieEditata = intent.getIntExtra(CHEIE_POZITIE, -1);
            tvTitlu.setText(R.string.titlu_editare);
            btnSalveaza.setText(R.string.salveaza_modificari);
            completeazaCampuri(avionPrimit);
        }

        btnSalveaza.setOnClickListener(v -> salveaza());
    }

    private void completeazaCampuri(Avion a) {
        etModel.setText(a.getModel());
        etLocuri.setText(String.valueOf(a.getNrLocuri()));
        String[] producatori = getResources().getStringArray(R.array.producatori);
        for (int i = 0; i < producatori.length; i++) {
            if (producatori[i].equals(a.getProducator())) {
                spProducator.setSelection(i);
                break;
            }
        }
        if (getString(R.string.tip_cargo).equals(a.getTip())) {
            rgTip.check(R.id.rbCargo);
        } else if (getString(R.string.tip_militar).equals(a.getTip())) {
            rgTip.check(R.id.rbMilitar);
        } else {
            rgTip.check(R.id.rbPasageri);
        }
        cbWifi.setChecked(a.isAreWifi());
        swServiciu.setChecked(a.isInServiciu());
        ratingBar.setRating(a.getRating());
    }

    private void salveaza() {
        String model = etModel.getText().toString().trim();
        String locuriText = etLocuri.getText().toString().trim();

        if (TextUtils.isEmpty(model)) {
            etModel.setError(getString(R.string.eroare_model));
            return;
        }
        int nrLocuri;
        try {
            nrLocuri = Integer.parseInt(locuriText);
        } catch (NumberFormatException e) {
            etLocuri.setError(getString(R.string.eroare_locuri));
            return;
        }

        RadioButton rbSelectat = findViewById(rgTip.getCheckedRadioButtonId());
        String tip = rbSelectat != null ? rbSelectat.getText().toString() : getString(R.string.tip_pasageri);

        Avion avion = new Avion(
                model,
                spProducator.getSelectedItem().toString(),
                nrLocuri,
                tip,
                cbWifi.isChecked(),
                swServiciu.isChecked(),
                ratingBar.getRating());

        Intent rezultat = new Intent();
        rezultat.putExtra(CHEIE_AVION, avion);           // Parcelable
        rezultat.putExtra(CHEIE_POZITIE, pozitieEditata);
        setResult(RESULT_OK, rezultat);
        Toast.makeText(this, R.string.salvat, Toast.LENGTH_SHORT).show();
        finish();
    }
}
