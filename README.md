windows-registry-util
============

100% pure Java, simple Windows registry utility.

[![Build Status](https://secure.travis-ci.org/sarxos/win-registry.png?branch=master)](http://travis-ci.org/sarxos/win-registry)

## Maven Integration

Add dependency to your project:

```xml
<dependency>
	<groupId>com.github.sarxos</groupId>
	<artifactId>windows-registry-util</artifactId>
	<version>0.1</version>
</dependency>
```

## Features

* createKey(HKey, String)
* deleteKey(HKey, String)
* deleteValue(HKey, String, String)
* readString(HKey, String, String)
* readStringSubKeys(HKey, String)
* readStringValues(HKey, String)
* writeStringValue(HKey, String, String, String)
