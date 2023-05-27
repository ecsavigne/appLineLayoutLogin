package com.example.applinelayoutlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class mostrarTiro extends AppCompatActivity {
    public WebView vWv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_tiro);
        setTitle("Mostrar tiros");

       // cargarBolitaWeb();
    }

    void cargarBolitaWeb() {
        vWv.setWebViewClient(new WebViewClient());
        vWv.loadUrl("https://www.labolitacubana.com/");
    }
}