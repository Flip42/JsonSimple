# JsonSimple [![Build Status](https://travis-ci.org/Flip42/JsonSimple.svg?branch=master)](https://travis-ci.org/Flip42/JsonSimple)

With this Library, you can easily parse a Json String to an Java Object.<br /> 
All you have to do is:

```java
JsonObject expression = new JsonObject();
expression.interprete(new Context("{\"key\" : \"value\"}"));
String value = expression.get("key");	//returns a String
```

Like this you can parse any Json String you have. In the folowing are some examples.

###Parse Numbers

```java
JsonObject expression = new JsonObject();
expression.interprete(new Context("{\"int\" : 42, \"double\" : 4e-2}"));
Integer theAnswer = expression.get("int");	//returns an Integer Object
Double number = expression.get("double");	//returns an Double Object
```

JsonSimple understands any Integer or Double Values signed or unsigned with exponent or without, try it by yourself ;)

###Parse Boolean Values

```java
JsonObject expression = new JsonObject();
expression.interprete(new Context("{\"bool\" : true}"));
Boolean bool = expression.get("bool");	//returns an Boolean Object
```

###Parse Objects

```java
JsonObject expression = new JsonObject();
expression.interprete(new Context("{\"outer\":{\"inner\":\"value\"}}"));
String value = ((Map)expression.get("outer")).get("inner");	//the inner Object works the same
```

If you parse an Object, you get a Map as the inner Object, which held all the inner values.

###Parse Arrays

```java
JsonObject expression = new JsonObject();
expression.interprete(new Context("{\"array\" : [\"value\", \"value2\" ] }"));
List<String> list = expression.get("array");	//returns a List
```

If you parse an Array, you get a List, but i think thats more kind of a improvement ;)<br />

If you parse more complex Objects you will be returned nested Maps and Lists like above.

