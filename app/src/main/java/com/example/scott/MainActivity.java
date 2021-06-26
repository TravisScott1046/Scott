package com.example.scott;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity implements Runnable{
    String TAG;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        Thread t=new Thread(this);
        t.start();
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

    }

    static final int REQUEST_SELECT_PHONE_NUMBER = 120;

    public void selectContact(View btn) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


       if(item.getItemId()==R.id.open_list){
            Intent list=new Intent(this,Multiyple.class);
        }
        return super.onOptionsItemSelected(item);
    }

    public void openWebPage(View btn){
        Uri webpage=Uri.parse("https://about.meituan.com/home");
        Intent intent=new Intent(Intent.ACTION_VIEW,webpage);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    @Override
    public void run() {
        Log.i(TAG, "run: ");
        //Message msg=handler.obtainMessage();
        //handler.sendMessage(msg);

        Document doc= null;
        try {
            doc=Jsoup.connect("http://www.dianping.com/wenjiang/ch10").get();
            Log.i(TAG, "run: "+doc.title());
            Elements foodnamee=doc.select("div.tit");
            for(Element e:foodnamee){
                String title=e.select("a").text();
                String uri =e.select("a").attr("herf");
            }
            Elements tables= doc.select("div.svr-info");
            for (Element table:tables){
            String svr=table.select("a").text();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

   private  String inputStrean2String(InputStream inputStream) throws IOException {
       final int bufferSize = 1024;
       final char[]buffer=new char[bufferSize];
       final StringBuilder out=new StringBuilder();
       Reader in=new InputStreamReader(inputStream,"gb2312");
       for(; ; ){
           int rsz=in.read(buffer,0,buffer.length);
           if(rsz<0)
               break;
           out.append(buffer,0,rsz);
       }
       return out.toString();
   }
   public void openthree(View btn){
        Log.i("open", "openOne: ");
        Intent opener=new Intent(this,MenuActivity.class);
        startActivityForResult(opener,2);
    }
}
