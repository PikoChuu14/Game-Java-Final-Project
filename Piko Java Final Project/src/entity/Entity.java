package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.GameUtility;

public class Entity {

	GamePanel gp;
	
	public int worldX;
	public int worldY;
	public int speed;
	
	public BufferedImage up1;
	public BufferedImage up2;
	public BufferedImage down1;
	public BufferedImage down2;
	public BufferedImage right1;
	public BufferedImage right2;
	public BufferedImage left1;
	public BufferedImage left2;
	
	public String direction = "down";
	
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	
	public Rectangle playerBodyArea = new Rectangle(0, 0, 48, 48);
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	//public Rectangle npcBodyArea = new Rectangle(0, 0, 48, 48);
	public int solidDefaultAreaX;
	public int solidDefaultAreaY;
	public int solidAreaDefaultX;
	public int solidAreaDefaultY;
	public boolean collisionOn = false;
	public int npcMovementDelayer = 0;
	
	String dialogues[] = new String [20];
	int dialogueIndex = 0;
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	
	//character status
	public int maxLife;
	public int life;
	
	public Entity (GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.collisionDetector.checkTile(this);
		gp.collisionDetector.checkPlayerCollideWithObject(this, false);
		gp.collisionDetector.checkPlayerCollideWithEntity(this, gp.npc);
		gp.collisionDetector.checkPlayerCollideWithEntity(this, gp.monster);
		gp.collisionDetector.checkEntityCollideWithPlayer(this);
		
		if (collisionOn == false) {
			
			if (direction == "up") {
				worldY -= speed;
			}
			
			else if (direction == "down") {
				worldY += speed;
			}
			
			else if (direction == "left") {
				worldX -= speed;
			}
			
			else if (direction == "right") {
				worldX += speed;
			}
			
		}
		
		spriteCounter++;
		
		//changing motion every 20 frame
		if (spriteCounter > 18) {
			
			if (spriteNumber == 1) {
				spriteNumber = 2;
			}
			
			else if  (spriteNumber == 2) {
				spriteNumber = 1;
			}
			
			spriteCounter = 0;
		}
		
	
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image  = null;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			if (direction == "up") {
				
				if (spriteNumber == 1)
					image = up1;
				
				if(spriteNumber == 2) {
					image = up2;
				}
			}
			
			else if (direction == "down") {
				
				if (spriteNumber == 1)
					image = down1;
				
				if(spriteNumber == 2) {
					image = down2;
				}
				
			}
			
			else if (direction == "left") {
				
				if (spriteNumber == 1)
					image = left1;
				
				if(spriteNumber == 2) {
					image = left2;
				}
				
			}
			
			else if (direction == "right") {
				
				if (spriteNumber == 1)
					image = right1;
				
				if(spriteNumber == 2) {
					image = right2;
				}
				
			}
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			
		}
	}
	
	public BufferedImage imageSetup (String imagePath) {
		
		GameUtility gameUtil = new GameUtility();
		BufferedImage playerImage = null;
		
		try {
			playerImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			playerImage = gameUtil.scaledImage(playerImage, gp.tileSize, gp.tileSize);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return playerImage;
		
	}
	
	public void speak() {
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		if (gp.player.direction == "up") {
			direction = "down";
		}
		
		if (gp.player.direction == "down") {
			direction = "up";
		}
		
		if (gp.player.direction == "left") {
			direction = "right";
		}
		
		if (gp.player.direction == "right") {
			direction = "left";
		}
		
	}
}
