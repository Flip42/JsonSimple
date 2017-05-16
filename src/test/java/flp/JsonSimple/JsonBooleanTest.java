package flp.JsonSimple;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JsonBooleanTest {

	private JsonBoolean expression;

	@Before
	public void init() {
		expression = new JsonBoolean();
	}

	@Test
	public void testTrue() {
		expression.interprete(new Context("true"));
		assertTrue(expression.getValue());
	}

	@Test
	public void testTrue2() {
		expression.interprete(new Context("TRUE"));
		assertTrue(expression.getValue());
	}

	@Test
	public void testFalse() {
		expression.interprete(new Context("false"));
		assertFalse(expression.getValue());
	}

	@Test
	public void testFalse2() {
		expression.interprete(new Context("FALSE"));
		assertFalse(expression.getValue());
	}

	@Test(expected=JsonException.class)
	public void testInvalid() {
		expression.interprete(new Context("null"));
	}
}
