package com.example.strivecheng.appdragview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.example.strivecheng.appdragview.adapter.AllAppItemAdapter;
import com.example.strivecheng.appdragview.adapter.MyAppDragAdapter;
import com.example.strivecheng.appdragview.adapter.MyAppSimpleHorizonAdapter;
import com.example.strivecheng.appdragview.data.AllAppSection;
import com.example.strivecheng.appdragview.data.AppInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Create by xingcc on 2018/7/23
 * main function:  仿支付宝应用管理功能
 *
 * @author xingcc
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView allAppRv;
    private AllAppItemAdapter allAppItemAdapter;
    /**
     * 我的应用数据源
     */
    private ArrayList<AppInfo> myAppInfos = new ArrayList<>();
    /**
     * 所有应用数据源，带分组头
     */
    private ArrayList<AllAppSection> allAppSections = new ArrayList<>();
    private RecyclerView myAppRv;
    private MyAppDragAdapter myAppDragAdapter;
    private Button editAppBtn;
    private Button finishBtn;
    private Button cancelBtn;
    private LinearLayout myAppLlt;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
    }


    private void initView() {

        myAppLlt = findViewById(R.id.my_app_llt);
        cancelBtn = findViewById(R.id.cancel_btn);
        finishBtn = findViewById(R.id.finish_btn);

        myAppRv = findViewById(R.id.my_app_rv);
        myAppRv.setLayoutManager(new GridLayoutManager(this, 5));
        myAppDragAdapter = new MyAppDragAdapter(getMyAppData());
        myAppRv.setAdapter(myAppDragAdapter);

        allAppRv = findViewById(R.id.all_app_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        allAppRv.setLayoutManager(gridLayoutManager);
        allAppItemAdapter = new AllAppItemAdapter(R.layout.section_info_item, R.layout.section_header_item, getData());
        allAppRv.setAdapter(allAppItemAdapter);

        headerView = LayoutInflater.from(this).inflate(R.layout.app_edit_header_view, null);
        editAppBtn = headerView.findViewById(R.id.edit_my_app_btn);

        RecyclerView horizonRv = headerView.findViewById(R.id.my_app_simple_horizon_rv);
        horizonRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horizonRv.setAdapter(new MyAppSimpleHorizonAdapter(myAppInfos));
        allAppItemAdapter.setHeaderView(headerView);

        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(myAppDragAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(myAppRv);

        // 开启拖拽
        myAppDragAdapter.enableDragItem(itemTouchHelper, R.id.app_info_icon_iv, true);

    }

    private void initClick() {
        cancelBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        editAppBtn.setOnClickListener(this);

        myAppDragAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.small_delete_iv:
                        AppInfo appInfo = myAppDragAdapter.getItem(position);
                        if (appInfo != null) {
//                            AppInfo info = isContain(appInfo.getCode(), myAppInfos);
//                            if (info != null) {
                            myAppInfos.remove(appInfo);
//                            } else {
//                                myAppInfos.add(info);
//                            }
                            myAppDragAdapter.notifyDataSetChanged();

                            AllAppSection allAppSection = isContainAppInfo(appInfo.getCode(), allAppSections);
                            if (allAppSection != null) {
                                AppInfo info = allAppSection.t;
                                info.setSelect(!info.isSelect());
                            }
                            allAppItemAdapter.notifyDataSetChanged();
                        }

                        break;
                    default:
                }
            }
        });

        allAppItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AllAppSection allAppSection = allAppItemAdapter.getItem(position);
                AppInfo appInfo = null;
                if (allAppSection != null) {
                    appInfo = allAppSection.t;
                }
                switch (view.getId()) {
                    case R.id.small_delete_iv:
                        if (appInfo != null) {
                            AppInfo info = isContain(appInfo.getCode(), myAppInfos);
                            if (info != null) {
                                myAppInfos.remove(info);
                            } else {
                                if (myAppInfos.size() >= 9) {
                                    Toast.makeText(MainActivity.this, "首页App个数不能多于9个", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                myAppInfos.add(appInfo);
                            }
                            appInfo.setSelect(!appInfo.isSelect());
                            allAppItemAdapter.notifyDataSetChanged();
                            myAppDragAdapter.notifyDataSetChanged();
                        }

                        break;
                    case R.id.info_rl:
                        if (appInfo ==null) {
                            return;
                        }
                        if ( "QGW".equals(appInfo.getCode())) {
                            startActivity(new Intent(MainActivity.this, ShopCarActivity.class));
                        }else  if ("QFZ".equals(appInfo.getCode())){
                            startActivity(new Intent(MainActivity.this, SectionActivity.class));
                        }
                        break;
                    default:
                }
            }
        });
        myAppDragAdapter.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        });
    }

    /**
     * 判断是否在我的app里面
     *
     * @param code
     * @param appInfos
     * @return
     */
    private AppInfo isContain(String code, List<AppInfo> appInfos) {
        if (code == null || "".equals(code)) {
            return null;
        }
        for (AppInfo appInfo : appInfos) {
            if (code.equals(appInfo.getCode())) {
                return appInfo;
            }
        }
        return null;
    }

    /**
     * 从所有数据源找出
     *
     * @param code
     * @param allAppSections
     * @return
     */
    private AllAppSection isContainAppInfo(String code, List<AllAppSection> allAppSections) {
        if (code == null || "".equals(code)) {
            return null;
        }
        for (AllAppSection appSection : allAppSections) {
            if (!appSection.isHeader) {
                AppInfo appInfo = appSection.t;
                if (appInfo != null) {
                    if (code.equals(appInfo.getCode())) {
                        return appSection;
                    }
                }
            }

        }
        return null;
    }

    private List<AppInfo> getMyAppData() {
        myAppInfos.add(new AppInfo("公告", R.mipmap.notice_icon, true, "GG"));
        myAppInfos.add(new AppInfo("日程", R.mipmap.rc_icon, true, "RC"));
        myAppInfos.add(new AppInfo("汇报", R.mipmap.work_report_icon, true, "HB"));
        myAppInfos.add(new AppInfo("文件", R.mipmap.wj_icon, true, "WJ"));
        myAppInfos.add(new AppInfo("项目跟踪", R.mipmap.xmgz_icon, true, "XMZZ"));
        return myAppInfos;
    }

    private List<AllAppSection> getData() {
        allAppSections.add(new AllAppSection(true, "通用"));
        allAppSections.add(new AllAppSection(new AppInfo("公告", R.mipmap.notice_icon, true, "GG")));
        allAppSections.add(new AllAppSection(new AppInfo("日程", R.mipmap.rc_icon, true, "RC")));
        allAppSections.add(new AllAppSection(new AppInfo("汇报", R.mipmap.work_report_icon, true, "HB")));
        allAppSections.add(new AllAppSection(new AppInfo("文件", R.mipmap.wj_icon, true, "WJ")));
        allAppSections.add(new AllAppSection(new AppInfo("项目跟踪", R.mipmap.xmgz_icon, true, "XMZZ")));
        allAppSections.add(new AllAppSection(new AppInfo("去购物", R.mipmap.xmgz_icon, true, "QGW")));
        allAppSections.add(new AllAppSection(new AppInfo("去分组", R.mipmap.xmgz_icon, true, "QFZ")));


        allAppSections.add(new AllAppSection(true, "继续教育"));
        allAppSections.add(new AllAppSection(new AppInfo("培训计划", R.mipmap.pxjh_icon, false, "PXJH")));
        allAppSections.add(new AllAppSection(new AppInfo("培训报名", R.mipmap.pxbm_icon, false, "PXBM")));
        allAppSections.add(new AllAppSection(new AppInfo("培训签到", R.mipmap.pxqd_icon, false, "PXQD")));
        allAppSections.add(new AllAppSection(new AppInfo("微课堂", R.mipmap.wkt_icon, false, "WKT")));
        allAppSections.add(new AllAppSection(new AppInfo("操作考核", R.mipmap.operation_exam_icon, false, "CZKH")));


        allAppSections.add(new AllAppSection(true, "实习生管理"));
        allAppSections.add(new AllAppSection(new AppInfo("实习生档案", R.mipmap.sxsdn_icon, false, "SXSDA")));
        allAppSections.add(new AllAppSection(new AppInfo("培训计划", R.mipmap.s_pxjh_icon, false, "S_PXJH")));
        allAppSections.add(new AllAppSection(new AppInfo("培训报名", R.mipmap.s_pxbm_icon, false, "S_PXBM")));
        allAppSections.add(new AllAppSection(new AppInfo("培训签到", R.mipmap.s_pxqd_icon, false, "S_PXQD")));
        allAppSections.add(new AllAppSection(new AppInfo("通知公告", R.mipmap.tzgg_icon, false, "TZGG")));
        allAppSections.add(new AllAppSection(new AppInfo("奖惩信息", R.mipmap.jcxx_icon, false, "JCXX")));
        allAppSections.add(new AllAppSection(new AppInfo("考勤管理", R.mipmap.kqgl_icon, false, "KQGL")));
        allAppSections.add(new AllAppSection(new AppInfo("科室评价", R.mipmap.kspj_icon, false, "KSPJ")));
        allAppSections.add(new AllAppSection(new AppInfo("留院观察", R.mipmap.lysq_icon, false, "LYGC")));
        allAppSections.add(new AllAppSection(new AppInfo("科室申请", R.mipmap.kssq_icon, false, "KSSQ")));
        allAppSections.add(new AllAppSection(new AppInfo("实习批次", R.mipmap.sxpc_icon, false, "SXPC")));
        allAppSections.add(new AllAppSection(new AppInfo("院校管理", R.mipmap.yxgl_icon, false, "YXGL")));
        allAppSections.add(new AllAppSection(new AppInfo("宿舍管理", R.mipmap.ssgl_icon, false, "SSGL")));
        return allAppSections;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn:
                myAppLlt.setVisibility(View.GONE);
                finishBtn.setVisibility(View.GONE);
                cancelBtn.setVisibility(View.GONE);
                allAppItemAdapter.setHeaderView(headerView);
                allAppItemAdapter.setEditModel(false);
                allAppItemAdapter.notifyDataSetChanged();
                break;
            case R.id.finish_btn:

                break;
            case R.id.edit_my_app_btn:
                allAppItemAdapter.setEditModel(true);
                allAppItemAdapter.removeAllHeaderView();
                allAppItemAdapter.notifyDataSetChanged();
                myAppLlt.setVisibility(View.VISIBLE);
                finishBtn.setVisibility(View.VISIBLE);
                cancelBtn.setVisibility(View.VISIBLE);
                break;
            default:
        }
    }
}
