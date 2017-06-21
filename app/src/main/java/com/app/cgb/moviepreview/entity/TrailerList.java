package com.app.cgb.moviepreview.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrailerList implements Serializable{


    private List<TrailersBean> trailers;

    public List<TrailersBean> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<TrailersBean> trailers) {
        this.trailers = trailers;
    }

    public List<BaseVideo> getVideos(){
        List<BaseVideo> videos = new ArrayList<>();
        videos.addAll(trailers);
        return videos;
    }

    public static class TrailersBean extends BaseVideo implements Serializable{
        /**
         * id : 64672
         * movieName : 《亚瑟王：剑的传奇》正式版预告片
         * coverImg : https://img5.mtime.cn/mg/2017/02/21/111556.45279918.jpg
         * movieId : 216639
         * url : https://vfx.mtime.cn/Video/2017/02/21/mp4/170221072058330503_480.mp4
         * hightUrl : https://vfx.mtime.cn/Video/2017/02/21/mp4/170221072058330503.mp4
         * videoTitle : 亚瑟王：剑的传奇 正式预告片
         * videoLength : 136
         * rating : -1
         * type : ["动作","冒险","剧情","奇幻"]
         * summary : 石中剑露出水面，裘德洛心神不宁
         */

        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private int videoLength;
        private String summary;
        private List<String> type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String getVideoUrl() {
            if (hightUrl!=null && !hightUrl.isEmpty()) {
                return hightUrl;
            }
                return url;
        }

        @Override
        public String getTitle() {
            return movieName;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public int getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
}
