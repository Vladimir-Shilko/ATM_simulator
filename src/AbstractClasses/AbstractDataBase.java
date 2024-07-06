package AbstractClasses;

import Classes.User;

import java.util.Map;

public abstract class AbstractDataBase {

    public abstract Map<String, User> loadDatabase();

    public abstract void saveDatabase( Map<String, User> userMap) ;
}
