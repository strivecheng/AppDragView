package com.example.strivecheng.appdragview;

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
    /** 我的应用数据源*/
    private ArrayList<AppInfo> myAppInfos = new ArrayList<>();
    /** 所有应用数据源，带分组头*/
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

        myAppLlt =  findViewById(R.id.my_app_llt);
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
        horizonRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
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
                switch (view.getId()) {
                    case R.id.small_delete_iv:
                        AllAppSection allAppSection = allAppItemAdapter.getItem(position);
                        AppInfo appInfo = allAppSection.t;
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
        myAppInfos.add(new AppInfo("公告", "", true, "GG"));
        myAppInfos.add(new AppInfo("日程", "", true, "RC"));
        myAppInfos.add(new AppInfo("汇报", "", true, "HB"));
        myAppInfos.add(new AppInfo("文件", "", true, "WJ"));
        myAppInfos.add(new AppInfo("项目跟踪", "", true, "XMZZ"));
        return myAppInfos;
    }

    private List<AllAppSection> getData() {
        allAppSections.add(new AllAppSection(true, "通用"));
        allAppSections.add(new AllAppSection(new AppInfo("公告", "", true, "GG")));
        allAppSections.add(new AllAppSection(new AppInfo("日程", "", true, "RC")));
        allAppSections.add(new AllAppSection(new AppInfo("汇报", "", true, "HB")));
        allAppSections.add(new AllAppSection(new AppInfo("文件", "", true, "WJ")));
        allAppSections.add(new AllAppSection(new AppInfo("项目跟踪", "", true, "XMZZ")));


        allAppSections.add(new AllAppSection(true, "继续教育"));
        allAppSections.add(new AllAppSection(new AppInfo("培训计划", "", false, "PXJH")));
        allAppSections.add(new AllAppSection(new AppInfo("培训报名", "", false, "PXBM")));
        allAppSections.add(new AllAppSection(new AppInfo("培训签到", "", false, "PXQD")));
        allAppSections.add(new AllAppSection(new AppInfo("微课堂", "", false, "WKT")));
        allAppSections.add(new AllAppSection(new AppInfo("操作考核", "", false, "CZKH")));


        allAppSections.add(new AllAppSection(true, "实习生管理"));
        allAppSections.add(new AllAppSection(new AppInfo("实习生档案", "", false, "SXSDA")));
        allAppSections.add(new AllAppSection(new AppInfo("培训计划", "", false, "S_PXJH")));
        allAppSections.add(new AllAppSection(new AppInfo("培训报名", "", false, "S_PXBM")));
        allAppSections.add(new AllAppSection(new AppInfo("培训签到", "", false, "S_PXQD")));
        allAppSections.add(new AllAppSection(new AppInfo("通知公告", "", false, "TZGG")));
        allAppSections.add(new AllAppSection(new AppInfo("奖惩信息", "", false, "JCXX")));
        allAppSections.add(new AllAppSection(new AppInfo("考勤管理", "", false, "KQGL")));
        allAppSections.add(new AllAppSection(new AppInfo("科室评价", "", false, "KSPJ")));
        allAppSections.add(new AllAppSection(new AppInfo("留院观察", "", false, "LYGC")));
        allAppSections.add(new AllAppSection(new AppInfo("科室申请", "", false, "KSSQ")));
        allAppSections.add(new AllAppSection(new AppInfo("实习批次", "", false, "SXPC")));
        allAppSections.add(new AllAppSection(new AppInfo("院校管理", "", false, "YXGL")));
        allAppSections.add(new AllAppSection(new AppInfo("宿舍管理", "", false, "SSGL")));
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
