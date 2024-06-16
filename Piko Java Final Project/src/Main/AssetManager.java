package Main;

import object.objectDoor;
import object.objectKey;
import object.objectBoots;
import object.objectChest;

public class AssetManager {
	
	GamePanel gp;
	
	public AssetManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		//setting position object dkat dlm map 50 x 50
		//coordinate x gp.tileSize
		gp.obj[0] = new objectKey();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new objectKey();
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		gp.obj[2] = new objectKey();
		gp.obj[2].worldX = 38 * gp.tileSize;
		gp.obj[2].worldY = 8 * gp.tileSize;
		
		gp.obj[3] = new objectDoor();
		gp.obj[3].worldX = 10 * gp.tileSize;
		gp.obj[3].worldY = 11 * gp.tileSize;
		
		gp.obj[4] = new objectDoor();
		gp.obj[4].worldX = 8 * gp.tileSize;
		gp.obj[4].worldY = 28 * gp.tileSize;
		
		gp.obj[5] = new objectDoor();
		gp.obj[5].worldX = 12 * gp.tileSize;
		gp.obj[5].worldY = 23 * gp.tileSize;
		
		gp.obj[6] = new objectChest();
		gp.obj[6].worldX = 10 * gp.tileSize;
		gp.obj[6].worldY = 8 * gp.tileSize;
		
		gp.obj[7] = new objectBoots();
		gp.obj[7].worldX = 37 * gp.tileSize;
		gp.obj[7].worldY = 42 * gp.tileSize;
	}
}
