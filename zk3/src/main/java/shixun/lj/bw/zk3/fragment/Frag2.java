package shixun.lj.bw.zk3.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import shixun.lj.bw.zk3.R;
import shixun.lj.bw.zk3.adapter.Fen1adapter;
import shixun.lj.bw.zk3.adapter.Fen2adapter;
import shixun.lj.bw.zk3.presenter.Fenpresenter1;
import shixun.lj.bw.zk3.presenter.Fenpresenter2;
import shixun.lj.bw.zk3.view.Fen1;
import shixun.lj.bw.zk3.view.Fen2;

/*
  name:刘江
  data:2019
*/public class Frag2 extends Fragment implements Fen1, Fen2 {

    private RecyclerView recys1;
    private Fenpresenter2 fenpresenter2;
    private RecyclerView recys2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2, container, false);
        recys1 = view.findViewById(R.id.recy1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recys1.setLayoutManager(linearLayoutManager);
        recys2 = view.findViewById(R.id.recy2);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        recys2.setLayoutManager(linearLayoutManager1);

        Fenpresenter1 fenpresenter1 = new Fenpresenter1(this);
        fenpresenter1.fen1();

        fenpresenter2 = new Fenpresenter2(this);


        return view;
    }

    @Override
    public void Fen1(List<shixun.lj.bw.zk3.bean.Fen1.ResultBean> result) {
        Fen1adapter fen1adapter = new Fen1adapter(result, getActivity());
        recys1.setAdapter(fen1adapter);

        fen1adapter.setOniteamclick(new Fen1adapter.oniteamclick() {
            @Override
            public void oniteamclick(String i) {
                Toast.makeText(getActivity(), i, Toast.LENGTH_SHORT).show();
                fenpresenter2.fen2(i);
            }
        });


    }

    @Override
    public void Fen2(List<shixun.lj.bw.zk3.bean.Fen2.ResultBean> result) {
        Fen2adapter fen2adapter = new Fen2adapter(result, getActivity());
        recys2.setAdapter(fen2adapter);
        }


}
