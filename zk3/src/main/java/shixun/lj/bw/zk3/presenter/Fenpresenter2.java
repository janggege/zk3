package shixun.lj.bw.zk3.presenter;

import java.util.List;

import shixun.lj.bw.zk3.adapter.Fen1adapter;
import shixun.lj.bw.zk3.fragment.Frag2;
import shixun.lj.bw.zk3.model.Fenmodel2;
import shixun.lj.bw.zk3.view.Fen2;

/*
  name:刘江
  data:2019
*/public class Fenpresenter2 {

    private final Fenmodel2 fenmodel2;
    private final Fen2 fen21;

    public Fenpresenter2(Frag2 fen2) {
        fenmodel2 = new Fenmodel2();
        fen21 = fen2;

    }

    public void fen2(String id) {
        fenmodel2.fen2(id);
        fenmodel2.setOnclick(new Fenmodel2.onclick() {
            @Override
            public void onclik(List<shixun.lj.bw.zk3.bean.Fen2.ResultBean> result) {
                fen21.Fen2(result);
            }
        });
    }
}
