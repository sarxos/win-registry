package com.github.sarxos.winreg;

import java.util.prefs.Preferences;

import com.github.sarxos.winreg.internal.WindowsPreferencesBuilder;

/**
 * HKEY
 * 
 * @author Bartosz Firyn (SarXos)
 */
public enum HKey {

	/**
	 * HKEY_CLASSES_ROOT
	 */
	HKCR(WindowsPreferencesBuilder.HKCR_VALUE, WindowsPreferencesBuilder
			.getHKCR()),

	/**
	 * HKEY_CURRENT_USER
	 */
	HKCU(0x80000001, Preferences.userRoot()),

	/**
	 * HKEY_LOCAL_MACHINE
	 */
	HKLM(0x80000002, Preferences.systemRoot()),

	/**
	 * HKEY_USERS
	 */
	HKU(WindowsPreferencesBuilder.HKU_VALUE, WindowsPreferencesBuilder.getHKU()),

	/**
	 * HKEY_CURRENT_CONFIG
	 */
	HKCC(WindowsPreferencesBuilder.HKCC_VALUE, WindowsPreferencesBuilder
			.getHKCC());

	private int hex = 0;

	private Preferences root = null;

	private HKey(final int hex, final Preferences root) {
		this.hex = hex;
		this.root = root;
	}

	public int hex() {
		return hex;
	}

	public Preferences root() {
		return root;
	}

	public static HKey fromHex(int hex) {
		HKey[] hks = HKey.values();
		for (HKey hk : hks) {
			if (hk.hex() == hex) {
				return hk;
			}
		}
		return null;
	}
}
