package com.example.hasee.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.hasee.util.NetUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_info);

        //测试网络数据
        if(NetUtil.getNetworkState(this) !=NetUtil.NETWORN_NONE){
            Log.d("myWeather","网络ok");
            Toast.makeText(this, "网络ok", Toast.LENGTH_LONG).show();
        }else {
            Log.d("myWeather","网络完蛋了");
            Toast.makeText(this,"网络完蛋了",Toast.LENGTH_LONG).show();
        }

    }
}
