package Classes;

import AbstractClasses.AbstractDataBase;
import interfaces.IDataBase;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class Database extends AbstractDataBase {
    private static final String FILE_NAME = "src/DataBase/database.txt";

    public Map<String, User> loadDatabase() {
        Map<String, User> userMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                String cardNumber = data[0];
                String pin = data[1];
                double balance = Double.parseDouble(data[2]);
                long lastFailedAttempt = Long.parseLong(data[3]);
                int failedAttempts = Integer.parseInt(data[4]);
                userMap.put(cardNumber, new User(cardNumber, pin, balance, lastFailedAttempt, failedAttempts));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userMap;
    }
    @Override
    public  void saveDatabase(Map<String, User> userMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : userMap.values()) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



