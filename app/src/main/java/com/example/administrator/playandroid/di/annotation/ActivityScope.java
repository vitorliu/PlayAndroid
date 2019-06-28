package com.example.administrator.playandroid.di.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
@Scope
@Retention(RUNTIME)
@Documented
public @interface ActivityScope {
}
