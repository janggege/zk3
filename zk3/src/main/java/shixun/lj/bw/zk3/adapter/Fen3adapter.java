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
import shixun.lj.bw.zk3.bean.Fen3;

/*
  name:刘江
  data:2019
*/public class Fen3adapter extends RecyclerView.Adapter<Fen3adapter.myviewholder> {
    List<Fen3.ResultBean> list;
    Context context;

    public Fen3adapter(List<Fen3.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fen3, null, false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, int i) {
        myviewholder.textView.setText(list.get(i).getCommodityName());
        Glide.with(context).load(list.get(i).getMasterPic()).into(myviewholder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {


        private final ImageView imageView;
        private final TextView textView;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.i3);
            textView = itemView.findViewById(R.id.t3);
        }
    }
}
