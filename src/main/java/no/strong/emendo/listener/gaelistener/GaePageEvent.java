package no.strong.emendo.listener.gaelistener;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import no.strong.emendo.data.Page;
import no.strong.emendo.data.manager.PageManager;
import no.strong.emendo.listener.PageEvent;

@Path("/page")
public class GaePageEvent implements PageEvent{

	PageManager pm = new PageManager();

	@GET
	@Path("{id}")
	@Produces("application/json")
	@Override
	public String get(@PathParam("id") String id) {
		Page page = pm.get(id);
		Gson gson = new Gson();
		return gson.toJson(page);
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public String add(String json) {
		pm.create(json);		
		return "ok";
	}

	@PUT
	@Path("/{id}")
	@Override
	public String modify(@PathParam("id") String id, String json) {
		pm.modify(id, json);
		return "";
	}

	@DELETE
	@Path("{id}")
	@Override
	public String delete(@PathParam("id") String id) {
		pm.delete(id);
		return "";
	}

}
