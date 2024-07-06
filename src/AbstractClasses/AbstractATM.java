package AbstractClasses;


import java.util.Map;
import java.util.Scanner;

public abstract class AbstractATM implements interfaces.IATM {
    private static final String DATABASE_FILE = "src/DataBase/database.txt"; // "src/Classes/database.txt
    private Map<String, AbstractUser> userMap;
    private Scanner scanner;
    private int atmLimit;
    AbstractDataBase database;
    public abstract void run();
    public abstract void loadUserData();
    public abstract void saveUserData();
    public abstract void menu(AbstractUser user);
}
