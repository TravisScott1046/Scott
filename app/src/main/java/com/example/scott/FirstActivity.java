package com.example.scott;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

public void openOne(View btn){
  Log.i("open", "openOne: ");
  Intent opener=new Intent(this,MainActivity.class);
  startActivityForResult(opener,2);
}
  public void openTwo(View btn){
    Log.i("open", "openTwo: ");
    Intent opener=new Intent(this,Multiyple.class);
    startActivityForResult(opener,1);
  }
}