package com.jentrent.punchlist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Task{

	@Id
	@Column(name = "TASK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;

	@Column(name = "TEXT")
	private String text;

	@Column(name = "PRIORITY")
	private Integer priority = 3;

	@Column(name = "DUE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date due;

	@Column(name = "ALERT")
	private boolean alert;

	@Column(name = "COMPLETED")
	private boolean completed;

	@Column(name = "CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "MODIFIED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@Column(name = "ACCOUNT_ID_FK")
	private Integer accountId;

	public Integer getTaskId(){

		return taskId;
	}

	public void setTaskId(Integer taskId){

		this.taskId = taskId;
	}

	public String getText(){

		return text;
	}

	public void setText(String text){

		this.text = text;
	}

	public Integer getPriority(){

		return priority;
	}

	public void setPriority(Integer priority){

		this.priority = priority;

		if(!isPriorityValid(priority)){
			throw new IllegalArgumentException("Invalid priority: " + priority + " Priority must be 1, 2, or 3.");
		}

	}

	public Date getDue(){

		return due;
	}

	public void setDue(Date due){

		this.due = due;
	}

	public boolean isAlert(){

		return alert;
	}

	public void setAlert(boolean alert){

		this.alert = alert;
	}

	public boolean isCompleted(){

		return completed;
	}

	public void setCompleted(boolean completed){

		this.completed = completed;
	}

	public Date getCreated(){

		return created;
	}

	public void setCreated(Date created){

		this.created = created;
	}

	public Date getModified(){

		return modified;
	}

	public void setModified(Date modified){

		this.modified = modified;
	}

	public Integer getAccountId(){

		return accountId;
	}

	public void setAccountId(Integer accountId){

		this.accountId = accountId;
	}

	public String getIdString(){

		return taskId.toString();
	}

	public String getPriorityString(){

		if(priority.intValue() == 1){
			return "High";
		}else if(priority.intValue() == 2){
			return "Medium";
		}else
			return "Low";

	}

	public String toString(){

		return ToStringBuilder.reflectionToString(this);
	}

	private static boolean isPriorityValid(int p){

		if(p > 0 && p <= 3){
			return true;
		}else
			return false;

	}

}
