package shixun.lj.bw.zk3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.jwkj.libzxing.QRCodeManager;
import com.umeng.socialize.UMShareAPI;

import shixun.lj.bw.zk3.R;
import shixun.lj.bw.zk3.fragment.Frag1;
import shixun.lj.bw.zk3.fragment.Frag2;
import shixun.lj.bw.zk3.fragment.Frag3;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开启管理者
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        fragmentTransaction.add(R.id.fragm, frag1);
        fragmentTransaction.add(R.id.fragm, frag2);
        fragmentTransaction.add(R.id.fragm, frag3);
        RadioGroup group = findViewById(R.id.group);
        group.check(R.id.bt1);
        fragmentTransaction.show(frag1).hide(frag2).hide(frag3);
        fragmentTransaction.commit();


    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.bt1:
                fragmentTransaction.show(frag1).hide(frag2).hide(frag3);
                break;
            case R.id.bt2:
                fragmentTransaction.hide(frag1).show(frag2).hide(frag3);
                break;
            case R.id.bt3:
                fragmentTransaction.hide(frag1).hide(frag2).show(frag3);
                break;
        }
        fragmentTransaction.commit();

    }

    //必须重写(如果用fragment的话，记得在依付的Activity里面重写就行，fragment不执行onActivityResult方法)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UMShareAPI.get(MainActivity.this).onActivityResult(requestCode, resultCode, data);

        //注册onActivityResult
        QRCodeManager.getInstance().with(MainActivity.this).onActivityResult(requestCode, resultCode, data);
    }


}
