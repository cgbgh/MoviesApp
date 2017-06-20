package com.app.cgb.moviepreview.entity;

import java.util.List;


public class MediaReview {
    private List<MediasBean> medias;

    public List<MediasBean> getMedias() {
        return medias;
    }

    public static class MediasBean {
        /**
         * name : 综艺
         * icon : http://img31.mtime.cn/mg/2014/05/06/101330.54577444_0X70X3.jpg
         * comments : [{"id":1709,"publishTime":1488271054,"title":"","summary":"\n《金刚狼3》给了这个系列一个令人满意的结局。"}]
         */

        private String name;
        private String icon;
        private List<CommentsBean> comments;

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public static class CommentsBean {
            /**
             * id : 1709
             * publishTime : 1488271054
             * title :
             * summary :
             《金刚狼3》给了这个系列一个令人满意的结局。
             */

            private int id;
            private int publishTime;
            private String title;
            private String summary;

            public int getId() {
                return id;
            }

            public int getPublishTime() {
                return publishTime;
            }

            public String getTitle() {
                return title;
            }

            public String getSummary() {
                return summary;
            }
        }
    }

}
