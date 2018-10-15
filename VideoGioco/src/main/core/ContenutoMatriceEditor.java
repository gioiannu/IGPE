package main.core;

public class ContenutoMatriceEditor {
	String s;
	int coordinataX;
	int coordinataY;
	
	public ContenutoMatriceEditor(String s, int x, int y)
	{
		this.s=s;
		this.coordinataX=x;
		this.coordinataY=y;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getCoordinataX() {
		return coordinataX;
	}

	public void setCoordinataX(int coordinataX) {
		this.coordinataX = coordinataX;
	}

	public int getCoordinataY() {
		return coordinataY;
	}

	public void setCoordinataY(int coordinataY) {
		this.coordinataY = coordinataY;
	}
	
	
		
}
