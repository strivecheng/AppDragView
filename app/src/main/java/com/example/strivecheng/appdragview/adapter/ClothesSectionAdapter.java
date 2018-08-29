package com.example.strivecheng.appdragview.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.strivecheng.appdragview.R;
import com.example.strivecheng.appdragview.data.ClothesSection;

import java.util.List;

/**
 * Created by xingcc on 2018/8/29.
 * main function
 *
 * @author strivecheng
 */

public class ClothesSectionAdapter extends BaseSectionQuickAdapter<ClothesSection,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public ClothesSectionAdapter(int layoutResId, int sectionHeadResId, List<ClothesSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ClothesSection item) {
        helper.setText(R.id.section_header_title,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClothesSection item) {
        helper.setText(R.id.clothes_info_name_tv,item.t.getName());

    }
}
