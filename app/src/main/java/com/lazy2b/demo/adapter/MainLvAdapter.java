package com.lazy2b.demo.adapter;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import com.lazy2b.demo.R;
import com.lazy2b.demo.model.MainLvItemModel;
import com.lazy2b.libs.adapter.BaseLvAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * 项目名: lazylibs-v2.0
 * 包 名: com.lazy2b.demo.adapter
 * Copyright © 2017, CAIMAO.COM All Rights Reserved.
 * $Id$
 */

public class MainLvAdapter extends BaseLvAdapter<MainLvItemModel> {


    public MainLvAdapter(Context _cxt, List _list, Handler _handler) {
        super(_cxt, _list, _handler, R.layout.item_main_lv);
    }

    @Override
    protected void handleView(BaseLvHolder _holder) {
        _holder.fill(getItem(_holder.position));
    }

    @Override
    protected Class<? extends BaseLvHolder> getHolderClass() {
        return LvHolder.class;
    }

    public static class LvHolder extends BaseLvHolder {
        @BindView(R.id.textView1)
        public TextView textView1;
        @BindView(R.id.textView2)
        public TextView textView2;

        @Override
        public void fill(Object... args) {
            MainLvItemModel item = (MainLvItemModel) args[0];
            textView1.setText(item.title);
            textView2.setText(" state -> " + item.state);
        }
    }
}
