package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.cgb.moviepreview.BitmapGetter;
import com.app.cgb.moviepreview.OnBitmapGet;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.entity.Images;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class PicAdapter extends BaseSingleTypeAdapter<Images.ImagesBean> {


    private int reqWidth;
    private ArrayMap<Integer, Integer> allHeightMap = new ArrayMap<>();
    private List<Integer> mPositionList;

    public PicAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.images_items, parent, false);
        reqWidth = parent.getWidth() / 2;
        return new ImagViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mListData!=null && mListData.size()>0){
            if (mPositionList !=null){
                return mPositionList.size();
            }
            return mListData.size();
        }
        return 0;
    }

    public void setList(List<Integer> positionList) {
        mPositionList = positionList;
        notifyDataSetChanged();
    }


    @Override
    public Images.ImagesBean getItem(int position) {
        int realPosition = position;
        if (mPositionList!=null){
            realPosition = mPositionList.get(position);
        }
        return mListData.get(realPosition);
    }

    class ImagViewHolder extends BaseViewHolder implements OnBitmapGet {
        private final BitmapGetter bitmapGetter;
        @BindView(R.id.iv_img)
        ImageView img;
        private int itemWidth;

        public ImagViewHolder(View view) {
            super(view);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();
            itemWidth = reqWidth - params.leftMargin - params.rightMargin;
            bitmapGetter = new BitmapGetter(mContext);
            bitmapGetter.setOnBItmapGet(this);
        }


        @Override
        public void fillView(int position) {
            int realPosition = position;
            if (mPositionList!=null){
                realPosition = mPositionList.get(position);
            }
            Images.ImagesBean item = getItem(position);
            String imgPath = item.getImage();
            Integer itemHeight = allHeightMap.get(realPosition);
            if (itemHeight != null) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img.getLayoutParams();
                layoutParams.height = itemHeight;
                img.setLayoutParams(layoutParams);
                PicLoadUtils.loadResizePic(mContext, imgPath, img, itemWidth, itemHeight);
            } else {
                PicLoadUtils.loadPlacePic(mContext,img);
                bitmapGetter.getBitmap(imgPath, realPosition);
            }
        }


        @Override
        public synchronized void onGet(Bitmap bitmap, int position, String path) {
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            bitmap.recycle();
            int itemHeight = (int) (reqWidth * height / (width * 1.0));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img.getLayoutParams();
            layoutParams.height = itemHeight;
            img.setLayoutParams(layoutParams);
            allHeightMap.put(position, itemHeight);

            PicLoadUtils.loadResizePic(mContext, path, img, itemWidth, itemHeight);
        }

        @Override
        public void onFail(Exception e) {
            ToastUtils.showShortToastSafe(mContext, e.getMessage());
        }
    }
}
