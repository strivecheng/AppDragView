package com.example.strivecheng.appdragview.adapter;

import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.strivecheng.appdragview.R;
import com.example.strivecheng.appdragview.data.GoodsInfo;

import java.util.List;

/**
 * Created by xingcc on 2018/8/9.
 * main function
 *
 * @author strivecheng
 */

public class ShopCarListAdapter extends BaseQuickAdapter<GoodsInfo,BaseViewHolder> {
    public ShopCarListAdapter(List<GoodsInfo> data) {
        super(R.layout.shop_car_list_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsInfo item) {
        helper.setText(R.id.goods_name_tv,item.getName())
                .setText(R.id.goods_price_tv,"￥"+item.getPrice()+"")
                .setText(R.id.goods_count_et,item.getCount()+"")
                .setText(R.id.goods_type_tv,item.getType())
        .addOnClickListener(R.id.add_goods_iv)
        .addOnClickListener(R.id.sub_goods_iv)
        .addOnClickListener(R.id.delete_btn)
        .addOnClickListener(R.id.select_cb);

        CheckBox checkBox = helper.getView(R.id.select_cb);
        checkBox.setChecked(item.isSelect());
//        .setOnCheckedChangeListener(R.id.select_cb, new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    buttonView.setChecked(!isChecked);
//                    item.setSelect(!isChecked);
//            }
//        });

    }

//    public  void refreshCountTv(int count){
//
//    }
}
