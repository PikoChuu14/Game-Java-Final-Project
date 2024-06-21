package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity {
	
	KeyHandler keyH;
	
	//camera paning variables
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.gp = gp;
		this.keyH = keyH;
		
		//screen center					//to adjust because point reference at top left corner
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		//setting bahagian badan player yg solid
		playerBodyArea = new Rectangle(8, 16, 25, 25);
		
		//set default value
		solidDefaultAreaX = playerBodyArea.x;   //8
		solidDefaultAreaY = playerBodyArea.y;   //16
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		//starting position on the world map
		//scale 48
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
				
		speed = 4;
		direction = "down";
		
		//player status
		//1 life equals to half heart
		maxLife = 6;
		life = maxLife;
	}
	
	//player movement image
	public void getPlayerImage() {
		
		up1 = imageSetup("/player/boy_up_1");
		up2 = imageSetup("/player/boy_up_2");
		down1 = imageSetup("/player/boy_down_1");
		down2 = imageSetup("/player/boy_down_2");
		right1 = imageSetup("/player/boy_right_1");
		right2 = imageSetup("/player/boy_right_2");
		left1 = imageSetup("/player/boy_left_1");
		left2 = imageSetup("/player/boy_left_2");
		
	}
	

	public void update() {
		//System.out.println("check2");
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			
			if(keyH.downPressed == true) {
				direction = "down";
			}
			
			if(keyH.leftPressed == true) {
				direction = "left";
			}
			
			if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//checking if player hit a solid tile
			collisionOn = false;
			gp.collisionDetector.checkTile(this);
			
			//check kalau player langgar object
			int objIndex = gp.collisionDetector.checkPlayerCollideWithObject(this, true);
			pickObject(objIndex);
			
			//npc collision
			int npcIndex = gp.collisionDetector.checkPlayerCollideWithEntity(this, gp.npc);
			interactWithNPC(npcIndex);
			
			//check monster collison
			int monsterIndex = gp.collisionDetector.checkPlayerCollideWithEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			//event collison
			gp.eHandler.checkEvent();
			
			gp.keyH.enterPressed = false;
			
			//if collison false, player allowed to move
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
				

	}
	
	public void contactMonster(int i) {
		
		if (i != 999) {
			
			life -= 1;
		}
		
		
	}

	public void interactWithNPC(int i) {
		
		if(i != 999) {
			
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogState;
				gp.npc[i].speak();
			}
		}
		
	}

	public void pickObject (int i) {
		
		//999 dtg drpd index playerCollideObject kat collisionDetector, any number work as long bukan number object punya array
		//takut ganggu
		if (i != 999) {
			
			int hasKey = 0;
			//langgar object hilang sbb bila yg langgar object tu player, index berubah jdi != 999
			
			//testing response
			//gp.obj[i] = null;
			
			String objectName = gp.obj[i].name;
			
			if (objectName == "Key") {
				gp.playSoundEffectAudio(1);
				hasKey++;
				gp.obj[i] = null;
				//System.out.println("Key: " + hasKey);
				gp.ui.displayNotification("You picked up a key!");
			}
			
			else if(objectName == "Door") {
				
				if (hasKey > 0) {
					gp.playSoundEffectAudio(2);
					gp.obj[i] = null;
					hasKey--;
					gp.ui.displayNotification("You unlocked the door!");
					
				}
				
				else gp.ui.displayNotification("The door is locked. "
						+ "Try find some keys around the world!");
				//else gp.playSoundEffectAudio(3);
			}
			
			else if(objectName == "Boots") {
				gp.playSoundEffectAudio(3);
				gp.obj[i] = null;
				speed += 2;
				gp.ui.displayNotification("You found a boot! Speed Up!");
			}
			
			else if(objectName == "Chest") {
				gp.ui.gameOver = true;
				gp.stopAudio();
			}
			
		}
	}
	
	public void draw(Graphics2D g2) {
		//System.out.println("check3");
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image  = null;
		
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
		
		g2.drawImage(image, screenX, screenY, null);
	}
	
}
