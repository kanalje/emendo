package no.strong.emendo.data.manager;

import java.util.List;

import com.google.gson.Gson;

import no.strong.emendo.data.Page;
import no.strong.emendo.storage.PageDAO;

public class PageManager {
	
	public void create(String id, String name, String content, String parentId, String siteId){
		Page page = new Page();
		page.setId(id);
		page.setName(name);
		page.setContent(content);
		page.setParentId(parentId);
		page.setSiteId(siteId);
		PageDAO.create(page);
	}
	
	public void create(String json) {
		
		Gson gson = new Gson();
		Page page = gson.fromJson(json, Page.class);
		PageDAO.create(page);
	}
	
	
	public void delete(String id) {
		PageDAO.delete(id);
	}
	
	
	public Page get(String id) {
		return PageDAO.get(id);
	}
	
	public List<Page> getAll(String siteId) {
		return PageDAO.getAll(siteId);
	}

	public void modify(String id, String json) {
		
		PageDAO.update(fromJson(json));
		
	}
	
	private Page fromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Page.class);
	}
}
