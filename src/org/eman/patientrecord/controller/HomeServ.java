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


@WebServlet("/Home")
public class HomeServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PatientrecordDAOImpl dao = new PatientrecordDAOImpl();
		int doc_id = 0;
		try{
			doc_id = (int) session.getAttribute("doc_id");
		}catch(NullPointerException e){
			e.getMessage();
		}
		List<Patient> allPatients = dao.getAllPatients(doc_id);
		session.setAttribute("allPatients", allPatients);
		
		String view = response.encodeURL("/home.jsp");
		request.getRequestDispatcher(view).forward(request, response);
		
	}

}
