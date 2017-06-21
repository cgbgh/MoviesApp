package com.app.cgb.moviepreview.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VideoList implements Serializable{


    /**
     * totalPageCount : 1
     * totalCount : 20
     * videoList : [{"id":64312,"url":"https://vfx.mtime.cn/Video/2017/01/19/mp4/170119225326610502_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/01/19/mp4/170119225326610502.mp4","image":"http://img5.mtime.cn/mg/2017/01/19/225306.24950788_235X132X4.jpg","title":"金刚狼3：殊死一战 中文预告片2","type":0,"length":135},{"id":64619,"url":"https://vfx.mtime.cn/Video/2017/02/16/mp4/170216103715670994_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/02/16/mp4/170216103715670994.mp4","image":"http://img5.mtime.cn/mg/2017/02/16/104020.97236297_235X132X4.jpg","title":"金刚狼3：殊死一战 中文终极预告片","type":0,"length":60},{"id":63012,"url":"https://vfx.mtime.cn/Video/2016/10/20/mp4/161020221328633413_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2016/10/20/mp4/161020221328633413.mp4","image":"http://img5.mtime.cn/mg/2016/10/20/221537.92301782_235X132X4.jpg","title":"金刚狼3：殊死一战 中文版预告片","type":0,"length":106},{"id":64689,"url":"https://vfx.mtime.cn/Video/2017/02/22/mp4/170222172938995784_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/02/22/mp4/170222172938995784.mp4","image":"http://img5.mtime.cn/mg/2017/02/22/172933.61224891_235X132X4.jpg","title":"金刚狼3：殊死一战 中文版IMAX预告片","type":0,"length":114},{"id":64451,"url":"https://vfx.mtime.cn/Video/2017/02/06/mp4/170206083710369707_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/02/06/mp4/170206083710369707.mp4","image":"http://img5.mtime.cn/mg/2017/02/06/083851.93746923_235X132X4.jpg","title":"金刚狼3：殊死一战 超级碗预告片","type":0,"length":30},{"id":63010,"url":"https://vfx.mtime.cn/Video/2016/10/20/mp4/161020211150943806_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2016/10/20/mp4/161020211150943806.mp4","image":"http://img5.mtime.cn/mg/2016/10/20/211210.83796221_235X132X4.jpg","title":"金刚狼3：殊死一战 预告片","type":0,"length":107},{"id":64311,"url":"https://vfx.mtime.cn/Video/2017/01/19/mp4/170119222058094010_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/01/19/mp4/170119222058094010.mp4","image":"http://img5.mtime.cn/mg/2017/01/19/222012.41471542_235X132X4.jpg","title":"金刚狼3：殊死一战 预告片2","type":0,"length":142},{"id":64682,"url":"https://vfx.mtime.cn/Video/2017/02/21/mp4/170221205526124674_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/02/21/mp4/170221205526124674.mp4","image":"http://img5.mtime.cn/mg/2017/02/21/205717.30411408_235X132X4.jpg","title":"金刚狼3：殊死一战 中文片段","type":1,"length":59},{"id":64558,"url":"https://vfx.mtime.cn/Video/2017/02/10/mp4/170210084220174608_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/02/10/mp4/170210084220174608.mp4","image":"http://img5.mtime.cn/mg/2017/02/10/084238.66220726_235X132X4.jpg","title":"金刚狼3 片段之昏昏欲睡的专车司机","type":1,"length":69},{"id":64617,"url":"https://vfx.mtime.cn/Video/2017/02/16/mp4/170216084640009400_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/02/16/mp4/170216084640009400.mp4","image":"http://img5.mtime.cn/mg/2017/02/16/084625.69905023_235X132X4.jpg","title":"金刚狼3：殊死一战 病毒视频","type":2,"length":46},{"id":64729,"url":"https://vfx.mtime.cn/Video/2017/02/27/mp4/170227095659407509_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/02/27/mp4/170227095659407509.mp4","image":"http://img5.mtime.cn/mg/2017/02/27/095645.40353766_235X132X4.jpg","title":"金刚狼3 制作特辑之范冰冰助阵","type":2,"length":85},{"id":64674,"url":"https://vfx.mtime.cn/Video/2017/02/21/mp4/170221093817598872_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/02/21/mp4/170221093817598872.mp4","image":"http://img5.mtime.cn/mg/2017/02/21/093826.39174916_235X132X4.jpg","title":"金刚狼17年混剪","type":2,"length":163},{"id":64838,"url":"https://vfx.mtime.cn/Video/2017/03/08/mp4/170308084649431390_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/03/08/mp4/170308084649431390.mp4","image":"http://img5.mtime.cn/mg/2017/03/08/085244.67123609_235X132X4.jpg","title":"金刚狼3 狼叔杀青花絮","type":2,"length":143},{"id":64786,"url":"https://vfx.mtime.cn/Video/2017/03/02/mp4/170302203249353426_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/03/02/mp4/170302203249353426.mp4","image":"http://img5.mtime.cn/mg/2017/03/02/203102.95044448_235X132X4.jpg","title":"金刚狼3：殊死一战 独家专访影片主创","type":3,"length":808},{"id":64767,"url":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301130540068685_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301130540068685.mp4","image":"http://img5.mtime.cn/mg/2017/03/01/130607.45367799_235X132X4.jpg","title":"金刚狼3：殊死一战 中国首映礼直播台上专访","type":4,"length":858},{"id":64769,"url":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301134829683301_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301134829683301.mp4","image":"http://img5.mtime.cn/mg/2017/03/01/134858.95378419_235X132X4.jpg","title":"金刚狼3：殊死一战 中国首映礼直播红毯","type":4,"length":684},{"id":64770,"url":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301135346563186_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301135346563186.mp4","image":"http://img5.mtime.cn/mg/2017/03/01/135403.36699364_235X132X4.jpg","title":"金刚狼3：殊死一战 中国首映礼直播嘉宾林嘉佑访谈","type":4,"length":345},{"id":64771,"url":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301135659435678_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301135659435678.mp4","image":"http://img5.mtime.cn/mg/2017/03/01/135735.23233893_235X132X4.jpg","title":"金刚狼3：殊死一战 中国首映礼直播媒体提问","type":4,"length":559},{"id":64773,"url":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301192353439085_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/03/01/mp4/170301192353439085.mp4","image":"http://img5.mtime.cn/mg/2017/03/01/192353.29291418_235X132X4.jpg","title":"金刚狼3：殊死一战 电影首映直播精彩回顾","type":4,"length":119},{"id":64802,"url":"https://vfx.mtime.cn/Video/2017/03/04/mp4/170304190704732407_480.mp4","hightUrl":"https://vfx.mtime.cn/Video/2017/03/04/mp4/170304190704732407.mp4","image":"http://img5.mtime.cn/mg/2017/03/04/190659.72456638_235X132X4.jpg","title":"金刚狼3：殊死一战狼叔告别宴","type":4,"length":88}]
     */

    private int totalPageCount;
    private int totalCount;
    private List<VideoListBean> videoList;

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<VideoListBean> getVideoList() {
        return videoList;
    }

    public List<BaseVideo> getVideos(){
        List<BaseVideo> videos = new ArrayList<>();
        videos.addAll(videoList);
        return videos;
    }

    public void setVideoList(List<VideoListBean> videoList) {
        this.videoList = videoList;
    }

    public static class VideoListBean extends BaseVideo implements Serializable{
        /**
         * id : 64312
         * url : https://vfx.mtime.cn/Video/2017/01/19/mp4/170119225326610502_480.mp4
         * hightUrl : https://vfx.mtime.cn/Video/2017/01/19/mp4/170119225326610502.mp4
         * image : http://img5.mtime.cn/mg/2017/01/19/225306.24950788_235X132X4.jpg
         * title : 金刚狼3：殊死一战 中文预告片2
         * type : 0
         * length : 135
         */

        private int id;
        private String url;
        private String hightUrl;
        private String image;
        private String title;
        private int type;
        private int length;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public String getVideoUrl() {
            if (hightUrl!=null && !hightUrl.isEmpty()){
                return hightUrl;
            }
            return url;
        }

    }
}
