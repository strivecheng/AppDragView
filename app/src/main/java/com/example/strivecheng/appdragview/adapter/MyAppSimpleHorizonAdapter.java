package com.example.strivecheng.appdragview.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.strivecheng.appdragview.R;
import com.example.strivecheng.appdragview.data.AppInfo;

import java.util.List;

/**
 * Created by xingcc on 2018/7/24.
 * main function
 *
 * @author strivecheng
 */

public class MyAppSimpleHorizonAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder> {
    public MyAppSimpleHorizonAdapter(@Nullable List<AppInfo> data) {
        super(R.layout.my_app_horizon_item,data);
    }



    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        helper.setImageResource(R.id.app_simple_horizon_iv,item.getImage());
    }
}
