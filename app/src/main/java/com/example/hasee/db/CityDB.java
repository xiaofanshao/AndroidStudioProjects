package com.example.hasee.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.hasee.bean.City;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CityDB {
    public static final String CITY_DB_NAME="city.db";//给外面用的
    private static final String CITY_TABLE_NAME="city";//给自己用的
    private SQLiteDatabase db;

    public CityDB(Context context, String path){
        db=context.openOrCreateDatabase(path,Context.MODE_PRIVATE,null);

    }

    public List<City> getAllCity(){
        List<City> list=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT * FROM "+ CITY_TABLE_NAME,null);

        while(c.moveToNext()){
            String province=c.getString(c.getColumnIndex("province"));
            String city=c.getString(c.getColumnIndex("city"));
            String number=c.getString(c.getColumnIndex("number"));
            String allPY=c.getString(c.getColumnIndex("allpy"));
            String allFirstPY=c.getString(c.getColumnIndex("allfirstpy"));
            String firstPY=c.getString(c.getColumnIndex("firstpy"));
            City item=new City(province,city,number,firstPY,allPY,allFirstPY);
            list.add(item);
        }
        return list;
    }

    public List<City> inquiry(String s){
        List<City> list=new ArrayList<>();
        //SELECT * FROM city WHERE city="北" OR province="北京"
        Cursor c=db.rawQuery("SELECT * FROM "+ CITY_TABLE_NAME+" WHERE city LIKE \""+s+"%\" OR province=\""+s+"\"",null);
        if(c.getColumnCount()!=0){
            while(c.moveToNext()){
                String province=c.getString(c.getColumnIndex("province"));
                String city=c.getString(c.getColumnIndex("city"));
                String number=c.getString(c.getColumnIndex("number"));
                String allPY=c.getString(c.getColumnIndex("allpy"));
                String allFirstPY=c.getString(c.getColumnIndex("allfirstpy"));
                String firstPY=c.getString(c.getColumnIndex("firstpy"));
                City item=new City(province,city,number,firstPY,allPY,allFirstPY);
                list.add(item);
            }
        }

        return list;
    }

}
