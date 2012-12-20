package com.github.sarxos.winreg.internal;

/**
 * Windows security masks.
 * 
 * @author Bartosz Firyn (sarxos)
 */
public class HKeyAccess {

	public static final int ALL = 0xf003f;
	public static final int READ = 0x20019;
	public static final int DELETE = 0x10000;
	public static final int QUERY_VALUE = 1;
	public static final int SET_VALUE = 2;
	public static final int CREATE_SUB_KEY = 4;
	public static final int ENUMERATE_SUB_KEYS = 8;
	public static final int WRITE = 0x20006;

}
