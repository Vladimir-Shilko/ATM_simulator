package AbstractClasses;

public abstract class AbstractCard {
    private String cardNumber;
    private String pin;

    public AbstractCard(String cardNumber, String pin) {
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
        return cardNumber.length() == 16;
    }
}
