package ib.facmed.unam.mx.massalud2.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import ib.facmed.unam.mx.massalud2.R;

/**
 * Created by samo92 on 06/12/2017.
 */

public class WebViewActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Bundle extras = getIntent().getExtras();
        url=extras.getString("url");

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(url);
    }
}
