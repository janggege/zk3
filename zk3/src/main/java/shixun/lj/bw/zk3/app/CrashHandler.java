package shixun.lj.bw.zk3.app;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/*
  name:刘江
  data:2019
*/public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler crashHandler = null;
    private Context mcontext;

    public CrashHandler() {

    }

    public void init(Context context) {
        mcontext = context;

        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    //单例模式
    public static CrashHandler getinstance() {
        if (crashHandler == null) {
            synchronized (CrashHandler.class) {
                if (crashHandler == null) {
                    synchronized (CrashHandler.class) {
                        crashHandler = new CrashHandler();
                    }
                }
            }
        }
        return crashHandler;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(mcontext, "捕获到异常", Toast.LENGTH_SHORT).show();
                Looper.loop();

            }
        }.start();
        String message = e.getMessage();
        String path = Environment.getExternalStorageDirectory() + "/" + "carshh";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        File file1 = new File(file , "error.log");
        try {
            FileOutputStream outputStream = new FileOutputStream(file1);
            byte[] buffer = message.getBytes();
            outputStream.write(buffer , 0 ,buffer.length);
            outputStream.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }
}
