package Classes;

public class Card extends AbstractClasses.AbstractCard{
    private String cardNumber;
    private String pin;

    public Card(String cardNumber, String pin) {
        super(cardNumber, pin);
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public boolean isValidCardNumber() {
        return cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
    }
}
