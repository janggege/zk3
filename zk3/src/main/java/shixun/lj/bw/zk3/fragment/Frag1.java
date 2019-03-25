package shixun.lj.bw.zk3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jwkj.libzxing.OnQRCodeScanCallback;
import com.jwkj.libzxing.QRCodeManager;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import shixun.lj.bw.zk3.R;
import shixun.lj.bw.zk3.activity.Sousuo;
import shixun.lj.bw.zk3.adapter.Xinpin;
import shixun.lj.bw.zk3.adapter.Xinpin1;
import shixun.lj.bw.zk3.bean.Data;
import shixun.lj.bw.zk3.bean.Tou;
import shixun.lj.bw.zk3.myline.Myline;
import shixun.lj.bw.zk3.presenter.Allpresenter;
import shixun.lj.bw.zk3.presenter.Showpresenter;
import shixun.lj.bw.zk3.view.Showall;
import shixun.lj.bw.zk3.view.Showview;

/*
  name:刘江
  data:2019
*/public class Frag1 extends Fragment implements Showview, Showall {

    private ImageView imageView;
    private TextView t1;
    private TextView t2;
    private FlyBanner flyBanner;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private String s;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        Myline myline = view.findViewById(R.id.line);
        imageView = myline.findViewById(R.id.line).findViewById(R.id.t1);
        TextView t1 = myline.findViewById(R.id.line).findViewById(R.id.t2);
        final EditText ed = myline.findViewById(R.id.line).findViewById(R.id.ed1);
        s = ed.getText().toString();
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sousuo.class);
                intent.putExtra("s", s);
                startActivity(intent);

            }
        });

        flyBanner = view.findViewById(R.id.flay);
        recyclerView1 = view.findViewById(R.id.recy1);
        recyclerView2 = view.findViewById(R.id.recy2);

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        recyclerView1.setLayoutManager(gridLayoutManager1);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(staggeredGridLayoutManager);
        t1 = myline.findViewById(R.id.line).findViewById(R.id.t2);
        //扫描
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeManager.getInstance().with(getActivity()).setReqeustType(0).scanningQRCode(new OnQRCodeScanCallback() {
                    @Override
                    public void onCompleted(String s) {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onCancel() {

                    }
                });


            }
        });
        Showpresenter showpresenter = new Showpresenter(this);
        showpresenter.show();

        Allpresenter allpresenter = new Allpresenter(this);
        allpresenter.all();
        return view;
    }


    @Override
    public void Showview(List<Tou.ResultBean> result) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            String imageUrl = result.get(i).getImageUrl();
            list.add(imageUrl);
        }
        flyBanner.setImagesUrl(list);


    }

    @Override
    public void Showall(Data data) {

        Data.ResultBean.PzshBean pzsh = data.getResult().getPzsh();
        List<Data.ResultBean.PzshBean.CommodityListBeanX> list1 = pzsh.getCommodityList();
        Xinpin xinpin1 = new Xinpin(list1, getActivity());
        recyclerView1.setAdapter(xinpin1);

        Data.ResultBean.RxxpBean rxxp = data.getResult().getRxxp();
        List<Data.ResultBean.RxxpBean.CommodityListBean> list = rxxp.getCommodityList();
        Xinpin1 xinpin11 = new Xinpin1(list, getActivity());
        recyclerView2.setAdapter(xinpin11);
    }
}
