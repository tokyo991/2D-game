package gameobject;



import java.awt.*;
import javax.swing.*;
import util.Resource;
import java.awt.image.BufferedImage;

public class Bullet2 {
	int x, y;//-- Controls the CURRENT location of THIS bullet
	//Each object of this class is a new BULLET
	 BufferedImage img;
	boolean visible;
	//sets weather THIS bullet is visible or not

	
	public int getX()
	{
		return x;
	}

	public boolean getVisible()
	{
		return visible;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Image getImage()
	{
		return img;
	}
	
	public Bullet2(int startX, int startY)
	{
		BufferedImage newBullet =  Resource.getResouceImage("data/bullet3.png");
		newBullet.createGraphics();
		x = startX;
		y = startY;
		visible = true;
	}
	//Just like the move class in Dude class...
	public void move(){
		x = x + 2; //x + bullet speed
		if (x > 700)// if x > board width
			//Make the bullet invisible
			visible = false;
	}
}