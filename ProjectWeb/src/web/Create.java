package web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.comptes.CompteCourant;
import accounts.comptes.CompteEpargne;
import users.groups.Group;
import users.actors.Client;
import users.actors.Employee;
import users.groups.GroupMembers;
import session.GestionProjetRemote;

@WebServlet(name="Create", urlPatterns={"/creation"})
public class Create extends HttpServlet {
	
	@EJB
	private GestionProjetRemote gpl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		List<Client> clts = gpl.consultationClients();
		req.setAttribute("Clients", clts);
		
		List<Employee> emps = gpl.consultationEmployes();
		req.setAttribute("Employes", emps);
		
		List<Group> grps = gpl.consultationGroupes();
		req.setAttribute("Groupes", grps);
		
		req.getRequestDispatcher("Create.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String typeCompte = req.getParameter("ctype");
		String cclient = req.getParameter("cclient");
		String cemp = req.getParameter("cemploye");
		String csolde = req.getParameter("csolde");
		String cdec = req.getParameter("cdec");
		String cint = req.getParameter("cint");
		if(csolde != null)
		{
			float fcsolde = Float.parseFloat(csolde);
			long clt_id = Long.parseLong(cclient);
			long emp_id = Long.parseLong(cemp);
			if(typeCompte.equals("Epargne"))
			{
				float fcint = Float.parseFloat(cint);
				gpl.ajouterCompte(new CompteEpargne(fcsolde,new Date(),fcint), clt_id, emp_id);
			}else{
				float fcdec = Float.parseFloat(cdec);
				gpl.ajouterCompte(new CompteCourant(fcsolde,new Date(),fcdec), clt_id, emp_id);
			}
		}
		
		String empgrp = req.getParameter("empgrp");
		String grpemp = req.getParameter("grpemp");
		if(empgrp != null && grpemp != null)
		{
			long gemp_id = Long.parseLong(empgrp);
			long ggrp_id = Long.parseLong(grpemp);
			gpl.ajouterEmplAGroup(gemp_id, ggrp_id, new GroupMembers());
		}
			
		res.sendRedirect("consultation");
	}
}
