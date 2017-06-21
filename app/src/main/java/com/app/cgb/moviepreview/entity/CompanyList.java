package com.app.cgb.moviepreview.entity;

import java.util.ArrayList;
import java.util.List;


public class CompanyList {

    private List<ProductionListBean> productionList;
    private List<DistributorListBean> distributorList;
    private List<SpecialEffectsListBean> specialEffectsList;
    private List<OtherListBean> otherList;

    public List<ProductionListBean> getProductionList() {
        return productionList;
    }

    public void setProductionList(List<ProductionListBean> productionList) {
        this.productionList = productionList;
    }

    public List<DistributorListBean> getDistributorList() {
        return distributorList;
    }

    public void setDistributorList(List<DistributorListBean> distributorList) {
        this.distributorList = distributorList;
    }

    public List<SpecialEffectsListBean> getSpecialEffectsList() {
        return specialEffectsList;
    }

    public void setSpecialEffectsList(List<SpecialEffectsListBean> specialEffectsList) {
        this.specialEffectsList = specialEffectsList;
    }

    public List<OtherListBean> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<OtherListBean> otherList) {
        this.otherList = otherList;
    }

    public List<BaseBean> getList(){
        ArrayList<BaseBean> list = new ArrayList<>();
        list.addAll(productionList);
        list.addAll(distributorList);
        list.addAll(specialEffectsList);
        list.addAll(otherList);
        return list;
    }

    public static class ProductionListBean extends BaseBean{
        /**
         * id : 125
         * name : 二十世纪福斯电影公司
         * locationName : 美国
         */

        private int id;
        private String name;
        private String locationName;
        public static final String TITLE = "制作公司";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocationName() {
            return locationName;
        }

        @Override
        public String getTitle() {
            return TITLE;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }
    }

    public static class DistributorListBean extends BaseBean{
        /**
         * id : 125
         * name : 二十世纪福斯电影公司
         * locationName : 美国
         */

        private int id;
        private String name;
        private String locationName;

        public static final String TITLE = "发行公司";


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocationName() {
            return locationName;
        }

        @Override
        public String getTitle() {
            return TITLE;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }
    }

    public static class SpecialEffectsListBean extends BaseBean{
        /**
         * id : 428
         * name : Image Engine Design Inc.
         * locationName :
         */

        private int id;
        private String name;
        private String locationName;

        public static final String TITLE = "特效公司";


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocationName() {
            return locationName;
        }

        @Override
        public String getTitle() {
            return TITLE;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }
    }

    public static class OtherListBean extends BaseBean{
        /**
         * id : 73207
         * name : Hollywood Trucks
         * locationName :
         */

        private int id;
        private String name;
        private String locationName;

        public static final String TITLE = "其他公司";


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocationName() {
            return locationName;
        }

        @Override
        public String getTitle() {
            return TITLE;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }
    }

    public abstract static class BaseBean{
        public abstract int getId();
        public abstract String getName();
        public abstract String getLocationName();
        public abstract String getTitle();
    }
}
