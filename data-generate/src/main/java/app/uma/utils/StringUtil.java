package app.uma.utils;

public class StringUtil {
	public static String UpperCaseFirst(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return  name;
	}
}
