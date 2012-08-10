package com.github.sarxos.winreg;

@SuppressWarnings("serial")
public class RegistryException extends Exception {

	public RegistryException() {
		super();
	}

	public RegistryException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RegistryException(String arg0) {
		super(arg0);
	}

	public RegistryException(Throwable arg0) {
		super(arg0);
	}
}
