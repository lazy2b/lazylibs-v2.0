package com.lazy2b.libs.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lazy2b.libs.constants.RespDataType;
import com.lazy2b.libs.interfaces.IContainerInit;
import com.lazy2b.libs.interfaces.IHttpHandler;
import com.lazy2b.libs.model.BaseReqCallBack;
import com.lazy2b.libs.model.RespBaseModel;
import com.lazy2b.libs.utils.ActStack;

/**
 * 项目名: lazylibs-v2.0
 * 包 名: com.lazy2b.libs.app
 * Copyright © 2017, LAZY2B.COM All Rights Reserved.
 * $Id$
 */

public class AbsBaseActivity extends AppCompatActivity implements IHttpHandler, ActStack.IActStackHandler, IContainerInit {
    /**
     * Context对象
     */
    protected Context mCxt = null;
    /**
     * 界面普通处理句柄
     */
    protected Handler mUIHandler = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCxt = this;
        setContentView(getContentViewId());
        init();
    }

    @Override
    public int getContentViewId() {
        return -1;
    }

    /**
     * 加入到Activity堆栈
     */
    @Override
    public void addToActStack() {
        ActStack.add(this);
    }

    /**
     * 从Activity堆栈中移除
     */
    @Override
    public void delByActStack() {
        ActStack.remove(this);
    }

    @Override
    public void init() {
        initData();
        findView();
        initView();
    }

    @Override
    public void initData() {
        mUIHandler = new Handler();
    }

    @Override
    public void findView() {

    }

    @Override
    public void initView() {

    }

    protected String action() {
        return this.getClass().getName();
    }

    @Override
    public void post(String action, String url, Class<?> retDataModelCls, Object bodyUnfixParms, RespDataType... rdt) {
        Http.send(action, url, new BaseReqCallBack(action, retDataModelCls, this, rdt), bodyUnfixParms);
    }

    @Override
    public void get(String action, String url, Class<?> retDataModelCls, RespDataType... rdt) {
        Http.send(action, url, new BaseReqCallBack(action, retDataModelCls, this, rdt));
    }

    @Override
    public void onLoading(String action, long total, long current, boolean isUploading) {

    }

    @Override
    public void onReqStart(String action) {

    }

    @Override
    public void onSuccess(RespBaseModel resp) {

    }

    @Override
    public void onFailure(String action, Throwable throwable) {

    }

    /**
     * 根据字符串资源id获取字符串
     *
     * @param resId
     * @return
     */
    protected String str(int resId) {
        try {
            return getString(resId);
        } catch (Exception e) {
            return "";
        }
    }
}
