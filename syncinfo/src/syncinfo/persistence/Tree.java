package syncinfo.persistence;

import java.math.BigDecimal;
import java.util.List;



public class Tree {
	
	private String id;
	
	private String text;
	
	private BigDecimal orgNo;
	
	private List<Tree> children;

	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public BigDecimal getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(BigDecimal orgNo) {
		this.orgNo = orgNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
//	private String state;
	
	
}
