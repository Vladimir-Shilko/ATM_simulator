import Classes.ATM;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();

        atm.loadUserData();
        atm.run();
        atm.saveUserData();
    }
}