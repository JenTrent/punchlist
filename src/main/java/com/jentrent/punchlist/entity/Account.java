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
public class Account{

	@Id
	@Column(name = "ACCOUNT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "PW_HASH")
	private String password;

	@Column(name = "CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "MODIFIED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	public Integer getAccountId(){

		return accountId;
	}

	public void setAccountId(Integer accountId){

		this.accountId = accountId;
	}

	public String getEmail(){

		return email;
	}

	public void setEmail(String email){

		if(email != null){
			email = email.toLowerCase();
		}

		this.email = email;
	}

	public String getFirstName(){

		return firstName;
	}

	public void setFirstName(String firstName){

		this.firstName = firstName;
	}

	public String getLastName(){

		return lastName;
	}

	public void setLastName(String lastName){

		this.lastName = lastName;
	}

	public String getPassword(){

		return password;
	}

	public void setPassword(String password){

		this.password = password;
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

	public String toString(){

		return ToStringBuilder.reflectionToString(this);
	}

}
