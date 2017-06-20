package com.app.cgb.moviepreview.entity;

import java.io.Serializable;

public abstract class BaseVideo implements Serializable{
    public abstract String getVideoUrl();
    public abstract String getTitle();
}
