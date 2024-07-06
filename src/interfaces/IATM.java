package interfaces;


import Classes.User;

public interface IATM {
    void run();

    void loadUserData();
    void saveUserData();
    void menu(User user);


}
