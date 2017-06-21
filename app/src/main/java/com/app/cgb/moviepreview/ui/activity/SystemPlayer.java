package com.app.cgb.moviepreview.ui.activity;

import android.view.View;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.entity.BaseVideo;
import com.app.cgb.moviepreview.entity.TrailerList;
import com.app.cgb.moviepreview.entity.VideoList;

import java.util.List;


public class SystemPlayer extends VideoPlayBaseActivity<BaseVideo> implements View.OnClickListener {

    private String TAG = SystemPlayer.class.getSimpleName();

    @Override
    public List<BaseVideo> getVideoList() {
        List<BaseVideo> medias = null;
        TrailerList trailerList = (TrailerList) getIntent().getSerializableExtra(Constants.TRAILERLIST);
        VideoList videoList = (VideoList) getIntent().getSerializableExtra(Constants.VIDEOLIST);
        if (trailerList!=null){
            medias = trailerList.getVideos();
        }else if (videoList!=null){
            medias = videoList.getVideos();
        }
        return medias;
    }


    @Override
    protected String getVideoPath(BaseVideo mediaItem) {
        return mediaItem.getVideoUrl();
    }

    @Override
    protected String getVideoTitle(BaseVideo mediaItem) {
        return mediaItem.getTitle();
    }


}
