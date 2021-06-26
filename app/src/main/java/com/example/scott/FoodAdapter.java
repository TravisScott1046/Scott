package com.example.scott;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodAdapter extends ArrayAdapter {


    public FoodAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HashMap<String,String>> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.food_item,parent,false);
        }       //获取需要的适配器布局，   得到适配器的基本形式

        Map<String,String> food = (Map<String, String>) getItem(position);
        TextView shop_name = (TextView) view.findViewById(R.id.shop_name);           //获得布局中的控件
        TextView food_name = (TextView) view.findViewById(R.id.food_name);
        TextView srv_name = (TextView) view.findViewById(R.id.srv);

        shop_name.setText(food.get("shopnamee"));           //适配器展现
        food_name.setText(food.get("title"));
        srv_name.setText(food.get("srv"));
        return view;
    }




}
