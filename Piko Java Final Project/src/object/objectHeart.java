package object;

import Main.GamePanel;
import entity.Entity;

public class objectHeart extends Entity {

	
	public objectHeart(GamePanel gp) {
		
		super(gp);
		
		name = "Heart";
		image = imageSetup("/objects/heart_full");
		image2 = imageSetup("/objects/heart_half");
		image3 = imageSetup("/objects/heart_blank");
		
	}
	
}
