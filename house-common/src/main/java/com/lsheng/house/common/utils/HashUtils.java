package com.lsheng.house.common.utils;

import java.nio.charset.Charset;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * 密码加密工具
 */
public class HashUtils {
	
	private static final HashFunction FUNCTION = Hashing.md5();
	
	private static final String SALT = "lipop";
	
	public static String encryPassword(String password){
	   HashCode hashCode =	FUNCTION.hashString(password+SALT, Charset.forName("UTF-8"));
	   return hashCode.toString();
	}

}
