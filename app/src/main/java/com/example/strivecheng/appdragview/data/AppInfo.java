package com.example.strivecheng.appdragview.data;

/**
 * Created by xingcc on 2018/7/23.
 * main function
 *
 * @author strivecheng
 */

public class AppInfo {
    private String Code;
    private String Title;
    private int Image;
    private boolean IsSelect;

    public AppInfo(String title, int image, boolean isSelect,String code) {
        Title = title;
        Image = image;
        IsSelect = isSelect;
        Code = code;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public boolean isSelect() {
        return IsSelect;
    }

    public void setSelect(boolean select) {
        IsSelect = select;
    }
}
