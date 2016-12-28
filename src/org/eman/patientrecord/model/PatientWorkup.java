package org.eman.patientrecord.model;

public class PatientWorkup {
	private int pid;
	private int wid;
	private int frequency;
	
	public PatientWorkup(){}
	
	public PatientWorkup(int pid, int wid, int frequency) {
		this.pid = pid;
		this.wid = wid;
		this.frequency = frequency;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	
	
}

