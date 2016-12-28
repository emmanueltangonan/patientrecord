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
import org.eman.patientrecord.model.PatientWorkup;


@WebServlet("/PatientInfo")
public class PatientInfor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		PatientrecordDAOImpl dao = new PatientrecordDAOImpl();
		
		Patient patient = dao.findPatient(pid);
		if(patient != null){
	//		HttpSession session = request.getSession();
			request.setAttribute("patient", patient);
		}
		List<PatientWorkup> ptwList =  dao.getAllPtw(pid);
		
		
		if(ptwList != null){
			request.setAttribute("ptwList", ptwList);
		}
		
		String view = response.encodeURL("/patientinfo.jsp");
		request.getRequestDispatcher(view).forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
