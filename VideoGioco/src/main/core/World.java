package main.core;

import java.util.ArrayList;

import main.core.interfaces.GameObject;

public class World {
		private Object[][] matrix;
		private int height;
		private int width;
		int winnerCounter=0;
		
		

		public World(int width, int height)
		{
			this.height= height;
			this.width=width;
			
			matrix= new Object[height][width];
			
			for(int i=0;i<height; i++)
				for(int j=0;j<width; j++)
					matrix[i][j]=new EmptyObject (0,0);
		}
		
		public World(int width, int height, Object[][] m)
		{
			this.height= height;
			this.width=width;
			
			matrix=m;
		}
		
		public void add(int i, int j, GameObject g)
		{
			matrix[j][i]= g;
		}
		
		public void remove(int i, int j)
		{
			matrix[j][i]= new EmptyObject (0,0);
		}

		public int getHeight() {
			return height;
		}

		public int getWidth() {
			return width;
		}
		
		public void empty()
		{
			for(int i=0;i<width; i++)
				for(int j=0;j<height; j++)
					matrix[j][i]=new EmptyObject (0,0);
		}
		
		@Override
		public String toString()
		{
			String s= "";
			for(int i=0;i<height; i++)
			{
				for(int j=0;j<width; j++)
				{
					if(matrix[i][j] instanceof EmptyObject)
					{
						s+= "-";
					}
					else if (matrix[i][j] instanceof Player)
					{
						s+= "P";
					}
					else if (matrix[i][j] instanceof Enemy)
					{
						s+= "E";
					}
					else if (matrix[i][j] instanceof Proiettile)
					{
						s+= "B";
					}
					else if (matrix[i][j] instanceof MovableObject)
					{
						s+= "M";
					}
					else if (matrix[i][j] instanceof SolidBrick)
					{
						s+= "_";
					}
					else if (matrix[i][j] instanceof Stairs)
					{
						s+= "|";
					}
				}
				
				s+= "\n";
			}
			return s;
		}
		
		public void update (final Player p,final Enemy[] e, final MovableObject[] m, final SolidBrick[] s, final Stairs[] stairs, EnemyAI eai) 
		{
			this.empty();
			this.add(p.getX(), p.getY(), p);
			if(p.getProiettile().isVisible())
				this.add(p.getProiettile().getX(), p.getProiettile().getY(), p.getProiettile());
			this.add(eai.getX(), eai.getY(), eai);
			
			for( final Enemy enemy : e)
			{
				this.add(enemy.getX(), enemy.getY(), enemy);
			}
			
			for( final MovableObject mo : m)
			{
				if(mo.getY()<this.getHeight())
				{
					this.add(mo.getX(), mo.getY(), mo);
					winnerCounter++;
				}
			}
			
			for(final SolidBrick sb : s)
			{
				this.add(sb.getX(), sb.getY(), sb);
			}
			for(final Stairs st : stairs)
			{
				this.add(st.getX(), st.getY(), st);
			}
			
		}
		
		
		public int getWinnerCounter() {
			return winnerCounter;
		}

		public void setWinnerCounter(int winnerCounter) {
			this.winnerCounter = winnerCounter;
		}
		
		public void removeOneCounter()
		{
			winnerCounter--;
		}
		
		
		
		public GameObject getObject(int x,int y) {
			return (GameObject) matrix[y][x];
		}

		
		
		
		
}
