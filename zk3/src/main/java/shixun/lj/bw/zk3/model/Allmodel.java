package shixun.lj.bw.zk3.model;

import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.zk3.bean.Data;
import shixun.lj.bw.zk3.utils.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class Allmodel {
    public interface OnXinpinclick {
        void xinpin(Data data);
    }

    OnXinpinclick onXinpinclick;

    public void setOnXinpinclick(OnXinpinclick onXinpinclick) {
        this.onXinpinclick = onXinpinclick;
    }


    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String data = (String) msg.obj;
                    Gson gson = new Gson();
                    Data data1 = gson.fromJson(data, Data.class);
                    onXinpinclick.xinpin(data1);
                    break;

            }
        }
    };


    public void all() {
        String path = "http://mobile.bwstudent.com/small/commodity/v1/commodityList";
        OkHttpUtils.getinstance().doGet(path, new Callback() {
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
