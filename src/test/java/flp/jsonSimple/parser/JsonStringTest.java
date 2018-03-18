package flp.jsonSimple.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import flp.jsonSimple.parser.Context;
import flp.jsonSimple.parser.JsonString;

public class JsonStringTest {

	private JsonString expression;

	@Before
	public void init() {
		expression = new JsonString();
	}

	@Test
	public void minimalString() {
		expression.interprete(new Context("\"key\""));
		assertEquals("key", expression.getValue());
	}

	@Test(expected=RuntimeException.class)
	public void failStringJson() {
		expression.interprete(new Context("{\"key\" : 42 }"));
	}

	@Test
	public void minimalQuotes() {
		expression.interprete(new Context("\"key \\\"test\\\" end\""));
		assertEquals("key \\\"test\\\" end", expression.getValue());
	}

	@Test
	public void escapedSlash() {
		expression.interprete(new Context("\"key \\test\""));
		assertEquals("key \\test", expression.getValue());
	}

	@Test
	public void confirmStartTrue() {
		assertTrue(expression.confirm(new Context("\"key\"")));
	}

	@Test
	public void confirmStartFalse() {
		assertFalse(expression.confirm(new Context("key")));
	}
}
