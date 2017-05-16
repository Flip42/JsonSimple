package flp.JsonSimple;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void minimalQuotes() {
		expression.interprete(new Context("\"key \\\"test\\\" end\""));
		assertEquals("key \\\"test\\\" end", expression.getValue());
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
