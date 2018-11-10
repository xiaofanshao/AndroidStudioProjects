package com.example.hasee.app;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.example.hasee.bean.City;
import com.example.hasee.db.CityDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private  static final String TAG="MyAPP";

    private static MyApplication myApplication;
    private CityDB mCityDB;

    private List<City> mCityList;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG,"MyApplication->Oncreate");

        myApplication=this;
        mCityDB=openCityDB();
        initCityList();
    }
    private void initCityList(){

        mCityList=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                prepareCityList();
            }
        }).start();
    }
    public List<City> getCityList(){
        return mCityList;
    }
    public List<City> getCityListByInquiry(String s){
        return mCityDB.inquiry(s);
    }


    private boolean prepareCityList(){
        mCityList=mCityDB.getAllCity();
        int i=0;
        for(City city:mCityList){
            i++;
            String cityName=city.getCity();
            String cityCode=city.getNumber();
            Log.d(TAG,cityCode+":"+cityName);
        }
        Log.d(TAG,"i="+i);
        return true;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }

    private CityDB openCityDB(){
        String  path= "/data"
                +Environment.getDataDirectory().getAbsolutePath()
                + File.separator+getPackageName()
                + File.separator+"databases1"
                +File.separator
                +CityDB.CITY_DB_NAME;
        File db=new File(path);
        Log.d(TAG,path);

        if(!db.exists()){
            String pathfolder="/data"
                    +Environment.getDataDirectory().getAbsolutePath()
                    +File.separator+getPackageName()
                    +File.separator+"databases1"
                    +File.separator;
            File dirFirstFolder=new File(pathfolder);
            if(!dirFirstFolder.exists()){
                dirFirstFolder.mkdir();
                Log.i("MyApp","mkdir");
            }
            Log.i("MyApp","db is not exitsts");

            try {
                InputStream is=getAssets().open("city.db");
                FileOutputStream fos=new FileOutputStream(db);
                int len=-1;
                byte [] buffer=new byte[1024];
                while((len=is.read(buffer))!=-1){
                    fos.write(buffer,0,len);
                    fos.flush();
                }
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return new CityDB(this,path);

    }
}
