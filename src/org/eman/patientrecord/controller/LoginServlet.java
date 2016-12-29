package org.eman.patientrecord.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eman.patientrecord.dao.PatientrecordDAOImpl;
import org.eman.patientrecord.model.Physician;
import org.eman.patientrecord.model.Workup;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PatientrecordDAOImpl dao = new PatientrecordDAOImpl();
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Physician user = dao.getPhysicianCredentials(username, password);
		
		if(user != null){
			session.setAttribute("doc_id", user.getDoc_id()); //TODO
			session.setAttribute("doc_name", user.getFirstname() + " " + user.getLastname()); //TODO
			session.setAttribute("doc_department", user.getDepartment());
		}else{
			request.setAttribute("error", true);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		ServletContext context = request.getServletContext();
		
		if(context.getAttribute("workups") == null){
			List<Workup> workups = dao.getAllWorkups();
			context.setAttribute("workups", workups);
		}
		
		String view = response.encodeURL("/Home");
		response.sendRedirect(request.getContextPath() + view);
		return;
	}
	
	

}
