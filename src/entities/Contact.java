package entities;

import javafx.scene.control.Button;

public class Contact {
	private String stt;
	private String name;
	private String address;
	private String source;
	private Button next;
	
	public Contact(String stt)
	{
		this.stt = stt;
		this.next = new Button("Next");
		this.name = null;
		this.address = null;
		this.source = null;
	}

	public String getSTT() {
		return stt;
	}

	public void setSTT(String stt) {
		this.stt = stt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Button getNext() {
		return next;
	}

	public void setNext(Button next) {
		this.next = next;
	}
	
	
}