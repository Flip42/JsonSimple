package flp.jsonSimple.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import flp.jsonSimple.parser.Context;
import flp.jsonSimple.parser.JsonArray;
import flp.jsonSimple.parser.JsonException;

public class JsonArrayTest {

	private JsonArray expression;

	@Before
	public void init() {
		expression = new JsonArray();
	}

	@Test
	public void emptyArray() {
		expression.interprete(new Context("[]"));
		assertEquals(0, expression.getValue().size());
	}

	@Test
	public void singleIntegerArray() {
		expression.interprete(new Context("[42]"));
		assertEquals(42, expression.getValue().get(0));
	}

	@Test
	public void trippleIntegerArray() {
		expression.interprete(new Context("[42,43,44]"));
		assertEquals(42, expression.getValue().get(0));
		assertEquals(43, expression.getValue().get(1));
		assertEquals(44, expression.getValue().get(2));
	}

	@Test
	public void singleStringArray() {
		expression.interprete(new Context("[\"test\"]"));
		assertEquals("test", expression.getValue().get(0));
	}

	@Test
	public void trippleStringArray() {
		expression.interprete(new Context("[\"test1\", \"test2\" , \"test3\"]"));
		assertEquals("test1", expression.getValue().get(0));
		assertEquals("test2", expression.getValue().get(1));
		assertEquals("test3", expression.getValue().get(2));
	}

	@Test
	public void singleBooleanArray() {
		expression.interprete(new Context("[true]"));
		assertEquals(true, expression.getValue().get(0));
	}

	@Test
	public void singleNullArray() {
		expression.interprete(new Context("[null]"));
		assertEquals(null, expression.getValue().get(0));
	}

	@Test
	public void trippleDoubleArray() {
		expression.interprete(new Context("[1.0, 2.0 , 2.0e3]"));
		assertEquals(1.0, expression.getValue().get(0));
		assertEquals(2.0, expression.getValue().get(1));
		assertEquals(2000.0, expression.getValue().get(2));
	}

	@Test
	public void confirmTrue() {
		assertTrue(expression.confirm(new Context("[]")));
	}

	@Test
	public void confirmFalse() {
		assertFalse(expression.confirm(new Context("key")));
	}

	@Test(expected=JsonException.class)
	public void arrayWithoutBrackets() {
		expression.interprete(new Context("exception"));
	}

	@Test(expected=JsonException.class)
	public void ArrayWithoutColon() {
		expression.interprete(new Context("[null null]"));
	}

	@Test(expected=JsonException.class)
	public void ArrayWithoutClosingBracket() {
		expression.interprete(new Context("[null"));
	}
}
