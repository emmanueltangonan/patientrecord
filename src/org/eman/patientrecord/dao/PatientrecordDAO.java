package org.eman.patientrecord.dao;

import java.util.ArrayList;
import java.util.List;

import org.eman.patientrecord.model.Patient;
import org.eman.patientrecord.model.PatientWorkup;
import org.eman.patientrecord.model.Physician;
import org.eman.patientrecord.model.Workup;

public interface PatientrecordDAO {
	public int addPatient(Patient patient);
	public int updatePatientRecords(Patient patient);
	public Patient findPatient(String pid);
	public boolean deletePatient(String pid);
	public List<Patient> getAllPatients(int doc_id);
	public int getMaxPatientId();
	
	public int addPatientWorkup(PatientWorkup ptw);
	public int updatePatientWorkup(PatientWorkup ptw);
//	public List<PatientWorkup> findPtw(PatientWorkup ptw);
	public List<PatientWorkup> getAllPtw(String pid);
	public boolean deletePtw(int pid, int wid);
	public ArrayList<Integer> getAllWid(int pid);
	public List<Workup> getAllWorkups();
	
	public List<Patient> searchPatients(int doc_id, String searchInput);
	
	public Physician getPhysicianCredentials(String username, String password);
}
