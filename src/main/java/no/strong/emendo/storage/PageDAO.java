package no.strong.emendo.storage;

import static no.strong.emendo.app.OfyService.ofy;

import java.util.List;

import no.strong.emendo.data.Page;


public class PageDAO {


	public static Page get(String id) {
		if (id == null)
			return null;

		return ofy().load().type(Page.class).id(id).get();
	}
	
	public static List<Page> getAll(String siteId) {
		if (siteId == null)
			return null;

		return ofy().load().type(Page.class).filter("siteId", siteId).list();
	}
	
	public static boolean create(Page page) {
		ofy().save().entity(page).now();
		return page.getId() != null;
	}
	
	public static void update(Page page) {
		ofy().save().entity(page).now();
	}

	public static void delete(String id) {
		ofy().delete().type(Page.class).id(id);
	}
	
	
}
