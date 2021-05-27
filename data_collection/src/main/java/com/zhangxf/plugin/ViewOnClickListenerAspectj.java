package com.zhangxf.plugin;

import android.view.View;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Description:
 * @Author: zhangxf
 * @CreateDate: 2021/5/21 16:50
 */
@Aspect
public class ViewOnClickListenerAspectj {
    /**
     * android.view.View.OnClickListener.onClick(android.view.View)
     *
     * @param joinPoint JoinPoint
     */
    @After("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
    public void onViewClickAOP(final JoinPoint joinPoint) {
        View view = (View) joinPoint.getArgs()[0];
        DataPrivate.trackViewOnClick(view);
    }

    /**
     * 支持 @onClickView 注解
     *
     * @param joinPoint JoinPoint
     */
    @After("execution(@com.zhangxf.plugin.onClickView * *(android.view.View))")
    public void onTrackViewOnClickAOP(final JoinPoint joinPoint) {
        View view = (View) joinPoint.getArgs()[0];
        DataPrivate.trackViewOnClick(view);
    }
}
