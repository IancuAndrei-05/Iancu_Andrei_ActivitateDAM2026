package com.example.laborator8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Lab 8: ListView cu CustomAdapter (imagine + descriere).
 * Imaginile sunt descarcate prin URL la deschiderea activitatii.
 * La click pe un item se deschide WebViewActivity cu link-ul asociat imaginii.
 */
public class ListaAvioane extends AppCompatActivity {

    private static final String TAG = "ListaAvioane";

    // imaginile de pe google
    private final String[] linkuriImagini = {
            "https://aeroaffaires.ro/wp-content/uploads/2025/08/a340.jpg",
            "https://media.defenseromania.ro/image/202210/w670/avion-ngad-02_09042500.jpg",
            "https://st.depositphotos.com/1001880/2141/i/450/depositphotos_21412449-stock-photo-airplane-flying-at-sunset.jpg",
            "https://www.opiniatimisoarei.ro/wp-content/gallery/poze-avion/mauritius.jpg",
            "https://cdn.pixabay.com/photo/2014/11/02/10/41/plane-513641_640.jpg"
    };

    private final String[] descriere = {
            "Airbus A340 - avion de linie cu patru motoare, pentru zboruri lungi",
            "Lockheed Martin LMXT - avion militar ",
            "Avion de pasageri in zbor",
            "Vedere din avion ",
            "Avion de pasageri la aterizare"
    };

    // paginile web cu detalii despre continutul fiecarei imagini (aceeasi ordine ca imaginile)
    private final String[] linkuriPagini = {
            "https://ro.wikipedia.org/wiki/Airbus_A340",
            "https://en.wikipedia.org/wiki/Lockheed_Martin_LMXT",
            "https://ro.wikipedia.org/wiki/Avion_de_linie",
            "https://ro.wikipedia.org/wiki/Fotografie_aerian%C4%83",
            "https://ro.wikipedia.org/wiki/Aterizare"
    };

    private final List<ImaginiDomeniu> listaImagini = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    private final Handler handler = new Handler(Looper.getMainLooper());
    private ImaginiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Insets.aplica(this);
        setContentView(R.layout.activity_lista_avioane2);
        Insets.padding(this);

        for (int i = 0; i < linkuriImagini.length; i++) {
            listaImagini.add(new ImaginiDomeniu(descriere[i], null, linkuriPagini[i]));
        }

        ListView lv = findViewById(R.id.lvImagini);
        adapter = new ImaginiAdapter(this, listaImagini);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            String link = listaImagini.get(position).getLink();
            if (link == null || link.isEmpty()) {
                Toast.makeText(this, R.string.link_lipsa, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra(WebViewActivity.CHEIE_LINK, link);
            startActivity(intent);
        });

        incarcaImagini();
    }

    /** Descarca fiecare imagine prin URL pe un fir secundar si actualizeaza lista pe UI thread. */
    private void incarcaImagini() {
        for (int i = 0; i < linkuriImagini.length; i++) {
            final int pozitie = i;
            final String url = linkuriImagini[i];
            executor.execute(() -> {
                Bitmap bitmap = descarcaImagine(url);
                handler.post(() -> {
                    if (isDestroyed()) return;
                    listaImagini.get(pozitie).setImagine(bitmap);
                    adapter.notifyDataSetChanged();
                });
            });
        }
    }

    private Bitmap descarcaImagine(String adresa) {
        HttpURLConnection conexiune = null;
        try {
            conexiune = (HttpURLConnection) new URL(adresa).openConnection();
            conexiune.setConnectTimeout(10000);
            conexiune.setReadTimeout(15000);
            conexiune.setInstanceFollowRedirects(true);
            // unele site-uri refuza cererile fara User-Agent
            conexiune.setRequestProperty("User-Agent", "Mozilla/5.0 (Android)");
            conexiune.connect();
            if (conexiune.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.w(TAG, "HTTP " + conexiune.getResponseCode() + " pentru " + adresa);
                return null;
            }
            try (InputStream in = conexiune.getInputStream()) {
                return BitmapFactory.decodeStream(in);
            }
        } catch (Exception e) {
            Log.e(TAG, "Eroare la descarcarea imaginii " + adresa, e);
            return null;
        } finally {
            if (conexiune != null) conexiune.disconnect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdownNow();
    }
}
