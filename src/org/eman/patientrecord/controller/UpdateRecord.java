package org.eman.patientrecord.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eman.patientrecord.dao.PatientrecordDAOImpl;
import org.eman.patientrecord.model.Patient;
import org.eman.patientrecord.model.PatientWorkup;

@WebServlet("/UpdateRecord")
public class UpdateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int caseNo = Integer.parseInt(request.getParameter("caseNo"));
		int doc_id = (int)session.getAttribute("doc_id");
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String sex = request.getParameter("sex").equals("Male") ? "m" : "f";
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String diagnosis = request.getParameter("diagnosis").equals("") ? "" : request.getParameter("diagnosis");
		
		Patient patient = new Patient(caseNo, firstname, lastname, dob, sex, address, diagnosis, doc_id);
	
		PatientrecordDAOImpl dao = new PatientrecordDAOImpl();
		
		int success= dao.updatePatientRecords(patient);

		ArrayList<Integer> updatedWid = new ArrayList<Integer>();
		
		Enumeration <String> e =  request.getParameterNames();
		while(e.hasMoreElements()){
			String id = (String)e.nextElement();
			if(id.startsWith("freq") && request.getParameter(id) != ""){
				int freq = Integer.parseInt(request.getParameter(id));
				int wid = Integer.parseInt(id.substring(4));
				updatedWid.add(wid);
				
				PatientWorkup ptw = new PatientWorkup(caseNo, wid, freq);
				dao.updatePatientWorkup(ptw);
			}
		}

		List<Integer> widList = dao.getAllWid(caseNo);
		
		widList.removeAll(updatedWid);
		
		for(int wid : widList){
			dao.deletePtw(caseNo, wid);
		}
		
		
		
/*		Iterator <PatientWorkup> it = ptwList.iterator();
		while(it.hasNext()){ 
			PatientWorkup ptw = (PatientWorkup)it.next();
		}*/
		
		if(success == 1) //success or fail message
			request.setAttribute("addMsg", true);
		else
			request.setAttribute("addMsg", false);
		
		String view = response.encodeURL("/RenderUpdateForm?pid="+caseNo);
	
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
		
		
	}

}
