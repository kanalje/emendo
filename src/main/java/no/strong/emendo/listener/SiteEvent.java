package no.strong.emendo.listener;

public interface SiteEvent {
	
	public String create(String siteName);
	public String delete(String siteId);
	public String lock(String siteId);	
	public String unLock(String siteId);
	
}
