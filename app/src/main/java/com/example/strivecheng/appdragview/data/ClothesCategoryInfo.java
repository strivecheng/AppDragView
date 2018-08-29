package com.example.strivecheng.appdragview.data;

import java.util.List;

/**
 * Created by xingcc on 2018/8/29.
 * main function
 *
 * @author strivecheng
 */

public class ClothesCategoryInfo {

    /**
     * id : 23
     * categoryName : 西服
     * goodsList : [{"id":"2","name":"很贵的西服","category":"23","pic":"http://172.22.0.147:8088/images/goods/011534472543215.jpg","price":"100","status":"1","number":"4","companyid":"d583e7de6d2d48b78fb3c7dcb180cb1f","tenantid":"ac88ceb386aa4231b09bf472cb937c24"}]
     */

    private String id;
    private String categoryName;
    private List<ClothesInfo> goodsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ClothesInfo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ClothesInfo> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsListBean {
        /**
         * id : 2
         * name : 很贵的西服
         * category : 23
         * pic : http://172.22.0.147:8088/images/goods/011534472543215.jpg
         * price : 100
         * status : 1
         * number : 4
         * companyid : d583e7de6d2d48b78fb3c7dcb180cb1f
         * tenantid : ac88ceb386aa4231b09bf472cb937c24
         */

        private String id;
        private String name;
        private String category;
        private String pic;
        private String price;
        private String status;
        private String number;
        private String companyid;
        private String tenantid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCompanyid() {
            return companyid;
        }

        public void setCompanyid(String companyid) {
            this.companyid = companyid;
        }

        public String getTenantid() {
            return tenantid;
        }

        public void setTenantid(String tenantid) {
            this.tenantid = tenantid;
        }
    }
}
