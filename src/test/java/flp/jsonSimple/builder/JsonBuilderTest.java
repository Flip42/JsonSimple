package flp.jsonSimple.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import flp.example.Person;
import flp.example.TestObject;

public class JsonBuilderTest {
	
	@Test
	public void minimalJson() {
		JsonBuilder builder = new JsonBuilder();
		String json = builder.parse(new Person("Vorname","Name",42));
		System.out.println(json);
		assertEquals("{\"_class\":\"flp.example.Person\",\"alter\":42,\"nachname\":\"Name\",\"vorname\":\"Vorname\"}", json);
	}
	@Test
	public void minimalTestObjectJson() {
		JsonBuilder builder = new JsonBuilder();
		String generatedJson = builder.parse(new TestObject());
		String json = "{\"_class\":\"flp.example.TestObject\",\"boolValue\":true,\"byteValue\":88,\"charValue\":e,\"doubleValue\":4.2E-10,\"integerValue\":42,\"shortValue\":888,\"stringValue\":\"string\"}";
		assertEquals(json, generatedJson);
	}
}
