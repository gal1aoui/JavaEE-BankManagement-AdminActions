package web.operations;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.comptes.Compte;
import accounts.operations.Operation;
import users.actors.Employee;
import session.GestionProjetRemote;

@WebServlet(name="Verser", urlPatterns={"/verser"})
public class Verser extends HttpServlet {
	
	@EJB
	private GestionProjetRemote gpr;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		List<Employee> employes = gpr.consultationEmployes();
		req.setAttribute("Employes", employes);
		
		List<Compte> comptes = gpr.consultationComptes();
		req.setAttribute("Comptes", comptes);
		
		List<Operation> op = gpr.consultationOperations();
		req.setAttribute("Operations", op);
		
		req.getRequestDispatcher("Verser.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String mnt = req.getParameter("montant");
		String Semp_id = req.getParameter("emp");
		String Scmp_id = req.getParameter("compte");
		
		long emp_id = Long.parseLong(Semp_id);
		long cmp_id = Long.parseLong(Scmp_id);
		float montant = Float.parseFloat(mnt);
		
		gpr.verser(cmp_id, montant, emp_id);
		
		res.sendRedirect("verser");
	}
}
