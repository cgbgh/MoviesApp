package com.app.cgb.moviepreview.ui.adapter.treeadapter;

import android.content.Context;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.Credits;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class CreditTypeItem extends TreeAdapterItem<Credits.TypesBean>{

    public CreditTypeItem(Credits.TypesBean data, Context context) {
        super(data,context);
    }

    @Override
    protected List<TreeAdapterItem> initChildren(Credits.TypesBean data) {
        List<Credits.TypesBean.PersonsBean> persons = data.getPersons();
        List<TreeAdapterItem> creditsItems =null;
        if (persons != null && persons.size() >0 ){
            creditsItems = new ArrayList<>();
            for (int i = 0; i < persons.size(); i++) {
                CreditsItem creditsItem = new CreditsItem(persons.get(i), mContext);
                creditsItems.add(creditsItem);
            }
        }
        return creditsItems;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_credit_type,getData().getTypeName())
                .setText(R.id.tv_credit_type_en, TextUtils.handleEmptyText(getData().getTypeNameEn()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.credits_type;
    }

    @Override
    public int getSpanSize() {
        return 0;
    }
}
