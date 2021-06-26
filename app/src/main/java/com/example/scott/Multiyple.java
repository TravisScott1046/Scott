package com.example.scott;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Multiyple extends AppCompatActivity implements Runnable, AdapterView.OnItemClickListener {

    Handler handler;
    ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);
        listView = (ListView) findViewById(R.id.foodie);


        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 2) {
                    ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) msg.obj;
                    FoodAdapter adapter = new FoodAdapter(Multiyple.this, R.layout.food_item,list);
                    listView.setAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
        listView.setOnItemClickListener(this);

        Thread t= new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(2);
        try {
            ArrayList<HashMap<String,String>> fooditem =new ArrayList<>();
            for(int i = 1;i<=12;i++) {
                Connection doc = Jsoup.connect("https://www.meishij.net/chufang/diy/wucan/?&page=" + Integer.toString(i)).timeout(5000);
                doc.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,/*;q=0.8");
                doc.header("Accept-Encoding", "gzip, deflate, sdch");
                doc.header("Accept-Language", "zh-CN,zh;q=0.8");
                doc.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                Elements tables= doc.get().select("div.listtyle1");
                for(int j = 0;j < tables.size();j++){
                    String title = tables.get(j).select("a").attr("title");
                    String uri = tables.get(j).select("a").attr("href");
                    String shopnamee = tables.get(j).select("a").text();
                    HashMap<String,String> food = new HashMap<>();
                    food.put("title",title);
                    food.put("uri",uri);
                    food.put("shopnamee",shopnamee);
                    if (!TextUtils.isEmpty(title)){
                        fooditem.add(food);
                    }

                }
            }
            msg.obj=fooditem;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}




