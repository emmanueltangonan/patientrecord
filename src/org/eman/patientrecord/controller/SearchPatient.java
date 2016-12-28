package org.eman.patientrecord.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eman.patientrecord.dao.PatientrecordDAOImpl;
import org.eman.patientrecord.model.Patient;

@WebServlet("/SearchPatient")
public class SearchPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PatientrecordDAOImpl dao = new PatientrecordDAOImpl();
		
		int doc_id = (int) session.getAttribute("doc_id");
		
		String searchInput = request.getParameter("searchInput");
		System.out.println("searchInput = " + searchInput);
		System.out.println("doc_id = "+doc_id);
		List<Patient> patientList = dao.searchPatients(doc_id, searchInput);
		
		System.out.println("size = " + patientList.size());
		
		if(patientList.size() == 0){
			patientList.add(null);
			request.setAttribute("patientList", patientList);
		}else{
			request.setAttribute("patientList", patientList);
		}
		request.setAttribute("searchInput", searchInput);
		
		String view = response.encodeURL("/Home");
		request.getRequestDispatcher(view).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
