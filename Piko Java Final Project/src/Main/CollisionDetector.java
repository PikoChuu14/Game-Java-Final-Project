package Main;

import entity.Entity;

public class CollisionDetector {

	GamePanel gp;
	
	public CollisionDetector (GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.playerBodyArea.x;
		int entityRightWorldX = entity.worldX + entity.playerBodyArea.x + entity.playerBodyArea.width;
		int entityTopWorldY = entity.worldY + entity.playerBodyArea.y;
		int entityBottomWorldY = entity.worldY + entity.playerBodyArea.y + entity.playerBodyArea.height;
		
		int entityLeftColumn = entityLeftWorldX / gp.tileSize;
		int entityRightColumn = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1;
		int tileNum2;
		
		if (entity.direction == "up") {
			
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileManage.mapTileNumber[entityLeftColumn][entityTopRow];
			tileNum2 = gp.tileManage.mapTileNumber[entityRightColumn][entityTopRow];
			
				if (gp.tileManage.tile[tileNum1].collision == true || gp.tileManage.tile[tileNum2].collision == true) {
					
					entity.collisionOn = true;
					
				}
			
		}
		
		else if (entity.direction == "down") {
			
			entityTopRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileManage.mapTileNumber[entityLeftColumn][entityBottomRow];
			tileNum2 = gp.tileManage.mapTileNumber[entityRightColumn][entityBottomRow];
			
				if (gp.tileManage.tile[tileNum1].collision == true || gp.tileManage.tile[tileNum2].collision == true) {
					
					entity.collisionOn = true;
				}
		}
		
		else if (entity.direction == "left") {
			
			entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileManage.mapTileNumber[entityLeftColumn][entityTopRow];
			tileNum2 = gp.tileManage.mapTileNumber[entityLeftColumn][entityBottomRow];
			
				if (gp.tileManage.tile[tileNum1].collision == true || gp.tileManage.tile[tileNum2].collision == true) {
					
					entity.collisionOn = true;
				}
		}
		
		else if (entity.direction == "right") {
			
			entityRightColumn = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileManage.mapTileNumber[entityRightColumn][entityTopRow];
			tileNum2 = gp.tileManage.mapTileNumber[entityRightColumn][entityBottomRow];
			
				if (gp.tileManage.tile[tileNum1].collision == true || gp.tileManage.tile[tileNum2].collision == true) {
					
					entity.collisionOn = true;
				}
		}
	}
	
	public int checkPlayerCollideWithObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for (int i = 0; i < gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
				
				//get entity solid area position
				entity.playerBodyArea.x = entity.worldX + entity.playerBodyArea.x;
				entity.playerBodyArea.y = entity.worldY + entity.playerBodyArea.y;
				
				//get object solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
			
				//checking collision
				if(entity.direction == "up") {
					
					entity.playerBodyArea.y -= entity.speed;
					
					if(entity.playerBodyArea.intersects(gp.obj[i].solidArea)) {
						//System.out.println("up collide");
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						
						if (player == true) {
							//elak npc amik object kalau implement nnti
							index = i;
						}
					}
					
				}

				else if(entity.direction == "down") {
					
					entity.playerBodyArea.y += entity.speed;
					
					if(entity.playerBodyArea.intersects(gp.obj[i].solidArea)) {
						//System.out.println("down collide");
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						
						if (player == true) {
							//elak npc amik object kalau implement nnti
							index = i;
						}
					}
					
				}
				
				else if(entity.direction == "left") {
					
					entity.playerBodyArea.x -= entity.speed;
					
					if(entity.playerBodyArea.intersects(gp.obj[i].solidArea)) {
						//System.out.println("left collide");
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						
						if (player == true) {
							//elak npc amik object kalau implement nnti
							index = i;
						}
					}
				}
				
				else if(entity.direction == "right") {
					
					entity.playerBodyArea.x += entity.speed;

					if(entity.playerBodyArea.intersects(gp.obj[i].solidArea)) {
						//System.out.println("right collide");
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						
						if (player == true) {
							//elak npc amik object kalau implement nnti
							index = i;
						}
					}
				}
				
				//prevent value x ngan y berubah walaupun tngh langgar object
				entity.playerBodyArea.x = entity.solidDefaultAreaX;
				entity.playerBodyArea.y = entity.solidDefaultAreaY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
				
			}
		}
		
		return index;
	}
	
}
