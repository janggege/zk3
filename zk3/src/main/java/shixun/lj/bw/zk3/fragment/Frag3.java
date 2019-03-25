package shixun.lj.bw.zk3.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jwkj.libzxing.OnQRCodeScanCallback;
import com.jwkj.libzxing.QRCodeManager;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

import shixun.lj.bw.zk3.R;
import shixun.lj.bw.zk3.activity.Main2Activity;
import shixun.lj.bw.zk3.receiver.MessageReceiver;

/*
  name:刘江
  data:2019
*/public class Frag3 extends Fragment implements View.OnClickListener {


    private WebView webView;
    private ImageView ion_qq;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag3, container, false);
        webView = view.findViewById(R.id.webview);
        //adnroid调用js方法
        WebSettings webSettings = webView.getSettings();
        view.findViewById(R.id.bt1).setOnClickListener(this);
        view.findViewById(R.id.bt2).setOnClickListener(this);
        view.findViewById(R.id.bt3).setOnClickListener(this);
        view.findViewById(R.id.bt4).setOnClickListener(this);
        view.findViewById(R.id.bt5).setOnClickListener(this);
        view.findViewById(R.id.bt6).setOnClickListener(this);
        ion_qq = view.findViewById(R.id.i1);


        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        // webView.loadUrl("file:///android_asset/info.html");
        webView.loadUrl("file:///android_asset/info.html");
        //交互  adnroid调用js方法  //长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //adnroid调用js方法
                webView.loadUrl("javascript:test1()");//可以传参数
                // webView.evaluateJavascript();//Android 4.4

                //   webView.loadUrl("javascript:(document.getElementsByTagName('img')[0].src='http://p4.so.qhmsg.com/bdr/326__/t0168082f62f4b041de.jpg'; )");
                return true;
            }
        });
        //1，js调用安卓   协议
        webView.addJavascriptInterface(this, "android");


        return view;
    }

    //js调用安卓方法需要添加注解
    @JavascriptInterface
    public void jump() {//传参
        Intent intent = new Intent(getActivity(), Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                UMShareAPI umShareAPI = UMShareAPI.get(getActivity());
                //登录授权监听
                umShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                        // 登录信息集合
                        // Log.i("Tag",map+"");
                        String s = map.get("profile_image_url");
                        // ion_qq是图片控件，只是为了验证登录成功后获取到你的QQ头像
                        Glide.with(getActivity()).load(s).into(ion_qq);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                break;


            case R.id.bt2:
                //分享监听
                UMShareListener umShareListener = new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA platform) {
                        Log.d("plat", "platform" + platform);
                        Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
                        Toast.makeText(getActivity(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                        Log.i("xxx", "onError: " + t);
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(getActivity(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
                    }
                };
                //友盟图片
                UMImage umImage = new UMImage(getActivity(), R.drawable.b);
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(umImage)//分享图片
                        .setCallback(umShareListener)//回调监听器
                        .share();

                break;
            case R.id.bt3:
                QRCodeManager.getInstance()
                        .with(getActivity())
                        .setReqeustType(0)
                        .scanningQRCode(new OnQRCodeScanCallback() {
                            @Override
                            public void onCompleted(String s) {
                                Toast.makeText(getActivity(), "结果:" + s, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onError(Throwable throwable) {
                                Toast.makeText(getActivity(), "错误:" + throwable.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(getActivity(), "扫描任务取消了", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.bt4:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                Bitmap logo_qRcode = QRCodeManager.getInstance().createQRCode("ZXingCode", 400, 400, bitmap);
                ion_qq.setImageBitmap(logo_qRcode);
                break;
            case R.id.bt5:
                Bitmap logo_qRcode1 = QRCodeManager.getInstance().createQRCode("ZXingCode", 400, 400);
                ion_qq.setImageBitmap(logo_qRcode1);
                break;
            case R.id.bt6:
                //开启信鸽的日志输出，线上版本不建议调用
                XGPushConfig.enableDebug(getActivity(), true);
                //注册数据更新监听器
                MessageReceiver receiver = new MessageReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("shixun.lj.bw.zk3.UPDATE_LISTVIEW");
                getActivity().registerReceiver(receiver, intentFilter);
                XGPushManager.registerPush(getActivity(), new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object o, int i) {
                        Log.i("xxx", o.toString());
                        //通过网络请求给后台
                    }

                    @Override
                    public void onFail(Object o, int i, String s) {

                    }
                });


                break;
            case R.id.bt7:
                Toast.makeText(getActivity(), "bb", Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt8:
                Toast.makeText(getActivity(), "a", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), 1 / 0, Toast.LENGTH_SHORT).show();
                break;


        }

    }
}
