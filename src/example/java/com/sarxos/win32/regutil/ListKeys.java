package com.sarxos.win32.regutil;

import java.util.List;


/**
 * @author Bartosz Firyn (SarXos)
 */
public class ListKeys {

	public static void main(String[] args) throws RegException {
		Win32Reg reg = Win32Reg.getInstance();
		String branch = "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Network";
		List<String> keys = reg.readStringSubKeys(HKey.HKLM, branch);
		for (String key : keys) {
			System.out.println(key);
		}
	}
}
