package shixun.lj.bw.zk3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import shixun.lj.bw.zk3.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final WebView webview = (WebView) findViewById(R.id.webview);
        //设置webview支持js交互
        webview.getSettings().setJavaScriptEnabled(true);
        //加载页面
        webview.loadUrl("file:///android_asset/infos.html");

        //设置弹出Alert
        webview.setWebChromeClient(new WebChromeClient() {
            //title就是网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {//此方法可获得网页标题
                Log.i("xxx", view.getTitle());
                super.onReceivedTitle(view, title);

            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                //false弹出  true和super不弹
                return false;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message,
                                      String defaultValue, JsPromptResult result) {
                // TODO Auto-generated method stub
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            /**处理定位的相关，否则WebView不会开启定位功能，类似百度地图这样的就没法定位*/
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);              //这个必须有
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });

        webview.setWebViewClient(new WebViewClient() {
            //拦截浏览器加载页面，让WebView加载页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl("url");
                return true;
            }
        });

        //js调用andorid方法
        webview.addJavascriptInterface(this, "android");
    }

    //js调用andorid方法 并传值
    @JavascriptInterface
    public void showUserInfo(String name, String pwd) {
        Toast.makeText(this, name + "," + pwd, Toast.LENGTH_LONG).show();


        /*Intent intent =new Intent(this,Main3Activity.class);
        startActivity(intent);*/
    }
}

