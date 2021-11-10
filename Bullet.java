package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import util.Resource;

public class Bullet {
	
	BufferedImage image;
	private BufferedImage fImage = Resource.getResouceImage("data/bullet2.png");
	private BufferedImage fImage1 = Resource.getResouceImage("data/bullet3.png");
	int x=6;
	int y=6;
	boolean fired = false;
	double theta;
	double cos;
	double sin;
	private Rectangle rectBound;
	private int width = 50;
	private int height = 50;
	Graphics g = fImage.getGraphics();
	
	public Bullet(int x, int y , double theta){
		/*try{
			image = ImageIO.read(fImage);
		}catch(Exception e){}
		if (image == null)*/
		image = fImage;
			image.createGraphics();
		
		this.x = x;
		this.y = y;
		this.theta = theta;
	}
	public Bullet(int i){
		/*try{
			image = ImageIO.read(fImage1);
		}catch(Exception e){}
		if (image == null)*/
		image = fImage1;
			image.createGraphics();
	}
	
	public void draw() {
		g.drawImage(fImage, x, y - image.getHeight(), null);
		g.setColor(Color.magenta);
//		Rectangle bound = getBound();
//		g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}
	
	/*public void addBullet(int x) {
		int numberOfBullets =5;	
		if (x == 1) {
			for (int i = 0; i < numberOfBullets ; i++) {
				if (!bullets[i].fired) {
					
					bullets[i].theta = theta;
					bullets[i].sin = Math.sin(bullets[i].theta - Math.PI / 2);
					bullets[i].cos = Math.cos(bullets[i].theta - Math.PI / 2);
					bullets[i].fired = true;
					break;
				}
			}
		}

		if (x == 2) {
			for (int i = 0; i < numberOfBullets; i++) {
				if (!bullets[i].fired) {
					bullets[i].fired = true;
					bullets[i].theta = Math.random() * Math.PI * 2;
					bullets[i].sin = Math.sin(bullets[i].theta - Math.PI / 2);
					bullets[i].cos = Math.cos(bullets[i].theta - Math.PI / 2);
					continue;
				}
			}
		}
	}*/
	
	public BufferedImage getImage(){return image;}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,image.getWidth()  , image.getHeight() );
	}
	
	public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = (int) x + (image.getWidth() - width)/2;
		rectBound.y = y - image.getHeight() + (image.getHeight() - height)/2;
		rectBound.width = width;
		rectBound.height = height;
		return rectBound;
	}
	
	
}

