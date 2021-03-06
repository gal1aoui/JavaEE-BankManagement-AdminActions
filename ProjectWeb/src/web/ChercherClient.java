package web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import users.actors.Client;
import session.GestionProjetRemote;

@WebServlet(name="ChercherClient", urlPatterns={"/chercheclient"})
public class ChercherClient extends HttpServlet {

	@EJB
	private GestionProjetRemote gpl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String name = req.getParameter("nom");
		List<Client> c = gpl.chercherClient(name);
		req.setAttribute("Clients", c);
		
		req.getRequestDispatcher("ChercherClient.jsp").forward(req,res);
	}
	
}
