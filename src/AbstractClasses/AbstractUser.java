package AbstractClasses;

public abstract class AbstractUser {

    private String cardNumber;
    private String pin;
    private double balance;

    public AbstractUser(String cardNumber, String pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }



    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        balance -= amount;
        return true;
    }

    public void transfer(AbstractUser user, double amount) {
        balance -= amount;
        user.deposit(amount);
    }

    public void printBalance() {
        System.out.println("Balance: " + balance);
    }

    public void printUser() {
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Balance: " + balance);
    }
}
