package org.eman.patientrecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eman.patientrecord.model.Patient;
import org.eman.patientrecord.model.PatientWorkup;
import org.eman.patientrecord.model.Physician;
import org.eman.patientrecord.model.Workup;
import org.eman.patientrecord.util.DBConnection;

public class PatientrecordDAOImpl implements PatientrecordDAO{
	public static final String DBADD_PATIENT = "INSERT INTO patient (pid, doc_id, lastname, firstname, sex, dob, address, diagnosis) "
			+ "VALUES(?,?,?,?,?,?,?,?)";
	public static final String DBFIND_PATIENT = "SELECT doc_id, lastname, firstname, sex, dob, address, diagnosis FROM patient WHERE pid = ?";
	public static final String DBGETALL_PATIENT = "SELECT pid, lastname, firstname, sex, dob, address, diagnosis FROM patient WHERE doc_id = ?";
	public static final String DBDELETE_PATIENT = "DELETE FROM patient WHERE pid = ?";
	public static final String DBADD_PATIENT_WORKUP = "INSERT INTO patient_workups (pid, wid, frequency) VALUES(?, ?, ?)";
	public static final String DBGETALL_WORKUP = "SELECT * FROM workup";
	public static final String DBGETMAXID_PATIENT = "SELECT * FROM patient WHERE pid = (SELECT MAX(pid) FROM patient)";
	public static final String DBGETALL_PTW = "SELECT * FROM patient_workups WHERE pid = ?";
	public static final String DBGET_PHYSICIAN = "SELECT doc_id, lastname, firstname, department FROM physician where username = ? AND password = ?";
	public static final String DBUPDATE_PT_RECORDS = "UPDATE patient SET lastname=?, firstname=?, sex=?, dob=?, address=?, diagnosis=? WHERE pid=?";
	public static final String DBUPDATE_PT_WORKUP = "INSERT INTO patient_workups (pid, wid, frequency)	VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE frequency = ?";
	public static final String DBDELETE_PTW = "DELETE FROM patient_workups WHERE pid=? AND wid=?";
	public static final String DBSEARCH_PATIENTS = "SELECT * FROM patient WHERE doc_id = ? AND lastname LIKE ?";
	//	public static final String DBFIND_PTW = "SELECT * FROM patient_workups WHERE pid = ? AND wid = ?";
	
	
	
	@Override
	public int addPatient(Patient patient) {
		Connection conn = DBConnection.getDBConnection();
		try (PreparedStatement stmt = conn.prepareStatement(DBADD_PATIENT);) {
			conn.setAutoCommit(false);
			
			stmt.setInt(1, patient.getCaseNo());
			stmt.setInt(2, patient.getAttPhys());
			stmt.setString(3, patient.getLastName());
			stmt.setString(4, patient.getFirstName());
			stmt.setString(5, patient.getSex());
			stmt.setString(6, patient.getDob());
			stmt.setString(7, patient.getAddress());
			stmt.setString(8, patient.getDiagnosis());
			
			stmt.execute();
			conn.setAutoCommit(true);
		}catch(SQLException ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
				return 0;
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
			   conn.close();
			} catch(Exception ex) {	}
		}
		return 1;
	}

	@Override
	public Patient findPatient(String pid) {
		Connection conn = DBConnection.getDBConnection();;
		ResultSet rs = null;
		Patient patient = null;
		
		try (PreparedStatement stmt = conn.prepareStatement(DBFIND_PATIENT);) {		
			stmt.setString(1, pid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				patient = new Patient(Integer.parseInt(pid), rs.getString("firstname"), rs.getString("lastname"), rs.getString("dob"),
						rs.getString("sex"), rs.getString("address"), rs.getString("diagnosis"), rs.getInt("doc_id"));
			}
			rs.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return patient;
	}
	
/*	@Override
	public List<PatientWorkup> findPtw(PatientWorkup ptw) {
		Connection conn = DBConnection.getDBConnection();;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<PatientWorkup> ptwList = new Vector<PatientWorkup>();;
		
		try{
			stmt = conn.prepareStatement(DBFIND_PTW);
			stmt.setInt(1, ptw.getPid());
			stmt.setInt(2, ptw.getWid());
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				ptwList.add(new PatientWorkup(rs.getInt("pid"), rs.getInt("wid"), rs.getInt("frequency")));
			}
		}catch(SQLException e){
				e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ptwList;
	}*/

	@Override
	public List<Patient> getAllPatients(int doc_id) {
		Connection conn = DBConnection.getDBConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<Patient> patientList = new Vector<Patient>();
		
		try{
			//stmt = conn.createStatement();
			stmt = conn.prepareStatement(DBGETALL_PATIENT);
			stmt.setInt(1, doc_id);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				patientList.add(new Patient(rs.getInt("pid"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("dob"), rs.getString("sex"),
						rs.getString("address"), rs.getString("diagnosis"), doc_id));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return patientList;
	}

	@Override
	public boolean deletePatient(String pid) {
		Connection conn = DBConnection.getDBConnection();
		PreparedStatement stmt = null;
		
		try{
			stmt = conn.prepareStatement(DBDELETE_PATIENT);
			stmt.setString(1, pid);
			stmt.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean deletePtw(int pid, int wid) {
		Connection conn = DBConnection.getDBConnection();
		PreparedStatement stmt = null;
		
		try{
			stmt = conn.prepareStatement(DBDELETE_PTW);
			stmt.setInt(1, pid);
			stmt.setInt(2, wid);
			stmt.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return true;
	}
	
	@Override
	public int addPatientWorkup(PatientWorkup ptw) {
		Connection conn = DBConnection.getDBConnection();
		
		try (PreparedStatement stmt = conn.prepareStatement(DBADD_PATIENT_WORKUP);) {
			conn.setAutoCommit(false);
			stmt.setInt(1, ptw.getPid());
			stmt.setInt(2, ptw.getWid());
			stmt.setInt(3, ptw.getFrequency());
			
			stmt.execute();
			conn.setAutoCommit(true);
		}catch(SQLException ex){
			ex.printStackTrace();
			try{
				conn.rollback();
			}catch(Exception e){}
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	@Override
	public List<Workup> getAllWorkups() {
		Connection conn = DBConnection.getDBConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Workup> list = new Vector<Workup>();
		
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(DBGETALL_WORKUP);
			
			while(rs.next()){
				list.add(new Workup(rs.getInt("wid"), rs.getString("name"), rs.getString("description"), rs.getDouble("price")));
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int getMaxPatientId() {
		Connection conn = DBConnection.getDBConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int maxId = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(DBGETMAXID_PATIENT);
			
			if(rs.next()){
				maxId = rs.getInt("pid");
			}else{
				return 1000;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxId;
	}

	@Override
	public List<PatientWorkup> getAllPtw(String pid) {
		Connection conn = DBConnection.getDBConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<PatientWorkup> ptwList = new Vector<PatientWorkup>();
		
		try{
			stmt = conn.prepareStatement(DBGETALL_PTW);
			stmt.setString(1, pid);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				ptwList.add(new PatientWorkup(rs.getInt("pid"), rs.getInt("wid"), rs.getInt("frequency")));
			}
		}catch(SQLException e){
				e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ptwList;
	}
	
	@Override
	public ArrayList<Integer> getAllWid(int pid) {
		Connection conn = DBConnection.getDBConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ArrayList<Integer> widList = new ArrayList<Integer>();
		
		try{
			stmt = conn.prepareStatement(DBGETALL_PTW);
			stmt.setInt(1, pid);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				widList.add(rs.getInt("wid"));
			}
		}catch(SQLException e){
				e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return widList;
	}

	@Override
	public Physician getPhysicianCredentials(String username, String password) {
		Connection conn = DBConnection.getDBConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Physician physician = null;
		
		try{
			stmt = conn.prepareStatement(DBGET_PHYSICIAN);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				physician = new Physician(rs.getInt("doc_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("department"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return physician;
	}

	@Override
	public int updatePatientRecords(Patient patient) {
		Connection conn = DBConnection.getDBConnection();
		PreparedStatement stmt = null;
		
		try{
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(DBUPDATE_PT_RECORDS);
			stmt.setString(1, patient.getLastName());
			stmt.setString(2, patient.getFirstName());
			stmt.setString(3, patient.getSex());
			stmt.setString(4, patient.getDob());
			stmt.setString(5, patient.getAddress());
			stmt.setString(6, patient.getDiagnosis());
			stmt.setInt(7, patient.getCaseNo());
			
			stmt.execute();
			conn.setAutoCommit(true);
		}catch(SQLException e){
			e.printStackTrace();
			try {
				conn.rollback();
				return 0;
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}finally{
			try {
				   conn.close();
			} catch(Exception ex) {}
		}
		return 1;
	}

	@Override
	public int updatePatientWorkup(PatientWorkup ptw) {
		Connection conn = DBConnection.getDBConnection();
		PreparedStatement stmt = null;
		
		try{
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(DBUPDATE_PT_WORKUP);
			stmt.setInt(1, ptw.getPid());
			stmt.setInt(2, ptw.getWid());
			stmt.setInt(3, ptw.getFrequency());
			stmt.setInt(4, ptw.getFrequency());
			
			stmt.execute();
			conn.setAutoCommit(true);
		}catch(SQLException e){
			e.printStackTrace();
			try {
				conn.rollback();
				return 0;
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}finally{
			try {
				conn.close();
			} catch(Exception ex) {}
		}
		return 1;
	}

	@Override
	public List<Patient> searchPatients(int doc_id, String searchInput) {
		Connection conn = DBConnection.getDBConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<Patient> patientList = new Vector<Patient>();
		
		try{
			stmt = conn.prepareStatement(DBSEARCH_PATIENTS);
			stmt.setInt(1, doc_id);
			stmt.setString(2, searchInput + "%");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				patientList.add(new Patient(rs.getInt("pid"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("dob"), rs.getString("sex"),
						rs.getString("address"), rs.getString("diagnosis"), doc_id));
			}
		}catch(SQLException e){
				e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return patientList;
	}



	

	
}
