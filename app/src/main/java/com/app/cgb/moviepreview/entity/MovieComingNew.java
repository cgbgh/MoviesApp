package com.app.cgb.moviepreview.entity;

import java.util.List;

public class MovieComingNew {

    private List<AttentionBean> attention;
    private List<MoviecomingsBean> moviecomings;

    public List<AttentionBean> getAttention() {
        return attention;
    }

    public void setAttention(List<AttentionBean> attention) {
        this.attention = attention;
    }

    public List<MoviecomingsBean> getMoviecomings() {
        return moviecomings;
    }

    public void setMoviecomings(List<MoviecomingsBean> moviecomings) {
        this.moviecomings = moviecomings;
    }

    public static class AttentionBean {
        /**
         * actor1 : 休·杰克曼
         * actor2 : 达芙妮·基恩
         * director : 詹姆斯·曼高德
         * id : 209688
         * image : http://img5.mtime.cn/mt/2016/11/15/100602.79581335_1280X720X2.jpg
         * isFilter : false
         * isTicket : true
         * isVideo : true
         * locationName : 美国
         * rDay : 3
         * rMonth : 3
         * rYear : 2017
         * releaseDate : 3月3日上映
         * title : 金刚狼3：殊死一战
         * type : 动作 / 剧情 / 科幻
         * videoCount : 3
         * videos : [{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/02/16/103716.67024936.jpg","length":60,"title":"金刚狼3：殊死一战 正式版中文预告片","url":"http://vf.test.com/Video/2017/02/16/mp4/170216103715670994.mp4","videoId":64619},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/01/19/225306.76666296.jpg","length":135,"title":"金刚狼3：殊死一战 中文预告片2","url":"http://vf.test.com/Video/2017/01/19/mp4/170119225326610502.mp4","videoId":64312},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2016/10/20/221537.16054341.jpg","length":106,"title":"金刚狼3：殊死一战 中文版预告片","url":"http://vf.test.com/Video/2016/10/20/mp4/161020221328633413.mp4","videoId":63012}]
         * wantedCount : 1577
         */

        private String actor1;
        private String actor2;
        private String director;
        private int id;
        private String image;
        private String locationName;
        private int rDay;
        private int rMonth;
        private int rYear;
        private String releaseDate;
        private String title;
        private String type;
//        private int videoCount;
//        private List<VideosBean> videos;

        public String getActor1() {
            return actor1;
        }

        public String getActor2() {
            return actor2;
        }

        public String getDirector() {
            return director;
        }

        public int getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getLocationName() {
            return locationName;
        }

        public int getRDay() {
            return rDay;
        }

        public int getRMonth() {
            return rMonth;
        }

        public int getRYear() {
            return rYear;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

    }

    public static class MoviecomingsBean {
        /**
         * actor1 : 布丽特·罗伯森
         * actor2 : 丹尼斯·奎德
         * director : 莱塞·霍尔斯道姆
         * id : 227258
         * image : http://img5.mtime.cn/mt/2017/02/03/091033.18171664_1280X720X2.jpg
         * isFilter : false
         * isTicket : true
         * isVideo : true
         * locationName : 美国
         * rDay : 3
         * rMonth : 3
         * rYear : 2017
         * releaseDate : 3月3日上映
         * title : 一条狗的使命
         * type : 冒险 / 喜剧 / 剧情
         * videoCount : 3
         * videos : [{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/02/02/220703.95592584.jpg","length":72,"title":"一条狗的使命 中国版预告片","url":"http://vf.test.com/Video/2017/02/02/mp4/170202220256361463.mp4","videoId":64418},{"hightUrl":"","image":"http://img31.mtime.cn/mg/2016/08/27/093929.42487788.jpg","length":148,"title":"一条狗的使命 预告片","url":"http://vf.test.com/Video/2016/08/27/mp4/160827093541357407.mp4","videoId":62265},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/01/03/094008.43060632.jpg","length":60,"title":"一条狗的使命 电视预告1","url":"http://vf.test.com/Video/2017/01/03/mp4/170103093954611596.mp4","videoId":64063}]
         * wantedCount : 2704
         */

        private String actor1;
        private String actor2;
        private String director;
        private int id;
        private String image;
        private String locationName;
        private int rDay;
        private int rMonth;
        private int rYear;
        private String releaseDate;
        private String title;
        private String type;
        private int videoCount;
//        private List<VideosBeanX> videos;
        private int wantedCount;

        public int getWantedCount() {
            return wantedCount;
        }

        public String getActor1() {
            return actor1;
        }


        public String getActor2() {
            return actor2;
        }


        public String getDirector() {
            return director;
        }

        public int getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getLocationName() {
            return locationName;
        }

        public int getRDay() {
            return rDay;
        }

        public int getRMonth() {
            return rMonth;
        }

        public int getRYear() {
            return rYear;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

    }
}
