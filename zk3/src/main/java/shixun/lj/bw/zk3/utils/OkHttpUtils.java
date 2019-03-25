package shixun.lj.bw.zk3.utils;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/*
  name:刘江
  data:2019   //    单例模式     懒汉式     先声明      后实例化
*/public class OkHttpUtils {
    public static OkHttpUtils okHttpUtils = null;

    //私有的构造方法
    public OkHttpUtils() {
    }

    //返回公共的静态的实例方法
    public static OkHttpUtils getinstance() {
        if (okHttpUtils == null) {
            //同步锁
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    //声明
    public static OkHttpClient okHttpClient = null;

    public static synchronized OkHttpClient getokhttpclient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                }
            });
            okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        }
        return okHttpClient;

    }

    //封装  get
    public static void doGet(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        getokhttpclient().newCall(request).enqueue(callback);

    }

    //网络请求封装post请求
    public static void doPost(String url, Map<String, String> map, Callback callback) {

        FormBody.Builder builder = new FormBody.Builder();

        //遍历集合   不懂
        for (String key : map.keySet()) {
            builder.add(key, map.get(key));
        }
        //构建  ?????
        FormBody build = builder.build();
        Request request = new Request.Builder().post(build).url(url).build();
        getokhttpclient().newCall(request).enqueue(callback);


    }

}
