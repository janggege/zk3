package shixun.lj.bw.zk3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shixun.lj.bw.zk3.R;
import shixun.lj.bw.zk3.bean.Data;

/*
  name:刘江
  data:2019
*/public class Xinpin extends RecyclerView.Adapter<Xinpin.Myciewholder> {
    List<Data.ResultBean.PzshBean.CommodityListBeanX> list;
    Context context;

    public Xinpin(List<Data.ResultBean.PzshBean.CommodityListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Myciewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.xinpin, null, false);
        Myciewholder myciewholder = new Myciewholder(view);
        return myciewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myciewholder myciewholder, int i) {
        myciewholder.t1.setText(list.get(i).getCommodityName().substring(0,3));
        Glide.with(context).load(list.get(i).getMasterPic()).into(myciewholder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myciewholder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView t1;

        public Myciewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            t1 = itemView.findViewById(R.id.xt1);
        }
    }
}
