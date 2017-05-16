package flp.JsonSimple;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class JsonObjectTest {

	private JsonObject expression;

	@Before
	public void init() {
		expression = new JsonObject();
	}

	@Test
	public void minimalObject() {
		expression.interprete(new Context("{}"));
		assertEquals(0, expression.getValue().size());
	}

	@Test
	public void minimalObjectWithValues() {
		expression.interprete(new Context("{\"test\":\"test\"}"));
		assertEquals("test", expression.get("test"));
	}

	@Test
	public void multiObject() {
		expression.interprete(new Context("{\"outer\":{\"inner\":\"value\"}}"));
		assertEquals("value", ((Map)expression.get("outer")).get("inner"));
	}

	@Test
	public void confirmStartTrue() {
		assertTrue(expression.confirm(new Context("{}")));
	}

	@Test
	public void confirmStartFalse() {
		assertFalse(expression.confirm(new Context("false")));
	}

	@Test(expected=JsonException.class)
	public void noObject() {
		expression.interprete(new Context(""));
	}

	@Test(expected=JsonException.class)
	public void noKeyValueSeparator() {
		expression.interprete(new Context("{\"test\"}"));
	}

	@Test(expected=JsonException.class)
	public void noClosing() {
		expression.interprete(new Context("{\"test\":\"test\""));
	}
}
