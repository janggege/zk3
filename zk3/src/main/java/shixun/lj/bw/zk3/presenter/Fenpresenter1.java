package shixun.lj.bw.zk3.presenter;

import java.util.List;

import shixun.lj.bw.zk3.model.Fenmodel1;
import shixun.lj.bw.zk3.view.Fen1;

/*
  name:刘江
  data:2019
*/public class Fenpresenter1 {

    private final Fenmodel1 fenmodel1;
    private final Fen1 fen11;

    public Fenpresenter1(Fen1 fen1) {
        fenmodel1 = new Fenmodel1();
        fen11 = fen1;
    }

    public void fen1() {
        fenmodel1.fendata1();
        fenmodel1.setFen1onclick(new Fenmodel1.fen1onclick() {
            @Override
            public void fen1cilck(List<shixun.lj.bw.zk3.bean.Fen1.ResultBean> result) {
                fen11.Fen1(result);
            }
        });


    }
}
