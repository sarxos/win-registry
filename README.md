100% pure Java, simple Windows registry utility.

[![Build Status](https://secure.travis-ci.org/sarxos/win-registry.png?branch=master)](http://travis-ci.org/sarxos/win-registry)

## Maven Integration

Binaries are not available in Maven central so you have to add this repository in your pom:

```xml
<repositories>
	<repository>
		<id>sarxos-repo</id>
		<url>http://www.sarxos.pl/repo/maven2</url>
	</repository>
</repositories>
```

Add dependency to your project:

```xml
<dependency>
	<groupId>com.sarxos</groupId>
	<artifactId>win-registry</artifactId>
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

## Examples

Get specific key value:

```java
Win32Reg reg = Win32Reg.getInstance();
String branch = "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion";
String value = reg.readString(HKey.HKLM, branch, "ProductName");
System.out.println("Windows Distribution = " + value);
```

```
Windows Distribution = Microsoft Windows XP
```

List keys under specific branch:

```java
Win32Reg reg = Win32Reg.getInstance();
String branch = "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Network";
List<String> keys = reg.readStringSubKeys(HKey.HKLM, branch);
for (String key : keys) {
	System.out.println(key);
}
```

```
Location Awareness
Shared Parameters
SMAddOns
UMAddOns
World Full Access Shared Parameters
```
