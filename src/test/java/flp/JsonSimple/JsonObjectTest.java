package flp.JsonSimple;

import static org.junit.Assert.*;

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
