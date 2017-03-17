package com.tongzhang.sensorgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.tongzhang.sensorgame.R.id.TextView03;

/**
 * Created by zt041 on 2017-03-16.
 */

public class StartPage extends Activity {
    public ImageView infoOperatingIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载启动图片
        setContentView(R.layout.start_page);
        infoOperatingIV = (ImageView) findViewById(R.id.infoOperating);
        getHomeActivity();
    }
    private void getHomeActivity(){
        Timer timer=new Timer();
        TimerTask task=new TimerTask(){
            public void run(){
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                Animation operatingAnim = AnimationUtils.loadAnimation(StartPage.this,R.anim.tip);

                //operatingAnim.setRepeatCount(1);
                //infoOperatingIV.startAnimation(operatingAnim);
                Intent intent=new Intent(StartPage.this,MainActivity.class);
                startActivity(intent);

            }
        };
        timer.schedule(task, 1000);


    }
}

