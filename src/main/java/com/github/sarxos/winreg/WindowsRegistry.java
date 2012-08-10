package com.github.sarxos.winreg;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


/**
 * Simple windows registry utility.
 * 
 * @author Bartosz Firyn (sarxos)
 */
public class WindowsRegistry {

	/**
	 * Static instance.
	 */
	private static WindowsRegistry instance = new WindowsRegistry();

	/**
	 * I'm singleton class.
	 */
	private WindowsRegistry() {
	}

	/**
	 * @return Static instance
	 */
	public static WindowsRegistry getInstance() {
		return instance;
	}

	/**
	 * Read a value from key and value name
	 * 
	 * @param hk HKEY
	 * @param key key
	 * @param valueName - value name
	 * @return Value
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public String readString(HKey hk, String key, String valueName) throws RegistryException {
		try {
			return ReflectedMethods.readString(hk.root(), hk.hex(), key, valueName);
		} catch (Exception e) {
			throw new RegistryException("Cannot read " + valueName + " value from key " + key, e);
		}
	}

	/**
	 * Read value(s) and value name(s) form given key
	 * 
	 * @param hk HKEY
	 * @param key key
	 * @return the value name(s) plus the value(s)
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Map<String, String> readStringValues(HKey hk, String key) throws RegistryException {
		try {
			return ReflectedMethods.readStringValues(hk.root(), hk.hex(), key);
		} catch (Exception e) {
			throw new RegistryException("Cannot read values from key " + key, e);
		}
	}

	/**
	 * Read the value name(s) from a given key
	 * 
	 * @param hk HKEY
	 * @param key key
	 * @return the value name(s)
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<String> readStringSubKeys(HKey hk, String key) throws RegistryException {
		try {
			return ReflectedMethods.readStringSubKeys(hk.root(), hk.hex(), key);
		} catch (Exception e) {
			throw new RegistryException("Cannot read sub keys from key " + key, e);
		}
	}

	/**
	 * Create key in registry.
	 * 
	 * @param hk HKEY
	 * @param key key
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void createKey(HKey hk, String key) throws RegistryException {
		int[] ret;
		try {
			ret = ReflectedMethods.createKey(hk.root(), hk.hex(), key);
		} catch (Exception e) {
			throw new RegistryException("Cannot create key " + key, e);
		}
		if (ret.length == 0) {
			throw new RegistryException("Cannot create key " + key + ". Zero return length");
		}
		if (ret[1] != RC.SUCCESS) {
			throw new RegistryException("Cannot create key " + key + ". Return code is " + ret[1]);
		}
	}

	/**
	 * Write a value in a given key/value name
	 * 
	 * @param hk HKEY to be used
	 * @param key key to be written
	 * @param valueName value name
	 * @param value value
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void writeStringValue(HKey hk, String key, String valueName, String value) throws RegistryException {
		try {
			ReflectedMethods.writeStringValue(hk.root(), hk.hex(), key, valueName, value);
		} catch (Exception e) {
			throw new RegistryException("Cannot write " + valueName + " value " + value + " in key " + key, e);
		}
	}

	/**
	 * Delete given key from registry.
	 * 
	 * @param hk HKEY
	 * @param key key
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void deleteKey(HKey hk, String key) throws RegistryException {
		int rc = -1;
		try {
			rc = ReflectedMethods.deleteKey(hk.root(), hk.hex(), key);
		} catch (Exception e) {
			throw new RegistryException("Cannot delete key " + key, e);
		}
		if (rc != RC.SUCCESS) {
			throw new RegistryException("Cannot delete key " + key + ". Return code is " + rc);
		}
	}

	/**
	 * Delete value from given key/value name.
	 * 
	 * @param hk HKEY
	 * @param key key
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void deleteValue(HKey hk, String key, String value) throws RegistryException {
		int rc = -1;
		try {
			rc = ReflectedMethods.deleteValue(hk.root(), hk.hex(), key, value);
		} catch (Exception e) {
			throw new RegistryException("Cannot delete value " + value + " from key " + key, e);
		}
		if (rc != RC.SUCCESS) {
			throw new RegistryException("Cannot delete value " + value + " from key " + key + ". Return code is " + rc);
		}
	}

}
