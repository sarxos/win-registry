package com.sarxos.win32.regutil;

/**
 * HKEY
 * 
 * @author Bartosz Firyn (SarXos)
 */
public enum HKey {

	/**
	 * HKEY_CURRENT_USER
	 */
	HKCU(0x80000001),
	
	/**
	 * HKEY_LOCAL_MACHINE
	 */
	HKLM(0x80000002);
	
	private int hex = 0;
	
	private HKey(final int hex) {
		this.hex = hex;
	}
	
	public int hex() {
		return hex;
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
