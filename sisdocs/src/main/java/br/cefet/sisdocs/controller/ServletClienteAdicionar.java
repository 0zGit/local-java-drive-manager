package br.cefet.sisdocs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.sisdocs.dao.ClienteDAO;
import br.cefet.sisdocs.model.Cliente;

/**
 * Servlet implementation class ServletClienteAdicionar
 */
@WebServlet("/ServletClienteAdicionar")
public class ServletClienteAdicionar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletClienteAdicionar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);

		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("pswd");

		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setLogin(login);
		cliente.setSenha(senha);

		ClienteDAO clienteDao = new ClienteDAO();
		try {
			clienteDao.adicionar(cliente);
			messages.put("response", "Cadastro efetuado com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("response", "Erro de cadastro.");
		}
		
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
