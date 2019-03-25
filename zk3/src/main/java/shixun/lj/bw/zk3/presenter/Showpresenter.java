package shixun.lj.bw.zk3.presenter;

import android.support.v4.app.FragmentActivity;

import java.util.List;

import shixun.lj.bw.zk3.bean.Tou;
import shixun.lj.bw.zk3.fragment.Frag1;
import shixun.lj.bw.zk3.model.Showmodel;
import shixun.lj.bw.zk3.view.Showview;

/*
  name:刘江
  data:2019
*/public class Showpresenter {

    private final Showmodel showmodel;
    private final Showview showview1;

    public Showpresenter(Showview showview) {
        showmodel = new Showmodel();
        showview1 = showview;


    }

    public void show() {
        showmodel.showp();
        showmodel.setOnclickTou(new Showmodel.OnclickTou() {
            @Override
            public void tou(List<Tou.ResultBean> result) {
                showview1.Showview(result);
            }
        });

    }
}
