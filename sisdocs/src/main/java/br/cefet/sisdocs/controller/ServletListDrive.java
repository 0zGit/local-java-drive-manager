package br.cefet.sisdocs.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.sisdocs.dao.FileDAO;
import br.cefet.sisdocs.model.Globals;

/**
 * Servlet implementation class ServletListDrive
 */
@WebServlet("/ServletListDrive")
public class ServletListDrive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListDrive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// creating key:value and setting it to "files" variable for jsp
		Map<String, String> files = new HashMap<String, String>();
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
		
		//Getting an array of available drives in system
        File[] roots = Globals.listDrives();
		//creating and setting directory path
		File directorypath = new File(roots[Globals.ROOT_DRIVE] + Globals.DRIVE_NAME);
		
		// getting a List of all files and directories
		File filesList[] = directorypath.listFiles();
		
		if(filesList == null) {
			messages.put("message", "Error: Please, create your drive folder first");
		}else if(filesList.length == 0){
			messages.put("message", "There are no files to display");
			messages.put("location",roots[Globals.ROOT_DRIVE] + Globals.DRIVE_NAME);
		}else {
			for (File file : filesList) {
				String size;
				if(file.length() >= 0 && file.length() < Globals.mb) {
					size = file.length() / Globals.kb + " Kb";
				}
				else if(file.length() >= Globals.mb && file.length() < Globals.gb) {
					size = file.length() / Globals.mb + " Mb";
				}
				else if(file.length() >= Globals.gb && file.length() < Globals.tb) {
					size = file.length() / Globals.gb + " Gb";
				}
				else{
					size = file.length() / Globals.tb + " Tb";
				}
				files.put(file.getName(), size);
			}
			messages.put("message", "Successfully displayed files");
			messages.put("location",roots[Globals.ROOT_DRIVE] + Globals.DRIVE_NAME);
			
			//list database file names to JSP <select>
			FileDAO fileDAO = new FileDAO();
			List<br.cefet.sisdocs.model.File> fileList = null;
			try {
				fileList = fileDAO.listarTodos();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("contents", fileList);
		}
		
		
		request.setAttribute("files", files);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
