package Classes;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;


public class ATM {
    private static final String DATABASE_FILE = "src/DataBase/database.txt"; // "src/Classes/database.txt
    private Map<String, User> userMap;
    private Scanner scanner;
    private int atmLimit;
    Database database;
//    public static void main(String[] args) {
//        ATM atm = new ATM();
//        atm.run();
//    }
    public ATM() {
        userMap = new HashMap<>();
        scanner = new Scanner(System.in);
        atmLimit = 20000000;
        database = new Database();
    }

    public void run() {

        while (true) {
            loadUserData();
            System.out.println("Введите номер карты (формат ХХХХ-ХХХХ-ХХХХ-ХХХХ):");
            String cardNumber = scanner.nextLine();
            Card card = new Card(cardNumber, "");

            if (!card.isValidCardNumber()) {
                System.out.println("Неверный формат номера карты.");
                continue;
            }

            if (!userMap.containsKey(cardNumber)) {
                System.out.println("Карта не найдена.");
                continue;
            }

            User user = userMap.get(cardNumber);
            if (user.isBlocked() && (System.currentTimeMillis() - user.getLastFailedAttempt() < 86400000)) {
                System.out.println("Карта заблокирована. Попробуйте позже.");
                continue;
            }
            while (!user.isBlocked()){
            System.out.println("Введите ПИН-код:");
            String pin = scanner.nextLine();

            if (!user.verifyPin(pin)) {
                continue;
            }
            break;
            }
            if(user.isBlocked()){
                saveUserData();
                continue;
            }
            menu(user);
        }
    }

    private void menu(User user) {
        while (true) {
            System.out.println("1. Проверить баланс");
            System.out.println("2. Снять средства");
            System.out.println("3. Пополнить баланс");
            System.out.println("4. Выход");
            int choice = 0;
            try{
            choice = scanner.nextInt();
            scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Неверный выбор. Попробуйте снова.");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("Ваш баланс: " + user.getBalance());
                    break;
                case 2:
                    //вывод средств
                    System.out.println("Выберите сумму для снятия.");
                    System.out.println("1. 5 p.");
                    System.out.println("2. 10 p.");
                    System.out.println("3. 20 p.");
                    System.out.println("4. 50 p.");
                    System.out.println("5. 100 p.");
                    System.out.println("6. Выбрать другую сумму.");
                    int choice2 = 0;
                    try{
                    choice2 = scanner.nextInt();
                    scanner.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Неверный выбор. Попробуйте снова.");
                        scanner.nextLine();
                        continue;
                    }
                    double amount = 0;
                    switch (choice2) {
                        case 1:
                            amount = 5;
                            break;
                        case 2:
                            amount = 10;
                            break;
                        case 3:
                            amount = 20;
                            break;
                        case 4:
                            amount = 50;
                            break;
                        case 5:
                            amount = 100;
                            break;
                        case 6:
                            System.out.println("Введите сумму для снятия(не более "+atmLimit+"):");
                            try{
                            amount = scanner.nextDouble();
                            scanner.nextLine();
                            }catch (InputMismatchException e){
                                System.out.println("Неверный выбор. Попробуйте снова.");
                                scanner.nextLine();
                                continue;
                            }
                            if (amount > atmLimit) {
                                System.out.println("Превышен лимит. Попробуйте снова.");
                                continue;
                            }
                            break;
                        default:
                            System.out.println("Неверный выбор. Попробуйте снова.");
                            break;
                    }

                    if (user.withdraw(amount)) {
                        System.out.println("Операция выполнена успешно. Ваш баланс: " + user.getBalance());
                    } else {
                        System.out.println("Недостаточно средств.");
                    }
                    break;
                case 3:
                    System.out.println("Введите сумму для пополнения:");
                    try{
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Неверный выбор. Попробуйте снова.");
                        scanner.nextLine();
                        continue;
                    }
                    if (user.deposit(amount)) {
                        System.out.println("Операция выполнена успешно. Ваш баланс: " + user.getBalance());
                    } else {
                        System.out.println("Сумма пополнения не может превышать 1 000 000.");
                    }
                    break;
                case 4:
                    saveUserData();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    public void loadUserData() {
        userMap = database.loadDatabase();
    }

    public void saveUserData() {
        database.saveDatabase(userMap);
    }
}
//write above this line example of database.txt
// 1234-5678-1234-5678 1234 1000.0 0 0


