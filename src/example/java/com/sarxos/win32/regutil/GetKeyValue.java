package com.sarxos.win32.regutil;

import com.github.sarxos.winreg.HKey;
import com.github.sarxos.winreg.RegistryException;
import com.github.sarxos.winreg.WindowsRegistry;



/**
 * @author Bartosz Firyn (SarXos)
 */
public class GetKeyValue {

	public static void main(String[] args) throws RegistryException {
		WindowsRegistry reg = WindowsRegistry.getInstance();
		String tree = "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion";
		String value = reg.readString(HKey.HKLM, tree, "ProductName");
		System.out.println("Windows Distribution = " + value);
	}
}
