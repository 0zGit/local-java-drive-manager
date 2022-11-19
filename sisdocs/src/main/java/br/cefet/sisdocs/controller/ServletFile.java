package br.cefet.sisdocs.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Part;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.sisdocs.dao.FileDAO;
import br.cefet.sisdocs.model.File;
import br.cefet.sisdocs.model.Folder;
import br.cefet.sisdocs.model.Globals;

/**
 * Servlet implementation class ServletFile
 */

@WebServlet("/ServletFile")
@MultipartConfig(fileSizeThreshold = Globals.mb * 1, maxFileSize = Globals.mb * 10, maxRequestSize = Globals.mb * 15)
public class ServletFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletFile() {
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

		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		
		if (filePart.getSubmittedFileName() == "") {
			messages.put("message", "Please, select a file.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			// copying file to local drive
			filePart.write(Globals.getUploadPath() + "\\" + fileName);

			// setting file attributes to dao
			File file = new File();
			Folder folder = new Folder();

			file.setName(fileName);
			folder.setPath(Globals.getUploadPath());
			file.setPath(folder);

			// calling DAO method
			FileDAO fileDAO = new FileDAO();
			try {
				fileDAO.Adicionar(file, folder);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// return
			messages.put("message", "Succesfully uploaded file");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
