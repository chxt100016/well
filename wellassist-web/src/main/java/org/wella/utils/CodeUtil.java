package org.wella.utils;

import java.util.UUID;

public class CodeUtil {
	//生成唯一的激活码
	public static String generateUniqueCode(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	private static char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	//随机生成密码6为书,生成找回密码验证码
	public static String  resetCode(){

		String result ="";
		for(int i=0;i<6;i++){
			result = result+chars[(int)(Math.random()*16)];
		}
		return result;
	}
}
