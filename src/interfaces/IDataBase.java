package interfaces;

import Classes.User;

public interface IDataBase {
    User getUser(String cardNumber);
    void saveUser(User user);
    void loadUserData();
    void saveUserData();
}
