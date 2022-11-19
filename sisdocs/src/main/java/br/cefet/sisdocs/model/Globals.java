package br.cefet.sisdocs.model;

import java.io.File;

// Drive system globals
public class Globals {
	public static final String DRIVE_NAME = "MyJavaDrive"; // change this in order to change your main's folder name
	public static final int ROOT_DRIVE = 0; // define the system drive of the array of system drives. 0 means first
											// drive (first label of your system)
	public static final int kb = 1024;
	public static final int mb = kb * 1024;
	public static final int gb = mb * 1024;
	public static final int tb = gb * 1024;
	private static String UPLOAD_PATH = "";

	/* METHODS */

	// method to generate local upload path
	public static String getUploadPath() {
		// Getting an array of available drives in system
		File[] roots = File.listRoots();
		// creating and setting directory path
		UPLOAD_PATH = roots[Globals.ROOT_DRIVE] + Globals.DRIVE_NAME;
		return UPLOAD_PATH;
	}

	// method to search for the location of the local Drive
	public static File[] listDrives() {
		// Getting an array of available drives in system
		File[] roots = File.listRoots();
		return roots;
	}

	// method to create local Drive
	public static boolean createDrive(File[] roots) {

		// Creating a File object in first drive for drive folder
		File drive = new File(roots[Globals.ROOT_DRIVE] + Globals.DRIVE_NAME);

		// Create directory for existed path.
		boolean isCreated = drive.mkdir();

		return isCreated;
	}
}
