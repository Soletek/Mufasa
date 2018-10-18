/**
 * This class models the Mufasa Account, the actual back account is modeled
 * with BankAccount. The Mufasa account in linked to an actual account.
 */
public class Account {

	Person owner;
	BankAccount customerAccount;
	PaymentDetails paymentDetails;
	
	public Account(BankAccount account, Person owner, PaymentDetails paymentDetails) {
		this.customerAccount = account;
		this.owner = owner;
		this.paymentDetails = paymentDetails;
	}
	
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public BankAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(BankAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
}
