package biz.unitech.uimodel;

public class Message {

	public static Message ORDER_CREATED = new Message("Zamówienie zostało stworzone. Aby wydrukować kliknij ", new Link[] {new Link("tutaj", "supplierOrderSummary.htm")});
	

	private String messageText;
	
	private Link[] links = new Link[] {};
	
	public Message(String message, Link[] links) {
		this.messageText = message;
		this.links = links;
	}
	
	public String getMessageText() {
		return messageText;
	}
	
	public Link[] getLinks() {
		return links;
	}
	
	public void setLinks(Link[] links) {
		this.links = links;
	}
	
	public void setMessageText(String message) {
		this.messageText = message;
	}
}