package com.sarxos.win32.regutil;

import java.util.List;


public class Test {

	public static void main(String[] args) throws RegException {

		Win32Reg reg = Win32Reg.getInstance();
		
		String value = reg.readString(HKey.HKLM, "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion", "ProductName");
		System.out.println("Windows Distribution = " + value);
		
		List<String> keys = reg.readStringSubKeys(HKey.HKLM, "SOFTWARE\\Microsoft");
		for (String key : keys) {
			System.out.println(key);
		}
	}
}
