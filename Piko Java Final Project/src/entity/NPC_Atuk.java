package entity;

import java.util.Random;

import Main.GamePanel;

public class NPC_Atuk extends Entity {

	
	public NPC_Atuk(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = imageSetup("/npc/oldman_up_1");
		up2 = imageSetup("/npc/oldman_up_2");
		down1 = imageSetup("/npc/oldman_down_1");
		down2 = imageSetup("/npc/oldman_down_2");
		right1 = imageSetup("/npc/oldman_right_1");
		right2 = imageSetup("/npc/oldman_right_2");
		left1 = imageSetup("/npc/oldman_left_1");
		left2 = imageSetup("/npc/oldman_left_2");
		
	}
	
	public void setDialogue() {
		
		dialogues[0] = "Buto";
		dialogues[1] = "KAu ni sape?";
		dialogues[2] = "Monyet ke?";
		dialogues[3] = "Nigger ke?";
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
	
	public void speak() {
		//make npc speak specific dialogue
		super.speak();
		
	}
}
