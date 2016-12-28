package org.eman.patientrecord.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eman.patientrecord.dao.PatientrecordDAOImpl;
import org.eman.patientrecord.model.Workup;

@WebServlet("/Initialize")
public class InitializeAddPtForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		
		PatientrecordDAOImpl dbAccess = new PatientrecordDAOImpl();
		
		if(context.getAttribute("workups") == null){
			List<Workup> workups = dbAccess.getAllWorkups();
			context.setAttribute("workups", workups);
		}
		int nextCaseNo = dbAccess.getMaxPatientId() + 1;
		context.setAttribute("nextCaseNo", nextCaseNo);

		request.getRequestDispatcher("/addpatient.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


}
