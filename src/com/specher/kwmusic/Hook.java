package com.specher.kwmusic;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {

	private static String packagename = "cn.kuwo.player";

	@Override
	public void handleLoadPackage(
			final XC_LoadPackage.LoadPackageParam paramLoadPackageParam)
			throws Throwable {

		if (paramLoadPackageParam.packageName.equals(packagename)) {
		

			XposedBridge.log("kwmusicplus:Loaded kumusic.");
			
			XposedHelpers.findAndHookMethod(View.class, "setLayoutParams", ViewGroup.LayoutParams.class, new XC_MethodHook() {
			    @Override
			    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
			    	View view = (View) param.thisObject;
			    	if(view.getId()==2131624255){//根据id过滤要隐藏的view
			    		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) param.args[0];
			    		layoutParams.height = 1;//设置布局高度为1，不知道为什么，设置为0的话会失败
						layoutParams.width = 0;//设置布局宽度为0
						view.setVisibility(View.GONE);
			    	}
			    	
			    }
			});
			
		}
	}
			
			
			
}
