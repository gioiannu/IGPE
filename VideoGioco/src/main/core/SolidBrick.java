package main.core;

public class SolidBrick extends AbstractGameObject{

	public SolidBrick(World world, int x, int y) {
		super(world, x, y);
		
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}

}
