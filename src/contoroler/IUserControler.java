package contoroler;

import model.User;

public interface IUserControler {

    public User getUser(String name, String pass);

    public void newUser(String name, String pass);

}