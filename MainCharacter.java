package gameobject;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import util.Animation;
import util.Resource;

public class MainCharacter {

	public static final int LAND_POSY = 80;
	public static final float GRAVITY = 0.4f;
	
	private static final int NORMAL_RUN = 0;
	private static final int JUMPING = 1;
	private static final int DOWN_RUN = 2;
	private static final int DEATH = 3;
	
	private float posY;
	private float posX;
	private float speedX;
	private float speedY;
	private Rectangle rectBound;
	
	public int score = 0;
	
	private int state = NORMAL_RUN;
	
	private Animation normalRunAnim;
	private BufferedImage jumping;
	private Animation downRunAnim;
	private BufferedImage deathImage;
	private Bullet bullet;
	private Bullet2 bullet2 = new Bullet2(2,3);

	private Bullet[] bullets = new Bullet[7];
	
	@SuppressWarnings("deprecation")
	private AudioClip jumpSound;
	@SuppressWarnings("deprecation")
	private AudioClip deadSound;
	@SuppressWarnings("deprecation")
	private AudioClip scoreUpSound;
	private AudioClip coinSound;
	
	@SuppressWarnings("deprecation")
	public MainCharacter() {
		posX = 50;
		posY = LAND_POSY;
		rectBound = new Rectangle();
		normalRunAnim = new Animation(90);
		normalRunAnim.addFrame(Resource.getResouceImage("data/main-character1.png"));
		normalRunAnim.addFrame(Resource.getResouceImage("data/main-character2.png"));
		jumping = Resource.getResouceImage("data/main-character3.png");
		downRunAnim = new Animation(90);
		downRunAnim.addFrame(Resource.getResouceImage("data/main-character5.png"));
		downRunAnim.addFrame(Resource.getResouceImage("data/main-character6.png"));
		deathImage = Resource.getResouceImage("data/main-character4.png");
		
		try {
			jumpSound =  Applet.newAudioClip(new URL("file","","data/jump.wav"));
			deadSound =  Applet.newAudioClip(new URL("file","","data/dead.wav"));
			scoreUpSound =  Applet.newAudioClip(new URL("file","","data/scoreup2.wav"));
			coinSound = Applet.newAudioClip(new URL("file","","data/coin_sound.wav"));
		} catch (MalformedURLException e) {			//what is this?
			e.printStackTrace();				//and this?
		}
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void draw(Graphics g) {
		switch(state) {
			case NORMAL_RUN:
				g.drawImage(normalRunAnim.getFrame(), (int) posX, (int) posY, null);
				break;
			case JUMPING:
				g.drawImage(jumping, (int) posX, (int) posY, null);
				break;
			case DOWN_RUN:
				g.drawImage(downRunAnim.getFrame(), (int) posX, (int) (posY + 20), null);
				break;
			case DEATH:
				g.drawImage(deathImage, (int) posX, (int) posY, null);
				break;
		}

	}
	
	public void update() {
		normalRunAnim.updateFrame();
		downRunAnim.updateFrame();
		if(posY >= LAND_POSY) {
			posY = LAND_POSY;
			if(state != DOWN_RUN) {
				state = NORMAL_RUN;
			}
		} else {
			speedY += GRAVITY;
			posY += speedY;
		}
	}
	
	public void jump() {
		if(posY >= LAND_POSY) {
			if(jumpSound != null) {
				jumpSound.play();
			}
			speedY = -7.5f;
			posY += speedY;
			state = JUMPING;
		}
	}
	
	public void down(boolean isDown) {
		if(state == JUMPING) {
			return;
		}
		if(isDown) {
			state = DOWN_RUN;
		} else {
			state = NORMAL_RUN;
		}
	}
	
	public void shoot() {				//a key is pressed - the right arrow key
			
		bullet2.move();
			
			/*int numberOfBullets =5;	
			if (x == 1) {
				for (int i = 0; i < numberOfBullets ; i++) {
					if (!bullets[i].fired) {
						
						bullets[i].theta = 0.45;
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
		/*function that shoots the bullet object*/
		
	}
	public void resetScore() {
		score=0;
		
	}
	
	public Rectangle getBound() {
		rectBound = new Rectangle();
		if(state == DOWN_RUN) {
			rectBound.x = (int) posX + 5;
			rectBound.y = (int) posY + 20;
			rectBound.width = downRunAnim.getFrame().getWidth() - 10;
			rectBound.height = downRunAnim.getFrame().getHeight();
		} else {
			rectBound.x = (int) posX + 5;
			rectBound.y = (int) posY;
			rectBound.width = normalRunAnim.getFrame().getWidth() - 10;
			rectBound.height = normalRunAnim.getFrame().getHeight();
		}
		return rectBound;
	}
	
	public void dead(boolean isDeath) {
		if(isDeath) {
			state = DEATH;
		} else {
			state = NORMAL_RUN;
		}
	}
	
	public void reset() {
		posY = LAND_POSY;
	}
	
	@SuppressWarnings("deprecation")
	public void playDeadSound() {
		deadSound.play();
	}
	
	@SuppressWarnings("deprecation")
	public void upScore() {
		score += 20;					//adds 20 points to the score after you escape the enemy
		if(score % 100 == 0) {				
			scoreUpSound.play();
		}
	}
	
	@SuppressWarnings("deprecation")			
	public void coinSound() {				//plays this wav when the dinosaur gets a coin
		coinSound.play();
	}
	
	@SuppressWarnings("deprecation")					//adds 130 points to the score after you shoot an enemy/get a coin
	public void upScore(boolean coin) {
		if(coin) score += 130;
		if(score % 100 == 0) {				
			scoreUpSound.play();
		}
	}
	
}
