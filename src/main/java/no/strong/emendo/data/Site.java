package no.strong.emendo.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Site {
	
	@Id public String id;
	public String name;
	public String domainName;
	public String rootPageId;
	@Index public String ownerId;
	//public UserAccount owner;
	
	public Site() {}
	
	public Site(String id, String name, String domainName, String rootPageId, String ownerId) {
		this.id = id;
		this.name = name;
		this.domainName = domainName;
		this.rootPageId = rootPageId;
		this.ownerId = ownerId;
	}
	
}
