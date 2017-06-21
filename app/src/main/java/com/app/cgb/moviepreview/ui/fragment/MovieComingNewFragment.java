package com.app.cgb.moviepreview.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MovieComingNew;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.activity.MTMovieDetailActivity;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.ui.view.MyScrollView;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.TextUtils;
import com.app.cgb.moviepreview.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class MovieComingNewFragment extends BaseRequstFragment<MovieComingNew> implements
        SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MovieComingNewFragment.class.getSimpleName();
    @BindView(R.id.attention_list)
    RecyclerView attentionList;
    @BindView(R.id.coming_count)
    TextView comingCount;
    @BindView(R.id.coming_list)
    RecyclerView comingList;
    @BindView(R.id.srf_layout)
    SwipeRefreshLayout srfLayout;
    @BindView(R.id.myscrollview)
    MyScrollView myScrollView;

    private CommonAdapter<MovieComingNew.AttentionBean> attentionAdpater;
    private CommonAdapter<MovieComingNew.MoviecomingsBean> movieComingAdapter;
    private CommonAdapter.OnItemInit attentionItemInit = new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, int position, List data) {
            setupAttentionListItem(holder, position, data);
        }
    };
    private CommonAdapter.OnItemInit comingItemInit = new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, int position, List data) {
            if (holder.getItemViewType() == CommonAdapter.ITEM_SECTION) {
                setupComingListTitle(holder, position, data);
            } else {
                setupMovieComingListItem(holder, position, data);
            }
        }
    };
    private CommonAdapter.SectionSupport sectionSupport = new CommonAdapter.SectionSupport<MovieComingNew.MoviecomingsBean>() {
        @Override
        public String getTitle(MovieComingNew.MoviecomingsBean moviecomingsBean) {
            return moviecomingsBean.getRYear() +
                    moviecomingsBean.getRMonth() +
                    moviecomingsBean.getRDay() + "";
        }

    };
    private List<MovieComingNew.AttentionBean> mAttention;
    private boolean isViewCreated;
    private boolean isVisibleToUser;
    private boolean isInitial;


    @Override
    protected int inflateView() {
        return R.layout.movie_coming_new;
    }

    @Override
    protected void initView() {
        setupAttentionList();
        setupComingList();
        srfLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        srfLayout.setOnRefreshListener(this);
        isViewCreated = true;
    }

    @Override
    public void request() {
        if (isViewCreated && isVisibleToUser) {
            isInitial = true;
            super.request();
        }

    }

    private void startDetailActivity(int id) {
        Intent intent = new Intent(mContext, MTMovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_ID, id);
        startActivity(intent);
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadMovieComingNew();
    }

    @Override
    public void scrollToTop() {
        myScrollView.smoothScrollTo(0, 0);
    }


    private void setupComingList() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        comingList.setLayoutManager(manager);
        movieComingAdapter = new CommonAdapter<>()
                .setLayoutId(R.layout.movie_coming_item)
                .setSectionSupport(R.layout.list_title, sectionSupport)
                .setOnItemInit(comingItemInit);
        comingList.setAdapter(movieComingAdapter);
    }

    private void setupMovieComingListItem(ViewHolder holder, int position, List data) {
        final MovieComingNew.MoviecomingsBean moviecomingsBean =
                (MovieComingNew.MoviecomingsBean) data.get(position);
        PicLoadUtils.loadNormalPic(mContext, moviecomingsBean.getImage(),
                (ImageView) holder.getView(R.id.iv_cover));
        holder.setText(R.id.title, moviecomingsBean.getTitle())
                .setText(R.id.wanted_count, moviecomingsBean.getWantedCount() + "人想看")
                .setText(R.id.type, "类型：" +
                        TextUtils.handleEmptyText(moviecomingsBean.getType()))
                .setText(R.id.director, "导演:" +
                        TextUtils.handleEmptyText(moviecomingsBean.getDirector()))
                .setText(R.id.actors, "主演:" +
                        TextUtils.handleEmptyText(moviecomingsBean.getActor1()) + "/" +
                        TextUtils.handleEmptyText(moviecomingsBean.getActor2()))
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startDetailActivity(moviecomingsBean.getId());
                    }
                });
    }

    private void setupComingListTitle(ViewHolder holder, int position, List data) {
        MovieComingNew.MoviecomingsBean moviecomingsBean =
                (MovieComingNew.MoviecomingsBean) data.get(position);
        String releaseDate = moviecomingsBean.getRMonth() + "月" +
                moviecomingsBean.getRDay() + "日";
        holder.setText(R.id.tv_title, releaseDate);
    }

    private void setupAttentionList() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        attentionList.setLayoutManager(manager);
        attentionAdpater = new CommonAdapter<>()
                .setLayoutId(R.layout.movie_attention_item)
                .setOnItemInit(attentionItemInit);
        attentionList.setAdapter(attentionAdpater);
    }

    private void setupAttentionListItem(ViewHolder holder, int position, List data) {
        final MovieComingNew.AttentionBean attentionBean =
                (MovieComingNew.AttentionBean) data.get(position);
        PicLoadUtils.loadNormalPic(mContext, attentionBean.getImage(),
                (ImageView) holder.getView(R.id.iv_cover));

        holder.setText(R.id.title, attentionBean.getTitle())
                .setText(R.id.release_date, "上映日期：" +
                        TextUtils.handleEmptyText(attentionBean.getReleaseDate()))
                .setText(R.id.type, "类型：" +
                        TextUtils.handleEmptyText(attentionBean.getType()))
                .setText(R.id.director, "导演：" +
                        TextUtils.handleEmptyText(attentionBean.getDirector()))
                .setText(R.id.actors, "主演：" +
                        TextUtils.handleEmptyText(attentionBean.getActor1()) + "/" +
                        TextUtils.handleEmptyText(attentionBean.getActor2()))
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startDetailActivity(attentionBean.getId());
                    }
                });
    }


    @Override
    public void onError(String msg) {
        ToastUtils.showShortToastSafe(mContext, msg);
    }

    @Override
    public void onResponse(final MovieComingNew movieComingNew) {
        final List<MovieComingNew.MoviecomingsBean> moviecomings = movieComingNew.getMoviecomings();
        List<MovieComingNew.AttentionBean> attention = movieComingNew.getAttention();
        comingCount.setText("即将上映(" + moviecomings.size() + "部)");
        setAttentionData(attention);
        movieComingAdapter.setData(moviecomings);
    }

    private void setAttentionData(final List<MovieComingNew.AttentionBean> attention) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return mAttention == null ? 0 : mAttention.size();
            }

            @Override
            public int getNewListSize() {
                return attention == null ? 0 : attention.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return true;
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return mAttention.get(oldItemPosition).getId() == attention.get(newItemPosition).getId();
            }
        });

        mAttention = attention;
        attentionAdpater.setData(attention, diffResult);
    }

    @Override
    public void onRefresh() {
        request();
    }

    @Override
    public void showLoading() {
        if (getUserVisibleHint()) {
            srfLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        if (srfLayout.isRefreshing()) {
            srfLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    srfLayout.setRefreshing(false);
                }
            }, 1000);

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (!isInitial){
            request();
        }
    }

}
