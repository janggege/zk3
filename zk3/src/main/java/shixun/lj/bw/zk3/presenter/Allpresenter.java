package shixun.lj.bw.zk3.presenter;

import shixun.lj.bw.zk3.bean.Data;
import shixun.lj.bw.zk3.fragment.Frag1;
import shixun.lj.bw.zk3.model.Allmodel;
import shixun.lj.bw.zk3.view.Showall;
import shixun.lj.bw.zk3.view.Showview;

/*
  name:刘江
  data:2019
*/public class Allpresenter {

    private final Allmodel allmodel;
    private final Showall showall1;


    public Allpresenter(Showall showall) {
        allmodel = new Allmodel();
        showall1 = showall;

    }

    public void all() {
        allmodel.all();
        allmodel.setOnXinpinclick(new Allmodel.OnXinpinclick() {
            @Override
            public void xinpin(Data data) {
                showall1.Showall(data);
            }
        });

    }
}
