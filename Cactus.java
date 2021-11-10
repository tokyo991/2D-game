package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy {
	
	public static final int Y_LAND = 125;
	
	private int X_axis;
	private int width;
	private int height;
	
	private BufferedImage image;
	private MainCharacter mainCharacter;
	
	private Rectangle rectBound;
	
	public Cactus(MainCharacter mainCharacter, int X_axis, int width, int height, BufferedImage image) {
		this.X_axis = X_axis;
		this.width = width;
		this.height = height;
		this.image = image;
		this.mainCharacter = mainCharacter;
		rectBound = new Rectangle();
	}
	
	public void update() {
		X_axis -= mainCharacter.getSpeedX();
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, X_axis, Y_LAND - image.getHeight(), null);
		g.setColor(Color.magenta);
//		Rectangle bound = getBound();
//		g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}
	
	public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = (int) X_axis + (image.getWidth() - width)/2;
		rectBound.y = Y_LAND - image.getHeight() + (image.getHeight() - height)/2;
		rectBound.width = width;
		rectBound.height = height;
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		if(X_axis < -image.getWidth()) {
			return true;
		}
		return false;
	}
	
	public void disappear() {
		
	}
	
}
