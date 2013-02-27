package biz.unitech.uimodel;

import java.util.ArrayList;
import java.util.List;


public class Messages {
	
	private List<Message> messages = new ArrayList<Message>();

	public Messages(Message[] messagesList) {
		for (Message message : messagesList) {
			messages.add(message);
		}
	}
	
	public Messages(Message message) {
		this(new Message[] {message});
	}

	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}