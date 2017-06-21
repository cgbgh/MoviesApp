package com.app.cgb.moviepreview.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.basic.BaseActivity;
import com.app.cgb.moviepreview.ui.view.SizableVideo;
import com.app.cgb.moviepreview.ui.view.VerticalProgressBar;
import com.app.cgb.moviepreview.utils.NetWorkUtil;
import com.app.cgb.moviepreview.utils.TimeUtils;
import com.app.cgb.moviepreview.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public abstract class VideoPlayBaseActivity<T> extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.svv_player)
    SizableVideo player;
    @BindView(R.id.tv_loading)
    TextView tvLoading;
    @BindView(R.id.tv_video_name)
    TextView tvVideoName;
    @BindView(R.id.iv_battery)
    ImageView ivBattery;
    @BindView(R.id.tv_time_clock)
    TextView tvTimeClock;
    @BindView(R.id.ibtn_volume)
    ImageButton ibtnVolume;
    @BindView(R.id.sb_volume)
    SeekBar sbVolume;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_current_time)
    TextView tvCurrentTime;
    @BindView(R.id.sb_progress)
    SeekBar sbProgress;
    @BindView(R.id.tv_total_time)
    TextView tvTotalTime;
    @BindView(R.id.iv_pre)
    ImageView ivPre;
    @BindView(R.id.iv_play_pause)
    ImageView ivPlayPause;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.iv_screen)
    ImageView ivScreen;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.ibtn_play_on_video)
    ImageButton ibtnPlay;
    @BindView(R.id.vpb_volume)
    VerticalProgressBar vpbVolume;
    @BindView(R.id.vpb_brightness)
    VerticalProgressBar vpbBrightness;
    @BindView(R.id.tv_onTouch_show)
    TextView tvOnTouch;
    @BindView(R.id.rl_volume)
    RelativeLayout rlVolume;
    @BindView(R.id.rl_brightness)
    RelativeLayout rlBrightness;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;
    private String TAG = VideoPlayBaseActivity.class.getSimpleName();
    //    更新进度的消息码
    private static final int PROGRESS = 0;
    //    更新时间的消息码
    private static final int TIME = 1;
    //    隐藏控制面板的消息码
    private static final int HIDE_CONTROLER = 2;
    //    显示网速
    private static final int NET_SPEED = 3;

    //    是否静音
    private boolean isMute = false;
    //    是否全屏，默认为true,但初始化时是默认大小
// 因为初始化时执行一次setScreenType，使屏幕变为默认大小，isFullSreen变为false
    private boolean isFullScreen = true;
    //    控制面板是否显示
    private boolean controllerShowed = true;

    private boolean isNetVideo = false;

    private int position;
    private List<T> medias;
    private AudioManager am;
    private int duration;
    private int maxVolume;
    private int currentVolume;
    private int screenWidth;
    private int screenHeight;
    private int videoWidth;
    private int videoHeight;
    private GestureDetector gestureDetector;
    private float startX;
    private float startY;
    private int mVol;
    private int mPro;
    private int currentBright;
    private int mBrightMode;
    private MyBroacastReceiver receiver;
    private SimpleDateFormat timeFormat;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIME:
                    updateTime();
                case PROGRESS:
                    updateVideoProgress();
                    removeAndSendMessagesDelayed(PROGRESS, 1000);
                    break;
                case HIDE_CONTROLER:
                    hideController();
                    break;

                case NET_SPEED:
                    long netSpeed = NetWorkUtil.getNetSpeed(VideoPlayBaseActivity.this);
                    tvLoading.setText(netSpeed + "kb/s");
                    mHandler.removeMessages(NET_SPEED);
                    mHandler.sendEmptyMessageDelayed(NET_SPEED, 2000);

                    break;
            }
        }
    };

    private void removeAndSendMessagesDelayed(int what, int mils) {
        mHandler.removeMessages(what);
        mHandler.sendEmptyMessageDelayed(what, mils);
    }

    private void updateVideoProgress() {
        //                    设置当前进度
        int currentPosition = player.getCurrentPosition();
        sbProgress.setProgress(currentPosition);

//                    设置当前时间
        String currentTime = TimeUtils.mills2String(currentPosition);
        tvCurrentTime.setText(currentTime);

        if (isNetVideo) {
            int bufferPercentage = player.getBufferPercentage();
            int max = sbProgress.getMax();
            int totalBuffer = bufferPercentage * max / 100;
            sbProgress.setSecondaryProgress(totalBuffer);
        } else {
            sbProgress.setSecondaryProgress(0);
        }
    }


    @Override
    public int setLayoutResId() {
        return R.layout.activity_system_player;
    }

    /**
     * 初始化视图
     */
    public void initView() {
        getScreenSize();
        setupVolume();
        setupBrightness();
        updateTime();
        registerBatteryLevelReciver();
        setGestureDetector();
        setupListener();
        hideController();
    }

    /**
     * 获取屏幕尺寸
     */
    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }

    private void setupVolume() {
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        sbVolume.setMax(maxVolume);
        vpbVolume.setMax(maxVolume);
        currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

        setVolume(currentVolume, isMute);
    }

    private void setupBrightness() {
        vpbBrightness.setMax(255);
        currentBright = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 255);
        vpbBrightness.setProgress(currentBright);
    }

    private void updateTime() {
        if (timeFormat == null) {
            timeFormat = new SimpleDateFormat("HH:mm:ss");
        }
        String time = timeFormat.format(new Date());
        tvTimeClock.setText(time);
        //        发送消息，更新时间
        removeAndSendMessagesDelayed(TIME, 1000);
    }

    private void registerBatteryLevelReciver() {
        receiver = new MyBroacastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);
    }

    private void setGestureDetector() {
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                playOrPause();
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (controllerShowed) {
                    hideController();
                } else {
                    showController();
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                setScreenType();
            }
        });
    }

    private void setupListener() {
        player.setOnPreparedListener(new MyOnPreparedListener());
        player.setOnCompletionListener(new MyOnCompletionListener());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            player.setOnInfoListener(new MyOnInfoListener());
        }

        sbProgress.setOnSeekBarChangeListener(new VideoOnSeekBarChangeListener());
        sbVolume.setOnSeekBarChangeListener(new VolumeOnSeekBarChangeListener());
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        mBrightMode = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        position = getIntent().getIntExtra("position", 0);
//        设置视频路径
        setVideoResource();
    }


    /**
     * 隐藏控制面板
     */
    private void hideController() {
//        TransitionManager.beginDelayedTransition(rlRoot);
//        RelativeLayout.LayoutParams topParams = (RelativeLayout.LayoutParams) rlTop.getLayoutParams();
//        RelativeLayout.LayoutParams bottomParams = (RelativeLayout.LayoutParams) llBottom.getLayoutParams();
//        topParams.addRule(RelativeLayout.ABOVE, R.id.svv_player);
//        bottomParams.addRule(RelativeLayout.BELOW, R.id.svv_player);
        rlTop.setVisibility(View.GONE);
        llBottom.setVisibility(View.GONE);
        controllerShowed = false;
        mHandler.removeMessages(HIDE_CONTROLER);

    }

    /**
     * 显示控制面板
     */
    private void showController() {
//        TransitionManager.beginDelayedTransition(rlRoot);
//        RelativeLayout.LayoutParams topParams = (RelativeLayout.LayoutParams) rlTop.getLayoutParams();
//        RelativeLayout.LayoutParams bottomParams = (RelativeLayout.LayoutParams) llBottom.getLayoutParams();
//        topParams.addRule(RelativeLayout.ABOVE, 0);
//        bottomParams.addRule(RelativeLayout.BELOW, 0);
        rlTop.setVisibility(View.VISIBLE);
        llBottom.setVisibility(View.VISIBLE);
        removeAndSendMessagesDelayed(HIDE_CONTROLER, 4000);
//        mHandler.sendEmptyMessageDelayed(HIDE_CONTROLER, 4000);
        controllerShowed = true;

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                mVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                mPro = player.getCurrentPosition();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                float disY = startY - moveY;
                float moveX = event.getX();
                float disX = moveX - startX;

                if (Math.abs(disY) > 5 || Math.abs(disX) > 5) {
                    if (Math.abs(disY) > Math.abs(disX)) {
                        if (startX > screenWidth / 2) {
                            changeVolWhileTouch(disY);
                        } else {
                            changeBrightWhileTouch(disY);
                        }
                    } else {
                        changeProgressWhileTouch(disX);
                    }
                    tvOnTouch.setVisibility(View.VISIBLE);
                }

                break;
            case MotionEvent.ACTION_UP:
                rlVolume.setVisibility(View.GONE);
                rlBrightness.setVisibility(View.GONE);
                tvOnTouch.setVisibility(View.GONE);
                mHandler.sendEmptyMessageDelayed(PROGRESS, 1000);
                break;
        }

        return super.onTouchEvent(event);
    }

    private void changeProgressWhileTouch(float disX) {
        if (disX != 0) {
            int changePro = (int) (disX * duration / screenWidth);
            if (changePro != 0) {
                int progress = Math.min(Math.max(0, mPro + changePro), duration);
                sbProgress.setProgress(progress);
                player.seekTo(progress);
                tvOnTouch.setText(TimeUtils.mills2String(progress) + "/" + TimeUtils.mills2String(duration));
            }

        }
    }

    private void changeBrightWhileTouch(float disY) {
        rlBrightness.setVisibility(View.VISIBLE);
        int brightChange = (int) (disY * 255 / (5 * screenHeight));
        if (brightChange != 0) {
            int brightness = Math.min(Math.max(0, currentBright + brightChange), 255);
            setBrightness(brightness);
            String brightPercent = (brightness * 100 / 255) + "%";
            tvOnTouch.setText("亮度:" + brightPercent);
        }
    }

    private void changeVolWhileTouch(float disY) {
        rlVolume.setVisibility(View.VISIBLE);
        int changeLevel = (int) (disY * maxVolume / screenHeight);
        if (changeLevel != 0) {
            int volume = Math.min(Math.max(0, mVol + changeLevel), maxVolume);
            String volPercent = (volume * 100 / maxVolume) + "%";
            tvOnTouch.setText("音量:" + volPercent);
            setVolume(volume, isVolEqulsZero(volume));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            int vol = currentVolume + 1;
            currentVolume = Math.min(vol, maxVolume);
            setVolume(currentVolume, isVolEqulsZero(currentVolume));
            showController();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            int vol = currentVolume - 1;
            currentVolume = Math.max(vol, 0);

            setVolume(currentVolume, isVolEqulsZero(currentVolume));
            showController();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, mBrightMode);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.stopPlayback();
        }
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    /**
     * 是否显示视频初始化加载界面
     *
     * @param showed
     */
    private void setLoadingShowed(boolean showed) {
        if (showed && isNetVideo) {
            llLoading.setVisibility(View.VISIBLE);
            mHandler.sendEmptyMessage(NET_SPEED);

        } else {
            llLoading.setVisibility(View.GONE);
            mHandler.removeMessages(NET_SPEED);
        }
    }


    /**
     * 设置音量
     *
     * @param volume 音量
     * @param isMute 是否静音
     */
    private void setVolume(int volume, boolean isMute) {
        if (!isMute) {
            am.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
            sbVolume.setProgress(volume);
            vpbVolume.setProgress(volume);
            ibtnVolume.setBackgroundResource(R.drawable.selector_volume_on);

        } else {
            am.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            sbVolume.setProgress(0);
            vpbVolume.setProgress(0);
            ibtnVolume.setBackgroundResource(R.drawable.selector_volume_off);
        }

    }

    /**
     * 获取视频信息，包括路径，被点击条目
     */
    public abstract List<T> getVideoList();

    /**
     * 设置视频播放路径
     */
    private void setVideoResource() {
        medias = getVideoList();
        Uri uri = getIntent().getData();
        if (medias != null && medias.size() > 0) {
            T mediaItem = medias.get(position);
            player.setVideoPath(getVideoPath(mediaItem));
            isNetVideo = NetWorkUtil.isNetVideo(getVideoPath(mediaItem));

            tvVideoName.setText(getVideoTitle(mediaItem));
            setPreNextEnable();
        } else if (uri != null) {
            player.setVideoURI(uri);
            isNetVideo = NetWorkUtil.isNetVideo(uri.toString());

            String[] paths = Uri.decode(uri.toString()).split("/");
            String path = paths[paths.length - 1];
            tvVideoName.setText(path);
            setPreEnable(false);
            setNextEnable(false);
        }
        setLoadingShowed(true);

    }


    protected abstract String getVideoPath(T mediaItem);

    protected abstract String getVideoTitle(T mediaItem);

    /**
     * 判断并设置上一个和下一个按钮是否可用
     */
    private void setPreNextEnable() {
        if (position == 0) {
            setPreEnable(false);
        } else {
            setPreEnable(true);
        }
        if (position == medias.size() - 1) {
            setNextEnable(false);
        } else {
            setNextEnable(true);
        }
    }

    private void setNextEnable(boolean enable) {
        if (enable) {
            ivNext.setImageResource(R.drawable.next_normal);
        } else {
            ivNext.setImageResource(R.drawable.next_gray);
        }
        ivNext.setEnabled(enable);
    }

    private void setPreEnable(boolean enable) {
        if (enable) {
            ivPre.setImageResource(R.drawable.pre_normal);
        } else {
            ivPre.setImageResource(R.drawable.pre_gray);
        }
        ivPre.setEnabled(enable);
    }


    private void setBrightness(int bright) {
        vpbBrightness.setProgress(bright);
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, bright);
        currentBright = bright;
    }


    @OnClick({R.id.iv_back, R.id.iv_pre, R.id.iv_play_pause, R.id.iv_next, R.id.iv_screen, R.id.ibtn_volume, R.id.ibtn_play_on_video})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                player.stopPlayback();
                finish();
                break;
            case R.id.iv_pre:
                playPre();
                break;
            case R.id.iv_play_pause:
                playOrPause();
                break;
            case R.id.iv_next:
                playNext();
                break;
            case R.id.iv_screen:
                setScreenType();
                break;

            case R.id.ibtn_volume:
                isMute = !isMute;
                setVolume(currentVolume, !isMute);
                break;
            case R.id.ibtn_play_on_video:
                playOrPause();
                break;

        }
        removeAndSendMessagesDelayed(HIDE_CONTROLER, 4000);
    }


    /**
     * 播放或暂停
     */
    private void playOrPause() {
        if (player.isPlaying()) {
            player.pause();
            ivPlayPause.setImageResource(R.drawable.selector_player_play);
            ibtnPlay.setVisibility(View.VISIBLE);
        } else {
            player.start();
            ivPlayPause.setImageResource(R.drawable.selector_player_pause);
            ibtnPlay.setVisibility(View.GONE);
        }
    }

    /**
     * 播放前一个视频
     */
    private void playPre() {
        position--;
        setLoadingShowed(true);
        mHandler.removeMessages(NET_SPEED);
        mHandler.sendEmptyMessage(NET_SPEED);
        setVideoResource();
        setPreNextEnable();
    }

    /**
     * 播放下一个视频
     */
    private void playNext() {
        position++;
        setLoadingShowed(true);
        mHandler.removeMessages(NET_SPEED);
        mHandler.sendEmptyMessage(NET_SPEED);
        setVideoResource();
        setPreNextEnable();
    }

    /**
     * 设置视频样式：全屏或默认
     */
    private void setScreenType() {
        if (isFullScreen) {
            if (videoWidth * screenHeight < videoHeight * screenWidth && videoHeight != 0) {
                videoWidth = videoWidth * screenHeight / videoHeight;
                videoHeight = screenHeight;
            } else if (videoHeight * screenWidth <= videoWidth * screenHeight & videoWidth != 0) {
                videoHeight = videoHeight * screenWidth / videoWidth;
                videoWidth = screenWidth;
            }

            player.setVideoSize(videoWidth, videoHeight);
            isFullScreen = false;
            ivScreen.setImageResource(R.drawable.selector_player_full_screen);
        } else {
            player.setVideoSize(screenWidth, screenHeight);
            isFullScreen = true;
            ivScreen.setImageResource(R.drawable.selector_player_default_screen);
        }
    }


    /**
     * 根据电池电量设置电池图标的显示样式
     *
     * @param level 电池电量值
     */
    private void setBatteryLevel(int level) {
        if (level <= 10) {
            ivBattery.setImageResource(R.drawable.battery_10);
        } else if (level <= 50) {
            ivBattery.setImageResource(R.drawable.battery_30);
        } else if (level <= 75) {
            ivBattery.setImageResource(R.drawable.battery_60);
        } else if (level < 100) {
            ivBattery.setImageResource(R.drawable.battery_90);
        } else {
            ivBattery.setImageResource(R.drawable.battery_100);
        }
    }

    /**
     * 判断音量是否为0，是则isMute==true
     *
     * @param volume
     * @return
     */
    private boolean isVolEqulsZero(int volume) {
        return isMute = volume == 0;
    }


    /**
     * 视频准备监听
     */
    private class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
            player.start();
            setLoadingShowed(false);
            setupScreenSize(mp);
            setupDuration();
//            发送Handler
            mHandler.sendEmptyMessageDelayed(PROGRESS, 1000);

        }
    }

    private void setupDuration() {
        duration = player.getDuration();
        sbProgress.setMax(player.getDuration());
        tvTotalTime.setText(TimeUtils.mills2String(duration));
    }

    private void setupScreenSize(MediaPlayer mp) {
        //            获取视频宽高
        videoWidth = mp.getVideoWidth();
        videoHeight = mp.getVideoHeight();
        setScreenType();
    }


    /**
     * 视频播放完成监听
     */
    private class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            ToastUtils.showShortToast(VideoPlayBaseActivity.this, "播放完成");
            if (position < medias.size() - 1) {
                position += 1;
                setVideoResource();
            }
        }
    }

    /**
     * 视频卡顿监听
     */
    private class MyOnInfoListener implements MediaPlayer.OnInfoListener {

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            switch (what) {
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                    setLoadingShowed(true);
                    break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    setLoadingShowed(false);
                    break;
            }
            return true;
        }
    }

    /**
     * 视频seekbar监听
     */
    private class VideoOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                player.seekTo(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            mHandler.removeMessages(HIDE_CONTROLER);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mHandler.sendEmptyMessageDelayed(HIDE_CONTROLER, 4000);
        }
    }

    /**
     * 声音seekbar监听
     */
    private class VolumeOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                currentVolume = progress;
                setVolume(currentVolume, isVolEqulsZero(currentVolume));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            mHandler.removeMessages(HIDE_CONTROLER);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mHandler.sendEmptyMessageDelayed(HIDE_CONTROLER, 4000);
        }
    }

    /**
     * 电池电量广播接收
     */
    private class MyBroacastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            setBatteryLevel(level);
        }
    }

}
