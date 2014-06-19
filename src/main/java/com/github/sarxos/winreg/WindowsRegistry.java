package com.github.sarxos.winreg;

import java.util.List;
import java.util.Map;

import com.github.sarxos.winreg.internal.RC;
import com.github.sarxos.winreg.internal.ReflectedMethods;


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
	 * @param hk the HKEY
	 * @param key the key
	 * @param valueName the value name
	 * @return String value
	 * @throws RegistryException when something is not right
	 */
	public String readString(HKey hk, String key, String valueName) throws RegistryException {
		return readString(hk, key, valueName, null);
	}
	
	/**
	 * Read a value from key and value name
	 * 
	 * @param hk the HKEY
	 * @param key the key
	 * @param valueName the value name
	 * @param charsetName which charset to use
	 * @return String value
	 * @throws RegistryException when something is not right
	 */
	public String readString(HKey hk, String key, String valueName, String charsetName) throws RegistryException {
		try {
			return ReflectedMethods.readString(hk.root(), hk.hex(), key, valueName, charsetName);
		} catch (Exception e) {
			throw new RegistryException("Cannot read " + valueName + " value from key " + key, e);
		}
	}

	/**
	 * Read value(s) and value name(s) form given key
	 * 
	 * @param hk the HKEY
	 * @param key the key
	 * @return the value name(s) plus the value(s)
	 * @throws RegistryException when something is not right
	 */
	public Map<String, String> readStringValues(HKey hk, String key) throws RegistryException {
		return readStringValues(hk, key, null);
	}

	/**
	 * Read value(s) and value name(s) form given key
	 * 
	 * @param hk the HKEY
	 * @param key the key
	 * @param charsetName which charset to use
	 * @return the value name(s) plus the value(s)
	 * @throws RegistryException when something is not right
	 */
	public Map<String, String> readStringValues(HKey hk, String key, String charsetName) throws RegistryException {
		try {
			return ReflectedMethods.readStringValues(hk.root(), hk.hex(), key, charsetName);
		} catch (Exception e) {
			throw new RegistryException("Cannot read values from key " + key, e);
		}
	}

	/**
	 * Read the value name(s) from a given key
	 * 
	 * @param hk the HKEY
	 * @param key the key
	 * @return the value name(s)
	 * @throws RegistryException when something is not right
	 */
	public List<String> readStringSubKeys(HKey hk, String key) throws RegistryException {
		return readStringSubKeys(hk, key, null);
	}

	/**
	 * Read the value name(s) from a given key
	 * 
	 * @param hk the HKEY
	 * @param key the key
	 * @param charsetName which charset to use
	 * @return the value name(s)
	 * @throws RegistryException when something is not right
	 */
	public List<String> readStringSubKeys(HKey hk, String key, String charsetName) throws RegistryException {
		try {
			return ReflectedMethods.readStringSubKeys(hk.root(), hk.hex(), key, charsetName);
		} catch (Exception e) {
			throw new RegistryException("Cannot read sub keys from key " + key, e);
		}
	}

	/**
	 * Create key in registry.
	 * 
	 * @param hk the HKEY
	 * @param key the key
	 * @throws RegistryException when something is not right
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
	 * @param hk the HKEY to be used
	 * @param key the key to be written
	 * @param valueName the value name
	 * @param value the value
	 * @throws RegistryException when something is not right
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
	 * @param hk the HKEY
	 * @param key the key to be deleted
	 * @throws RegistryException when something is not right
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
	 * @param hk the HKEY
	 * @param key the key
	 * @param value the value
	 * @throws RegistryException when something is not right
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
