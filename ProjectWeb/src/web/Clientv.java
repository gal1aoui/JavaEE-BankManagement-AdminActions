package web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import compte.comptes.Compte;

import users.actors.Client;
import users.session.GestionProjetRemote;

@WebServlet(name="Client", urlPatterns={"/client"})
public class Clientv extends HttpServlet {

	@EJB
	private GestionProjetRemote gpl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		Client c = gpl.consultationClient(id);
		req.setAttribute("Client", c);
		
		List<Compte> comptes = gpl.consultationComptesParClient(id);
		req.setAttribute("Comptes", comptes);
		req.getRequestDispatcher("Client.jsp").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}