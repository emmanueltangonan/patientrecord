package org.eman.patientrecord.model;

public class Physician {
	private int doc_id;
	private String firstname;
	private String lastname;
	private String department;
	
	public Physician(int doc_id, String firstname, String lastname, String department) {
		this.doc_id = doc_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.department = department;
	}

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	
}
