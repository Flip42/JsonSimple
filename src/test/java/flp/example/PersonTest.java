package flp.example;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private Person person;

	@Before
	public void init() {
		person = new Person("Name","Nachname",42);
	}

	@Test
	public void testAlter() {
		assertEquals(42,person.getAlter());
	}

	@Test
	public void testVorname() {
		assertEquals("Name",person.getVorname());
	}

	@Test
	public void testNachname() {
		assertEquals("Nachname",person.getNachname());
	}

	@Test
	public void testToJson() {
		String json = person.toJson();
		assertEquals("Nachname",new Person().newInstance(json).getNachname());
	}

	@Test(expected=RuntimeException.class)
	public void testAlterException() {
		Person localPerson = new Person("Name","Nachname",-1);
	}

}
