package interfaces;

public interface IUser {
    String getCardNumber();
    double getBalance();
    boolean verifyPin(String inputPin);
    boolean isBlocked();
    long getLastFailedAttempt();
    boolean withdraw(double amount);
}
