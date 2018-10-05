package main.core.interfaces;

public interface DynamicObject extends GameObject{
	
	Directions getDirection();
	
	int getSpeed();
	
	void setSpeed(int speed);
	
	void update();
}
