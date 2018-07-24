package com.example.strivecheng.appdragview.adapter;

import android.support.v4.app.ActivityCompat;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.strivecheng.appdragview.R;
import com.example.strivecheng.appdragview.data.AllAppSection;
import com.example.strivecheng.appdragview.data.AppInfo;

import java.util.List;

/**
 * Created by xingcc on 2018/7/23.
 * main function
 * 带分组，所有数据源
 * @author strivecheng
 */

public class AllAppItemAdapter extends BaseSectionQuickAdapter<AllAppSection,BaseViewHolder> {
    /** 是否是编辑模式*/
    private boolean isEditModel = false;

    public void setEditModel(boolean editModel) {
        isEditModel = editModel;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */


    public AllAppItemAdapter(int layoutResId, int sectionHeadResId, List<AllAppSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AllAppSection item) {
        helper.setText(R.id.section_header_title,item.header);

    }

    @Override
    protected void convert(BaseViewHolder helper, AllAppSection item) {
        AppInfo appInfo = item.t;
        helper.setText(R.id.app_info_title_tv,appInfo.getTitle());
        helper.setImageResource(R.id.app_info_icon_iv,appInfo.getImage());
        if (isEditModel) {
            if (appInfo.isSelect()) {
                helper.setImageResource(R.id.small_delete_iv,R.mipmap.delete_icon);
            }else {
                helper.setImageResource(R.id.small_delete_iv,R.mipmap.add_icon);
            }
            helper.setBackgroundColor(R.id.info_rv,ActivityCompat.getColor(mContext,R.color.bgLightGray));
            helper.addOnClickListener(R.id.small_delete_iv);
            helper.setGone(R.id.small_delete_iv,true);
        }else {
            helper.setGone(R.id.small_delete_iv,false);
            helper.setBackgroundColor(R.id.info_rv,ActivityCompat.getColor(mContext,R.color.bgWhite));

        }

    }
}
