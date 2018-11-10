package com.example.hasee.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.hasee.app.MyApplication;
import com.example.hasee.bean.City;

import java.util.ArrayList;
import java.util.List;

public class SelectCity extends Activity implements View.OnClickListener {

    private ImageView mBackBtn;
    private ListView mlistview;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_city);

        mBackBtn=(ImageView)findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        mlistview=(ListView)findViewById(R.id.listview);

        final MyApplication  myApplication=(MyApplication) getApplication();
        final List<City> cityList=myApplication.getCityList();
        final ArrayAdapter<City> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cityList);
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



        mEditText=(EditText)findViewById(R.id.search_bar);
        TextWatcher mTextWatcher=new TextWatcher() {
            private List<City> list;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MyApplication myApplication1=(MyApplication)getApplication();
                List<City> list=myApplication.getCityListByInquiry(s.toString());


                ArrayAdapter<City> adapter=new ArrayAdapter<City>(SelectCity.this,android.R.layout.simple_list_item_1,list);
                mlistview.setAdapter(adapter);


            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        };
        mEditText.addTextChangedListener(mTextWatcher);






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
