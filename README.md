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
	<version>0.2</version>
</dependency>
```

## Features

* createKey(HKey, String)
* deleteKey(HKey, String)
* deleteValue(HKey, String, String)
* readString(HKey, String, String)
* readString(HKey, String, String, String)
* readStringSubKeys(HKey, String)
* readStringSubKeys(HKey, String, String)
* readStringValues(HKey, String)
* readStringValues(HKey, String, String)
* writeStringValue(HKey, String, String, String)

## Limits

1. It can read values of ```REG_SZ``` and ```REG_EXPAND_SZ``` only.
2. It can read only these entries which user has permission to access.

## Example

Read Windows distribution name:

```java
WindowsRegistry reg = WindowsRegistry.getInstance();
String tree = "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion";
String value = reg.readString(HKey.HKLM, tree, "ProductName");
System.out.println("Windows Distribution = " + value);
```

List keys under ```SOFTWARE\Microsoft\Windows NT\CurrentVersion\Network```:

```java
WindowsRegistry reg = WindowsRegistry.getInstance();
String branch = "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Network";
List<String> keys = reg.readStringSubKeys(HKey.HKLM, branch);
for (String key : keys) {
	System.out.println(key);
}
```

## Credits

Great kudos and appreciation goes to:

* Konstantin Kladko, who wrote WindowsPreferences class,
* Tarun Elankath ([lenkite](https://github.com/lenkite)), who [described](http://lenkite.blogspot.fr/2008/05/access-windows-registry-using-java.html) method to hack the above class,
* Yunqi Ouyang ([oyyq99999](https://github.com/oyyq99999)), who managed to [hack it](https://github.com/sarxos/win-registry/pull/1) to gain access to HKCR, HKU and HKCC.



## License

Copyright (C) 2011 - 2014 Bartosz Firyn and contributors

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
