package object;

import Main.GamePanel;
import entity.Entity;

public class objectBoots extends Entity {


	public objectBoots(GamePanel gp) {
		
		super(gp);
		
		name = "Boots";
		down1 = imageSetup("/objects/boots");
		
	}
}

