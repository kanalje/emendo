package no.strong.emendo.data.manager;

import java.util.List;

import com.google.gson.Gson;

import no.strong.emendo.data.Site;
import no.strong.emendo.storage.SiteDAO;

public class SiteManager {

	
	public Site get(String id) {
		return SiteDAO.get(id);
	}
	
	public void create(String id, String name, String domainName, String rootPageId, String ownerId) {
		Site site = new Site(id, name, domainName, rootPageId, ownerId);
		SiteDAO.create(site);
	}
	
	public void create(String json) {
		Gson gson = new Gson();
		Site site = gson.fromJson(json, Site.class);
		SiteDAO.create(site);
	}

	public List<Site> getSiteIdsByOwner(String ownerId) {
		return SiteDAO.getAllByUser(ownerId);
	}
}
