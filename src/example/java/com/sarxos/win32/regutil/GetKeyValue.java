package com.sarxos.win32.regutil;

/**
 * @author Bartosz Firyn (SarXos)
 */
public class GetKeyValue {

	public static void main(String[] args) throws RegException {
		Win32Reg reg = Win32Reg.getInstance();
		String tree = "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion";
		String value = reg.readString(HKey.HKLM, tree, "ProductName");
		System.out.println("Windows Distribution = " + value);
	}
}
