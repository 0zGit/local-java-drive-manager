package br.cefet.sisdocs.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.sisdocs.dao.FileDAO;
import br.cefet.sisdocs.model.Globals;

/**
 * Servlet implementation class ServletDeleteFile
 */
@WebServlet("/ServletDeleteFile")
public class ServletDeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
		
        String name = request.getParameter("content");
        
        if(name == null) {
        	messages.put("message", "Please, select a file.");
        }else {
        	FileDAO fileDAO = new FileDAO();
    		File file = new File(Globals.getUploadPath()+"/"+name);
    		
    		try { 
    			if(fileDAO.Delete(name)) {
    				file.delete();
    				messages.put("message", "Succesfully deleted content.");
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			messages.put("message", "Failed to delete content.");
    		}        	
        }
		
		
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
