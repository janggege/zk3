package shixun.lj.bw.zk3.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.zk3.R;
import shixun.lj.bw.zk3.bean.Fen2;
import shixun.lj.bw.zk3.bean.Fen3;
import shixun.lj.bw.zk3.utils.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class Fen2adapter extends RecyclerView.Adapter<Fen2adapter.Myviewholder> {

    List<Fen2.ResultBean> list;
    Context context;
    Handler handler = new Handler();
    private int j = 0;

    public Fen2adapter(List<Fen2.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fen2, null, false);
        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder myviewholder, int i) {
        myviewholder.textView.setText(list.get(i).getName());
        String id1 = list.get(i).getId();
        String url = "http://172.17.8.100/small/commodity/v1/findCommodityByCategory?categoryId=" + id1 + "&&page=1&&count=10";
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        myviewholder.recyclerView.setLayoutManager(gridLayoutManager);

        OkHttpUtils.doGet(url, new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //开主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String string = null;
                        try {
                            string = response.body().string();

                            Gson gson = new Gson();
                            final Fen3 fen3 = gson.fromJson(string, Fen3.class);
                            List<Fen3.ResultBean> result = fen3.getResult();
                            Fen3adapter fen3adapter = new Fen3adapter(result, context);
                            myviewholder.recyclerView.setAdapter(fen3adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {


        private final TextView textView;
        private final RecyclerView recyclerView;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.f2);
            recyclerView = itemView.findViewById(R.id.recy3);


        }
    }

}
