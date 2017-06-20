package com.app.cgb.moviepreview.entity;

import java.util.List;

public class MoviesOfCompany {

    /**
     * productionTotalCount : 173
     * distributorTotalCount : 346
     * otherTotalCount : 21
     * currentPageCount : 3
     * movies : [{"id":175637,"name":"我还没动真格","nameEn":"俺はまだ本気出してないだけ","rating":"7.1","img":"http://img31.mtime.cn/mt/2013/03/15/135740.74485564_1280X720X2.jpg","year":"2013","directorId":1490230,"director":"福田雄一","releaseArea":"日本","actor1Id":942024,"actor1":"Shinichi Tsutsumi","actor2Id":1819939,"actor2":"Ai Hashimoto","releaseDate":"2013年6月15日"},{"id":192288,"name":"小小的家","nameEn":"小さいおうち","rating":"8.2","img":"http://img31.mtime.cn/mt/2014/01/06/154656.44162194_1280X720X2.jpg","year":"2014","directorId":892942,"director":"山田洋次","releaseArea":"日本","actor1Id":1000368,"actor1":"Takako Matsu","actor2Id":930183,"actor2":"Baishou Chieko","releaseDate":"2014年1月25日"},{"id":192737,"name":"武士的食谱","nameEn":"武士の献立","rating":"7.0","img":"http://img31.mtime.cn/mt/2013/11/04/164314.85443280_1280X720X2.jpg","year":"2014","directorId":1201598,"director":"朝原雄三","releaseArea":"日本","actor1Id":938261,"actor1":"Aya Ueto","actor2Id":1305266,"actor2":"Kengo Kora","releaseDate":"2013年12月14日"},{"id":219777,"name":"恋人们","nameEn":"恋人たち","rating":"7.6","img":"http://img31.mtime.cn/mt/2015/11/24/134416.78678123_1280X720X2.jpg","year":"2015","directorId":892934,"director":"桥口亮辅","releaseArea":"日本","actor1Id":2101542,"actor1":"Ryo Ikeda","actor2Id":2154895,"actor2":"Atsushi Shinohara","releaseDate":"2015年11月14日"},{"id":229123,"name":"植物图鉴","nameEn":"植物圖鑑","rating":"6.4","img":"http://img31.mtime.cn/mt/2015/10/19/091303.76313452_1280X720X2.jpg","year":"2016","directorId":1945773,"director":"三木康一郎","releaseArea":"日本","actor1Id":1994957,"actor1":"Takanori Iwata","actor2Id":1403790,"actor2":"Mitsuki Takahata","releaseDate":"2016年6月4日"},{"id":230258,"name":"荒地之恋","nameEn":"荒地の恋","rating":"-1.0","img":"http://img31.mtime.cn/mt/2015/12/07/154700.98329919_1280X720X2.jpg","year":"2015","directorId":893675,"director":"渡边孝好","releaseArea":"日本","actor1Id":946770,"actor1":"Tomorowo Taguchi","actor2Id":1009868,"actor2":"Yasuko Tomita","releaseDate":"2016年1月9日"},{"id":230862,"name":"毛骨悚然","nameEn":"クリーピー","rating":"6.4","img":"http://img31.mtime.cn/mt/2016/06/08/141558.37347675_1280X720X2.jpg","year":"2016","directorId":892858,"director":"黑泽清","releaseArea":"日本","actor1Id":920996,"actor1":"Hidetoshi Nishijima","actor2Id":926320,"actor2":"Yuko Takeuchi","releaseDate":"2016年6月18日"},{"id":231403,"name":"像\u201c像那样的东西\u201d那样的东西","nameEn":"の・ようなもの のようなもの","rating":"-1.0","img":"http://img31.mtime.cn/mt/2016/01/19/151503.97428488_1280X720X2.jpg","year":"2016","directorId":1814825,"director":"Yasukazu Sugiyama","releaseArea":"日本","actor1Id":1180516,"actor1":"Ken'ichi Matsuyama","actor2Id":1121181,"actor2":"Katsunobu Itô","releaseDate":"2016年1月16日"},{"id":233987,"name":"讨厌的女人","nameEn":"嫌な女","rating":"-1.0","img":"http://img31.mtime.cn/mt/2016/05/06/164105.96400936_1280X720X2.jpg","year":"2016","directorId":939290,"director":"黑木瞳","releaseArea":"日本","actor1Id":1831711,"actor1":"Yô Yoshida","actor2Id":946574,"actor2":"Yoshino Kimura","releaseDate":"2016年6月25日"},{"id":236837,"name":"家族之苦2","nameEn":"家族はつらいよII","rating":"0.0","img":"http://img31.mtime.cn/mt/837/236837/236837_1280X720X2.jpg","year":"2017","directorId":892942,"director":"山田洋次","releaseArea":"日本","actor1Id":923612,"actor1":"Satoshi Tsumabuki","actor2Id":924687,"actor2":"Yû Aoi","releaseDate":""}]
     */

    private int productionTotalCount;
    private int distributorTotalCount;
    private int otherTotalCount;
    private int currentPageCount;
    private List<MoviesBean> movies;

    public int getProductionTotalCount() {
        return productionTotalCount;
    }

    public void setProductionTotalCount(int productionTotalCount) {
        this.productionTotalCount = productionTotalCount;
    }

    public int getDistributorTotalCount() {
        return distributorTotalCount;
    }

    public void setDistributorTotalCount(int distributorTotalCount) {
        this.distributorTotalCount = distributorTotalCount;
    }

    public int getOtherTotalCount() {
        return otherTotalCount;
    }

    public void setOtherTotalCount(int otherTotalCount) {
        this.otherTotalCount = otherTotalCount;
    }

    public int getCurrentPageCount() {
        return currentPageCount;
    }

    public void setCurrentPageCount(int currentPageCount) {
        this.currentPageCount = currentPageCount;
    }

    public List<MoviesBean> getMovies() {
        return movies;
    }

    public void setMovies(List<MoviesBean> movies) {
        this.movies = movies;
    }

    public static class MoviesBean {
        /**
         * id : 175637
         * name : 我还没动真格
         * nameEn : 俺はまだ本気出してないだけ
         * rating : 7.1
         * img : http://img31.mtime.cn/mt/2013/03/15/135740.74485564_1280X720X2.jpg
         * year : 2013
         * directorId : 1490230
         * director : 福田雄一
         * releaseArea : 日本
         * actor1Id : 942024
         * actor1 : Shinichi Tsutsumi
         * actor2Id : 1819939
         * actor2 : Ai Hashimoto
         * releaseDate : 2013年6月15日
         */

        private int id;
        private String name;
        private String nameEn;
        private double rating;
        private String img;
        private String year;
        private int directorId;
        private String director;
        private String releaseArea;
        private int actor1Id;
        private String actor1;
        private int actor2Id;
        private String actor2;
        private String releaseDate;

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

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public int getDirectorId() {
            return directorId;
        }

        public void setDirectorId(int directorId) {
            this.directorId = directorId;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getReleaseArea() {
            return releaseArea;
        }

        public void setReleaseArea(String releaseArea) {
            this.releaseArea = releaseArea;
        }

        public int getActor1Id() {
            return actor1Id;
        }

        public void setActor1Id(int actor1Id) {
            this.actor1Id = actor1Id;
        }

        public String getActor1() {
            return actor1;
        }

        public void setActor1(String actor1) {
            this.actor1 = actor1;
        }

        public int getActor2Id() {
            return actor2Id;
        }

        public void setActor2Id(int actor2Id) {
            this.actor2Id = actor2Id;
        }

        public String getActor2() {
            return actor2;
        }

        public void setActor2(String actor2) {
            this.actor2 = actor2;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }
    }
}
