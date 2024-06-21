package object;

import Main.GamePanel;
import entity.Entity;

public class objectChest extends Entity {
	
	public objectChest(GamePanel gp) {
		
		super(gp);
		
		name = "Chest";
		down1 = imageSetup("/objects/chest");

	}
	
}
