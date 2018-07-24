package com.example.strivecheng.appdragview.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.strivecheng.appdragview.R;
import com.example.strivecheng.appdragview.data.AppInfo;

import java.util.List;

/**
 * Created by xingcc on 2018/7/23.
 * main function  可拖拽适配器
 *
 * @author strivecheng
 */

public class MyAppDragAdapter extends BaseItemDraggableAdapter<AppInfo,BaseViewHolder>{


    public MyAppDragAdapter(List<AppInfo> data) {
        super(R.layout.section_info_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        helper.setText(R.id.app_info_title_tv,item.getTitle())
        .setImageResource(R.id.small_delete_iv,R.mipmap.delete_icon)
        .setImageResource(R.id.app_info_icon_iv,item.getImage());
        helper.addOnClickListener(R.id.small_delete_iv);
    }
}
