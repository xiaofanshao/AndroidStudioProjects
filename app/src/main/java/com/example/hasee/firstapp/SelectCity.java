package com.example.hasee.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        final List<City> cityList=myApplication.getCityList();
        ArrayAdapter<City> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cityList);
        mlistview.setAdapter(adapter);
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City ct=cityList.get(position);
                Intent i=new Intent();
                i.putExtra("cityCode",ct.getNumber());
                setResult(RESULT_OK,i);
                finish();

            }
        });




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                Intent i=new Intent();
                i.putExtra("cityCode","101010100");
                setResult(RESULT_OK,i);
                finish();
                break;
            default:
                break;
        }
    }
}
