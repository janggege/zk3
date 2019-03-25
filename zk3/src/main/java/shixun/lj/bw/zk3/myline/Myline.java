package shixun.lj.bw.zk3.myline;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import shixun.lj.bw.zk3.R;

/*
  name:刘江
  data:2019
*/public class Myline extends LinearLayout {

    private ImageView i1;
    private TextView i2;
    private EditText ed1;

    public Myline(Context context) {
        super(context);
    }

    public Myline(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    public Myline(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initview(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.myline, null, false);
        addView(view);
        i1 = view.findViewById(R.id.t1);
        i2 = view.findViewById(R.id.t2);
        ed1 = view.findViewById(R.id.ed1);
    }

}
