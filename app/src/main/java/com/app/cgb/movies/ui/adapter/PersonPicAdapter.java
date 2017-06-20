package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.PersonsDetail;
import com.app.cgb.moviepreview.utils.PicLoadUtils;

import butterknife.BindView;

public class PersonPicAdapter extends BaseSingleTypeAdapter<PersonsDetail.DataBean.BackgroundBean.ImagesBean>{

    public PersonPicAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.person_pics_item, parent, false);
        return new PersonPicViewHolder(view);
    }

    class PersonPicViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_person)
        ImageView ivPerson;
        public PersonPicViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            String image = getItem(position).getImage();
            PicLoadUtils.loadNormalPic(mContext,image,ivPerson);
        }
    }
}
