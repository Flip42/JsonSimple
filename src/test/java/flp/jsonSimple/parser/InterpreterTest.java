package flp.jsonSimple.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import flp.jsonSimple.parser.Context;
import flp.jsonSimple.parser.JsonObject;



public class InterpreterTest {

	private JsonObject expression;

	@Before
	public void init() {
		expression = new JsonObject();
	}

	@Test
	public void minimalJson() {
		expression.interprete(new Context("{\"key\" : \"value\"}"));
		assertEquals("value", expression.get("key"));
	}

	@Test
	public void twoValueJson() {
		expression.interprete(new Context("{\"key\" :\"value\", \n \"key2\": \"value2\"}"));
		assertEquals("value", expression.get("key"));
		assertEquals("value2", expression.get("key2"));
	}

	@Test
	public void intValueJson() {
		expression.interprete(new Context("{\"key\" : 42 }"));
		assertEquals(new Integer(42), expression.get("key"));
	}

	@Test(expected=RuntimeException.class)
	public void failIntValueJson() {
		expression.interprete(new Context("{\"key\" : 4a2 }"));
	}

	@Test
	public void negativeIntValueJson() {
		expression.interprete(new Context("{\"key\" : -42}"));
		assertEquals(new Integer(-42), expression.get("key"));
	}

	@Test
	public void doubleValueJson() {
		expression.interprete(new Context("{\"key\" : 4.2 }"));
		assertEquals(new Double(4.2), expression.get("key"));
	}

	@Test
	public void negativeDoubleValueJson() {
		expression.interprete(new Context("{\"key\" : -4.2 }"));
		assertEquals(new Double(-4.2), expression.get("key"));
	}

	@Test
	public void exponentValueJson() {
		expression.interprete(new Context("{\"key\" : 2e6 }"));
		assertEquals(new Double(2000000), expression.get("key"));
	}

	@Test
	public void positiveExponentValueJson() {
		expression.interprete(new Context("{\"key\" : 2e+6}"));
		assertEquals(new Double(2000000), expression.get("key"));
	}

	@Test
	public void negativeExponentValueJson() {
		expression.interprete(new Context("{\"key\" : 2e-6 }"));
		assertEquals(new Double(2e-6), expression.get("key"));
	}

	@Test
	public void trueValueJson() {
		expression.interprete(new Context("{\"key\" : true }"));
		assertEquals(new Boolean(true), expression.get("key"));
	}

	@Test
	public void falseValueJson() {
		expression.interprete(new Context("{\"key\" : false }"));
		assertEquals(new Boolean(false), expression.get("key"));
	}

	@Test
	public void nullValueJson() {
		expression.interprete(new Context("{\"key\" : null }"));
		assertNull( expression.get("key"));
	}

	@Test
	public void ObjectValueJson() {
		expression.interprete(new Context("{\"key\" : {\"key\" : \"value\"} }"));
		assertEquals("value", ((Map) expression.get("key")).get("key"));
	}

	@Test
	public void EmptyArrayJson() {
		expression.interprete(new Context("{\"key\" : [ ] }"));
		assertEquals(0, ((List<Object>)expression.get("key")).size());
	}

	@Test
	public void StringArrayJson() {
		expression.interprete(new Context("{\"key\" : [\"value\", \"value2\" ] }"));
		assertEquals(2, ((List<Object>)expression.get("key")).size());
		assertEquals("value", ((List<Object>)expression.get("key")).get(0));
		assertEquals("value2", ((List<Object>)expression.get("key")).get(1));
	}

	@Test
	public void MultiLineJson() {
		expression.interprete(new Context("{\n\"key1\" : \"value1\", \n\"key2\" : 42, \n\"key3\" : null \n }"));
		assertEquals("value1", expression.get("key1"));
		assertEquals(42, expression.get("key2"));
		assertNull(expression.get("key3"));
	}

	@Test
	public void IntArrayJson() {
		expression.interprete(new Context("{\"key\" : [42, 43, -44 ] }"));
		assertEquals(3, ((List<Object>)expression.get("key")).size());
		assertEquals(new Integer(42), ((List<Object>)expression.get("key")).get(0));
		assertEquals(new Integer(43), ((List<Object>)expression.get("key")).get(1));
		assertEquals(new Integer(-44), ((List<Object>)expression.get("key")).get(2));
	}

	@Test
	public void DoubleArrayJson() {
		expression.interprete(new Context("{\"key\" : [4.2, -4.3, 4.4 ] }"));
		assertEquals(3, ((List<Object>)expression.get("key")).size());
		assertEquals(new Double(4.2), ((List<Object>)expression.get("key")).get(0));
		assertEquals(new Double(-4.3), ((List<Object>)expression.get("key")).get(1));
		assertEquals(new Double(+4.4), ((List<Object>)expression.get("key")).get(2));
	}

	@Test
	public void BooleanArrayJson() {
		expression.interprete(new Context("{\"key\" : [true, true, false] }"));
		assertEquals(3, ((List<Object>)expression.get("key")).size());
		assertEquals(new Boolean(true), ((List<Object>)expression.get("key")).get(0));
		assertEquals(new Boolean(true), ((List<Object>)expression.get("key")).get(1));
		assertEquals(new Boolean(false), ((List<Object>)expression.get("key")).get(2));
	}

//	@Test
//	public void PersonToJsonToPerson() {
//		SerializableJsonObject p = new Person("Vorname", "Nachname", 24);
//		//System.out.println(p.toJson());
//		expression.interprete(new Context(p.toJson()));
//		assertEquals(p, Person.newInstance(p.toJson()));
//	}
	
	//TODO arrays (Strings, Zahlen,..)
}
