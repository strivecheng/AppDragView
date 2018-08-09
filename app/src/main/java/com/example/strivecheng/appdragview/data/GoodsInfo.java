package com.example.strivecheng.appdragview.data;

import java.io.Serializable;

/**
 * Created by xingcc on 2018/8/9.
 * main function
 * 商品的模型
 * @author strivecheng
 */

public class GoodsInfo  implements Serializable{
    private double Price;
    private String Name;
    private String IconUrl;
    private String Type;
    private int Count;
    private boolean isSelect;

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}

