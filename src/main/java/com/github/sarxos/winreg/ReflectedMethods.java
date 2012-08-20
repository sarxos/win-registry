package com.github.sarxos.winreg;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;


/**
 * A set of methods used to operate on Windows registry. Those methods comes
 * from private Sun API from <code>WindowsPreferences</code> written by
 * Konstantin Kladko. Access method used here has been initially found by Tarun
 * Elankath
 * (http://lenkite.blogspot.fr/2008/05/access-windows-registry-using-java.html).
 * 
 * @author Bartosz Firyn (sarxos)
 */
public final class ReflectedMethods {

	private static final Class<?> IC = int.class;
	private static final Class<?> BAC = byte[].class;

	private static final Method OPEN_KEY = setupMethod("WindowsRegOpenKey", new Class[] { IC, BAC, IC });
	private static final Method CLOSE_KEY = setupMethod("WindowsRegCloseKey", new Class[] { IC });
	private static final Method QUERY_VALUE_EX = setupMethod("WindowsRegQueryValueEx", new Class[] { IC, BAC });
	private static final Method ENUM_VALUE = setupMethod("WindowsRegEnumValue", new Class[] { IC, IC, IC });
	private static final Method QUERY_INFO_KEY = setupMethod("WindowsRegQueryInfoKey1", new Class[] { IC });
	private static final Method ENUM_KEY_EX = setupMethod("WindowsRegEnumKeyEx", new Class[] { IC, IC, IC });
	private static final Method CREATE_KEY_EX = setupMethod("WindowsRegCreateKeyEx", new Class[] { IC, BAC });
	private static final Method SET_VALUE_EX = setupMethod("WindowsRegSetValueEx", new Class[] { IC, BAC, BAC });
	private static final Method DELETE_VALUE = setupMethod("WindowsRegDeleteValue", new Class[] { IC, BAC });
	private static final Method DELETE_KEY = setupMethod("WindowsRegDeleteKey", new Class<?>[] { IC, BAC });

	private static Method setupMethod(String name, Class<?>[] args) {
		Method method = null;
		try {
			method = HKey.HKCU.root().getClass().getDeclaredMethod(name, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		method.setAccessible(true);
		return method;
	}

	protected static int deleteValue(Preferences root, int hkey, String key, String value) throws Exception {
		int[] handles = keyOpen(root, hkey, key, HKeyAccess.ALL);
		int rc = (Integer) invoke(DELETE_VALUE, root, handles[0], value);
		keyClose(root, handles);
		return rc;
	}

	protected static int deleteKey(Preferences root, int hkey, String key) throws Exception {
		return (Integer) invoke(DELETE_KEY, root, hkey, key);
	}

	protected static String readString(Preferences root, int hkey, String key, String value) throws Exception {
		int[] handles = keyOpen(root, hkey, key, HKeyAccess.READ);
		byte[] valb = (byte[]) invoke(QUERY_VALUE_EX, root, handles[0], value);
		keyClose(root, handles);
		return (valb != null ? new String(valb).trim() : null);
	}

	protected static Map<String, String> readStringValues(Preferences root, int hkey, String key) throws Exception {
		int[] handles = keyOpen(root, hkey, key, HKeyAccess.READ);
		int[] keyInfo = (int[]) invoke(QUERY_INFO_KEY, root, handles[0]);
		int count = keyInfo[2]; // count
		int maxlen = keyInfo[3]; // value length max
		HashMap<String, String> results = new HashMap<String, String>();
		for (int index = 0; index < count; index++) {
			byte[] name = (byte[]) invoke(ENUM_VALUE, root, handles[0], index, maxlen + 1);
			String strName = new String(name);
			String value = readString(root, hkey, key, strName);
			results.put(strName.trim(), value);
		}
		keyClose(root, handles);
		return results;
	}

	protected static List<String> readStringSubKeys(Preferences root, int hkey, String key) throws Exception {
		int[] handles = keyOpen(root, hkey, key, HKeyAccess.READ);
		int[] info = (int[]) invoke(QUERY_INFO_KEY, root, handles[0]);
		int count = info[0]; // count
		int maxlen = info[3]; // value length max
		List<String> results = new ArrayList<String>();
		for (int index = 0; index < count; index++) {
			byte[] name = (byte[]) invoke(ENUM_KEY_EX, root, handles[0], index, maxlen + 1);
			results.add(new String(name).trim());
		}
		keyClose(root, handles);
		return results;
	}

	protected static int[] createKey(Preferences root, int hkey, String key) throws Exception {
		int[] handles = (int[]) invoke(CREATE_KEY_EX, root, hkey, key);
		keyClose(root, handles);
		return handles;
	}

	protected static void writeStringValue(Preferences root, int hkey, String key, String valueName, String value) throws Exception {
		int[] handles = keyOpen(root, hkey, key, HKeyAccess.ALL);
		invoke(SET_VALUE_EX, root, handles[0], valueName, value);
		keyClose(root, handles);
	}

	private static Object invoke(Method method, Preferences root, Object... args) throws Exception {
		Object[] arguments = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			Object arg = args[i];
			if (arg instanceof Integer) {
				arguments[i] = (Integer) arg;
			} else if (arg instanceof String) {
				arguments[i] = strToNativeBytes(arg.toString());
			}
		}
		return method.invoke(root, arguments);
	}

	private static int[] keyOpen(Preferences root, int hkey, String key, int access) throws Exception {
		int[] handles = (int[]) invoke(OPEN_KEY, root, hkey, key, access);
		if (handles[1] != RC.SUCCESS) {
			throw new IllegalAccessException("Cannot open " + HKey.fromHex(hkey) + "\\" + key);
		}
		return handles;
	}

	private static void keyClose(Preferences root, int[] handles) throws Exception {
		invoke(CLOSE_KEY, root, handles[0]);
	}

	private static byte[] strToNativeBytes(String str) {
		byte[] result = new byte[str.length() + 1];
		for (int i = 0; i < str.length(); i++) {
			result[i] = (byte) str.charAt(i);
		}
		result[str.length()] = 0;
		return result;
	}

	private ReflectedMethods() {
	}

}
