package org.eman.patientrecord.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eman.patientrecord.dao.PatientrecordDAOImpl;
import org.eman.patientrecord.model.Patient;
import org.eman.patientrecord.model.PatientWorkup;

@WebServlet("/AddPatient")
public class PatientCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		
		String button = request.getParameter("addButton");
		
		if(button.equals("Save")){
	//		System.out.println("next Case No = " + context.getAttribute("nextCaseNo"));
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
			
			int success = dao.addPatient(patient);
			
			Enumeration <String> e =  request.getParameterNames();
			while(e.hasMoreElements()){
				String id = (String)e.nextElement();
				if(id.startsWith("freq")){
					int freq = Integer.parseInt(request.getParameter(id));
					int wid = Integer.parseInt(id.substring(4));
					
					PatientWorkup ptw = new PatientWorkup(caseNo, wid, freq);
					dao.addPatientWorkup(ptw);
				}
			}
			
			if(success == 1) //success or fail message
				request.setAttribute("addMsg", true);
			else
				request.setAttribute("addMsg", false);
			
			request.setAttribute("caseNo", caseNo);
			String view = response.encodeURL("/Initialize");
	//		response.sendRedirect(view);
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
