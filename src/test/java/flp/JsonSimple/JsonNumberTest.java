package flp.JsonSimple;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JsonNumberTest {

	private JsonNumber expression;

	@Before
	public void init() {
		expression = new JsonNumber();
	}

	@Test
	public void readNull() {
		expression.interprete(new Context("0"));
		assertEquals(new Integer(0), expression.getValue());
	}

	@Test
	public void readFourtyTwo() {
		expression.interprete(new Context("42"));
		assertEquals(new Integer(42), expression.getValue());
	}

	@Test
	public void readPositiveInt() {
		expression.interprete(new Context("+42"));
		assertEquals(new Integer(42), expression.getValue());
	}

	@Test
	public void readPositiveDouble() {
		expression.interprete(new Context("+42.0"));
		assertEquals(new Double(42), expression.getValue());
	}

	@Test
	public void readNegativeInt() {
		expression.interprete(new Context("-42"));
		assertEquals(new Integer(-42), expression.getValue());
	}

	@Test
	public void readNegativeDouble() {
		expression.interprete(new Context("-42.0"));
		assertEquals(new Double(-42), expression.getValue());
	}

	@Test
	public void readDouble() {
		expression.interprete(new Context("1.0"));
		assertEquals(new Double(1.0), expression.getValue());
	}

	@Test
	public void readDoubleExponentPlus() {
		expression.interprete(new Context("1.0e+2"));
		assertEquals(new Double(100.0), expression.getValue());
	}

	@Test
	public void readDoubleExponentMinus() {
		expression.interprete(new Context("1.0E-1"));
		assertEquals(new Double(0.1), expression.getValue());
	}

	@Test
	public void readOne() {
		expression.interprete(new Context("1"));
		assertEquals(new Integer(1), expression.getValue());
	}

	@Test
	public void confirmStartTrueMinus() {
		assertTrue(expression.confirm(new Context("-1")));
	}

	@Test
	public void confirmStartTrueZero() {
		assertTrue(expression.confirm(new Context("0")));
	}

	@Test
	public void confirmStartTrueFive() {
		assertTrue(expression.confirm(new Context("5")));
	}

	@Test
	public void confirmStartTrueNine() {
		assertTrue(expression.confirm(new Context("9")));
	}

	@Test
	public void confirmStartFalse() {
		assertFalse(expression.confirm(new Context("key")));
	}

	@Test(expected=JsonException.class)
	public void numberFormatExceptionFail() {
		expression.interprete(new Context("1.1.1"));
	}
}
