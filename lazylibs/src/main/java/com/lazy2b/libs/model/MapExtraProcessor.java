/**
 * 项目名:LazyLibs
 * 包  名:com.lazy2b.libs.model
 * 文件名:MapExtraProcessor.java
 * 创  建:2015年12月31日上午10:01:18
 * Copyright © 2015, lazy2b.com All Rights Reserved.
 */
package com.lazy2b.libs.model;

import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * 类名: MapExtraProcessor <br/>
 * 描述: TODO. <br/>
 * 功能: TODO. <br/>
 *
 * @author E-mail:jack.lin@qq.com
 * @version $Id: MapExtraProcessor.java 80 2016-06-15 06:20:34Z lazy2b $
 */
@Deprecated
public class MapExtraProcessor implements ExtraProcessor, ExtraTypeProvider {

    protected Class<?> retCls;

    public MapExtraProcessor(Class<?> cls) {
        retCls = cls;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.alibaba.fastjson.parser.deserializer.ExtraProcessor#processExtra(java
     * .lang.Object, java.lang.String, java.lang.Object)
     */
    @Override
    public void processExtra(Object map, String key, Object val) {
        try {

            // if (val.toString().startsWith("[")) {
            ((Map<Object, Object>) map).put(key, "Oh no! you are SB!");// JSON.parseArray(val.toString(),
            // retCls));
            // } else if (val instanceof JSONObject) {
            // ((Map<Object, Object>) map).put(key,
            // JSON.parseObject(val.toString(), retCls));
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Type getExtraType(Object object, String key) {
        return retCls;
    }

}
