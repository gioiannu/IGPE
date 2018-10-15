package main.core;

import java.util.ArrayList;
import java.util.List;

import main.core.interfaces.GameObject;

public class Editor {

	private Object[][] matrix;

	
	
	public ArrayList<MovableObject> getMovableObjects() {
		return movableObjects;
	}

	public void setMovableObjects(ArrayList<MovableObject> movableObjects) {
		this.movableObjects = movableObjects;
	}

	public ArrayList<SolidBrick> getSolidBricks() {
		return solidBricks;
	}

	public ArrayList<Stairs> getStairs() {
		return stairs;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	private ArrayList <SolidBrick> solidBricks;
	private ArrayList <Stairs> stairs;
	private ArrayList <MovableObject> movableObjects;
	private ArrayList <Enemy> enemies;
	
	int width= 20;
	int height= 23;
	
	
	public Editor ()
	{
		
		matrix= new Object[20*10][23*10];
		solidBricks = new ArrayList<>();
		stairs = new ArrayList<>();
		movableObjects= new ArrayList<>();
		enemies = new ArrayList<>();
	}
	
	public Object[][] getMatrix() {
		return matrix;
	}
	
	public void setMatrix(Object[][] matrix) {
		this.matrix = matrix;
	}

	

	public void addSolidBrick(SolidBrick s)
	{
		solidBricks.add(s);
	}
	public void addMovableObject(MovableObject s)
	{
		movableObjects.add(s);
	}
	public void addEnemy(Enemy s)
	{
		enemies.add(s);
	}
	public void addStair(Stairs s)
	{
		stairs.add(s);
	}
	
	public void setSolidBricks(ArrayList<SolidBrick> solidBricks) {
		this.solidBricks = solidBricks;
	}
	
	
	
	public void setStairs(ArrayList<Stairs> stairs) {
		this.stairs = stairs;
	}
	

	
	public ArrayList <Enemy> getEnemies() {
		return enemies;
	}


	
	public void emptyMatrix() {
		for(int i=0; i<width; i++)
			for(int j=0;j<height; j++)
				matrix[i][j]="0";
	}
	
	public void clear() {
		emptyMatrix();
		solidBricks.clear();
		stairs.clear();
		movableObjects.clear();
		enemies.clear();
	}
	
	
	public void addElement (int x, int y,GameObject g)
	{
					matrix[x][y]=g;
			
		
	}
	
	
	public void addEmptyElement(int x, int y)
	{
		if( !(matrix[x][y] instanceof Stairs) && x<height && x>=0 && y< width && y>=0)
		{
			remove((GameObject) matrix [x][y]);
			matrix[x][y]=0;
		}
	}
	
	public synchronized void  remove (GameObject g)
	{
		if(g instanceof SolidBrick)
			solidBricks.remove(g);
		else if(g instanceof Stairs)
			stairs.remove(g);
		else if (g instanceof MovableObject)
			movableObjects.remove(g);
		else if (g instanceof Enemy)
			enemies.remove(g);
	}



}
