package com.app.cgb.moviepreview.entity;

import java.util.List;

public class MovieExtendDetail {

    /**
     * news : [{"newCount":100,"id":1566844,"type":2,"image":"http://img5.mtime.cn/mg/2017/03/03/083150.40599253.jpg","title":"专访《金刚狼3》休·杰克曼","title2":"\u201c我把每场戏都当成最后一场去演\u201d","publishTime":1488532266}]
     * events : {"eventCount":8,"list":["《金刚狼3：殊死一战》是休·杰克曼最后一次饰演金刚狼，这个角色自第一部《X战警》以来已过去17年。","英文片名Logan是金刚狼作为普通人的名字，第三部老年金刚狼的能力不断退化，会增加更多\u201c人类\u201d的特性，和女儿的亲情戏也是一大看点，片名暗示了电影的主题，也暗示了狼叔的最终归属。","影片借鉴了漫威漫画《暮狼寻乡》的一些剧情，不过休·杰克曼说他出演本片的灵感更多来自电影《不可饶恕》。"],"title":"该片你该了解的8件事"}
     * dataBankEntry : {"isClassicLines":true,"classicLinesCount":3,"isBehind":false,"isCompany":true,"mediaReviewCount":2,"isMediaReview":true,"companyCount":19,"soundCount":0,"isSound":false}
     * relelatedMovielist : [{"typeName":"前集","typeEn":"moviesFollows","movies":[{"movieID":10044,"title":"X战警","nameEn":"X-Men","rating":"7.6","releaseArea":"美国","img":"http://img31.mtime.cn/mt/2012/10/10/172824.19198575_1280X720X2.jpg","year":"2000","directorId1":901657,"director1":"布莱恩·辛格","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":907794,"actor2":"帕特里克·斯图尔特","releaseDate":"2000年7月14日"},{"movieID":10045,"title":"X战警2","nameEn":"X2","rating":"7.5","releaseArea":"英国","img":"http://img31.mtime.cn/mt/2014/02/22/223810.30159822_1280X720X2.jpg","year":"2003","directorId1":901657,"director1":"布莱恩·辛格","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":915083,"actor2":"哈莉·贝瑞","releaseDate":"2003年4月24日"},{"movieID":17100,"title":"X战警：背水一战","nameEn":"X-Men: The Last Stand","rating":"7.5","releaseArea":"美国","img":"http://img31.mtime.cn/mt/2014/10/20/100429.28064745_1280X720X2.jpg","year":"2006","directorId1":897914,"director1":"布莱特·拉特纳","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":907794,"actor2":"帕特里克·斯图尔特","releaseDate":"2006年5月26日"},{"movieID":51430,"title":"金刚狼","nameEn":"X-Men Origins: Wolverine","rating":"7.4","releaseArea":"中国","img":"http://img31.mtime.cn/mt/2014/02/23/035944.65256341_1280X720X2.jpg","year":"2009","directorId1":1184061,"director1":"加文·胡德","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":909026,"actor2":"列维·施瑞博尔","releaseDate":"2009年5月3日"},{"movieID":92772,"title":"X战警：第一战","nameEn":"X-Men: First Class","rating":"8.1","releaseArea":"美国","img":"http://img21.mtime.cn/mt/2011/05/28/104401.91468547_1280X720X2.jpg","year":"2011","directorId1":903330,"director1":"马修·沃恩","directorId2":0,"director2":"","actor1Id":959054,"actor1":"迈克尔·法斯宾德","actor2Id":915290,"actor2":"詹姆斯·麦卡沃伊","releaseDate":"2011年6月3日"},{"movieID":112225,"title":"金刚狼2","nameEn":"The Wolverine","rating":"7.0","releaseArea":"中国","img":"http://img31.mtime.cn/mt/2014/02/25/184512.53172468_1280X720X2.jpg","year":"2013","directorId1":898922,"director1":"詹姆斯·曼高德","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":1779721,"actor2":"冈本多绪","releaseDate":"2013年10月17日"},{"movieID":154557,"title":"X战警：逆转未来","nameEn":"X-Men: Days of Future Past","rating":"8.2","releaseArea":"中国","img":"http://img31.mtime.cn/mt/2015/05/18/155756.26390809_1280X720X2.jpg","year":"2014","directorId1":901657,"director1":"布莱恩·辛格","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":915290,"actor2":"詹姆斯·麦卡沃伊","releaseDate":"2014年5月23日"},{"movieID":208116,"title":"X战警：天启","nameEn":"X-Men: Apocalypse","rating":"7.6","releaseArea":"中国","img":"http://img31.mtime.cn/mt/2016/05/25/161352.96089978_1280X720X2.jpg","year":"2016","directorId1":901657,"director1":"布莱恩·辛格","directorId2":0,"director2":"","actor1Id":915290,"actor1":"詹姆斯·麦卡沃伊","actor2Id":959054,"actor2":"迈克尔·法斯宾德","releaseDate":"2016年6月3日"}]}]
     */

    private EventsBean events;
    private DataBankEntryBean dataBankEntry;
    private List<NewsBean> news;
    private List<RelelatedMovielistBean> relelatedMovielist;

    public EventsBean getEvents() {
        return events;
    }

    public void setEvents(EventsBean events) {
        this.events = events;
    }

    public DataBankEntryBean getDataBankEntry() {
        return dataBankEntry;
    }

    public void setDataBankEntry(DataBankEntryBean dataBankEntry) {
        this.dataBankEntry = dataBankEntry;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public List<RelelatedMovielistBean> getRelelatedMovielist() {
        return relelatedMovielist;
    }

    public void setRelelatedMovielist(List<RelelatedMovielistBean> relelatedMovielist) {
        this.relelatedMovielist = relelatedMovielist;
    }

    public static class EventsBean {
        /**
         * eventCount : 8
         * list : ["《金刚狼3：殊死一战》是休·杰克曼最后一次饰演金刚狼，这个角色自第一部《X战警》以来已过去17年。","英文片名Logan是金刚狼作为普通人的名字，第三部老年金刚狼的能力不断退化，会增加更多\u201c人类\u201d的特性，和女儿的亲情戏也是一大看点，片名暗示了电影的主题，也暗示了狼叔的最终归属。","影片借鉴了漫威漫画《暮狼寻乡》的一些剧情，不过休·杰克曼说他出演本片的灵感更多来自电影《不可饶恕》。"]
         * title : 该片你该了解的8件事
         */

        private int eventCount;
        private String title;
        private List<String> list;

        public int getEventCount() {
            return eventCount;
        }

        public void setEventCount(int eventCount) {
            this.eventCount = eventCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }

    public static class DataBankEntryBean {
        /**
         * isClassicLines : true
         * classicLinesCount : 3
         * isBehind : false
         * isCompany : true
         * mediaReviewCount : 2
         * isMediaReview : true
         * companyCount : 19
         * soundCount : 0
         * isSound : false
         */

        private boolean isClassicLines;
        private int classicLinesCount;
        private boolean isBehind;
        private boolean isCompany;
        private int mediaReviewCount;
        private boolean isMediaReview;
        private int companyCount;
        private int soundCount;
        private boolean isSound;

        public boolean isIsClassicLines() {
            return isClassicLines;
        }

        public void setIsClassicLines(boolean isClassicLines) {
            this.isClassicLines = isClassicLines;
        }

        public int getClassicLinesCount() {
            return classicLinesCount;
        }

        public void setClassicLinesCount(int classicLinesCount) {
            this.classicLinesCount = classicLinesCount;
        }

        public boolean isIsBehind() {
            return isBehind;
        }

        public void setIsBehind(boolean isBehind) {
            this.isBehind = isBehind;
        }

        public boolean isIsCompany() {
            return isCompany;
        }

        public void setIsCompany(boolean isCompany) {
            this.isCompany = isCompany;
        }

        public int getMediaReviewCount() {
            return mediaReviewCount;
        }

        public void setMediaReviewCount(int mediaReviewCount) {
            this.mediaReviewCount = mediaReviewCount;
        }

        public boolean isIsMediaReview() {
            return isMediaReview;
        }

        public void setIsMediaReview(boolean isMediaReview) {
            this.isMediaReview = isMediaReview;
        }

        public int getCompanyCount() {
            return companyCount;
        }

        public void setCompanyCount(int companyCount) {
            this.companyCount = companyCount;
        }

        public int getSoundCount() {
            return soundCount;
        }

        public void setSoundCount(int soundCount) {
            this.soundCount = soundCount;
        }

        public boolean isIsSound() {
            return isSound;
        }

        public void setIsSound(boolean isSound) {
            this.isSound = isSound;
        }
    }

    public static class NewsBean {
        /**
         * newCount : 100
         * id : 1566844
         * type : 2
         * image : http://img5.mtime.cn/mg/2017/03/03/083150.40599253.jpg
         * title : 专访《金刚狼3》休·杰克曼
         * title2 : “我把每场戏都当成最后一场去演”
         * publishTime : 1488532266
         */

        private int newCount;
        private int id;
        private int type;
        private String image;
        private String title;
        private String title2;
        private int publishTime;

        public int getNewCount() {
            return newCount;
        }

        public void setNewCount(int newCount) {
            this.newCount = newCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle2() {
            return title2;
        }

        public void setTitle2(String title2) {
            this.title2 = title2;
        }

        public int getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(int publishTime) {
            this.publishTime = publishTime;
        }
    }

    public static class RelelatedMovielistBean {
        /**
         * typeName : 前集
         * typeEn : moviesFollows
         * movies : [{"movieID":10044,"title":"X战警","nameEn":"X-Men","rating":"7.6","releaseArea":"美国","img":"http://img31.mtime.cn/mt/2012/10/10/172824.19198575_1280X720X2.jpg","year":"2000","directorId1":901657,"director1":"布莱恩·辛格","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":907794,"actor2":"帕特里克·斯图尔特","releaseDate":"2000年7月14日"},{"movieID":10045,"title":"X战警2","nameEn":"X2","rating":"7.5","releaseArea":"英国","img":"http://img31.mtime.cn/mt/2014/02/22/223810.30159822_1280X720X2.jpg","year":"2003","directorId1":901657,"director1":"布莱恩·辛格","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":915083,"actor2":"哈莉·贝瑞","releaseDate":"2003年4月24日"},{"movieID":17100,"title":"X战警：背水一战","nameEn":"X-Men: The Last Stand","rating":"7.5","releaseArea":"美国","img":"http://img31.mtime.cn/mt/2014/10/20/100429.28064745_1280X720X2.jpg","year":"2006","directorId1":897914,"director1":"布莱特·拉特纳","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":907794,"actor2":"帕特里克·斯图尔特","releaseDate":"2006年5月26日"},{"movieID":51430,"title":"金刚狼","nameEn":"X-Men Origins: Wolverine","rating":"7.4","releaseArea":"中国","img":"http://img31.mtime.cn/mt/2014/02/23/035944.65256341_1280X720X2.jpg","year":"2009","directorId1":1184061,"director1":"加文·胡德","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":909026,"actor2":"列维·施瑞博尔","releaseDate":"2009年5月3日"},{"movieID":92772,"title":"X战警：第一战","nameEn":"X-Men: First Class","rating":"8.1","releaseArea":"美国","img":"http://img21.mtime.cn/mt/2011/05/28/104401.91468547_1280X720X2.jpg","year":"2011","directorId1":903330,"director1":"马修·沃恩","directorId2":0,"director2":"","actor1Id":959054,"actor1":"迈克尔·法斯宾德","actor2Id":915290,"actor2":"詹姆斯·麦卡沃伊","releaseDate":"2011年6月3日"},{"movieID":112225,"title":"金刚狼2","nameEn":"The Wolverine","rating":"7.0","releaseArea":"中国","img":"http://img31.mtime.cn/mt/2014/02/25/184512.53172468_1280X720X2.jpg","year":"2013","directorId1":898922,"director1":"詹姆斯·曼高德","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":1779721,"actor2":"冈本多绪","releaseDate":"2013年10月17日"},{"movieID":154557,"title":"X战警：逆转未来","nameEn":"X-Men: Days of Future Past","rating":"8.2","releaseArea":"中国","img":"http://img31.mtime.cn/mt/2015/05/18/155756.26390809_1280X720X2.jpg","year":"2014","directorId1":901657,"director1":"布莱恩·辛格","directorId2":0,"director2":"","actor1Id":911757,"actor1":"休·杰克曼","actor2Id":915290,"actor2":"詹姆斯·麦卡沃伊","releaseDate":"2014年5月23日"},{"movieID":208116,"title":"X战警：天启","nameEn":"X-Men: Apocalypse","rating":"7.6","releaseArea":"中国","img":"http://img31.mtime.cn/mt/2016/05/25/161352.96089978_1280X720X2.jpg","year":"2016","directorId1":901657,"director1":"布莱恩·辛格","directorId2":0,"director2":"","actor1Id":915290,"actor1":"詹姆斯·麦卡沃伊","actor2Id":959054,"actor2":"迈克尔·法斯宾德","releaseDate":"2016年6月3日"}]
         */

        private String typeName;
        private String typeEn;
        private List<MoviesBean> movies;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeEn() {
            return typeEn;
        }

        public void setTypeEn(String typeEn) {
            this.typeEn = typeEn;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class MoviesBean {
            /**
             * movieID : 10044
             * title : X战警
             * nameEn : X-Men
             * rating : 7.6
             * releaseArea : 美国
             * img : http://img31.mtime.cn/mt/2012/10/10/172824.19198575_1280X720X2.jpg
             * year : 2000
             * directorId1 : 901657
             * director1 : 布莱恩·辛格
             * directorId2 : 0
             * director2 :
             * actor1Id : 911757
             * actor1 : 休·杰克曼
             * actor2Id : 907794
             * actor2 : 帕特里克·斯图尔特
             * releaseDate : 2000年7月14日
             */

            private int movieID;
            private String title;
            private String nameEn;
            private String rating;
            private String releaseArea;
            private String img;
            private String year;
            private int directorId1;
            private String director1;
            private int directorId2;
            private String director2;
            private int actor1Id;
            private String actor1;
            private int actor2Id;
            private String actor2;
            private String releaseDate;

            public int getMovieID() {
                return movieID;
            }

            public void setMovieID(int movieID) {
                this.movieID = movieID;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNameEn() {
                return nameEn;
            }

            public void setNameEn(String nameEn) {
                this.nameEn = nameEn;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getReleaseArea() {
                return releaseArea;
            }

            public void setReleaseArea(String releaseArea) {
                this.releaseArea = releaseArea;
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

            public int getDirectorId1() {
                return directorId1;
            }

            public void setDirectorId1(int directorId1) {
                this.directorId1 = directorId1;
            }

            public String getDirector1() {
                return director1;
            }

            public void setDirector1(String director1) {
                this.director1 = director1;
            }

            public int getDirectorId2() {
                return directorId2;
            }

            public void setDirectorId2(int directorId2) {
                this.directorId2 = directorId2;
            }

            public String getDirector2() {
                return director2;
            }

            public void setDirector2(String director2) {
                this.director2 = director2;
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
}
