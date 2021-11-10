package gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Resource;

public class EnemiesManager {
	
	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private BufferedImage comet;
	private BufferedImage coin;
	private Random rand;
	
	private List<Enemy> enemies;
	private List<Enemy> coins;
	private MainCharacter mainCharacter;
	private Bullet bullet = new Bullet(1);
	
	public EnemiesManager(MainCharacter mainCharacter) {
		rand = new Random();
		cactus1 = Resource.getResouceImage("data/cactus1.png");
		cactus2 = Resource.getResouceImage("data/cactus2.png");
		comet = Resource.getResouceImage("data/comet3.png");
		coin = Resource.getResouceImage("data/coin.png");
		enemies = new ArrayList<Enemy>();
		coins = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());
		coins.add(createCoin());
	}
	
	public void update() {
		/*for(Enemy e : enemies) {
			e.update();
		}
		for(Coin c : coins) {
			c.update();
		}*/
		
		for(int i=0; i<coins.size(); i++) {
			coins.get(i).update();				//this is necessary
			enemies.get(i).update();
		}
		Enemy enemy = enemies.get(0);
		Enemy coin = coins.get(0);
		if(enemy.isOutOfScreen()) {
			mainCharacter.upScore();			//overload upscore with upscore (true)
			enemies.clear();
			coins.clear();
			enemies.add(createEnemy());
			coins.add(createCoin());
		}
				//if (enemy.wasShot()) enemies.clear(); ??????????
	}
	
	public void draw(Graphics g) {
		for(Enemy e : enemies) {
			e.draw(g);
		}
	}
	
	public void drawCoin(Graphics g) {
		for(Enemy c : coins) {
			c.draw(g);
		}
	}
	
	private Enemy createEnemy() {
		// if (enemyType = getRandom)
		int type = rand.nextInt(4);		//returns random number between 0 and 2
		switch(type) {
		case 0: 
			return new Cactus(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		case 1:
			return new Cactus(mainCharacter, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		case 2: 
			return new Comet(mainCharacter, 800, comet.getWidth() - 10, comet.getHeight() -10, comet);  		//comet, my new enemy
		/*case 4: 
			return new Coin(mainCharacter, 800, coin.getWidth() - 10, coin.getHeight() -10, coin);			*/	//a coin, which is not an enemy but moves the same way
		default:
			return new Cactus(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} 
	}
	
	private Coin createCoin() {
		return new Coin(mainCharacter, 850, coin.getWidth() - 10, coin.getHeight() -15, coin);				//a coin, which is not an enemy but moves the same way
		
	}
	
	public boolean isCollision() {
		for(Enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean coinCollision() {
		for(Enemy c : coins) {
			if (mainCharacter.getBound().intersects(c.getBound())) {
				mainCharacter.upScore(true);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean wasShot() {
		for(Enemy e : enemies) {
			if(bullet.getBound().intersects(e.getBound()))
				return true;
		}
		return false;
	}
	
	public void coinReset() {
		coins.clear();					
		coins.add(createCoin());
	}
	
	
	public void reset() {
		enemies.clear();			//an ArrayList method, removes all elements of the list
		coins.clear();
		enemies.add(createEnemy());			//an ArrayList method, adds enemy to the list
		coins.add(createCoin());
	}
	
}
