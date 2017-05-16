# JsonSimple [![Build Status](https://travis-ci.org/Flip42/JsonSimple.svg?branch=master)](https://travis-ci.org/Flip42/JsonSimple)

With this Library, you can easily parse a Json String to an Java Object.
All you have to do is:

```java
JsonObject expression = new JsonObject();
expression.interprete(new Context("{\"key\" : \"value\"}"));
String value = expression.get("key");	//returns a String
```
