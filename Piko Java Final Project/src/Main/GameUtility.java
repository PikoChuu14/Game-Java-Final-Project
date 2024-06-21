package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameUtility {
	
	public BufferedImage scaledImage(BufferedImage originalTile, int width, int height) {
		
		BufferedImage scaledImage = new BufferedImage(width, height, originalTile.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(originalTile, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}

}
