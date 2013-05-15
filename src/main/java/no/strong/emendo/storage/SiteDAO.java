package no.strong.emendo.storage;

import static no.strong.emendo.app.OfyService.ofy;

import java.util.List;

import no.strong.emendo.data.Site;


public class SiteDAO {

	public static Site get(String id) {
		if (id == null)
			return null;

		return ofy().load().type(Site.class).id(id).get();
	}
	
	public static void create(Site site){
		ofy().save().entity(site).now();
	}

	public static List<Site> getAllByUser(String ownerId) {
		return ofy().load().type(Site.class).filter("ownerId", ownerId).list();
	}
	
}
