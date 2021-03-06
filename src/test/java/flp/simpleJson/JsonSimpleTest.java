package flp.simpleJson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import flp.example.Person;
import flp.jsonSimple.JsonSimple;
import flp.jsonSimple.parser.JsonObject;



public class JsonSimpleTest {

	@Test
	public void minimalJson() {
		JsonObject expression = JsonSimple.parse("{\"key\" : \"value\"}");
		assertEquals("value", expression.get("key"));
	}
	@Test
	public void minimalJsonReverse() {
		JsonObject obj = JsonSimple.getObject("{\"key\" : \"value\"}");
		assertEquals("value", obj.get("key"));
	}
	@Test
	public void minimalJsonByObject() {
		String json = JsonSimple.parse(new Person());
		assertEquals("{\"_class\":\"flp.example.Person\",\"alter\":0,\"nachname\":\"empty\",\"vorname\":\"empty\"}", json);
	}

	@Test
	public void twoValueJson() {
		JsonObject expression = JsonSimple.parse("{\"key\" :\"value\", \n \"key2\": \"value2\"}");
		assertEquals("value", ((JsonObject) expression).get("key"));
		assertEquals("value2", expression.get("key2"));
	}

	@Test
	public void intValueJson() {
		JsonObject expression = JsonSimple.parse("{\"key\" : 42 }");
		assertEquals(new Integer(42), expression.get("key"));
	}

	@Test(expected=RuntimeException.class)
	public void failIntValueJson() {
		JsonSimple.parse("{\"key\" : 4a2 }");
	}

	@Test
	public void negativeIntValueJson() {
		JsonObject expression = JsonSimple.parse("{\"key\" : -42}");
		assertEquals(new Integer(-42), expression.get("key"));
	}

	@Test
	public void doubleValueJson() {
		JsonObject expression = JsonSimple.parse("{\"key\" : 4.2 }");
		assertEquals(new Double(4.2), expression.get("key"));
	}

	@Test
	public void negativeDoubleValueJson() {
		JsonObject expression = JsonSimple.parse("{\"key\" : -4.2 }");
		assertEquals(new Double(-4.2), expression.get("key"));
	}

	@Test
	public void exponentValueJson() {
		JsonObject expression = JsonSimple.parse("{\"key\" : 2e6 }");
		assertEquals(new Double(2000000), expression.get("key"));
	}

}
