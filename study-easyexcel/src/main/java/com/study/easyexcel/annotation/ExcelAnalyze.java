package com.study.easyexcel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ElementType 是 Enum 枚举类型，它用来指定 Annotation 的类型。
 * "每 1 个 Annotation" 都与 "1～n 个 ElementType" 关联。当 Annotation 与某个 ElementType 关联时，就意味着：Annotation有了某种用途。例如，若一个
 * Annotation 对象是 METHOD 类型，则该 Annotation 只能用来修饰方法。
 *
 * RetentionPolicy 是 Enum 枚举类型，它用来指定 Annotation 的策略。通俗点说，就是不同 RetentionPolicy 类型的 Annotation 的作用域不同。
 * "每 1 个 Annotation" 都与 "1 个 RetentionPolicy" 关联。
 * a) 若 Annotation 的类型为 SOURCE，则意味着：Annotation 仅存在于编译器处理期间，编译器处理完之后，该 Annotation 就没用了。 例如，" @Override" 标志就是一个
 * Annotation。当它修饰一个方法的时候，就意味着该方法覆盖父类的方法；并且在编译期间会进行语法检查！编译器处理完后，"@Override" 就没有任何作用了。
 * b) 若 Annotation 的类型为 CLASS，则意味着：编译器将 Annotation 存储于类对应的 .class 文件中，它是 Annotation 的默认行为。
 * c) 若 Annotation 的类型为 RUNTIME，则意味着：编译器将 Annotation 存储于 class 文件中，并且可由JVM读入。
 */

/**
 * @Author x.zaft
 * @Date 2020/11/25
 * @Version v1
 **/
@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnalyze {

    /** 非空 */
    boolean nonEmpty() default false;

    /**
     * 格式类型 0-date 1-address 2-email 3-phone 4-number 5-dict 6-native 7-city 8-民族 9-credentialsCode,
     * 10-bank,11-punctuation 99-参保地
     */
    int formatCategory() default -1;

    /** 日期格式 */
    String format() default "";

    String message() default "";

}
