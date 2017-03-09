package com.zzq.zzq_collapsing_demo_master.utils;

import android.content.Context;

/**
 * 保存应用程序的Context对象，一些程序没有Context的时候调用get方法可以获得一个Context
 * 
 * @author sam.sun
 */
public class ContextUtil {
	private static Context context;

	// 应用程序初始化的时候初始化context
	public static void init(Context ctx) {
		context = ctx;
	}

	public static Context getContext(){
		return context;
	}
}
