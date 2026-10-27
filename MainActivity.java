package com.baziyar.football49;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.FrameLayout;
public class MainActivity extends Activity {
  private FrameLayout root;
  @Override public void onCreate(Bundle b) {
    super.onCreate(b);
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    root = new FrameLayout(this); WebView w = new WebView(this); w.setBackgroundColor(Color.WHITE);
    WebSettings s = w.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setDatabaseEnabled(true); s.setAllowFileAccess(true); s.setAllowContentAccess(true); s.setMediaPlaybackRequiresUserGesture(false); s.setSupportZoom(false);
    w.setWebChromeClient(new WebChromeClient()); w.setWebViewClient(new WebViewClient() {
      @Override public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) { super.onReceivedError(view, request, error); if (request.isForMainFrame()) { TextView msg = new TextView(MainActivity.this); msg.setText("بازی باز نشد. فایل index.html را بررسی کنید."); msg.setTextColor(Color.DKGRAY); msg.setTextSize(18); msg.setPadding(24,24,24,24); root.removeAllViews(); root.addView(msg); } }
    }); root.addView(w); setContentView(root); w.loadUrl("file:///android_asset/index.html");
  }
  @Override public void onBackPressed() { View child=root.getChildAt(0); if(child instanceof WebView && ((WebView)child).canGoBack()) ((WebView)child).goBack(); else super.onBackPressed(); }
}
