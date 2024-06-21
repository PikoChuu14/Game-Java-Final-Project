package monster;

import java.util.Random;

import Main.GamePanel;
import entity.Entity;

public class greenSlime extends Entity {

	public greenSlime(GamePanel gp) {
		super(gp);
		
		name = "Green Slime";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		
		solidArea.x = 3;
		solidArea.y = 10;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}

	public void getImage() {
		
		up1 = imageSetup("/monster/greenslime_down_1");
		up2 = imageSetup("/monster/greenslime_down_2");
		down1 = imageSetup("/monster/greenslime_down_1");
		down2 = imageSetup("/monster/greenslime_down_2");
		left1 = imageSetup("/monster/greenslime_down_1");
		left2 = imageSetup("/monster/greenslime_down_2");
		right1 = imageSetup("/monster/greenslime_down_1");
		right2 = imageSetup("/monster/greenslime_down_2");
	}
	
	public void setAction() {
		
		npcMovementDelayer++;
		
		if (npcMovementDelayer == 120) {
			//simple AI sample
			Random random = new Random();
			int i = random.nextInt(100) + 1; // tambah satu supaya start dri 1 rather than 0
			
			if(i <= 25) {
				direction = "up";
			}
			
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			npcMovementDelayer = 0;
		}
		
		
	}
	
}
