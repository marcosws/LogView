package reglog.logs.model.entity;

import java.sql.Timestamp;

public class LogFilter {
	
	private int idLog;
	private String logText;
	private String nameWorkstation;
	private String nameUser;
	private String nameClassName;
	private String nameStatus;
	private Timestamp dateTime;
	
	public int getIdLog() {
		return idLog;
	}
	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}
	public String getLogText() {
		return logText;
	}
	public void setLogText(String logText) {
		this.logText = logText;
	}
	public String getNameWorkstation() {
		return nameWorkstation;
	}
	public void setNameWorkstation(String nameWorkstation) {
		this.nameWorkstation = nameWorkstation;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getNameClassName() {
		return nameClassName;
	}
	public void setNameClassName(String nameClassName) {
		this.nameClassName = nameClassName;
	}
	public String getNameStatus() {
		return nameStatus;
	}
	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	
	

}
