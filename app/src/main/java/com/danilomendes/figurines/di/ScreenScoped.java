package com.danilomendes.figurines.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by danilo on 08-11-2017.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ScreenScoped {
}
