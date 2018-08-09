package com.example.strivecheng.appdragview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.strivecheng.appdragview.adapter.ShopCarListAdapter;
import com.example.strivecheng.appdragview.data.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by xingcc on 2018/8/9
 * main function: 购物车界面
 *
 * @author xingcc
 */
public class ShopCarActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backBtn;
    private RecyclerView shopListRv;
    private CheckBox allSelectCb;
    private TextView totalTv;
    private Button payMoneyBtn;
    private ArrayList<GoodsInfo> goodsInfos;
    private ShopCarListAdapter shopCarListAdapter;
    /**
     * 记录是否是全选状态
     */
    private boolean isAllSelect = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        goodsInfos = new ArrayList<>();

        backBtn = findViewById(R.id.back_btn);
        shopListRv = findViewById(R.id.select_shop_list_rv);
        allSelectCb = findViewById(R.id.all_select_cb);
        totalTv = findViewById(R.id.total_tv);
        payMoneyBtn = findViewById(R.id.pay_money_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        shopListRv.setLayoutManager(layoutManager);
        shopCarListAdapter = new ShopCarListAdapter(getData());
        shopListRv.setAdapter(shopCarListAdapter);
        shopListRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //初始化先判断所有商品是否选中
        setAllSelectCbState();
    }


    private void initListener() {
        backBtn.setOnClickListener(this);
        payMoneyBtn.setOnClickListener(this);
        allSelectCb.setOnClickListener(this);
        shopCarListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsInfo goodsInfo = shopCarListAdapter.getItem(position);
                if (goodsInfo == null) {
                    return;
                }
                TextView countTv = (TextView) adapter.getViewByPosition(shopListRv, position, R.id.goods_count_et);
                switch (view.getId()) {
                    case R.id.sub_goods_iv:
                        if (goodsInfo.getCount() <= 1) {
                            Toast.makeText(ShopCarActivity.this, "不能再少啦！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        goodsInfo.setCount(goodsInfo.getCount() - 1);
                        countTv.setText(goodsInfo.getCount() + "");
                        if (goodsInfo.isSelect()) {
                            refreshTotal();
                        }
                        break;
                    case R.id.add_goods_iv:
                        goodsInfo.setCount(goodsInfo.getCount() + 1);
                        countTv.setText(goodsInfo.getCount() + "");
                        if (goodsInfo.isSelect()) {
                            refreshTotal();
                        }
                        break;
                    case R.id.delete_btn:
                        goodsInfos.remove(position);
                        adapter.notifyItemRemoved(position);
                        refreshTotal();
                        break;
                    case R.id.select_cb:
                        goodsInfo.setSelect(!goodsInfo.isSelect());
                        refreshTotal();
                        setAllSelectCbState();
                        break;
                    default:
                }
            }
        });

//        allSelectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (buttonView.isEnabled()) {
//                    allSelectGoods(isChecked);
//                    shopCarListAdapter.notifyDataSetChanged();
//                    refreshTotal();
//                }
//            }
//        });
    }

    private void initData() {
        refreshTotal();
    }

    /**
     * 刷新总结
     */
    private void refreshTotal() {
        getTotal();
        String content = "合计支付：<font color=\"#33000000\" ><big>" + getTotal() + "</big></font> ";
        totalTv.setText(Html.fromHtml(content));
//        totalTv.setText("" + getTotal());
    }

    /**
     * 获取要支付的总价
     */
    private double getTotal() {
        double total = 0;
        for (GoodsInfo goods : goodsInfos) {
            if (goods.isSelect()) {
                total = total + goods.getPrice() * goods.getCount();
            }
        }
        return total;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.pay_money_btn:
                Toast.makeText(this, "您一共消费了" + getTotal() + "元！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.all_select_cb:
                boolean isChecked = ((CheckBox) v).isChecked();
                allSelectGoods(isChecked);
                shopCarListAdapter.notifyDataSetChanged();
                refreshTotal();
                break;
            default:
        }
    }

    /**
     * 判断是否所有商品全为选中
     */
    private boolean allGoodsIsAllSelect() {
        isAllSelect = true;
        for (GoodsInfo goods : goodsInfos) {
            isAllSelect = isAllSelect && goods.isSelect();
        }
        return isAllSelect;
    }

    /**
     * 设置全选按钮的状态
     */
    private void setAllSelectCbState() {
        allGoodsIsAllSelect();
        allSelectCb.setChecked(isAllSelect);
    }

    /**
     * 全选或者取消全选商品
     */
    private void allSelectGoods(boolean isSelect) {
        for (GoodsInfo goods : goodsInfos) {
            goods.setSelect(isSelect);
        }
    }

    private List<GoodsInfo> getData() {
        for (int i = 0; i < 5; i++) {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setCount(1);
            goodsInfo.setName("这是最牛逼的手机 iPhone X");
            goodsInfo.setPrice(10);
            goodsInfo.setType("深空灰");
            goodsInfo.setSelect(false);
            goodsInfos.add(goodsInfo);
        }
        return goodsInfos;
    }

}
