package shixun.lj.bw.zk3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import shixun.lj.bw.zk3.R;
import shixun.lj.bw.zk3.myline.Myline;

public class Sousuo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        Myline myline = findViewById(R.id.line1);
        EditText editText = myline.findViewById(R.id.ed1);
        Intent intent = getIntent();
        String s = intent.getExtras().getString("s");

        editText.setText(s);

    }
}
