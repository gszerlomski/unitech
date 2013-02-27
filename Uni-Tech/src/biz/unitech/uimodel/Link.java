package biz.unitech.uimodel;

public class Link {

	private String linkText;
	private String linkTarget;

	public Link(String linkText, String linkTarget) {
		this.linkText = linkText;
		this.linkTarget = linkTarget;
	}
	
	public String getLinkTarget() {
		return linkTarget;
	}
	
	public String getLinkText() {
		return linkText;
	}
	
	public void setLinkTarget(String linkTarget) {
		this.linkTarget = linkTarget;
	}
	
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}
}
