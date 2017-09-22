/**
 * Copyright 2017 bejson.com
 */
package com.lazy2b.demo.model;

import com.lazy2b.libs.interfaces.ILazyBase;
import com.lazy2b.libs.model.BaseModel;
import com.lazy2b.libs.model.RespBaseModel;

/**
 * Auto-generated: 2017-09-21 18:14:37
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class RespAppLineModel extends RespBaseModel implements ILazyBase {

    public String url;
    public String appurl;
    public ContractModel Contract;
    public String fkurl;
    public String configurl;
    public int byyapp_android_id;
    public int byyapp_ios_id;

}