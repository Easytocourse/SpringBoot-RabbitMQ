package com.easytocourse.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class ActionItems {
    
	@Id
	@GeneratedValue
	private int id;

	private String action;
	private String name;
	private String email;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date duedate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ActionItems(int id, String action, String name, String email, Date duedate) {
		super();
		this.id = id;
		this.action = action;
		this.name = name;
		this.email = email;
		this.duedate = duedate;
	}
	public ActionItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ActionItems [id=" + id + ", action=" + action + ", name=" + name + ", email=" + email + ", duedate="
				+ duedate + "]";
	}
	
}