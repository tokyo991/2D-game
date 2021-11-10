package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Coin extends Enemy {

	private int X_axis;
	private int width;
	private int height;
	
	public static final int Y_LAND = 40;
	
	private BufferedImage image;
	private MainCharacter mainCharacter;
	private Rectangle rectBound;
	
	public Coin(MainCharacter mainCharacter, int X_axis, int width, int height, BufferedImage image) {
		this.X_axis = X_axis;
		this.width = width;
		this.height = height;
		this.image = image;
		this.mainCharacter = mainCharacter;
		
	}

	//@Override
	public void update() {
		X_axis -= mainCharacter.getSpeedX();
		
	}

	//@Override
	public void draw(Graphics g) {
		g.drawImage(image, X_axis, Y_LAND - image.getHeight(), null);
		g.setColor(Color.magenta);	
		 
	}

	public Rectangle getBound() {			//what does this really do, does it create a rectangle around the enemy?
		rectBound = new Rectangle();
		rectBound.x = (int) X_axis + (image.getWidth() - width)/3;
		rectBound.y = Y_LAND - image.getHeight() + (image.getHeight() - height)/8;
		rectBound.width = width;
		rectBound.height = height;
		return rectBound;
	}

	//@Override
	public boolean isOutOfScreen() {
		if(X_axis < -image.getWidth()) {
			return true;
		}
		return false;
	}
	
}
