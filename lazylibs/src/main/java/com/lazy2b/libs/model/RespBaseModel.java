/**
 * 项目名:LazyLibs
 * 包  名:com.lazy2b.libs.model
 * 文件名:RespBaseModel.java
 * 创  建:2015-10-28上午9:50:58
 * Copyright © 2015, lazy2b.com All Rights Reserved.
 */
package com.lazy2b.libs.model;

import com.lazy2b.libs.constants.RespDataType;
import com.lazy2b.libs.interfaces.ILazyBase;

import java.util.List;
import java.util.Map;

/**
 * 类名: RespBaseModel <br/>
 * 描述: 所有Http请求返回数据的基类. <br/>
 * 功能: TODO. <br/>
 *
 * @author E-mail:jack.lin@qq.com
 * @version $Id$
 */
@SuppressWarnings("serial")
public class RespBaseModel extends BaseModel {

    /**
     * Http状态
     */
    public int state = 0;
    public String error = "";

    /**
     * 请求标记
     */
    public String action = "";
    /**
     * 请求响应，字符串格式
     */
    public String str = "";
    /**
     * 数据是否为空，解析异常或为空
     */
    public boolean empty = false;

    /**
     * 特殊返回1
     *
     * @see RespDataType
     */
    public List<?> list = null;
    /**
     * 特殊返回2
     *
     * @see RespDataType
     */
    public Map<?, ?> map = null;
    /**
     * 特殊返回3
     *
     * @see RespDataType
     */
    public Object obj = null;

}