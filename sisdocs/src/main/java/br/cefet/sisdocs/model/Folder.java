package br.cefet.sisdocs.model;

public class Folder {
	private String path;

	public Folder() {}
	public Folder(String path) {
		super();
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
