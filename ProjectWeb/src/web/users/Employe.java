package web.users;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import users.actors.Employee;
import session.GestionProjetRemote;

@WebServlet(name="Employee", urlPatterns={"/employe"})
public class Employe extends HttpServlet {
	
	@EJB
	private GestionProjetRemote gpr;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		List<Employee> emps = gpr.consultationEmployes();
		
		long id = Long.parseLong(req.getParameter("id"));
		Employee e = gpr.consultationEmploye(id);
		
		req.setAttribute("Employe", e);
		req.setAttribute("Employes", emps);
		
		req.getRequestDispatcher("Employe.jsp").forward(req,res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String esold = req.getParameter("esolde");
		String eemail = req.getParameter("eemail");
		String epassword = req.getParameter("epass");
		if(esold != null && eemail != null && epassword != null)
		{
			float esolde = Float.parseFloat(esold);
			gpr.ajouterEmploye(new Employee(eemail, epassword, esolde));
		}
		
		if(req.getParameter("EmployeSup") != null)
		{
			gpr.ajouterSuperieurHierarchique(Long.parseLong(req.getParameter("id")), Long.parseLong(req.getParameter("sup")));
		}
		res.sendRedirect("consultation");
	}
}
