package model;

import model.interfaces.IUser;

public class User implements IUser {
	final String name;

	public User(String name) {
		this.name = name;
	}
}
