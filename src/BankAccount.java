import java.math.BigDecimal;

public interface BankAccount {
	boolean linkToCard(Card paymentCard);
	Card getLinkedCard();
	boolean makeTransaction(BankAccount targetAccount, BigDecimal amount);
}
