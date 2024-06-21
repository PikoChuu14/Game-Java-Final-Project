package object;

import Main.GamePanel;
import entity.Entity;

public class objectKey extends Entity {
	
	public objectKey(GamePanel gp) {
		super(gp);
		
		name = "Key";
		down1 = imageSetup("/objects/key");

	}
}
