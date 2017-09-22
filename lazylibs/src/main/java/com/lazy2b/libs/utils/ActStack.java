/**
 * 项目名:LazyLibs
 * 包  名:com.lazy2b.libs.utils
 * 文件名:ActStack.java
 * 创  建:2015-10-28上午9:50:58
 * Copyright © 2015, lazy2b.com All Rights Reserved.
 */
package com.lazy2b.libs.utils;

import android.app.Activity;

import com.lazy2b.libs.interfaces.ILazyBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名: ActStack <br/>
 * 描述: Activity堆栈. <br/>
 * 功能: TODO. <br/>
 *
 * @author E-mail:jack.lin@qq.com
 * @version $Id: ActStack.java 3 2015-10-28 03:27:15Z lazy2b $
 */
public class ActStack implements ILazyBase {

    public interface IActStackHandler extends ILazyBase {
        void addToActStack();

        void delByActStack();
    }

    static List<Activity> mStack = null;

    public static List<Activity> inst() {
        if (mStack == null) {
            mStack = new ArrayList<Activity>();
        }
        return mStack;
    }

    public static void add(Activity item) {
        try {
            inst().add(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void remove(Activity item) {
        try {
            if (inst().contains(item)) {
                inst().remove(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Activity top() {
        try {
            if (inst().size() > 0) {
                return inst().get(inst().size() - 1);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void finishAll() {
        for (Activity act : inst()) {
            if (act != null && !act.isFinishing()) {
                act.finish();
            }
        }
        inst().clear();
    }

}
