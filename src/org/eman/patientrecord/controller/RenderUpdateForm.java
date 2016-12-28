package org.eman.patientrecord.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eman.patientrecord.dao.PatientrecordDAOImpl;
import org.eman.patientrecord.model.Patient;
import org.eman.patientrecord.model.PatientWorkup;

@WebServlet("/RenderUpdateForm")
public class RenderUpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		
		PatientrecordDAOImpl dao = new PatientrecordDAOImpl();
		
		Patient patient = dao.findPatient(pid);
		request.setAttribute("patient", patient);
		
		List<PatientWorkup> ptwList = dao.getAllPtw(pid);
		HashMap<Integer, Integer> ptwMap = new HashMap<>();
		
		Iterator<PatientWorkup> it = ptwList.iterator();
		while(it.hasNext()){
			PatientWorkup ptw = (PatientWorkup) it.next();
			ptwMap.put(ptw.getWid(), ptw.getFrequency());
		}
		
		
		request.setAttribute("ptwMap", ptwMap);
		
		String view = response.encodeURL("/updaterecord.jsp");
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}

}
