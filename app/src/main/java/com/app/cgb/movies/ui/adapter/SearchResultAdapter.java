package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseAdapter;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends PagerAdapter {

    private static final int PAGE_COUNT = 2;
    private final Context mContext;
    //    private final int mType;
    private final SearchResult mSearchResult;
    private final List<BaseAdapter> itemAdapters;
    private View convertView;

    public SearchResultAdapter(Context context, SearchResult searchResult) {
        mContext = context;
//        mType = type;
        mSearchResult = searchResult;
        itemAdapters = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MovieSearchResultAdapter adapter = null;
        View view = View.inflate(mContext, R.layout.fragment_search_list, null);
        TextView tvNoData = (TextView) view.findViewById(R.id.tv_no_data);
        RecyclerView rvList = (RecyclerView) view.findViewById(R.id.rv_list);
        switch (position) {
            case 0:
                adapter = new MovieSearchResultAdapter(mContext, Constants.SEARCH_TYPE_MOVIES);
                adapter.setData(mSearchResult.getMovies());
                setHasData(tvNoData, rvList, mSearchResult.getMoviesCount());
                break;
            case 1:
                adapter = new MovieSearchResultAdapter(mContext, Constants.SEARCH_TYPE_PERSONS);
                adapter.setData(mSearchResult.getPersons());
                setHasData(tvNoData, rvList, mSearchResult.getPersonsCount());
                break;
        }
        rvList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.setAdapter(adapter);

        itemAdapters.add(adapter);
        container.addView(view);
        return view;
    }

    private void setHasData(TextView tvNoData, RecyclerView rvList, int dataCount) {
        if (dataCount > 0) {
            tvNoData.setVisibility(View.GONE);
            rvList.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
            rvList.setVisibility(View.GONE);
        }
    }

//    public BaseAdapter getItemAdapter(int position){
//
//    }

    public void addData(SearchResult searchResult, int position) {
        if (position == 0) {
            itemAdapters.get(position).addData(searchResult.getMovies());
        } else if (position == 1) {
            itemAdapters.get(position).addData(searchResult.getPersons());
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
//        super.destroyItem(container, position, object);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "影片（" + mSearchResult.getMoviesCount() + "）";
            case 1:
                return "影人（" + mSearchResult.getPersonsCount() + "）";
        }
        return super.getPageTitle(position);
    }

    public List<BaseAdapter> getItemAdapters() {
        return itemAdapters;
    }
}
