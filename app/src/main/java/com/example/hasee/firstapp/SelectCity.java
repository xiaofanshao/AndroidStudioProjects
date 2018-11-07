package com.example.hasee.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.hasee.app.MyApplication;
import com.example.hasee.bean.City;

import java.util.List;

public class SelectCity extends Activity implements View.OnClickListener {

    private ImageView mBackBtn;
    private ListView mlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_city);

        mBackBtn=(ImageView)findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        mlistview=(ListView)findViewById(R.id.listview);

        MyApplication  myApplication=(MyApplication) getApplication();
        List<City> list=myApplication.getCityList();
        ArrayAdapter<City> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        mlistview.setAdapter(adapter);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                Intent i=new Intent();
                i.putExtra("cityCode","101160101");
                setResult(RESULT_OK,i);
                finish();
                break;
            default:
                break;
        }
    }
}
