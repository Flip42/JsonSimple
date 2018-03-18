package flp.jsonSimple.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import flp.jsonSimple.parser.Context;
import flp.jsonSimple.parser.JsonException;

public class JsonContextTest {

	private Context context;

	@Test
	public void testGetChar() {
		context = new Context("test");
		assertEquals('t', context.getChar());
	}
	@Test
	public void testGetCharEndOfString() {
		context = new Context("test");
		context.eat("test");
		assertEquals('*', context.getChar());
	}

	@Test
	public void testEatEmpty() {
		context = new Context("test");
		context.eat("");
		assertEquals('t', context.getChar());
	}

	@Test
	public void testEatString() {
		context = new Context("test1");
		context.eat("test");
		assertEquals('1', context.getChar());
	}

	@Test(expected=JsonException.class)
	public void testEatNull() {
		context = new Context("test");
		context.eat(null);
	}

	@Test(expected=JsonException.class)
	public void testEatOther() {
		context = new Context("test");
		context.eat("fail");
	}

	@Test
	public void testEatIgnoreCaseEmpty() {
		context = new Context("test");
		context.eatIgnoreCase("");
		assertEquals('t', context.getChar());
	}

	@Test
	public void testEatIgnoreCaseString() {
		context = new Context("test1");
		context.eatIgnoreCase("test");
		assertEquals('1', context.getChar());
	}

	@Test(expected=JsonException.class)
	public void testEatIgnoreCaseNull() {
		context = new Context("test");
		context.eatIgnoreCase(null);
	}

	@Test(expected=JsonException.class)
	public void testEatIgnoreCaseOther() {
		context = new Context("test");
		context.eatIgnoreCase("fail");
	}

	@Test
	public void testSkipBlanks() {
		context = new Context(" \t\ntest");
		context.skipBlanks();
		assertEquals('t', context.getChar());
	}
}
