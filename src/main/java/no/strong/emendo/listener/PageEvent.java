package no.strong.emendo.listener;

public interface PageEvent {
	
	public String get(String id);
	public String add(String json);
	public String delete(String id);
	public String modify(String id, String json);
}

