package org.eman.patientrecord.model;

import java.time.LocalDate;
import java.time.Period;

public class Patient {
	private int caseNo;
	private String firstname;
	private String lastname;
	private String dob;
	private String sex;
	private String address;
	private String diagnosis;
	private int attPhys;
	private int age;
	
	public Patient(){}
	
	public Patient(int caseNo, String firstname, String lastname, String dob, String sex, String address, String diagnosis,
			int attPhys) {
		this.caseNo = caseNo;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.sex = sex;
		this.address = address;
		this.diagnosis = diagnosis;
		this.attPhys = attPhys;
		
		int year = Integer.parseInt(this.dob.substring(0, 4));
		int mon = Integer.parseInt(this.dob.substring(5, 7));
		int day = Integer.parseInt(this.dob.substring(8, 10));
		
		LocalDate bd = LocalDate.of(year, mon, day);
		LocalDate now = LocalDate.now();
		this.age = Period.between(bd, now).getYears();

	}

	
	public int getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(int caseNo) {
		this.caseNo = caseNo;
	}

	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public int getAttPhys() {
		return attPhys;
	}
	public void setAttPhys(int attPhys) {
		this.attPhys = attPhys;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
