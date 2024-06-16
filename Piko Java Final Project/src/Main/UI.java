package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.objectKey;

public class UI {
	
	GamePanel gp;
	Font arial_40;
	BufferedImage keyIcon;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Airal", Font.PLAIN, 40);
		objectKey key = new objectKey();
		keyIcon = key.image;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void draw(Graphics2D g2) {
		
		g2.setFont(arial_40);
		g2.setColor(Color.WHITE);
		g2.drawImage(keyIcon, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x " + gp.player.hasKey, 74, 65);
	}
}
