package shixun.lj.bw.zk3.app;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/*
  name:刘江
  data:2019
*/public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        CrashHandler.getinstance().init(this);

    }
}
