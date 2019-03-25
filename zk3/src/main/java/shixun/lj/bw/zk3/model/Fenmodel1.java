package shixun.lj.bw.zk3.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.zk3.bean.Fen1;
import shixun.lj.bw.zk3.utils.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class Fenmodel1 {
    public interface fen1onclick {
        void fen1cilck(List<Fen1.ResultBean> result);
    }

    fen1onclick fen1onclick;

    public void setFen1onclick(Fenmodel1.fen1onclick fen1onclick) {
        this.fen1onclick = fen1onclick;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String data = (String) msg.obj;
                    Gson gson = new Gson();
                    Fen1 fen1 = gson.fromJson(data, Fen1.class);
                    List<Fen1.ResultBean> result = fen1.getResult();
                    fen1onclick.fen1cilck(result);
                    break;
            }
        }
    };

    public void fendata1() {
        String url = "http://172.17.8.100/small/commodity/v1/findFirstCategory";
        OkHttpUtils.getinstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.what = 1;
                message.obj = string;
                handler.sendMessage(message);


            }
        });
    }
}
