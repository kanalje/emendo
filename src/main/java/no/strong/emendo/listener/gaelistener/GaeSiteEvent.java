package no.strong.emendo.listener.gaelistener;

import java.util.List;

import javax.ws.rs.*;

import com.google.gson.Gson;
import com.google.gson.reflect.*;

import no.strong.emendo.data.Page;
import no.strong.emendo.data.Site;
import no.strong.emendo.data.manager.PageManager;
import no.strong.emendo.data.manager.SiteManager;
import no.strong.emendo.listener.SiteEvent;

@Path("/site")
public class GaeSiteEvent implements SiteEvent{
	
	Gson gson = new Gson();
	SiteManager sm = new SiteManager();
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public String get(@PathParam("id") String id) {
		return gson.toJson(sm.get(id));
	}
	
	@GET
	@Path("/ownedby/{userId}")
	@Produces("application/json")
	public String getOwnedBy(@PathParam("userId") String userId) {
		List<Site> sitesOwned = sm.getSiteIdsByOwner(userId);
		return gson.toJson(sitesOwned, new TypeToken<List<Site>>(){}.getType());
	}
	
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public String create(String json) {
		sm.create(json);
		return "";			
	}

	@DELETE
	@Path("/{id}/delete")
	@Produces("text/plain")
	public String delete(@PathParam("id") String id) {
		//Site.delete(id);
		return "";
	}

	@POST
	@Path("/{id}/lock")
	public String lock(@PathParam("id") String id) {
		// TODO Auto-generated method stub
		return "Site " + id + " locked";
	}

	@POST
	@Path("/{id}/unlock")
	public String unLock(@PathParam("id") String id) {
		// TODO Auto-generated method stub
		return "Site " + id + " unlocked";
	}
	
	@GET
	@Path("/{id}/all_pages")
	public String getStructure(@PathParam("id") String id){
		PageManager pm = new PageManager();
		List<Page> pages = pm.getAll(id);		
		String s = gson.toJson(pages, new TypeToken<List<Page>>(){}.getType());
		
		return s;
	}
	
}
