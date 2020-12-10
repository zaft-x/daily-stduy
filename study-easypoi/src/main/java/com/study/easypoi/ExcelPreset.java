package com.study.easypoi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel 模板生成预设值
 *@description: 
 *@author: zaft_x
 *@time: 2020/12/10
 * 
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelPreset {
    /**列*/
    int index() default 0;
    /**预设值集合*/
    String[] value() default {};

    /**涉及行数*/
    int rows() default 99;

}
