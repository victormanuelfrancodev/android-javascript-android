package com.amigoyucatan.appsell.webscriptprueba;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    Button botonPrueba;
    JavaScriptInterface Jinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.sitioweb);
        botonPrueba = (Button) findViewById(R.id.boton);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }
        Jinterface = new JavaScriptInterface(this);
        mWebView.addJavascriptInterface(Jinterface,"javascript_obj");


        final WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);

        WebViewClient wvc = new WebViewClient();
        mWebView.setWebViewClient(wvc);
        mWebView.loadUrl("file:///android_asset/webview.html");

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mWebView, url);
                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
                injectFunction();
            }

        });

        botonPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript: updateFromAndroid('gps information')",null);
                }

        });

    }

    public void onDestroy(){
        super.onDestroy();
        mWebView.removeJavascriptInterface("javascript_obj");
    }


    private void injectFunction() {
        mWebView.loadUrl("javascript: " +
                "window.androidObj.textToAndroid = function(message) { javascript_obj.textFromWeb(message) }");
    }

}
