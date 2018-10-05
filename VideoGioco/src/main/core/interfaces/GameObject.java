package main.core.interfaces;

public interface GameObject {  //OGGETTO DI GIOCO GENERICO
	
	
	int getX();
	int getY();
	
	boolean collision(GameObject g);
	

}
