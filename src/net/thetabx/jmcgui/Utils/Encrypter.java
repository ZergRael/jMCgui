package net.thetabx.jmcgui.Utils;

public class Encrypter {

	public static String encrypt(String str)
	{
		String encryptedStr = "";
		for(int i = 0; i < str.length(); i++)
			encryptedStr += (char)(str.charAt(i) + i);
		return encryptedStr;
	}
	
	public static String decrypt(String encryptedStr)
	{
		String str = "";
		for(int i = 0; i < encryptedStr.length(); i++)
			str += (char)(encryptedStr.charAt(i) - i);
		return str;
	}
}
