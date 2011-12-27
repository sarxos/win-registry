package com.sarxos.win32.regutil;

@SuppressWarnings("serial")
public class RegException extends Exception {

	public RegException() {
		super();
	}

	public RegException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RegException(String arg0) {
		super(arg0);
	}

	public RegException(Throwable arg0) {
		super(arg0);
	}
}
