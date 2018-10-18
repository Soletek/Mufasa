import java.math.BigDecimal;

public class Mufasa {
	MufasaConfiguration configuration;
	
	// this key is used to store the account data for the bank account used
	// as a target account, where test transaction is made to. (requirement F4)
	final String TEST_ACCOUNT_STORAGE_KEY = "TEST_ACCOUNT_STORAGE_KEY";
	
	public Mufasa(MufasaConfiguration configuration) {
		super();
		this.configuration = configuration;
	}
	
	
	public boolean validatePerson(String personName, PersonDatabase db) {
		Person person = db.getPerson(personName);
		
		if (person == null) {
			return false;
		}
		
		if (!person.getFirstName().matches("[A-Za-z]*")) {
			return false;
		}
		
		if (!person.getLastName().matches("[A-Za-z]*")) {
			return false;
		}
		
		if (!person.getUsername().matches("[A-Za-z0-9_]*")) {
			return false;
		}
		
		if (person.getUsername().matches(".*_.*_.*")) {
			return false;
		}
		
		// TODO: other validation should be here
		
		return true;
	}
	
	public String doTestTransaction(BankAccount customerAccount) {
		BankAccount mufasaTestAccount = (BankAccount)configuration.getValue(TEST_ACCOUNT_STORAGE_KEY);
		BigDecimal testAmount = new BigDecimal(0.01);
		String output = "";
		
		// Make the test transaction
		try {
			boolean success = customerAccount.makeTransaction(mufasaTestAccount, testAmount);
			
			if (!success) {
				output += "Failed!";
				return output;
			}
		} catch (RuntimeException e) {
			output += "Failed!";
			return output;
		}
		
		output += "Pass!\n";
		
		// Refund money
		try {
			boolean success = mufasaTestAccount.makeTransaction(customerAccount, testAmount);
			
			if (!success) {
				output += "Refund Failed!";
				return output;
			}
		} catch (RuntimeException e) {
			output += "Refund Failed!";
			return output;
		}
		
		output += "Refund Success!";
		return output;
	}
}
