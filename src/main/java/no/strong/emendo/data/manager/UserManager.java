package no.strong.emendo.data.manager;

import java.util.UUID;

import no.strong.emendo.data.User;
import no.strong.emendo.storage.UserDAO;

public class UserManager {

	
	public User create(String email){
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setEmail(email);
		UserDAO.create(user);
		return user;
	}
	
	public User getByEmail(String email) {
		return UserDAO.getByEmail(email);
	}
	
	public User get(String id) {
		return UserDAO.get(id);
	}
}
