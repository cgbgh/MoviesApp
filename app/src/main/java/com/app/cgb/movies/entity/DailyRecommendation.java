package com.app.cgb.moviepreview.entity;

public class DailyRecommendation {

    /**
     * news : {"newsID":1567455,"title":"\"怪兽之王\"金刚银幕变形记","type":0,"imageUrl":"http://img5.mtime.cn/mg/2017/03/22/092652.86172135.jpg"}
     * trailer : {"trailerID":64943,"title":"《寻梦环游记》中文预告","imageUrl":"http://img5.mtime.cn/mg/2017/03/16/165458.29417943.jpg","mp4Url":"https://vfx.mtime.cn/Video/2017/03/15/mp4/170315222409670447.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/03/15/mp4/170315222409670447.mp4","movieId":227434}
     * review : {"reviewID":7999646,"title":"由灰变成了暗淡无光的黑","posterUrl":"http://img5.mtime.cn/mt/2016/09/13/113719.63302576_1280X720X2.jpg","movieName":"五十度黑","imageUrl":"http://img5.mtime.cn/mg/2017/03/22/100711.76833039.jpg"}
     * viewingGuide : {"id":"10792","imageUrl":"http://img31.mtime.cn/mg/2015/03/31/100230.43767720.jpg"}
     * topList : {"id":1474,"title":"那些荣获奥斯卡的LGBT电影","imageUrl":"http://img5.mtime.cn/mg/2017/03/01/173620.86296561.jpg","type":0}
     */

    private NewsBean news;
    private TrailerBean trailer;
    private ReviewBean review;
    private ViewingGuideBean viewingGuide;
    private TopListBean topList;

    public NewsBean getNews() {
        return news;
    }

    public void setNews(NewsBean news) {
        this.news = news;
    }

    public TrailerBean getTrailer() {
        return trailer;
    }

    public void setTrailer(TrailerBean trailer) {
        this.trailer = trailer;
    }

    public ReviewBean getReview() {
        return review;
    }

    public void setReview(ReviewBean review) {
        this.review = review;
    }

    public ViewingGuideBean getViewingGuide() {
        return viewingGuide;
    }

    public void setViewingGuide(ViewingGuideBean viewingGuide) {
        this.viewingGuide = viewingGuide;
    }

    public TopListBean getTopList() {
        return topList;
    }

    public void setTopList(TopListBean topList) {
        this.topList = topList;
    }

    public static class NewsBean {
        /**
         * newsID : 1567455
         * title : "怪兽之王"金刚银幕变形记
         * type : 0
         * imageUrl : http://img5.mtime.cn/mg/2017/03/22/092652.86172135.jpg
         */

        private int newsID;
        private String title;
        private int type;
        private String imageUrl;

        public int getNewsID() {
            return newsID;
        }

        public void setNewsID(int newsID) {
            this.newsID = newsID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public static class TrailerBean {
        /**
         * trailerID : 64943
         * title : 《寻梦环游记》中文预告
         * imageUrl : http://img5.mtime.cn/mg/2017/03/16/165458.29417943.jpg
         * mp4Url : https://vfx.mtime.cn/Video/2017/03/15/mp4/170315222409670447.mp4
         * hightUrl : https://vfx.mtime.cn/Video/2017/03/15/mp4/170315222409670447.mp4
         * movieId : 227434
         */

        private int trailerID;
        private String title;
        private String imageUrl;
        private String mp4Url;
        private String hightUrl;
        private int movieId;

        public int getTrailerID() {
            return trailerID;
        }

        public void setTrailerID(int trailerID) {
            this.trailerID = trailerID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getMp4Url() {
            return mp4Url;
        }

        public void setMp4Url(String mp4Url) {
            this.mp4Url = mp4Url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }
    }

    public static class ReviewBean {
        /**
         * reviewID : 7999646
         * title : 由灰变成了暗淡无光的黑
         * posterUrl : http://img5.mtime.cn/mt/2016/09/13/113719.63302576_1280X720X2.jpg
         * movieName : 五十度黑
         * imageUrl : http://img5.mtime.cn/mg/2017/03/22/100711.76833039.jpg
         */

        private int reviewID;
        private String title;
        private String posterUrl;
        private String movieName;
        private String imageUrl;

        public int getReviewID() {
            return reviewID;
        }

        public void setReviewID(int reviewID) {
            this.reviewID = reviewID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPosterUrl() {
            return posterUrl;
        }

        public void setPosterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public static class ViewingGuideBean {
        /**
         * id : 10792
         * imageUrl : http://img31.mtime.cn/mg/2015/03/31/100230.43767720.jpg
         */

        private String id;
        private String imageUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public static class TopListBean {
        /**
         * id : 1474
         * title : 那些荣获奥斯卡的LGBT电影
         * imageUrl : http://img5.mtime.cn/mg/2017/03/01/173620.86296561.jpg
         * type : 0
         */

        private int id;
        private String title;
        private String imageUrl;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
