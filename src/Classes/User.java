package Classes;

public class User extends AbstractClasses.AbstractUser{
        private final String cardNumber;
        private final String pin;
        private double balance;
        private long lastFailedAttempt;
        private int failedAttempts;

        public User(String cardNumber, String pin, double balance, long lastFailedAttempt, int failedAttempts) {
            super(cardNumber, pin, balance);

            this.cardNumber = cardNumber;
            this.pin = pin;
            this.balance = balance;
            this.lastFailedAttempt = lastFailedAttempt;
            this.failedAttempts = failedAttempts;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public double getBalance() {
            return balance;
        }

        public boolean verifyPin(String inputPin) {
            if (System.currentTimeMillis() - lastFailedAttempt > 86400000) {
                failedAttempts = 0;
            }

            if (!pin.equals(inputPin)) {
                failedAttempts++;
                lastFailedAttempt = System.currentTimeMillis();
                if (failedAttempts >= 3) {
                    System.out.println("Введен неправильный ПИН 3 раза. Карта заблокирована на 24 часа.");
                } else {
                    System.out.println("Неправильный ПИН-код. Осталось попыток: " + (3 - failedAttempts));
                }
                return false;
            }

            failedAttempts = 0; // сбросить счетчик после успешной авторизации
            return true;
        }

        public boolean isBlocked() {
            return failedAttempts >= 3;
        }

        public long getLastFailedAttempt() {
            return lastFailedAttempt;
        }

        public boolean withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
                return true;
            } else {
                return false;
            }
        }

        public boolean deposit(double amount) {
            if (amount <= 1000000) {
                balance += amount;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return cardNumber + " " + pin + " " + balance + " " + lastFailedAttempt + " " + failedAttempts;
        }
    }
