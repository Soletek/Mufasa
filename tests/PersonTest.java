import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// This test fakes PersonDatabase
public class PersonTest {

	Mufasa mufasa;
	PersonDatabase databaseFake;
	
	@Before
	public void setup(){
		Person jack = new Person();
		jack.setFirstName("Jack");
		jack.setLastName("Black");
		jack.setUsername("nice_jack123");
		
		Person hackerman = new Person();
		hackerman.setFirstName("Hacker");
		hackerman.setLastName("Hi");
		hackerman.setUsername("__hacker__");
		
		//Mock create
		databaseFake = EasyMock.createMock(PersonDatabase.class);
		EasyMock.expect(databaseFake.getPerson("Jack")).andReturn(jack).anyTimes();
		EasyMock.expect(databaseFake.getPerson("Hackerman")).andReturn(hackerman).anyTimes();
		EasyMock.expect(databaseFake.getPerson(EasyMock.anyString())).andReturn(null).anyTimes();
		EasyMock.replay(databaseFake); 
		
		mufasa = new Mufasa(null);
	}
	
	@Test
	public void test_validatePerson_valid_username_passes() {	
		boolean result = mufasa.validatePerson("Jack", databaseFake);
		assertTrue(result);
	}
	
	@Test
	public void test_validatePerson_invalid_username_returns_false() {
		boolean result = mufasa.validatePerson("Hackerman", databaseFake);
		assertFalse(result);
	}
	
	@Test
	public void test_validatePerson_missing_person_returns_false() {
		boolean result = mufasa.validatePerson("IDoNotExist", databaseFake);
		assertFalse(result);
	}
	
	@After
	public void finish() {
		EasyMock.verify(databaseFake);
	}
}
