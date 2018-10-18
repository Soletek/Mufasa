
public interface PaymentDetails {
	String getStreetAddress();
	String getCity();
	String getPostalCode();
	Country getCountry();
	String getPasswordHash();
	Card getPaymentCard();
}
