import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//This test uses EasyMock mocks to verify that transaction work correctly
public class AccountTest {

	Mufasa mufasa;
	MufasaConfiguration configuration;
	BankAccount customerAccount;
	BankAccount mufasaAccount;
	
	@Before
	public void setup(){
		Card card = new Card();
		card.setCardHolderName("Testy Tester");
		card.setCardNumberCredit("0000-0000-1234-1234-1234");
		
		//Mock create
		configuration = EasyMock.createMock(MufasaConfiguration.class);
		mufasaAccount = EasyMock.createMock(BankAccount.class);
		customerAccount = EasyMock.createMock(BankAccount.class);
	
		//Configure mocks that are shared between test cases
		EasyMock.expect(configuration.getValue("TEST_ACCOUNT_STORAGE_KEY")).andReturn(mufasaAccount);
		EasyMock.replay(configuration);
		
		mufasa = new Mufasa(configuration);
		
	}
	
	@Test
	public void test_successful_test_transaction() {	
		// times(1) also ensures, that the transactions are only made once
		EasyMock.expect(customerAccount.makeTransaction(EasyMock.eq(mufasaAccount), EasyMock.isA(BigDecimal.class)))
			.andReturn(true).times(1);
		EasyMock.expect(mufasaAccount.makeTransaction(EasyMock.eq(customerAccount), EasyMock.isA(BigDecimal.class)))
			.andReturn(true).times(1);
		
		EasyMock.replay(customerAccount);
		EasyMock.replay(mufasaAccount);
		
		String result = mufasa.doTestTransaction(customerAccount);
	
		assertEquals("Pass!\nRefund Success!", result);
	}
	
	@Test
	public void test_failing_test_transaction() {
		EasyMock.expect(customerAccount.makeTransaction(EasyMock.eq(mufasaAccount), EasyMock.isA(BigDecimal.class)))
			.andReturn(false);
		EasyMock.expect(mufasaAccount.makeTransaction(EasyMock.eq(customerAccount), EasyMock.isA(BigDecimal.class)))
			.andReturn(true).anyTimes();;
		
		EasyMock.replay(customerAccount);
		EasyMock.replay(mufasaAccount);

		String result = mufasa.doTestTransaction(customerAccount);
	
		assertEquals("Failed!", result);
	}
	
	@Test
	public void test_failing_test_transaction_refund() {
		EasyMock.expect(customerAccount.makeTransaction(EasyMock.eq(mufasaAccount), EasyMock.isA(BigDecimal.class)))
			.andReturn(true);
		EasyMock.expect(mufasaAccount.makeTransaction(EasyMock.eq(customerAccount), EasyMock.isA(BigDecimal.class)))
			.andThrow(new RuntimeException("Failed to create transaction.")).anyTimes();
		
		EasyMock.replay(customerAccount);
		EasyMock.replay(mufasaAccount);
		
		String result = mufasa.doTestTransaction(customerAccount);
	
		assertEquals("Pass!\nRefund Failed!", result);
	}
	
	@After
	public void finish() {
		EasyMock.verify(configuration);
		EasyMock.verify(mufasaAccount);
		EasyMock.verify(customerAccount);
	}


}
