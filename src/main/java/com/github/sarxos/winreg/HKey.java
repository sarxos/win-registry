package com.github.sarxos.winreg;

import static com.github.sarxos.winreg.internal.WindowsPreferencesBuilder.HKCC_VALUE;
import static com.github.sarxos.winreg.internal.WindowsPreferencesBuilder.HKCR_VALUE;
import static com.github.sarxos.winreg.internal.WindowsPreferencesBuilder.HKCU_VALUE;
import static com.github.sarxos.winreg.internal.WindowsPreferencesBuilder.HKLM_VALUE;
import static com.github.sarxos.winreg.internal.WindowsPreferencesBuilder.HKU_VALUE;

import java.util.prefs.Preferences;

import com.github.sarxos.winreg.internal.WindowsPreferencesBuilder;


/**
 * HKEY enumeration.
 * 
 * @author Bartosz Firyn (sarxos)
 * @author Yunqi Ouyang (oyyq99999)
 */
public enum HKey {

	/**
	 * HKEY_CLASSES_ROOT
	 */
	HKCR(HKCR_VALUE, WindowsPreferencesBuilder.getHKCR()),

	/**
	 * HKEY_CURRENT_USER
	 */
	HKCU(HKCU_VALUE, Preferences.userRoot()),

	/**
	 * HKEY_LOCAL_MACHINE
	 */
	HKLM(HKLM_VALUE, Preferences.systemRoot()),

	/**
	 * HKEY_USERS
	 */
	HKU(HKU_VALUE, WindowsPreferencesBuilder.getHKU()),

	/**
	 * HKEY_CURRENT_CONFIG
	 */
	HKCC(HKCC_VALUE, WindowsPreferencesBuilder.getHKCC());

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
