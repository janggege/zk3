package shixun.lj.bw.zk3.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import shixun.lj.bw.zk3.R;

/*
  name:刘江
  data:2019
*/public class Main3activity  extends Activity {
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        final WebView webview =(WebView) findViewById(R.id.webview);
        //得到WebSettings
        WebSettings webSettings = webview.getSettings();
        //加载页面之前设置支持js交互
        webSettings.setJavaScriptEnabled(true);
        //第一种加载本地html页面
        webview.loadUrl("file:///android_asset/newsinfo.html");
        //第二种加载网页 有网  加联网权限
        //	webview.loadUrl("https://www.baidu.com");
        //	webview.loadUrl("http://172.16.10.119:8080/bwie/mhIndex.do?m=index");
        //第三种加载html字符串
		/*String html = "<html><head><title>TextView使用HTML</title></head><body><p><strong>强调</strong></p><p><em>斜体</em></p>"
                + "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1"
                + "</p><p><font color=\"#00bbaa\">颜色2</p><h1>标题1</h1><h3>标题2</h3><h6>标题3</h6><p>大于>小于<</p><p>" +
                "下面是网络图片</p><img src=\"http://avatar.csdn.net/0/3/8/2_zhang957411207.jpg\"/></body></html>";
		webview.loadData(html, "text/html", "gbk");*/
        /**
         * android调js方法
         *
         */
        webview.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                webview.loadUrl("javascript:test1()");

                return false;
            }
        });
        /**
         * js调android方法
         * 第一步设置支持js调用android方法
         *
         */
        webview.addJavascriptInterface(this, "android");


        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //不使用系统浏览器 使用WebView内置浏览器
                view.loadUrl(url);
                return true;
            }
        });

        webview.setWebChromeClient(new WebChromeClient(){
            //获取网页加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);


            }

        });
    }
    //定义一个android方法
    @JavascriptInterface
    public void toastMessage(){
        Toast.makeText(this, "js调用android方法了", Toast.LENGTH_LONG).show();
    }


}
