package org.eman.patientrecord.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eman.patientrecord.dao.PatientrecordDAOImpl;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		
		PatientrecordDAOImpl dao = new PatientrecordDAOImpl();
		
		if(dao.deletePatient(pid)){
			request.setAttribute("isDeleted", true);
		}
		String view = response.encodeURL("/Home");
		request.getRequestDispatcher(view).forward(request, response);
	}

}
