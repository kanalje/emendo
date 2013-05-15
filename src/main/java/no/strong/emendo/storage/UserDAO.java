package no.strong.emendo.storage;

import static no.strong.emendo.app.OfyService.ofy;
import no.strong.emendo.data.User;


public class UserDAO {

	public static User get(String id) {
		if (id == null)
			return null;
		
		return ofy().load().type(User.class).id(id).get();
	}
	
	public static void create(User user) {
		ofy().save().entity(user).now();
	}

	public static User getByEmail(String email) {
		if (email == null)
			return null;
		
		User user = ofy().load().type(User.class).filter("email", email).first().getValue();
		return user;
	}
	
}
