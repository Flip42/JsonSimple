package flp.JsonSimple;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JsonValueTest {

	private Expression expression;

	@Before
	public void init() {
		expression = new JsonValue();
	}

	@Test
	public void valueString() {
		expression = expression.interprete(new Context("\"key\""));
		assertEquals("key", expression.getValue());
	}

	@Test
	public void valueInteger() {
		expression = expression.interprete(new Context("123"));
		assertEquals(new Integer(123), expression.getValue());
	}

	@Test
	public void valueConfirmInteger() {
		assertTrue(expression.confirm(new Context("123")));
	}

	@Test
	public void valueConfirmIntegerFail() {
		assertFalse(expression.confirm(new Context("exception")));
	}

	@Test
	public void justForCodeCoverage() {
		assertNull(expression.getValue());
	}

	@Test(expected=JsonException.class)
	public void valueFail() {
		expression = expression.interprete(new Context("exception"));
	}
}
