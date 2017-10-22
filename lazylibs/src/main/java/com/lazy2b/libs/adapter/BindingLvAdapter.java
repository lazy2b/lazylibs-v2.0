/**
 * 项目名:LazyLibs
 * 包  名:com.lazy2b.libs
 * 文件名:BaseLvAdapter.java
 * 创  建:2015年10月20日下午4:04:30
 * Copyright © 2015, lazy2b.com All Rights Reserved.
 */
package com.lazy2b.libs.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lazy2b.libs.R;
import com.lazy2b.libs.interfaces.ILazyBase;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名: BaseLvAdapter <br/>
 * 描述: TODO. <br/>
 * 功能: TODO. <br/>
 *
 * @author E-mail:jack.lin@qq.com
 * @version $Id$
 */
public abstract class BindingLvAdapter<T, VBD extends ViewDataBinding> extends BaseAdapter implements ILazyBase {

    protected List<T> mList = null;

    protected Handler mUIHandler = null;

    protected Context mCxt = null;

    protected LayoutInflater mInflater = null;

    protected int mContentViewResId = android.R.layout.activity_list_item;

    public BindingLvAdapter() {
    }

    public BindingLvAdapter(Context _cxt, List<T> _list, Handler _handler) {

        mCxt = _cxt;
        mUIHandler = _handler;
        setList(_list);

    }

    public BindingLvAdapter(Context _cxt, List<T> _list, Handler _handler, int _resId) {

        this(_cxt, _list, _handler);
        mContentViewResId = _resId;
        mInflater = LayoutInflater.from(_cxt);

    }

    public void addList(List<T> _list) {

        if (mList == null) {
            mList = new ArrayList<T>();
        }

        if (_list != null) {
            mList.addAll(_list);
        }

    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> _list) {

        if (mList == null) {
            mList = new ArrayList<T>();
        }

        if (_list != null) {
            mList.clear();
            mList.addAll(_list);
        }

    }

    @Override
    public int getCount() {

        try {
            return mList.size();
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public T getItem(int position) {

        try {
            return mList.get(position);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public long getItemId(int position) {

        return position;

    }

//    /**
//     * @param view
//     * @return
//     */
//    private BaseLvHolder getHolder(View view, Class<? extends BaseLvHolder> cls) {
//        BaseLvHolder holder = getHolder(view);
//        if (holder != null) {
//            ButterKnife.bind(holder, view);
//        } else {
//            holder = (BaseLvHolder) BaseViewHolder.create(mCxt, view, cls);
//        }
//        return holder;
//    }
//
//    @Deprecated
//    protected BaseLvHolder getHolder() {
//        return null;
//    }
//
//    protected BaseLvHolder getHolder(View view) {
//        return getHolder();
//    }
//
//    protected Class<? extends BaseLvHolder> getHolderClass() {
//        return BaseLvHolder.class;
//    }
//
//    protected void handleView(BaseLvHolder _holder) {
//        _holder.fill(getItem(_holder.position));
//    }
//
//    /**
//     * Holder中需要一次性设置的控件初始化;<br/>
//     * 如手动设置大小，字体颜色等等
//     *
//     * @param _holder
//     */
//    protected void initView(BaseLvHolder _holder) {
//    }
//

    /**
     * Holder中需要一次性设置的控件初始化;<br/>
     * 如手动设置大小，字体颜色等等
     *
     * @param _holder
     */
    protected void initView(VBD binding) {
    }

    protected int getContentViewResId() {
        return mContentViewResId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        VBD binding = null;
        if (view == null) {
            try {
                binding = DataBindingUtil.inflate(mInflater, getContentViewResId(), null, false);
                view = binding.getRoot();
            } catch (Exception e) {
                KLog.i(e.getMessage());
            }
            initView(binding);

            view.setTag(R.id.lv_item_position, position);
            view.setTag(R.id.lv_item_model, getItem(position));
            view.setTag(getContentViewResId(), binding);

        } else {
            binding = (VBD) view.getTag(getContentViewResId());
        }
        binding.setVariable(getVariableId(), getItem(position));
        return view;
    }

    protected abstract int getVariableId();

    //    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//
//        BaseLvHolder holder;
//
//        if (view == null) {
//
//            ViewDataBinding binding = null;
//
//            try {
//                binding = DataBindingUtil.inflate(mInflater, getContentViewResId(), parent, parent != null);
//                view = binding.getRoot();//mInflater.inflate(getContentViewResId(), null);
//            } catch (Exception e) {
//
//                KLog.i(e.getMessage());
//
//            }
//
//            holder = getHolder(view, getHolderClass());
//
//            holder.position = position;
//
//            initView(holder);
//
//            initView(holder, binding);
//
//            view.setTag(getContentViewResId(), holder);
//
//        } else {
//
//            holder = (BaseLvHolder) view.getTag(getContentViewResId());
//
//            holder.position = position;
//
//        }
//
//        handleView(holder);
//
//        return view;
//
//    }

//    public static class BaseLvHolder extends BaseViewHolder {
//        public int position;
//
//        public BaseLvHolder() {
//            super();
//        }
//
//        public BaseLvHolder(Context cxt, Handler handler, View root) {
//            super(cxt, handler, root);
//        }
//
//        public BaseLvHolder(Context cxt, View root) {
//            super(cxt, root);
//        }
//
//        @Override
//        public void fill(Object... args) {
//        }
//    }

//    public static class BaseLvHolder extends BaseViewHolder {
//        public int position;
//
//        public BaseLvHolder() {
//            super();
//        }
//
//        public BaseLvHolder(Context cxt, Handler handler, View root) {
//            super(cxt, handler, root);
//        }
//
//        public BaseLvHolder(Context cxt, View root) {
//            super(cxt, root);
//        }
//
//        @Override
//        public void fill(Object... args) {
//        }
//    }
}
