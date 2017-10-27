package com.go2wheel.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeConverter {
	
	public static Pattern sizePtn = Pattern.compile("\\s*([0-9]*\\.?[0-9]*)\\s*([^0-9]*?)\\s*");
	
	
	public static int toOneTenth(String sizeStr) {
		Matcher m = SizeConverter.sizePtn.matcher(sizeStr);
		if (m.matches()) {
			String v = m.group(1);
			String unit = m.group(2).toUpperCase();
			switch (unit) {
			case "IN":
				return Math.round(Float.valueOf(v) * 254);
			case "GAL":
				return Math.round(Float.valueOf(v) * 37.8541178F);
			case "LB":
				return Math.round(Float.valueOf(v) * 4.5359237F);
			default:
				return Integer.valueOf(v);
			}
		} else {
			return 0;
		}
	}
	
	public static float toInch(int sizeInOneTenthMm, int tails) {
		int tv = (int) Math.pow(10, tails);
		float f = Math.round(((float)sizeInOneTenthMm / 254) * tv); 
		return  f / tv;
	}
	
	public static float toPound(int volInOneTenthKg, int tails) {
		int tv = (int) Math.pow(10, tails);
		float f = Math.round(((float)volInOneTenthKg / 4.5359237) * tv); 
		return  f / tv;
	}
}
