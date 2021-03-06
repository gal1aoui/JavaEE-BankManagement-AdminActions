package web.users;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.comptes.Compte;
import users.actors.Client;
import session.GestionProjetRemote;

@WebServlet(name="Client", urlPatterns={"/client"})
public class Clients extends HttpServlet {

	@EJB
	private GestionProjetRemote gpl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		Client c = gpl.consultationClient(id);
		req.setAttribute("Client", c);
		
		List<Compte> comptes = gpl.consultationComptesParClient(id);
		req.setAttribute("Comptes", comptes);
		req.getRequestDispatcher("Client.jsp").forward(req,res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String nom = req.getParameter("nom");
		String email = req.getParameter("email");
		String password = req.getParameter("password");		
		if(email != null && email != null && password != null)
		{
		gpl.ajouterClient(new Client(email, password, nom));
		}
		
		res.sendRedirect("consultation");
	}
}
