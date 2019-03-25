package shixun.lj.bw.zk3.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.zk3.bean.Fen2;
import shixun.lj.bw.zk3.utils.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class Fenmodel2 {
    public interface onclick {
        void onclik(List<Fen2.ResultBean> result);
    }

    onclick onclick;

    public void setOnclick(Fenmodel2.onclick onclick) {
        this.onclick = onclick;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String data = (String) msg.obj;
                    Gson gson = new Gson();
                    Fen2 fen2 = gson.fromJson(data, Fen2.class);
                    List<Fen2.ResultBean> result = fen2.getResult();
                    onclick.onclik(result);


                    break;
            }
        }
    };


    public void fen2(String id) {
        String url = "http://172.17.8.100/small/commodity/v1/findSecondCategory?firstCategoryId=" + id;
        OkHttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i("aaaa", string);
                Message message = new Message();
                message.what = 1;
                message.obj = string;
                handler.sendMessage(message);

            }
        });

    }
}
