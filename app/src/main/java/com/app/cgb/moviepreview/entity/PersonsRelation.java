package com.app.cgb.moviepreview.entity;


import java.util.List;

public class PersonsRelation {


    /**
     * id : 10650
     * image : http://img31.mtime.cn/mt/2014/02/22/224532.65745754_1280X720X2.jpg
     * name : 隔世情缘
     * year : 2001
     * rating : 7.6
     * offices : [{"name":"演员"}]
     * personates : [{"name":"Leopold"}]
     * awards : [{"eventName":"第59届金球奖","year":"2002","awardName":"最佳原创歌曲"}]
     * releaseDate : 2003-1-1
     * releaseCountry : 中国
     * totalCount : 86
     */

    private int id;
    private String image;
    private String name;
    private String year;
    private String rating;
    private String releaseDate;
    private String releaseCountry;
    private int totalCount;
    private List<OfficesBean> offices;
    private List<PersonatesBean> personates;
    private List<AwardsBean> awards;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseCountry() {
        return releaseCountry;
    }

    public void setReleaseCountry(String releaseCountry) {
        this.releaseCountry = releaseCountry;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<OfficesBean> getOffices() {
        return offices;
    }

    public void setOffices(List<OfficesBean> offices) {
        this.offices = offices;
    }

    public List<PersonatesBean> getPersonates() {
        return personates;
    }

    public void setPersonates(List<PersonatesBean> personates) {
        this.personates = personates;
    }

    public List<AwardsBean> getAwards() {
        return awards;
    }

    public void setAwards(List<AwardsBean> awards) {
        this.awards = awards;
    }

    public static class OfficesBean {
        /**
         * name : 演员
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class PersonatesBean {
        /**
         * name : Leopold
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class AwardsBean {
        /**
         * eventName : 第59届金球奖
         * year : 2002
         * awardName : 最佳原创歌曲
         */

        private String eventName;
        private String year;
        private String awardName;

        public String getEventName() {
            return eventName;

        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getAwardName() {
            return awardName;
        }

        public void setAwardName(String awardName) {
            this.awardName = awardName;
        }
    }
}
