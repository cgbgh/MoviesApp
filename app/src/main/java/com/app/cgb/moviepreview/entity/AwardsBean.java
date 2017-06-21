package com.app.cgb.moviepreview.entity;

import java.util.List;


public abstract class AwardsBean {

    public abstract List<? extends Awards> getAwards();

    public abstract static class Awards{
        public abstract int getFestivalId();
        public abstract int getNominateCount();
        public abstract int getWinCount();
        public abstract List<? extends BaseAwards> getNominateAwards();
        public abstract List<? extends BaseAwards> getWinAwards();
    }

    public abstract static class BaseAwards{

        public abstract String getAwardName();

        public abstract String getFestivalEventYear();

        public abstract int getSequenceNumber();

        public List<? extends BasePerson> getPersons(){
            return null;
        }

        public String getMovieTitle() {
            return "";
        }

        public String getMovieTitleEn() {
            return "";
        }

        public String getMovieYear() {
            return "";
        }

        public String getRoleName() {
            return "";
        }

        public int getMovieId() {
            return 0;
        }

        public String getImage() {
            return "";
        }

        public abstract void setIsTitle(boolean isTitle);

        public abstract boolean isTitle();
    }


    public abstract static class BasePerson {
        public abstract String getNameCn();

        public abstract String getNameEn();

        public abstract int getPersonId();
    }

    public abstract static class BaseFestival{

        public abstract int getFestivalId();

        public abstract String getImg();

        public abstract String getNameCn();

        public abstract String getNameEn();

        public abstract String getShortName();
    }

}
