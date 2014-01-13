package nl.tudelft.bsg.utopolis.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Message implements Serializable{
	private static final long serialVersionUID = 2540772239923107514L;
	
	@Id
	@GeneratedValue
	private int id;
	@OneToOne(cascade={CascadeType.ALL})
	private Player player;
	private String from;
	private String subject;
	private String message;
	private Date entryDate;
	private Date openedDate;
	private int assignNum;
	
	public int getId() {
		return id;
	}
	
	public void setId(int messageId) {
		this.id = messageId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public Date getEntryDate() {
		return entryDate;
	}
	
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	public int getAssignNum() {
		return assignNum;
	}
	
	public void setAssignNum(int assignNum) {
		this.assignNum = assignNum;
	}

	public Date getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
