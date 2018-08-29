package com.example.strivecheng.appdragview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.strivecheng.appdragview.adapter.ClothesSectionAdapter;
import com.example.strivecheng.appdragview.data.ClothesCategoryInfo;
import com.example.strivecheng.appdragview.data.ClothesSection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SectionActivity extends AppCompatActivity {

    private RecyclerView clothesListRv;
    private LinearLayoutManager layoutManager;
    private ClothesSectionAdapter clothesSectionAdapter;
    private ArrayList<ClothesSection> clothesSections;
    private String data = "[\n" +
            "            {\n" +
            "                \"id\": \"23\",\n" +
            "                \"categoryName\": \"西服\",\n" +
            "                \"goodsList\": [\n" +
            "                    {\n" +
            "                        \"id\": \"2\",\n" +
            "                        \"name\": \"很贵的西服\",\n" +
            "                        \"category\": \"23\",\n" +
            "                        \"pic\": \"http://172.22.0.147:8088/images/goods/011534472543215.jpg\",\n" +
            "                        \"price\": \"100\",\n" +
            "                        \"status\": \"1\",\n" +
            "                        \"number\": \"4\",\n" +
            "                        \"companyid\": \"d583e7de6d2d48b78fb3c7dcb180cb1f\",\n" +
            "                        \"tenantid\": \"ac88ceb386aa4231b09bf472cb937c24\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"cfdca51f41bd4efbaf247b9a67d25cdc\",\n" +
            "                \"categoryName\": \"衬衫\",\n" +
            "                \"goodsList\": [\n" +
            "                    {\n" +
            "                        \"id\": \"1\",\n" +
            "                        \"name\": \"精品衬衫\",\n" +
            "                        \"category\": \"cfdca51f41bd4efbaf247b9a67d25cdc\",\n" +
            "                        \"pic\": \"http://172.22.0.147:8088/images/goods/011534492988673.jpg\",\n" +
            "                        \"price\": \"66\",\n" +
            "                        \"status\": \"1\",\n" +
            "                        \"number\": \"20\",\n" +
            "                        \"companyid\": \"d583e7de6d2d48b78fb3c7dcb180cb1f\",\n" +
            "                        \"tenantid\": \"ac88ceb386aa4231b09bf472cb937c24\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"id\": \"d7c546d1a7f947539c8e2a6bedd5ba3b\",\n" +
            "                        \"name\": \"好看的衬衫\",\n" +
            "                        \"category\": \"cfdca51f41bd4efbaf247b9a67d25cdc\",\n" +
            "                        \"pic\": \"http://172.22.0.147:8088/images/goods/011534492988673.jpg\",\n" +
            "                        \"price\": \"333\",\n" +
            "                        \"status\": \"1\",\n" +
            "                        \"number\": \"300\",\n" +
            "                        \"companyid\": \"d583e7de6d2d48b78fb3c7dcb180cb1f\",\n" +
            "                        \"tenantid\": \"ac88ceb386aa4231b09bf472cb937c24\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        initView();
    }

    private void initView() {
        clothesSections = new ArrayList<>();
        getData();
        clothesListRv = findViewById(R.id.clothes_list_rv);
        layoutManager = new LinearLayoutManager(this);
        clothesListRv.setLayoutManager(layoutManager);
        clothesSectionAdapter = new ClothesSectionAdapter(R.layout.section_clothes_info_item,R.layout.section_header_item,  clothesSections);
        clothesListRv.setAdapter(clothesSectionAdapter);
    }

    private void getData() {
        Gson gson = new Gson();
        ArrayList<ClothesCategoryInfo> clothesCategoryInfos = gson.fromJson(data, new TypeToken<ArrayList<ClothesCategoryInfo>>() {
        }.getType());
        if (clothesCategoryInfos != null) {
            clothesSections.clear();
            for (int i = 0; i < clothesCategoryInfos.size(); i++) {
                ClothesSection clothesSection = new ClothesSection(true,clothesCategoryInfos.get(i).getCategoryName());
                clothesSections.add(clothesSection);
                for (int j = 0; j < clothesCategoryInfos.get(i).getGoodsList().size(); j++) {
                    ClothesSection clothesSection1 = new ClothesSection(clothesCategoryInfos.get(i).getGoodsList().get(j));
                    clothesSections.add(clothesSection1);
                }
            }
        }
    }
}
