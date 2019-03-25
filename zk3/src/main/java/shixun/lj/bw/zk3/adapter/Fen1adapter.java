package shixun.lj.bw.zk3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import shixun.lj.bw.zk3.R;
import shixun.lj.bw.zk3.bean.Fen1;

/*
  name:刘江
  data:2019
*/public class Fen1adapter extends RecyclerView.Adapter<Fen1adapter.Myviewholder> {
    List<Fen1.ResultBean> list;
    Context context;

    public Fen1adapter(List<Fen1.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fen1, null, false);
        Myviewholder myviewholder = new Myviewholder(view);

        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, final int i) {
        myviewholder.t1.setText(list.get(i).getName());
        myviewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = list.get(i).getId();
                oniteamclick.oniteamclick(id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {

        private final TextView t1;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.ft1);
        }
    }

    public  interface oniteamclick {
        void oniteamclick(String i);
    }

     oniteamclick oniteamclick;

    public void setOniteamclick(Fen1adapter.oniteamclick oniteamclick) {
        this.oniteamclick = oniteamclick;
    }
}
