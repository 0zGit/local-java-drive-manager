package br.cefet.sisdocs.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.sisdocs.model.Globals;

/**
 * Servlet implementation class ServletCreateDrive
 */
@WebServlet("/ServletCreateDrive")
public class ServletCreateDrive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreateDrive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Prepare messages.
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
		
        File[] roots = Globals.listDrives();
		boolean isCreated = Globals.createDrive(roots);
		
		if (isCreated) {
			messages.put("message", "Succesfully created directory under " + roots[Globals.ROOT_DRIVE] + Globals.DRIVE_NAME);
		} else {
			messages.put("message", "Folder under " + roots[Globals.ROOT_DRIVE] + Globals.DRIVE_NAME + " is already created");
		}
		
		
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
