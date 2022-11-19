package br.cefet.sisdocs.model;

public class File {
	private String name;
	private Folder path;
	
	public File() {}
	public File(String name) {
		super();
		this.name = name;
	}
	
	// GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Folder getPath() {
		return path;
	}
	public void setPath(Folder path) {
		this.path = path;
	}
}
