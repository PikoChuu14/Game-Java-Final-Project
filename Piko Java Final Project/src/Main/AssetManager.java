package Main;

import entity.NPC_Atuk;
import monster.greenSlime;

public class AssetManager {
	
	GamePanel gp;
	
	public AssetManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		
	}
	
	public void setNPC() {
		
		gp.npc[0] = new NPC_Atuk(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;

	}
	
	public void setMonster() {
		
		gp.monster[0] = new greenSlime(gp);
		gp.monster[0].worldX = gp.tileSize*23;
		gp.monster[0].worldY = gp.tileSize*36;
		
		gp.monster[1] = new greenSlime(gp);
		gp.monster[1].worldX = gp.tileSize*23;
		gp.monster[1].worldY = gp.tileSize*37;
		
		
	}
}
