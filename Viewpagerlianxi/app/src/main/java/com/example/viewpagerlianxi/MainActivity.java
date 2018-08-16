package com.example.viewpagerlianxi;

import android.graphics.Color;
import android.os.Build;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    List<ImageView> imageViewList=new ArrayList<>();
    ViewPager viewPager;
    int currentposition=1;
    Button button;
    ListView listView;
    LayoutInflater inflater;
    PopupWindow popupWindow;
    View viewpop;
    boolean is=true;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    viewPager.setCurrentItem(msg.arg1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater=getLayoutInflater();
        button= (Button) findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupWindow();
               

            }
        });
        String data[]={"哈哈","哈哈","哈哈","哈哈","哈哈","哈哈","哈哈","哈哈","哈哈","哈哈","哈哈","哈哈","哈哈"};
        viewpop=inflater.inflate(R.layout.toastlayout,null);
        listView=viewpop.findViewById(R.id.listview);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        viewPager=(ViewPager) findViewById(R.id.firstviewpager);
        ImageView view0=new ImageView(this);
        view0.setImageResource(R.drawable.xusong1);
        view0.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView view1=new ImageView(this);
        view1.setScaleType(ImageView.ScaleType.FIT_XY);
        view1.setImageResource(R.drawable.xusong1);
        ImageView view2=new ImageView(this);
        view2.setImageResource(R.drawable.xusong2);
        view2.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView view3=new ImageView(this);
        view3.setImageResource(R.drawable.xusong3);
        view3.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView view4=new ImageView(this);
        view4.setImageResource(R.drawable.xusong4);
        view4.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView view5=new ImageView(this);
        view5.setImageResource(R.drawable.xusong5);
        view5.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView view6=new ImageView(this);
        view6.setImageResource(R.drawable.xusong5);
        view6.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewList.add(view6);
        imageViewList.add(view1);
        imageViewList.add(view2);
        imageViewList.add(view3);
        imageViewList.add(view4);
        imageViewList.add(view5);
        imageViewList.add(view0);
        viewPager.setAdapter(new myViewadapter(imageViewList));
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentposition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state != ViewPager.SCROLL_STATE_IDLE) return;
                if (currentposition == 0) {
                    viewPager.setCurrentItem(imageViewList.size() - 2, false);
                }
                else if (currentposition==imageViewList.size()-1){
                    viewPager.setCurrentItem(1,false);
                }
            }
        });
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=1;
                message.arg1=currentposition+1;
                handler.sendMessage(message);
            }
        },5000,5000);
    }
    public void showPopupWindow(){
        popupWindow=new PopupWindow(viewpop, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setContentView(viewpop);
        View viewparent=inflater.inflate(R.layout.activity_main,null);
        popupWindow.setAnimationStyle(R.style.popwindowstyle);
        popupWindow.showAtLocation(viewparent, Gravity.BOTTOM,0,0);

    }
}

