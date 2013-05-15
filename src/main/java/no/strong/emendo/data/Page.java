package no.strong.emendo.data;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Page {

	@Id
	String id;
	String parentId;
	@Index String siteId;
	String name;
	String content;
	List<String> children;
	
	public Page(){ }

	public String getParentId() { return parentId; }
	public void setParentId(String parent) { this.parentId = parent; }

	public String getSiteId() { return siteId; }
	public void setSiteId(String siteId) { this.siteId = siteId; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	
	public List<String> getChildren() { return children; }
	public void setChildren(List<String> children) { this.children = children; }
}
