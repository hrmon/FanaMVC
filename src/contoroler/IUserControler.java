package contoroler;

import model.User;

public interface IUserControler {

	public User getUser(String name);

	public void newUser(String name);

}