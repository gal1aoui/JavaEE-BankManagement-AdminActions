package web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import users.groups.Group;
import users.actors.Client;
import users.actors.Employee;
import session.GestionProjetRemote;

@WebServlet(name="Consult", urlPatterns={"/consultation"})
public class Consult extends HttpServlet {

	@EJB
	private GestionProjetRemote gpr;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		List<Client> clts = gpr.consultationClients();
		req.setAttribute("Clients", clts);
		
		List<Employee> emps = gpr.consultationEmployes();
		req.setAttribute("Employes", emps);
		
		List<Group> grps = gpr.consultationGroupes();
		req.setAttribute("Groupes", grps);
				
		req.getRequestDispatcher("Consult.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if(req.getParameter("Clientremove") != null)
		{
			gpr.supprimerClient(Long.parseLong(req.getParameter("id")));
		}
		if(req.getParameter("Employeremove") != null)
		{
			gpr.supprimerEmploye(Long.parseLong(req.getParameter("id")));
		}
		if(req.getParameter("Groupremove") != null)
		{
			gpr.supprimerGroupe(Long.parseLong(req.getParameter("id")));
		}
		resp.sendRedirect("consultation");;
	}
}