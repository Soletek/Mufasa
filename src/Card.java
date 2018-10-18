import java.util.Date;

public class Card {
	String cardHolderName;
	String cardType;
	String cardNumberDebit;
	String cardNumberCredit;
	Date expiryDate;
	public Card() {
		super();
	}
	public Card(String cardHolderName, String cardType, String cardNumberDebit, String cardNumberCredit,
			Date expiryDate) {
		super();
		this.cardHolderName = cardHolderName;
		this.cardType = cardType;
		this.cardNumberDebit = cardNumberDebit;
		this.cardNumberCredit = cardNumberCredit;
		this.expiryDate = expiryDate;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumberDebit() {
		return cardNumberDebit;
	}
	public void setCardNumberDebit(String cardNumberDebit) {
		this.cardNumberDebit = cardNumberDebit;
	}
	public String getCardNumberCredit() {
		return cardNumberCredit;
	}
	public void setCardNumberCredit(String cardNumberCredit) {
		this.cardNumberCredit = cardNumberCredit;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
