package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.objectHeart;
import entity.Entity;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Threading thread;
	Font arial_40;
	Font arial_80B;
	//BufferedImage keyIcon;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean notificationOn = false;
	public String message = "";
	int notificationTimer = 0;
	public boolean gameOver = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Airal", Font.PLAIN, 40);
		arial_80B = new Font("Airal", Font.BOLD, 80);
		//objectKey key = new objectKey(gp);
		//keyIcon = key.image;
		
		//create HUD object
		Entity heart = new objectHeart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
	}
	
	public void displayNotification(String text) {
		
		message = text;
		notificationOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		//PLAY STATE
		if (gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		
		if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		
		//DIALGOUE STATE
		if(gp.gameState == gp.dialogState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		
	}
	
	public void drawPlayerLife() {
		
		
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		//Draw blank heart
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		//reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//draw current life
		while (i <gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			
			i++;
			x += gp.tileSize;
			
		}
		

	}

	public void drawTitleScreen() {
		
		g2.setColor(new Color(0, 0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
		String text = "Boyfriend Adventure";
		int x = getCenteredTextForXCoordinate(text);
		int y = gp.tileSize*3;
		
		//shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		
		//Main color
		g2.setColor(Color.white);
		g2.drawString(text, x ,y);
		
		//blue boy image
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		//display menu item
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		
		text = "New Game";
		x = getCenteredTextForXCoordinate(text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y);
		
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "Load Game";
		x = getCenteredTextForXCoordinate(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "Quit";
		x = getCenteredTextForXCoordinate(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		
		if(commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
	}

	public void drawDialogueScreen() {
		
		//dialog window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		
		drawSubWindow(x, y , width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(currentDialogue, x, y);
			y += 40;
		}
		
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		//no 4 set opacity
		Color c = new Color(0, 0 ,0,130);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}

	public void drawPauseScreen() {
		
		String text = "PAUSE";
		int x = getCenteredTextForXCoordinate(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public int getCenteredTextForXCoordinate (String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		
		return x;
	}
	
	/*public void draw(Graphics2D g2) {
		
		if (gameOver == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			
			
		}
		
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			g2.drawImage(keyIcon, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 74, 65);
			
			//notification
			if (notificationOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*3);
				
				notificationTimer++;
				
				if(notificationTimer > 120) {
					notificationTimer = 0;
					notificationOn = false;
				}
			}
		}
	}*/
}
