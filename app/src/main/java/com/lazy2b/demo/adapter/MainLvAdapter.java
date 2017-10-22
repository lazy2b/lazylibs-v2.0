package com.lazy2b.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lazy2b.demo.BR;
import com.lazy2b.demo.OtherActivity;
import com.lazy2b.demo.R;
import com.lazy2b.demo.databinding.ItemMainLvBinding;
import com.lazy2b.demo.model.MainLvItemModel;
import com.lazy2b.demo.model.MainViewModel;
import com.lazy2b.libs.adapter.BindingLvAdapter;

import java.util.List;

/**
 * 项目名: lazylibs-v2.0
 * 包 名: com.lazy2b.demo.adapter
 * Copyright © 2017, CAIMAO.COM All Rights Reserved.
 * $Id$
 */

public class MainLvAdapter extends BindingLvAdapter<MainLvItemModel, ItemMainLvBinding> {


    public MainLvAdapter(Context _cxt, List _list, Handler _handler) {
        super(_cxt, _list, _handler, R.layout.item_main_lv);
    }

    @Override
    protected int getVariableId() {
        return BR.item;
    }


    @Override
    protected void initView(ItemMainLvBinding binding) {
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainViewModel.get((AppCompatActivity) mCxt).select((Integer) v.getTag(R.id.lv_item_position) - 1);
                mCxt.startActivity(new Intent(mCxt, OtherActivity.class));
            }
        });
    }

}
