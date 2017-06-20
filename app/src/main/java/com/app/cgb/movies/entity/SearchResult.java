package com.app.cgb.moviepreview.entity;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
    /**
     * fullMatchType : 2
     * movies : [{"id":236875,"name":"那年夏天你去了哪里","nameEn":"Cherry Returns","movieType":"悬疑 | 剧情","img":"http://img5.mtime.cn/mt/2016/12/13/185923.83618260_1280X720X2.jpg","titleOthersCn":["樱劫"],"realTime":"2016年12月30日","rYear":2016,"rMonth":12,"rDay":30,"year":"2016","rLocation":"中国","locationName":"中国","rating":"6","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["周隼"],"actors":["宋佳","林家栋"]},{"id":210558,"name":"琅琊榜","nameEn":"Nirvana in Fire","movieType":"剧情 | 古装","img":"http://img31.mtime.cn/mt/2015/09/21/093558.68883686_1280X720X2.jpg","titleOthersCn":[],"realTime":"2015年9月19日","rYear":2015,"rMonth":9,"rDay":19,"year":"2015","rLocation":"中国","locationName":"中国","rating":"8","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["李雪"],"actors":["胡歌","刘涛"]},{"id":227648,"name":"伪装者","nameEn":"The Disguiser","movieType":"悬疑","img":"http://img31.mtime.cn/mt/2016/02/04/150504.40122121_1280X720X2.jpg","titleOthersCn":["The Pretender","Disguiser"],"realTime":"2015年8月31日","rYear":2015,"rMonth":8,"rDay":31,"year":"2015","rLocation":"中国","locationName":"中国","rating":"7.7","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["李雪"],"actors":["胡歌","靳东"]},{"id":53702,"name":"少年杨家将","nameEn":"","movieType":"武侠 | 古装","img":"http://img31.mtime.cn/mt/2013/01/05/152752.58192743_1280X720X2.jpg","titleOthersCn":[],"realTime":"","rYear":0,"rMonth":0,"rDay":0,"year":"2005","rLocation":"中国","locationName":"中国","rating":"8.5","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":[""],"actors":["胡歌","何润东"]},{"id":48870,"name":"仙剑奇侠传","nameEn":"","movieType":"武侠 | 古装 | 爱情","img":"http://img31.mtime.cn/mt/2014/02/23/034815.49143921_1280X720X2.jpg","titleOthersCn":[],"realTime":"","rYear":0,"rMonth":0,"rDay":0,"year":"2005","rLocation":"中国","locationName":"中国","rating":"7.9","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["梁胜权"],"actors":["胡歌","刘亦菲"]},{"id":169639,"name":"麦兜当当伴我心","nameEn":"McDull·Pork of Music","movieType":"动画 | 喜剧","img":"http://img31.mtime.cn/mt/2012/07/06/183755.50573987_1280X720X2.jpg","titleOthersCn":["麥兜·噹噹伴我心","Mcdull","McDull·The Pork of Music"],"realTime":"2012年7月10日","rYear":2012,"rMonth":7,"rDay":10,"year":"2012","rLocation":"中国","locationName":"中国","rating":"7.7","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["谢立文"],"actors":["吴君如","黄秋生"]},{"id":206155,"name":"龙之谷：破晓奇兵","nameEn":"Dragon Nest · Warriors'dawn","movieType":"爱情 | 动画 | 奇幻","img":"http://img31.mtime.cn/mt/2014/07/23/103741.47019013_1280X720X2.jpg","titleOthersCn":["龙之谷前传","龙之谷大电影","龙之谷之黑龙崛起","Dragon Nest: Rise of the Black Dragon"],"realTime":"2014年7月31日","rYear":2014,"rMonth":7,"rDay":31,"year":"2014","rLocation":"中国","locationName":"中国","rating":"7.2","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["宋岳峰"],"actors":["胡歌","景甜"]},{"id":87842,"name":"仙剑奇侠传3","nameEn":"","movieType":"武侠 | 古装 | 奇幻","img":"http://img31.mtime.cn/mt/2014/02/23/063026.17027226_1280X720X2.jpg","titleOthersCn":["仙剑奇侠传三"],"realTime":"","rYear":0,"rMonth":0,"rDay":0,"year":"2009","rLocation":"中国","locationName":"中国","rating":"7.3","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":[""],"actors":["胡歌","杨幂"]},{"id":51615,"name":"射雕英雄传","nameEn":"The Eagle-shooting Heroes","movieType":"武侠 | 古装 | 爱情","img":"http://img31.mtime.cn/mt/2014/02/23/040107.84615826_1280X720X2.jpg","titleOthersCn":[],"realTime":"2008年","rYear":2008,"rMonth":0,"rDay":0,"year":"2008","rLocation":"中国","locationName":"中国","rating":"7","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["李国立"],"actors":["胡歌","林依晨"]},{"id":50013,"name":"聊斋之小倩","nameEn":"","movieType":"爱情 | 剧情 | 悬疑","img":"http://img31.mtime.cn/mt/2014/02/23/035241.39733678_1280X720X2.jpg","titleOthersCn":[],"realTime":"2005年","rYear":2005,"rMonth":0,"rDay":0,"year":"2005","rLocation":"中国","locationName":"中国","rating":"7.9","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["吴锦源"],"actors":["胡歌","杨幂"]},{"id":146707,"name":"华丽之后","nameEn":"Diva","movieType":"爱情 | 剧情","img":"http://img31.mtime.cn/mt/2016/09/06/165939.26751161_1280X720X2.jpg","titleOthersCn":["一天之后","DIVA华丽之后"],"realTime":"2012年8月16日","rYear":2012,"rMonth":8,"rDay":16,"year":"2012","rLocation":"中国香港","locationName":"中国香港","rating":"7.2","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["麦曦茵"],"actors":["容祖儿","胡歌"]},{"id":151893,"name":"无懈可击之高手如林","nameEn":"","movieType":"","img":"http://img21.mtime.cn/mt/2011/07/14/184409.60251165_1280X720X2.jpg","titleOthersCn":[],"realTime":"2011年8月6日","rYear":2011,"rMonth":8,"rDay":6,"year":"2011","rLocation":"中国","locationName":"中国","rating":"7.4","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["蒋家骏"],"actors":["胡歌","唐嫣"]},{"id":228931,"name":"大好时光","nameEn":"Good Time","movieType":"","img":"http://img31.mtime.cn/mt/2015/10/09/163307.77049105_1280X720X2.jpg","titleOthersCn":[],"realTime":"2015年10月16日","rYear":2015,"rMonth":10,"rDay":16,"year":"2015","rLocation":"中国","locationName":"中国","rating":"6.6","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["夏晓昀"],"actors":["胡歌","王晓晨"]},{"id":102031,"name":"神话","nameEn":"","movieType":"爱情 | 奇幻","img":"http://img5.mtime.cn/mt/2016/09/20/174253.68667162_1280X720X2.jpg","titleOthersCn":[],"realTime":"2010年1月4日","rYear":2010,"rMonth":1,"rDay":4,"year":"2010","rLocation":"中国","locationName":"中国","rating":"6.6","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":[""],"actors":["胡歌","白冰"]},{"id":214781,"name":"生活启示录","nameEn":"","movieType":"","img":"http://img31.mtime.cn/mt/2014/06/02/094823.49685032_1280X720X2.jpg","titleOthersCn":[],"realTime":"2014年5月22日","rYear":2014,"rMonth":5,"rDay":22,"year":"2014","rLocation":"中国","locationName":"中国","rating":"7.2","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":[""],"actors":["胡歌","闫妮"]},{"id":161683,"name":"风中奇缘","nameEn":"The Sound of Desert","movieType":"古装 | 爱情 | 历史","img":"http://img31.mtime.cn/mt/2014/09/10/154530.90084062_1280X720X2.jpg","titleOthersCn":["大漠谣"],"realTime":"2014年10月1日","rYear":2014,"rMonth":10,"rDay":1,"year":"2014","rLocation":"中国","locationName":"中国","rating":"6.8","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["李国立"],"actors":["胡歌","刘诗诗"]},{"id":150886,"name":"摩登新人类","nameEn":"","movieType":"","img":"http://img21.mtime.cn/mt/2011/07/06/190648.22657590_1280X720X2.jpg","titleOthersCn":[],"realTime":"2011年7月6日","rYear":2011,"rMonth":7,"rDay":6,"year":"2011","rLocation":"中国","locationName":"中国","rating":"7.8","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["刘国辉"],"actors":["胡歌","陈柏霖"]},{"id":51532,"name":"别爱我","nameEn":"","movieType":"爱情","img":"http://img31.mtime.cn/mt/2014/02/23/040042.20849558_1280X720X2.jpg","titleOthersCn":[],"realTime":"","rYear":0,"rMonth":0,"rDay":0,"year":"2006","rLocation":"中国","locationName":"中国","rating":"8.1","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["李国立"],"actors":["徐若瑄","胡歌"]},{"id":52763,"name":"天外飞仙","nameEn":"Fairy From The Wonderland","movieType":"古装 | 爱情 | 奇幻","img":"http://img31.mtime.cn/mt/2014/02/23/040605.60532170_1280X720X2.jpg","titleOthersCn":[],"realTime":"","rYear":0,"rMonth":0,"rDay":0,"year":"2005","rLocation":"中国","locationName":"中国","rating":"7.1","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":[""],"actors":["胡歌","林依晨"]},{"id":156073,"name":"轩辕剑之天之痕","nameEn":"","movieType":"古装 | 奇幻","img":"http://img21.mtime.cn/mt/2012/05/03/183304.36913928_1280X720X2.jpg","titleOthersCn":[],"realTime":"","rYear":0,"rMonth":0,"rDay":0,"year":"2012","rLocation":"中国","locationName":"中国","rating":"5.8","releaseStatus":0,"isTicket":false,"isFilter":false,"directors":["李国立"],"actors":["胡歌","刘诗诗"]}]
     * moviesCount : 31
     * cinemas : []
     * cinemasCount : 0
     * locationCinemas : []
     * locationCinemasCount : 0
     * persons : [{"id":1249821,"name":"胡戈","img":"http://img31.mtime.cn/ph/2014/02/22/212616.38370511_1280X720X2.jpg","nameEn":"Ge Hu","sex":"男","birthday":"","profession":"导演 | 演员","constellation":"","birthLocation":"","rating":9.3,"loveDeep":93,"personMovies":[{"title":"鸟笼山剿匪记","year":"2006","url":"http://movie.mtime.com/45876/","movieId":45876}]},{"id":1248697,"name":"胡歌","img":"http://img31.mtime.cn/ph/2016/05/16/212018.34707986_1280X720X2.jpg","nameEn":"Hugh","sex":"男","birthday":"1982年9月20日","profession":"演员","constellation":"处女座","birthLocation":"中国 上海","rating":8.2,"loveDeep":82,"personMovies":[{"title":"琅琊榜","year":"2015","url":"http://movie.mtime.com/210558/","movieId":210558},{"title":"仙剑奇侠传","year":"2005","url":"http://movie.mtime.com/48870/","movieId":48870},{"title":"伪装者","year":"2015","url":"http://movie.mtime.com/227648/","movieId":227648}]}]
     * personsCount : 2
     * goods : {"keywordUrl":"https://mall-wv.mtime.cn/?utm_source=app_search_btn_more#!/commerce/list/keyword/胡歌/","goodsList":[{"goodsId":104369,"name":"GSC 粘土人 高海千歌","imageSrc":"https://img5.mtime.cn/mg/2016/10/12/165920.96182234_294X294X4.jpg","marketPrice":21900,"minSalePrice":21900,"goodsUrl":"https://mall-wv.mtime.cn/?utm_source=app_search_result#!/commerce/item/104369/","iconText":"超前预售","background":"#4EC178"},{"goodsId":103413,"name":"HT自杀小队1:6小丑女人偶","imageSrc":"https://img31.mtime.cn/mg/2016/08/03/184112.43896295_294X294X4.jpg","marketPrice":149000,"minSalePrice":149000,"goodsUrl":"https://mall-wv.mtime.cn/?utm_source=app_search_result#!/commerce/item/103413/","iconText":"超前预售","background":"#4EC178"}]}
     * goodsCount : 2
     */

    private int fullMatchType;
    private int moviesCount;
    private int personsCount;
    private List<MoviesBean> movies;
    private List<PersonsBean> persons;

    public int getFullMatchType() {
        return fullMatchType;
    }

    public List<MoviesBean> getMovies() {
        return movies;
    }

    public int getMoviesCount() {
        return moviesCount;
    }

    public List<PersonsBean> getPersons() {
        return persons;
    }

    public int getPersonsCount() {
        return personsCount;
    }

    public static class MoviesBean implements Serializable {


        /**
         * id : 236875
         * name : 那年夏天你去了哪里
         * nameEn : Cherry Returns
         * movieType : 悬疑 | 剧情
         * img : http://img5.mtime.cn/mt/2016/12/13/185923.83618260_1280X720X2.jpg
         * titleOthersCn : ["樱劫"]
         * realTime : 2016年12月30日
         * rYear : 2016
         * rMonth : 12
         * rDay : 30
         * year : 2016
         * rLocation : 中国
         * locationName : 中国
         * rating : 6
         * releaseStatus : 0
         * isTicket : false
         * isFilter : false
         * directors : ["周隼"]
         * actors : ["宋佳","林家栋"]
         */

        private int id;
        private String name;
        private String nameEn;
        private String movieType;
        private String img;
        private String realTime;
        private int rYear;
        private int rMonth;
        private int rDay;
        private String year;
        private String rLocation;
        private String locationName;
        private String rating;
        private int releaseStatus;
        private List<String> directors;
        private List<String> actors;

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

        public String getMovieType() {
            return movieType;
        }

        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getRealTime() {
            return realTime;
        }

        public void setRealTime(String realTime) {
            this.realTime = realTime;
        }

        public int getRYear() {
            return rYear;
        }

        public void setRYear(int rYear) {
            this.rYear = rYear;
        }

        public int getRMonth() {
            return rMonth;
        }

        public void setRMonth(int rMonth) {
            this.rMonth = rMonth;
        }

        public int getRDay() {
            return rDay;
        }

        public void setRDay(int rDay) {
            this.rDay = rDay;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getRLocation() {
            return rLocation;
        }

        public void setRLocation(String rLocation) {
            this.rLocation = rLocation;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public int getReleaseStatus() {
            return releaseStatus;
        }

        public void setReleaseStatus(int releaseStatus) {
            this.releaseStatus = releaseStatus;
        }

        public List<String> getDirectors() {
            return directors;
        }

        public void setDirectors(List<String> directors) {
            this.directors = directors;
        }

        public List<String> getActors() {
            return actors;
        }

        public void setActors(List<String> actors) {
            this.actors = actors;
        }
    }

    public static class PersonsBean implements Serializable {


        /**
         * id : 1249821
         * name : 胡戈
         * img : http://img31.mtime.cn/ph/2014/02/22/212616.38370511_1280X720X2.jpg
         * nameEn : Ge Hu
         * sex : 男
         * birthday :
         * profession : 导演 | 演员
         * constellation :
         * birthLocation :
         * rating : 9.3
         * loveDeep : 93
         * personMovies : [{"title":"鸟笼山剿匪记","year":"2006","url":"http://movie.mtime.com/45876/","movieId":45876}]
         */

        private int id;
        private String name;
        private String img;
        private String nameEn;
        private String sex;
        private String birthday;
        private String profession;
        private String constellation;
        private String birthLocation;
        private List<PersonMoviesBean> personMovies;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getBirthLocation() {
            return birthLocation;
        }

        public void setBirthLocation(String birthLocation) {
            this.birthLocation = birthLocation;
        }

        public List<PersonMoviesBean> getPersonMovies() {
            return personMovies;
        }

        public void setPersonMovies(List<PersonMoviesBean> personMovies) {
            this.personMovies = personMovies;
        }

        public static class PersonMoviesBean implements Serializable{
            /**
             * title : 鸟笼山剿匪记
             * year : 2006
             * url : http://movie.mtime.com/45876/
             * movieId : 45876
             */

            private String title;
            private String year;
            private String url;
            private int movieId;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getMovieId() {
                return movieId;
            }

            public void setMovieId(int movieId) {
                this.movieId = movieId;
            }
        }
    }


//    /**
//     * suggestions : [{"id":51430,"type":1,"contentType":"电影","movieType":"动作 | 冒险 | 科幻","isFilter":false,"titlecn":"金刚狼","titleen":"X-Men Origins: Wolverine","rLocation":"美国","locationName":"美国","year":"2009","director":"加文·胡德","cover":"http://img31.mtime.cn/mt/2014/02/23/035944.65256341_1280X720X2.jpg"},{"id":145980,"type":1,"contentType":"电视剧","movieType":"动画 | 科幻 | 动作","isFilter":false,"titlecn":"金刚狼","titleen":"Wolverine","rLocation":"美国","locationName":"美国","year":"2011","director":"Masashi Abe","cover":"http://img21.mtime.cn/mt/2011/03/09/190830.83971975_1280X720X2.jpg"},{"id":209688,"type":1,"contentType":"电影","movieType":"动作 | 剧情 | 科幻","isFilter":false,"titlecn":"金刚狼3：殊死一战","titleen":"Logan","rLocation":"美国","locationName":"美国","year":"2017","director":"詹姆斯·曼高德","cover":"http://img5.mtime.cn/mt/2017/02/28/112612.39246709_1280X720X2.jpg"},{"id":112225,"type":1,"contentType":"电影","movieType":"动作 | 冒险 | 科幻","isFilter":false,"titlecn":"金刚狼2","titleen":"The Wolverine","rLocation":"美国","locationName":"美国","year":"2013","director":"詹姆斯·曼高德","cover":"http://img31.mtime.cn/mt/2014/02/25/184512.53172468_1280X720X2.jpg"},{"id":138306,"type":1,"contentType":"电视剧","movieType":"动画","isFilter":false,"titlecn":"金刚狼与X战警","titleen":"Wolverine and the X-Men","rLocation":"美国","locationName":"美国","year":"2008","director":"Nicholas Filippi","cover":"http://img21.mtime.cn/mt/2010/10/09/160429.69546087_1280X720X2.jpg"}]
//     * goodsCount : 5
//     */
//
//    private List<SuggestionsBean> suggestions;
//
//    public List<SuggestionsBean> getSuggestions() {
//        return suggestions;
//    }
//
//    public void setSuggestions(List<SuggestionsBean> suggestions) {
//        this.suggestions = suggestions;
//    }
//
//    public static class SuggestionsBean {
//        /**
//         * id : 51430
//         * type : 1
//         * contentType : 电影
//         * movieType : 动作 | 冒险 | 科幻
//         * isFilter : false
//         * titlecn : 金刚狼
//         * titleen : X-Men Origins: Wolverine
//         * rLocation : 美国
//         * locationName : 美国
//         * year : 2009
//         * director : 加文·胡德
//         * cover : http://img31.mtime.cn/mt/2014/02/23/035944.65256341_1280X720X2.jpg
//         *
//         *
//         *id: 1248697,
//         *type: 3,
//         *titlecn: "胡歌",
//         *titleen: "Hugh",
//         *sex: "男",
//         *birthLocation: "中国 上海",
//         *profession: "演员",
//         *birth: "1982-9-20",
//         *cover: "http://img31.mtime.cn/ph/2016/05/16/212018.34707986_1280X720X2.jpg"
//         */
//
//        private int id;
//        private int type;
//        private String contentType;
//        private String movieType;
//        private String titlecn;
//        private String titleen;
//        private String rLocation;
//        private String locationName;
//        private String year;
//        private String director;
//        private String cover;
//        private String sex;
//        private String birthLocation;
//        private String profession;
//        private String birth;
//
//        public String getBirth() {
//            return birth;
//        }
//
//        public String getBirthLocation() {
//            return birthLocation;
//        }
//
//        public String getProfession() {
//            return profession;
//        }
//
//        public String getrLocation() {
//            return rLocation;
//        }
//
//        public String getSex() {
//            return sex;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getType() {
//            return type;
//        }
//
//        public void setType(int type) {
//            this.type = type;
//        }
//
//        public String getContentType() {
//            return contentType;
//        }
//
//        public void setContentType(String contentType) {
//            this.contentType = contentType;
//        }
//
//        public String getMovieType() {
//            return movieType;
//        }
//
//        public void setMovieType(String movieType) {
//            this.movieType = movieType;
//        }
//
//        public String getTitlecn() {
//            return titlecn;
//        }
//
//        public void setTitlecn(String titlecn) {
//            this.titlecn = titlecn;
//        }
//
//        public String getTitleen() {
//            return titleen;
//        }
//
//        public void setTitleen(String titleen) {
//            this.titleen = titleen;
//        }
//
//        public String getRLocation() {
//            return rLocation;
//        }
//
//        public void setRLocation(String rLocation) {
//            this.rLocation = rLocation;
//        }
//
//        public String getLocationName() {
//            return locationName;
//        }
//
//        public void setLocationName(String locationName) {
//            this.locationName = locationName;
//        }
//
//        public String getYear() {
//            return year;
//        }
//
//        public void setYear(String year) {
//            this.year = year;
//        }
//
//        public String getDirector() {
//            return director;
//        }
//
//        public void setDirector(String director) {
//            this.director = director;
//        }
//
//        public String getCover() {
//            return cover;
//        }
//
//        public void setCover(String cover) {
//            this.cover = cover;
//        }
//    }
}
