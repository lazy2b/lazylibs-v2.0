package com.lazy2b.demo.model;

import com.lazy2b.libs.model.BaseModel;

/**
 * 项目名: lazylibs-v2.0
 * 包 名: com.lazy2b.demo.model
 * Copyright © 2017, CAIMAO.COM All Rights Reserved.
 * $Id$
 */

public class MainLvItemModel extends BaseModel {

    public MainLvItemModel(String title, int state) {
        this.title = title;
        this.state = state;
    }

    public String title;
    public int state;

}
