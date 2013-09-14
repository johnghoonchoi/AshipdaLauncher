package com.example.ashipdalauncher.util;


/**
 * Class LogUtil.
 * print to log using Class and Method name.
 * 130812 Kim Taehee
 * slhyvaa@nate.com
 */

public class LogUtil {
	private static boolean debug = true;// BuildConfig.DEBUG;
	private static String Tag = "User Logged";

	/* vervose */
	public static void v(String log)
	{
		if(debug) {
			android.util.Log.v(Tag, new Exception().getStackTrace()[1].getClassName() + "::" 
					+ new Exception().getStackTrace()[1].getMethodName() + "():" + log); //한 수준 이전의 클래스명과 메소드명 호출
		}
	}

	/* debug */
	public static void d(String log)
	{
		if(debug) {
			android.util.Log.d(Tag, new Exception().getStackTrace()[1].getClassName() + "::" 
					+ new Exception().getStackTrace()[1].getMethodName() + "():" + log); //한 수준 이전의 클래스명과 메소드명 호출
		}
	}

	/* info */
	public static void i(String log)
	{
		if(debug) {
			android.util.Log.i(Tag, new Exception().getStackTrace()[1].getClassName() + "::" 
					+ new Exception().getStackTrace()[1].getMethodName() + "():" + log); //한 수준 이전의 클래스명과 메소드명 호출
		}
	}

	/* warn */
	public static void w(String log)
	{
		android.util.Log.w(Tag, new Exception().getStackTrace()[1].getClassName() + "::" 
				+ new Exception().getStackTrace()[1].getMethodName() + "():" + log); //한 수준 이전의 클래스명과 메소드명 호출
	}

	/* error */
	public static void e(String log)
	{
		android.util.Log.e(Tag, new Exception().getStackTrace()[1].getClassName() + "::" 
				+ new Exception().getStackTrace()[1].getMethodName() + "():" + log); //한 수준 이전의 클래스명과 메소드명 호출
	}
}