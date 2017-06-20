package com.app.cgb.moviepreview.entity;

import java.util.List;

public class Images{

    private List<ImagesBean> images;
    private List<ImageTypesBean> imageTypes;

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public List<ImageTypesBean> getImageTypes() {
        return imageTypes;
    }

    public void setImageTypes(List<ImageTypesBean> imageTypes) {
        this.imageTypes = imageTypes;
    }

    public static class ImagesBean{
        /**
         * id : 6905270
         * image : http://img31.mtime.cn/pi/2014/07/23/201329.48191085_1000X1000.jpg
         * type : 1
         */

        private int id;
        private String image;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

    }

    public static class ImageTypesBean{
        /**
         * type : -1
         * typeName : 显示所有
         */

        private int type;
        private String typeName;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}
