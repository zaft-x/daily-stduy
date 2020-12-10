package com.study.util;

import org.springframework.beans.BeanUtils;

/**
 * @Author x.zaft
 * @Date 2020/12/10
 * @Version
 **/

public class ObjectUtil {
    public static <T> T copy(Object source, Class<T> target) {
        try {
            if (null != source) {
                T t = target.newInstance();
                BeanUtils.copyProperties(source, t);
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
