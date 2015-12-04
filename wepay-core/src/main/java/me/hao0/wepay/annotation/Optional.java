package me.hao0.wepay.annotation;

/**
 * 标记字段是可选的
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 11/11/15
 * @since 1.0.0
 */
public @interface Optional {

    /**
     * 是否任何情况下都可选
     * @return optional or not
     */
    boolean any() default true;
}
