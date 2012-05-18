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

## Examples

```java
Win32Reg reg = Win32Reg.getInstance();
		
String value = reg.readString(HKey.HKLM, "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion", "ProductName");
System.out.println("Windows Distribution = " + value);
		
List<String> keys = reg.readStringSubKeys(HKey.HKLM, "SOFTWARE\\Microsoft");
for (String key : keys) {
	System.out.println(key);
}
```

This should print:

```
Active Setup
AD7Metrics
ADs
Advanced INF Setup
ALG
AppEnv
...
... many other entries here
...
Windows NT
Windows Portable Devices
Windows Script Host
Windows Scripting Host
Windows Search
Wisp
```
