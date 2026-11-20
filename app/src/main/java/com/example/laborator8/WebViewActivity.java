package com.example.laborator8;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

/** Lab 8: deschide direct in WebView link-ul cu detalii despre imaginea selectata. */
public class WebViewActivity extends AppCompatActivity {

    public static final String CHEIE_LINK = "link";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Insets.aplica(this);
        setContentView(R.layout.activity_web_view);
        Insets.padding(this);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        // paginile se deschid in WebView, nu in browserul extern
        webView.setWebViewClient(new WebViewClient());

        String link = getIntent().getStringExtra(CHEIE_LINK);
        if (link != null) {
            webView.loadUrl(link);
        }

        // butonul Back navigheaza inapoi in pagina, apoi inchide activitatea
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        });
    }
}
