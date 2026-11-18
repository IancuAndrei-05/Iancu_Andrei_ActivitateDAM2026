package com.example.laborator8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

    private final List<ImaginiDomeniu> listaImagini = new ArrayList<>();

}
