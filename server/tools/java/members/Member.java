package com.agilewriters.workshop.members;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    private String fname;
private String lname;
private String email;
private String password;

    public String getFname() { return this.fname;}
public void setFname(String fname) { this.fname = fname; }
public String getLname() { return this.lname;}
public void setLname(String lname) { this.lname = lname; }
public String getEmail() { return this.email;}
public void setEmail(String email) { this.email = email; }
public String getPassword() { return this.password;}
public void setPassword(String password) { this.password = password; }


    @Override
    public String toString() {
        String s = "fname="+fname+",lname="+lname+",email="+email+",password="+password+",";
        return "Member [" +s+ "]";
    }

}
