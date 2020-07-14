package iss.workshop.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebviewActivity extends AppCompatActivity {

    private String mUrl;
    private WebView mWebView;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent= getIntent();
        mUrl= intent.getStringExtra(MainActivity.EXTERNAL_URL);

        mWebView=findViewById(R.id.web_view);
        mWebView.setWebViewClient(new WebViewClient());


        mProgressBar=findViewById(R.id.progress_bar);;
        mProgressBar.setMax(100);

        mWebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView webView,int newProgress){
                if (newProgress==100){
                    mProgressBar.setVisibility(View.GONE);
                }
                else{
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl(mUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode==KeyEvent.KEYCODE_BACK)&&mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
    return super.onKeyDown(keyCode,event);
    }

    public class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView View, String url){
            if("developer.android.com".equals(Uri.parse(url).getHost())){
                return false;
            }

            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }


}