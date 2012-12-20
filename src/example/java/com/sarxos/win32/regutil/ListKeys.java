package com.sarxos.win32.regutil;

import java.util.List;

import com.github.sarxos.winreg.HKey;
import com.github.sarxos.winreg.RegistryException;
import com.github.sarxos.winreg.WindowsRegistry;



/**
 * @author Bartosz Firyn (SarXos)
 */
public class ListKeys {

	public static void main(String[] args) throws RegistryException {
		WindowsRegistry reg = WindowsRegistry.getInstance();
		String branch = "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Network";
		List<String> keys = reg.readStringSubKeys(HKey.HKLM, branch);
		for (String key : keys) {
			System.out.println(key);
		}
	}
}
